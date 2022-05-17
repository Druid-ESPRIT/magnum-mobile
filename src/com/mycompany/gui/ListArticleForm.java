/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.MultiButton;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.WAIT_CURSOR;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Article;

import com.mycompany.services.ServiceArticle;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;

public class ListArticleForm extends BaseForm {

    private ServiceArticle as = new ServiceArticle();
    private ArrayList<Article> articles;

    public ListArticleForm(Resources res) {
        super("My Articles", BoxLayout.y());
        this.revalidate();
        articles = as.AfficherArticles();
        
        
        
         Tabs swipe = new Tabs();
        
        Label s1 = new Label();
        Label s2 = new Label();
        
        addTab(swipe,s1, res.getImage("bg-3.jpg"),"","",res);
        
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
        
        
        
        Container list = new Container(BoxLayout.y());
        list.setScrollableY(true);
        for (Article article : articles) {

            MultiButton mb = new MultiButton(article.getTitle());
            mb.setTextLine2(article.getContent());
            mb.setTextLine3(article.getUrl());
            mb.addActionListener((evt) -> {
                //new ArticleDetailsForm(res, article).show();
                 new CommentaireForm(res, article.getId()).show();
            });

            mb.addLongPressListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                   as.SupprimerArticle(article.getId());
                   new ListArticleForm(res).show();
                }
            });
            list.add(mb);
        }

        this.getToolbar().addCommandToRightBar("Add", null, (evt) -> {
            new AjoutArticleForm(res).show();
        });

        this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
            new NewsfeedForm(res).showBack();
        });
        this.add(list);
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
}
