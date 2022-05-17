/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Slider;
import com.codename1.ui.Tabs;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Offer;
import com.mycompany.entities.Order;
import com.mycompany.enums.OrderStatus;
import com.mycompany.services.serviceOrder;
import java.util.Date;

/**
 *
 * @author asus
 */
public class placeOrder extends BaseForm{
   Form current;
    private Resources theme;

    public placeOrder (Resources res,Offer ofs)
    {
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        current=this;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Information about offer");
        getContentPane().setScrollVisible(false);

        super.addSideMenu(res);
        tb.addSearchCommand(e -> {

        });


        Tabs swipe = new Tabs();

        Label s1 = new Label();
        Label s2 = new Label();

        addTab(swipe,s1, res.getImage("s3.jpg"),"","",res);
        

        // Design

        swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();

        ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        Graphics g = unselectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAlpha(100);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        Image selectedWalkthru = Image.createImage(size, size, 0);
        g = selectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        RadioButton[] rbs = new RadioButton[swipe.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(BOTTOM);
        Container radioContainer = new Container(flow);
        for (int iter = 0; iter < rbs.length; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }

        rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if (!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });

        Component.setSameSize(radioContainer, s1, s2);
        add(LayeredLayout.encloseIn(swipe, radioContainer));
  

    

       // SpanLabel description = new SpanLabel("Description : "+ofs.getDescription(),"NewsTopLine2");
        Label price = new Label("Price : $"+ofs.getPrice()+"/month","NewsTopLine2");
        Label plan = new Label();
        Label totallb = new Label();
         Label plantag = new Label("month(s)");
         Label totaltag = new Label("Total : $");
        
       // addStringValue("plan",plan);
       // addStringValue("total",totallb);
         
         Button image = new Button();
        image.setUIID("Label");
        Container cnt =  BorderLayout.west(image);


        cnt.add(BorderLayout.CENTER, BoxLayout.encloseY(        
                BoxLayout.encloseX(price),
                 BoxLayout.encloseX(plantag),
                  BoxLayout.encloseX(totaltag)
        ));
        Container cnt2 =  createPricingSlider(ofs,plan,totallb,plantag,totaltag);

        addAll(cnt,cnt2); 
      
        Button btnPlaceOrder = new Button("Buy");
        addStringValue("",btnPlaceOrder);
        

       
        //onClick button event
        
        btnPlaceOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (plan.getText().equals("") || totallb.getText().equals("")){
                        Dialog.show("Please double check your infos","","Cancel","OK");
                    }
                    else {
                        InfiniteProgress ip = new InfiniteProgress();;
                        final Dialog iDialog = ip.showInfiniteBlocking();
                        int id =1;
                        Date date = new Date();
                        Order ors = new Order(ofs.getId(),
                                id,
                                Integer.parseInt(plan.getText()),
                                Float.parseFloat(totallb.getText()),
                                String.valueOf(date),
                                OrderStatus.PENDING
                                
                                
                        );
                        
                        System.out.println("data order == "+ors);
                        
                        //app methode ajouterMatch mt3 service Match bch nzidou données te3na fi base
                        serviceOrder.getInstance().createOrder(ors);
                        
                        
                        iDialog.dispose(); 
                        
                        //offerManager
                        new offerManager(res).show();
                        
                        refreshTheme();
                    }
                }catch(Exception ex) {
                    ex.printStackTrace();
                }
            }           
        });
        

       


    }
    private Container createPricingSlider(Offer ofs,Label plan,Label totallb,Label plantag,Label totaltag) {
         
           Container container = new Container(new FlowLayout());
        Slider slider = new Slider();
        Style sliderStyle = UIManager.getInstance().getComponentStyle("Label");
          sliderStyle.setFgColor(0);
         slider.setThumbImage(
         FontImage.createMaterial(FontImage.MATERIAL_RADIO_BUTTON_CHECKED, sliderStyle, 4).toImage());

slider.setMinValue(1);
slider.setIncrements(1);
slider.setMaxValue(12);
slider.setEditable(true); 

slider.addActionListener(e -> {
        int monthrange = 1;
        monthrange = slider.getProgress(e);
        plan.setText(String.valueOf(monthrange));
        float unitprice = ofs.getPrice();
        float total = monthrange * unitprice;
        totallb.setText(String.valueOf(total));
        plantag.setText(monthrange+"month(s)");
        totaltag.setText("Total : $"+total);
        
        });
slider.animate();
      
        


container.addAll(slider);
    
    
    return container;
    }
    private void addTab(Tabs swipe, Label spacer ,Image image, String string, String text, Resources res) {

        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        
        if(image.getHeight() < size)
        {
            image = image.scaledHeight(size);
        }

        if(image.getHeight() > Display.getInstance().getDisplayHeight() / 2)
        {
             image = image.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
        }

        ScaleImageLabel imageScale = new ScaleImageLabel(image);
        imageScale.setUIID("Container");
        imageScale.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);

        Label overLay = new Label("","ImageOverlay");

        Container page1 = LayeredLayout.encloseIn(
            imageScale,
            overLay,
            BorderLayout.south(
                BoxLayout.encloseY(
                    new SpanLabel(text, "LargeWhiteText"),
                    spacer
                )
            )
        );

        swipe.addTab("",res.getImage("back-logo.jpeg"), page1);



    }

    public void bindButtonSelection(Button btn , Label l)
    {
        btn.addActionListener(e -> {

            if(btn.isSelected())
            {
                updateArrowPosition(btn,l);
            }

        });
    }

    private void updateArrowPosition(Button btn, Label l) {

        l.getUnselectedStyle().setMargin(LEFT, btn.getX() + btn.getWidth() / 2 - l.getWidth() / 2);
        l.getParent().repaint();
    }

    private void addStringValue(String s, Component v) {

        add(BorderLayout.west(new Label(s,"PaddedLabel")).add(BorderLayout.CENTER,v));
        add(createLineSeparator(0xeeeeee));

    }
    
}
