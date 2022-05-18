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
import static com.codename1.ui.Component.RIGHT;
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
import com.codename1.ui.Tabs;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Offer;
import com.mycompany.services.serviceOffer;
import java.util.ArrayList;

/**
 *
 * @author asus
 */
public class offerListing extends BaseForm {
       Form current;
    public offerListing(Resources res){
     super("Newsfeed",BoxLayout.y());
          
          Toolbar tb = new Toolbar(true);
          current = this;
          setToolbar(tb);
        getTitleArea().setUIID("container") ;
        setTitle("Choose an offer");
        getContentPane().setScrollVisible(false);
        super.addSideMenu(res);
        
        tb.addSearchCommand(e -> {
            
        });
        
        Tabs swipe = new Tabs();
        
        Label s1 = new Label();
        Label s2 = new Label();
        
        addTab(swipe,s1, res.getImage("bg-3.jpg"),"","",res);
        
        
        
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

     

     
        
         //************************************************************************
         //*****************************FinDesign*********************************
        //************************************************************************
       

        
        //Appel affichageMatche
        ArrayList<Offer>list = serviceOffer.getInstance().offersListing();
        
        for(Offer ofs : list ){
           // String urlImage ="logoo.png"; 
            String urlImage = "http://localhost/Magnum-mobile/src/resources/"+ofs.getImage();
            
            Image placeHolder = Image.createImage(120, 90);
            EncodedImage enc = EncodedImage.createFromImage(placeHolder,false);
            URLImage urlim = URLImage.createToStorage(enc, urlImage, urlImage,URLImage.RESIZE_SCALE);
            
            addButton(urlim,ofs,res);
              
            ScaleImageLabel image = new ScaleImageLabel(urlim);
            Container containerImg = new Container();
            image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        }
    
}
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
 
 swipe.addTab("",res.getImage("logoo.png"), page1);
 
 
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

    private void addButton(Image img,Offer ofs , Resources res) {
        int height = Display.getInstance().convertToPixels(11.5f);
        int width = Display.getInstance().convertToPixels(14f);
        
        Button image = new Button(img.fill(width, height));
        image.setUIID("Label");
        Container cnt =  BorderLayout.west(image);
        
        SpanLabel nomMatcheText = new SpanLabel("Description : "+ofs.getDescription(),"NewsTopLine2");
        Label nomArbitreText = new Label("Price : $"+ofs.getPrice(),"NewsTopLine2");
       
        Label margin = new Label("","NewsTopLine2");
        
        //createLineSeparator();
        Label lDetailMatch = new Label(" ");
        lDetailMatch.setUIID("NewsTopLine");
        Style DetailProduitStyle = new Style(lDetailMatch.getUnselectedStyle());
        DetailProduitStyle.setFgColor(0xf7ad02);
        lDetailMatch.setTextPosition(RIGHT);

       
        //Supp Button
        Label lSupprimer = new Label(" ");
        lSupprimer.setUIID("NewsTopLine");
        Style supprimerStyle = new Style(lSupprimer.getUnselectedStyle());
        supprimerStyle.setFgColor(0xf21f1f);
        
        FontImage supprimerImage = FontImage.createMaterial(FontImage.MATERIAL_DELETE, supprimerStyle);
        lSupprimer.setIcon(supprimerImage);
        lSupprimer.setTextPosition(RIGHT);
        //click delete icon
     
        //update icon
        Label lModifier = new Label(" ");
        lModifier.setUIID("NewsTopLine");
        Style modifierStyle = new Style(lModifier.getUnselectedStyle());
        modifierStyle.setFgColor(0xf7ad02);
        
        FontImage mFontImage = FontImage.createMaterial(FontImage.MATERIAL_MODE_EDIT, modifierStyle);
        lModifier.setIcon(mFontImage);
        lModifier.setTextPosition(LEFT);
          
        //edit
      lModifier.addPointerPressedListener(l ->  {
            //System.out.println("Hello update");
            new editOfferForm(res,ofs).show();
        });
        
        
        
        //
        
        cnt.add(BorderLayout.CENTER,BoxLayout.encloseY(
                
                BoxLayout.encloseX(nomMatcheText),
                BoxLayout.encloseX(nomArbitreText),
                //BoxLayout.encloseX(stadeText),
                BoxLayout.encloseX(margin, lDetailMatch)
        ));
        
        //add(cnt); 
        add(cnt);

        Button btnAjouter = new Button("See infos");
        addStringValue("", btnAjouter);
        btnAjouter.addActionListener((e) -> {

            new selectedOffer(res,ofs).show();

        });

    }
    private void addStringValue(String s, Component v) {

        add(BorderLayout.west(new Label(s,"PaddedLabel")).add(BorderLayout.CENTER,v));
        add(createLineSeparator(0xeeeeee));

    }
    
}
