/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.MultiButton;
import static com.codename1.ui.Component.WAIT_CURSOR;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
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
        super("My Articles", GridLayout.autoFit());
        this.revalidate();
        articles = as.AfficherArticles();
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
}
