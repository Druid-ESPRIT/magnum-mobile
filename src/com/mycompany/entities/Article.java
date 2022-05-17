/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

/**
 *
 * @author zeineb
 */
public class Article {
    private int id;
  private String title;
  private String content;
  private String url;
  private int user_id;

   
    public Article() {
    }

    public Article(String title, String content, String url,int author) {
        this.title = title;
        this.content = content;
        this.url = url;
        this.user_id=author;
    }
    
      public Article(String title, String content, String url) {
        this.title = title;
        this.content = content;
        this.url = url;
    }
      
      public Article(int id,String title, String content, String url) {
        this.id=id;
          this.title = title;
        this.content = content;
        this.url = url;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
     public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }






  
}

