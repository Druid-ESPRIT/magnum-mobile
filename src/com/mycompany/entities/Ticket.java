/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.entities;

/**
 *
 * @author user
 */
public class Ticket {
    
    
    
    private int id;
    private String subject;
    private String description;
    private String creationdate;
    private String resolverid;
    private int userid;
    private String status;
    private String evaluate;

    public Ticket() {
    }

 

    public int getid() {
        return id;
    }

    public void setid(int id) {
        this.id = id;
    }

    public String getsubject() {
        return subject;
    }

    public void setsubject(String subject) {
        this.subject = subject;
    }

    public String getdescription() {
        return description;
    }

    public void setdescription(String description) {
        this.description = description;
    }

    public String getcreationdate() {
        return creationdate;
    }

    public void setcreationdate(String creationdate) {
        this.creationdate = creationdate;
    }

    public String getresolverid() {
        return resolverid;
    }

    public void setresolverid(String resolverid) {
        this.resolverid = resolverid;
    }

    public int getuserid() {
        return userid;
    }

    public void setuserid(int userid) {
        this.userid = userid;
    }

    public String getstatus() {
        return status;
    }

    public void setstatus(String status) {
        this.status = status;
    }

    public String getevaluate() {
        return evaluate;
    }

    public void setevaluate(String evaluate) {
        this.evaluate = evaluate;
    }

    public Ticket(int id,int userid, String subject, String description ,String resolverid,String evaluate) {
        this.id = id;
        this.subject = subject;
        this.description = description;
        this.resolverid = resolverid;
        this.userid = userid;
        this.evaluate = evaluate;
        
       
       
    }
    
    
    
    

    
    
    
}
