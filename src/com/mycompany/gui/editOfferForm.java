/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.FloatingHint;
import com.codename1.ext.filechooser.FileChooser;
import com.codename1.io.File;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Offer;
import com.mycompany.services.serviceOffer;

/**
 *
 * @author asus
 */
public class editOfferForm extends BaseForm {
    Form current;
    public editOfferForm(Resources res , Offer ofs) {
    
     super("Newsfeed",BoxLayout.y());
          
          Toolbar tb = new Toolbar(true);
          current = this;
          setToolbar(tb);
        getTitleArea().setUIID("container") ;
        setTitle("Add new offer");
        getContentPane().setScrollVisible(false);
    
        
        super.addSideMenu(res);
        TextField description = new TextField(ofs.getDescription() , "description" , 20 , TextField.ANY);
        TextField price = new TextField(String.valueOf(ofs.getPrice()).toString() , "price" , 20 , TextField.ANY);
        TextField image = new TextField(ofs.getImage() , "image" , 20 , TextField.ANY);
        
        
         description.setUIID("NewsTopLine");
        
         price.setUIID("NewsTopLine");
      
         image.setUIID("NewsTopLine");
        
         
         description.setSingleLineTextArea(true);
        
         price.setSingleLineTextArea(true);
      
        image.setEnabled(false);

        Button btnimage = new Button("Choose image");
         btnimage.setUIID("Button");
        
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
       
         
         Button btnModifier = new Button("Edit");
         
         btnModifier.setUIID("Button");
         
         //Event OnClick btnModifier
         btnModifier.addPointerPressedListener(l -> { 
             ofs.setDescription(description.getText());
             ofs.setPrice(Float.parseFloat(price.getText()));
             ofs.setImage(image.getText());

             
         
         
         //app fonction modifier match
         if(serviceOffer.getInstance().editOffer(ofs)){
             new offerManager(res).show();
         }
         });
         Button btnAnnuler = new Button ("Cancel");
         btnAnnuler.addActionListener(e -> {
             
             new offerManager(res).show();
             
         });
         
      
         
         Container content = BoxLayout.encloseY(
                 createLineSeparator(),
                 new FloatingHint(description),
                 createLineSeparator(),
                 new FloatingHint(price),
                 createLineSeparator(),
                 new FloatingHint(image),
                 createLineSeparator(),
                 btnimage,
                 btnModifier,
                 btnAnnuler
         );
         
         add(content);
         show();
         
    }
    
}
