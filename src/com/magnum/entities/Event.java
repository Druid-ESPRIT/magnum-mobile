/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magnum.entities;

import java.util.Date;





public class Event {
    
    private int id;
    private User user;
    private String name;
    private String description;
    private String type;
    private String location;
    private Date date;
    private boolean payant;
    private double prix;
    private String status;
    private int MaxP;
    private String image;

    public Event() {
    }

    public int getMaxP() {
        return MaxP;
    }

    public void setMaxP(int MaxP) {
        this.MaxP = MaxP;
    }
    
    

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Event(User user, String name, String description, String type, String location, Date date, int Maxp, boolean payant, double prix, String image) {
        this.user = user;
        this.name = name;
        this.description = description;
        this.type = type;
        this.location = location;
        this.date = date;
        this.payant = payant;
        this.prix = prix;
        this.image = image;
        this.MaxP = Maxp;
    }
    
     public Event(String name, String description, String type, String status, String location, Date date, int Maxp, boolean payant, double prix, String image) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.location = location;
        this.date = date;
        this.payant = payant;
        this.prix = prix;
        this.image = image;
        this.MaxP = Maxp;
        this.status = status;
    }
    
     public Event(User user, String name, String description, String type, int Maxp, String location, Date date, boolean payant) {
        this.user = user;
        this.name = name;
        this.description = description;
        this.type = type;
        this.location = location;
        this.date = date;
        this.payant = payant;
        this.MaxP = Maxp;
    }
     
     


    public Event(int id, User user, String name, String description, String type,int Maxp, String location, Date date, boolean payant, double prix, String status) {
        this.id = id;
        this.user = user;
        this.name = name;
        this.description = description;
        this.type = type;
        this.location = location;
        this.date = date;
        this.payant = payant;
        this.prix = prix;
        this.status = status;
        this.MaxP = Maxp;
    }
    
    
    
    
    
    
   
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isPayant() {
        return payant;
    }

    public void setPayant(boolean payant) {
        this.payant = payant;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Event{" + "id=" + id + ", user=" + user + ", name=" + name + ", description=" + description + ", type=" + type + ", location=" + location + ", date=" + date + ", payant=" + payant + ", prix=" + prix + ", status=" + status + '}';
    }
    
    

    

}
