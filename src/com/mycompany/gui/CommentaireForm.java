/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.MultiButton;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Commentaire;



import com.mycompany.gui.AddCommentaireForm;
import com.mycompany.services.ServiceCommentaire;
import java.util.ArrayList;

public class CommentaireForm extends BaseForm{
    ServiceCommentaire commentaireService = new ServiceCommentaire();
    public ArrayList<Commentaire> commentaires;
    @SuppressWarnings("unused")
    private Resources theme;
    
    public CommentaireForm(Resources res, int article_id){
        super("Commentaires", GridLayout.autoFit());
        this.theme = theme;
        
        commentaires = commentaireService.afficherCommentaire(article_id);
        System.out.println(commentaires);
        Container list = new Container(BoxLayout.y());
        list.setScrollableY(true);
        for (Commentaire commentaire : commentaires) {
            
               
                MultiButton mb = new MultiButton(commentaire.getMessage());
             
                
                    mb.addLongPressListener((evt) -> {
                        commentaireService.supprimerCommentaire(commentaire.getId());
                        new ListArticleForm(res).showBack();
                    });
                
                
                list.add(mb);
            
            
        }
        
        this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
            new ListArticleForm(res).showBack();
        });
        
        this.getToolbar().addCommandToRightBar("add", null, (evt) -> {
            new AddCommentaireForm(res, article_id, 0).show();
        });
        this.add(list);
    }
}
