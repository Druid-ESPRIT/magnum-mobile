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
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Categorie;
import com.mycompany.services.ServiceCategorie;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Ticket;
import com.mycompany.services.ServiceTicket;
import java.util.ArrayList;

/**
 *
 * @author Azizc
 */
public class DetTicketForm extends BaseForm {
    Form current;
   
    public DetTicketForm(Resources res,int t){
                super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        current=this;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Ajouter Ticket");
        getContentPane().setScrollVisible(false);

        super.addSideMenu(res);
        tb.addSearchCommand(e -> {

        });
        //design
       
                Tabs swipe = new Tabs();

        Label s1 = new Label();
        Label s2 = new Label();

        addTab(swipe,s1,res.getImage("back-logo.jpeg"),"","",res);

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

        ButtonGroup barGroup = new ButtonGroup();
        RadioButton mesListes = RadioButton.createToggle("Mes Categorie", barGroup);
        mesListes.setUIID("SelectBar");
        RadioButton liste = RadioButton.createToggle("Autres", barGroup);
        liste.setUIID("SelectBar");
        RadioButton partage = RadioButton.createToggle("Categoriser", barGroup);
        partage.setUIID("SelectBar");
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");


        mesListes.addActionListener((e) -> {
               InfiniteProgress ip = new InfiniteProgress();
        final Dialog ipDlg = ip.showInifiniteBlocking();
       
        //  ListReclamationForm a = new ListReclamationForm(res);
          //  a.show();
            refreshTheme();
        });

        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(3, mesListes, liste, partage),
                FlowLayout.encloseBottom(arrow)
        ));

        partage.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(partage, arrow);
        });
        bindButtonSelection(mesListes, arrow);
        bindButtonSelection(liste, arrow);
        bindButtonSelection(partage, arrow);
        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });
       
               
        //Appel affichage methode
        ArrayList<Ticket>list = ServiceTicket.getInstance().AfficherCons(t);
             int i = 0;
        for(Ticket t1 : list)
        {
            i++;
            addTicket(t1,res,i);
        }

       


       
       
        //enddesign
       
       
       
       
    }
   
   
   
        private void addTab(Tabs swipe, Label spacer, Image image, String string, String text, Resources res) {

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


    private void updateArrowPosition(Button btn, Label l) {

        l.getUnselectedStyle().setMargin(LEFT, btn.getX() + btn.getWidth() / 2 - l.getWidth() / 2);
        l.getParent().repaint();
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

    private void addTicket(Ticket t, Resources res, int i) {
        Button image = new Button();
        image.setUIID("Label");
        Container cnt = BorderLayout.west(image);
         
        Label TicketTxt = new Label("N: "+i,"NewsTopLine2");
        Label idTxt = new Label(" Ticket: "+t.getid(),"NewsTopLine2");
        Label useridTxt = new Label("user : "+t.getuserid(),"NewsTopLine2");
        Label subjectTxt = new Label("Subject: "+t.getsubject(),"NewsTopLine2");
        Label descriptionTxt = new Label("Description: "+t.getdescription(),"NewsTopLine2");
        Label statusTxt = new Label("STATUS: "+t.getstatus(),"NewsTopLine2");
       // Label resolveridTxt = new Label("RESOLVER ID: "+t.getresolverid(),"NewsTopLine2");
       // Label creationdatedTxt = new Label("date: "+t.getcreationdate(),"NewsTopLine2");
      //  Label evaluateTxt = new Label("evaluate: "+t.getevaluate(),"NewsTopLine2");
       
        Label margin = new Label("","NewsTopLine2");        
       
       
     
       

 
       
       
          if("Pending".equals(t.getstatus()))
        {
               
         Button s1 = new Button("⭐ ");
        s1.addActionListener(e -> {    
            ServiceTicket.getInstance().ModifierEvaluate(t.getid(),1);
            new ListTicketForm(res).show();
           
             new DetTicketForm(res,t.getuserid()).show();
           
        });
        s1.setUIID("Link");
       
       
       
        //Label Evaluate = new Label("Evaluate!");
         Button s2 = new Button("⭐ ");
        s2.addActionListener(e -> {    
            ServiceTicket.getInstance().ModifierEvaluate(t.getid(),2);
            new DetTicketForm(res,t.getuserid()).show();
        });
        s2.setUIID("Link");
       
         Button s3 = new Button("⭐ ");
        s3.addActionListener(e -> {    
            ServiceTicket.getInstance().ModifierEvaluate(t.getid(),3);
            new DetTicketForm(res,t.getuserid()).show();
        });
        s3.setUIID("Link");
       
       
         Button s4 = new Button("⭐ ");
        s4.addActionListener(e -> {    
            ServiceTicket.getInstance().ModifierEvaluate(t.getid(),4);
            new DetTicketForm(res,t.getuserid()).show();
        });
        s4.setUIID("Link");
       
         Button s5 = new Button("⭐ ");
        s5.addActionListener(e -> {    
            ServiceTicket.getInstance().ModifierEvaluate(t.getid(),5);
            new DetTicketForm(res,t.getuserid()).show();
        });
        s5.setUIID("Link");
       
     
          cnt.add(BorderLayout.CENTER, BoxLayout.encloseY(

            BoxLayout.encloseX(TicketTxt),
            BoxLayout.encloseX(idTxt),
            BoxLayout.encloseX(useridTxt),
            BoxLayout.encloseX(subjectTxt),
            BoxLayout.encloseX(descriptionTxt),
            BoxLayout.encloseX(statusTxt),
            //FlowLayout.encloseCenter(s1,s2,s3,s4,s5),
            BoxLayout.encloseX(margin)
                   
                       ));
        }else{
             
             
             
         Button s1 = new Button("⭐ ");
        s1.addActionListener(e -> {    
            ServiceTicket.getInstance().ModifierEvaluate(t.getid(),1);
            new ListTicketForm(res).show();
           
             new DetTicketForm(res,t.getuserid()).show();
           
        });
        s1.setUIID("Link");
       
       
       
        //Label Evaluate = new Label("Evaluate!");
         Button s2 = new Button("⭐ ");
        s2.addActionListener(e -> {    
            ServiceTicket.getInstance().ModifierEvaluate(t.getid(),2);
            new DetTicketForm(res,t.getuserid()).show();
        });
        s2.setUIID("Link");
       
         Button s3 = new Button("⭐ ");
        s3.addActionListener(e -> {    
            ServiceTicket.getInstance().ModifierEvaluate(t.getid(),3);
            new DetTicketForm(res,t.getuserid()).show();
        });
        s3.setUIID("Link");
       
       
         Button s4 = new Button("⭐ ");
        s4.addActionListener(e -> {    
            ServiceTicket.getInstance().ModifierEvaluate(t.getid(),4);
            new DetTicketForm(res,t.getuserid()).show();
        });
        s4.setUIID("Link");
       
         Button s5 = new Button("⭐ ");
        s5.addActionListener(e -> {    
            ServiceTicket.getInstance().ModifierEvaluate(t.getid(),5);
            new DetTicketForm(res,t.getuserid()).show();
        });
        s5.setUIID("Link");
       
     
          cnt.add(BorderLayout.CENTER, BoxLayout.encloseY(

            BoxLayout.encloseX(TicketTxt),
            BoxLayout.encloseX(idTxt),
            BoxLayout.encloseX(useridTxt),
            BoxLayout.encloseX(subjectTxt),
            BoxLayout.encloseX(descriptionTxt),
            BoxLayout.encloseX(statusTxt),
            FlowLayout.encloseCenter(s1,s2,s3,s4,s5),
            BoxLayout.encloseX(margin)
                   
                       ));}
           
         

           
           
        add(cnt);
        Button btnt = new Button("     ");
        addStringValue("", btnt);

        btnt.addActionListener((e) -> {     });
       
       
       
    }

   

   

 
  private void addStringValue(String s, Component v) {

        add(BorderLayout.west(new Label(s,"PaddedLabel")).add(BorderLayout.CENTER,v));
        add(createLineSeparator(0xeeeeee));

    }
 
   
}