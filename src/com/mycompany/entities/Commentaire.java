/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

import java.util.Date;


public class Commentaire {
    
    private int id;
    private int article_id;
    private int user_id;
    private String message;
    private Date submitDate;

    public Commentaire(int id, int article_id, int user_id, String message, Date submitDate) {
        this.id = id;
        this.article_id = article_id;
        this.user_id = user_id;
        this.message = message;
        this.submitDate = new Date();
    }
    
      public Commentaire(int article_id, String message) {
        this.article_id = article_id;
        this.message = message;
    }

    public Commentaire() {
    }

    public Commentaire(int id, int article_id, int user_id, String message) {
        this.id = id;
        this.article_id = article_id;
        this.user_id = user_id;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getArticle_id() {
        return article_id;
    }

    public void setArticle_id(int article_id) {
        this.article_id = article_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(Date submitDate) {
        this.submitDate = submitDate;
    }

    @Override
    public String toString() {
        return "Commentaire{" + "id=" + id + ", article_id=" + article_id + ", user_id=" + user_id + ", message=" + message + ", submitDate=" + submitDate + '}';
    }
    
    
    
    
}
