/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Toolbar;
import com.codename1.ui.util.Resources;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.mycompany.entities.Offer;
import com.mycompany.services.serviceOffer;
import com.codename1.ext.filechooser.FileChooser;
import com.codename1.io.File;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Util;
import com.codename1.ui.TextArea;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.table.TableLayout;
import java.io.IOException;


/**
 *
 * @author asus
 */
public class addOfferForm extends BaseForm{
    
      Form current;
    
       public addOfferForm(Resources res) {
          super("Newsfeed",BoxLayout.y());
          
          Toolbar tb = new Toolbar(true);
          current = this;
          setToolbar(tb);
        getTitleArea().setUIID("container") ;
        setTitle("Create a new offer");
        getContentPane().setScrollVisible(false);
        
        tb.addSearchCommand(e -> {
            
        });
        
        Tabs swipe = new Tabs();
        
        Label s1 = new Label();
        Label s2 = new Label();
        
        addTab(swipe,s1, res.getImage("blog1.jpg"),"","",res);
        
        
        
        //**************************Debut Design************************
         
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

            ButtonGroup barGroup = new ButtonGroup();
        RadioButton offerlist = RadioButton.createToggle("Offers list", barGroup);
        offerlist.setUIID("SelectBar");
        RadioButton addoffer = RadioButton.createToggle("Add new offer", barGroup);
        addoffer.setUIID("SelectBar");
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");

        

      

        addoffer.addActionListener((e) -> {
            InfiniteProgress ip = new InfiniteProgress();

            final Dialog ipDlg = ip.showInifiniteBlocking();
        
            new addOfferForm(res).show();
                   
            refreshTheme();
        });


        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(2, offerlist, addoffer),
                FlowLayout.encloseBottom(arrow)
        ));

        addoffer.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(addoffer, arrow);
        });
        bindButtonSelection(offerlist, arrow);
        bindButtonSelection(addoffer, arrow);
        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });
         offerlist.addActionListener(e -> {       
            new offerManager(res).show();
             
        });

       
        
        //*****************************FinDesign*********************************
                
    
        
        TextField description = new TextField("","write something here...", 200, TextArea.ANY);
        description.setUIID("textFieldBack");
        addStringValue("Description",description);
        
        TextField price = new TextField("","Set a price...",4, TextArea.NUMERIC);
        price.setUIID("textFieldBack");
        addStringValue("Price",price);
       
        
        Label image = new Label("","Image");
         image.setUIID("textFieldBack");
        addStringValue("Image",image);
        
        Button btnimage = new Button("Choose image");
        addStringValue("",btnimage);
        
        btnimage.addActionListener((ev) ->  {
       ActionListener callback = e->{          
         if (e != null && e.getSource() != null) {
         String filePath = (String)e.getSource();
         File fileimage = new File(filePath);
          String file = fileimage.getName();
          image.setText(file);
          String dir = "C:/Users/asus/Documents/NetBeansProjects/Magnum-mobile/src/resources";
           File imageDir = new File(dir);
        
          
         
         
      
   }
};                    
if (FileChooser.isAvailable()) {
FileChooser.showOpenDialog(".png,.jpeg", callback);
} else {
    Display.getInstance().openGallery(callback, Display.GALLERY_IMAGE);
}


 });
                     
        Button btnAjouter = new Button("Add");
        addStringValue("",btnAjouter);
        

       
        //onClick button event
        
        btnAjouter.addActionListener((e) ->  {
                try {
                    if (description.getText().equals("") || image.getText().equals("")|| price.getText().equals("")){
            Dialog.show("Please double check your infos","","Cancel","OK");
                    }
                    else {
                        InfiniteProgress ip = new InfiniteProgress();;
                        final Dialog iDialog = ip.showInfiniteBlocking();
                        int id =1;
                        Offer ofs = new Offer(Integer.parseInt(String.valueOf(id).toString()),
                                              Float.parseFloat(price.getText()),
                                              String.valueOf(description.getText()).toString(),                                       
                                              String.valueOf(image.getText()).toString()
                                         
                                );
                        
                        System.out.println("data offer == "+ofs);
                        
                        //app methode ajouterMatch mt3 service Match bch nzidou données te3na fi base
                        serviceOffer.getInstance().addOffer(ofs);
                        
                        
                        iDialog.dispose(); //nahio loadig ma 3mlt l'ajout   
                        
                        //offerManager
                        new offerManager(res).show();
                        
                        refreshTheme();
                    }
                }catch(Exception ex) {
                    ex.printStackTrace();
                }
           
        });
        
       }

    addOfferForm(Form current) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void addStringValue(String s, Component v) {
        
       add(BorderLayout.west(new Label(s,"PaddedLabel"))
               .add(BorderLayout.CENTER,v));
       add(createLineSeparator(0xeeeeee));
    }

    
    //*************************************************************************************

   
   
    private void addTab(Tabs swipe, Label spacer , Image image, String string, String text, Resources res) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        
        if(image.getHeight() <size) {
            image = image.scaledHeight(size);
        }
        
        
        if(image.getHeight() > Display.getInstance().getDisplayHeight() / 2){
        
        image = image.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
    }
    
 ScaleImageLabel imageScale = new ScaleImageLabel(image);
 imageScale.setUIID("Container");
 imageScale.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
 
 Label overLay = new Label("","ImageOverlay");
 
 Container page1 =
         LayeredLayout.encloseIn(
         imageScale,
                 overLay,
                 BorderLayout.south(
                 BoxLayout.encloseY( 
                 new SpanLabel(text, "LargeWhiteText"),
                         FlowLayout.encloseIn(),
                         spacer
                    )
                 )
         );
 
 swipe.addTab("",res.getImage("back-logo.jpeg"), page1);
 
 
    }     
    
    public void bindButtonSelection(Button btn , Label l ){
        
        btn.addActionListener(e -> {
        if(btn.isSelected()){
            updateArrowPosition(btn,l);
        }
        
    });
    }

    private void updateArrowPosition(Button btn, Label l) {
       l.getUnselectedStyle().setMargin(LEFT, btn.getX() + btn.getWidth()  / 2 -l.getWidth() / 2);
       l.getParent().repaint();
    }
    
}
