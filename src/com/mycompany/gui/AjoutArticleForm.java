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
import com.mycompany.entities.Article;
import com.mycompany.services.ServiceArticle;


public class AjoutArticleForm extends BaseForm {
    
    @SuppressWarnings("unused")
    private Resources theme;
    private Article a = new Article();
    private ServiceArticle as = new ServiceArticle();
    
    
    public AjoutArticleForm(Resources res){
        super("Ajouter Article",BoxLayout.y());
                
        Button add = new Button("Ajouter");
        
       
        
        TextField titre = new TextField( "Titre");
        TextField contenu = new TextField("Contenu");

        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Article a = new Article(titre.getText().toString(),contenu.getText().toString() ," ");
                as.AjouterArticle(a);
                new ListArticleForm(res).showBack();
            }
        });
        
       
        
        this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
            new ListArticleForm(res).showBack();
        });
        addAll(titre, contenu, add);     
    }

    
}
