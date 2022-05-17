/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Commentaire;
import com.mycompany.gui.CommentaireForm;
import com.mycompany.services.ServiceCommentaire;

public class AddCommentaireForm extends BaseForm{
    
    @SuppressWarnings("unused")
    private Commentaire c = new Commentaire();
    private ServiceCommentaire cs = new ServiceCommentaire();
    
    
    public AddCommentaireForm(Resources res, int article_id, int comment_id){
        super("Add Commentaire",BoxLayout.y());
          
        Button add = new Button("Create");
        TextField contenu = new TextField("Contenu");

        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Commentaire c = new Commentaire(article_id,contenu.getText() );
               
                    cs.ajouterCommentaire(c,article_id);
                    
                new CommentaireForm(res, article_id).showBack();
            }
        });
        this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
            new CommentaireForm(res, article_id).showBack();
        });
        addAll(contenu, add);     
    }
}
