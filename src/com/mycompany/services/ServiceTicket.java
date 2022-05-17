/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.services;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Ticket;
import static com.mycompany.services.ServiceCategorie.resultOk;
import com.mycompany.utils.Statics;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 *
 * @author user
 */
public class ServiceTicket {
    
    public static ServiceTicket instance = null;
    
    private ConnectionRequest req;
    
    
    public static ServiceTicket getInstance()
    {
       if(instance == null)
           instance= new ServiceTicket();
        return instance;
    }
    
    
    public  ServiceTicket()
    {
       req= new ConnectionRequest();
    }
    
    public void ajoutTicket(Ticket ticket)
    {
       String url =Statics.BASE_URL+"/AjouterTicketMobile?id="+ticket.getid()+"&userid="+ticket.getuserid()+"&subject="+ticket.getsubject()+"&description="+ticket.getdescription()+"&status="+"Pending"+"&resolverid"+"";
  
       req.setUrl(url);
       req.addResponseListener((e) ->{
       
           String str= new String(req.getResponseData());
           System.out.println("data==> "+str);
       });
       NetworkManager.getInstance().addToQueueAndWait(req);
       
    }
    
    
    
    //affichage 
    
    public ArrayList<Ticket> AfficherTicket()
    {

        ArrayList<Ticket> result = new ArrayList<>();
        String url = Statics.BASE_URL+"/AfficherAllTicketMobile";
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                
                JSONParser jsonp;
                jsonp = new JSONParser();
                
                try 
                {
                    Map<String,Object>mapTicket = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String,Object>> ListOfMaps = (List<Map<String,Object>>) mapTicket.get("root");
                    for(Map<String, Object> obj : ListOfMaps)
                    {
                        
                        
                        
                        
                        Ticket t = new Ticket();
                         
                        float id=Float.parseFloat(obj.get("id").toString());
                         String subject=obj.get("subject").toString();
                         String description=obj.get("description").toString();
//                         String creationdate=obj.get("creationdate").toString();
                        // String resolverid=obj.get("resolverid").toString();
                         float userid=Float.parseFloat(obj.get("userid").toString());
                         String status=obj.get("status").toString();
                       //  String evaluate=obj.get("evaluate").toString();
                      String evaluate= String.valueOf(obj.get("evaluate"));
                         System.out.println(obj.get("evaluate"));
                         
                    //   String evaluate=  Float.toString((float) obj.get("evaluate"));
                    
                         t.setid((int)id);
                         t.setuserid((int)userid);
                         t.setsubject(subject);
                         t.setdescription(description);
                         t.setstatus(status);
                         //t.setcreationdate(creationdate);
                       //  t.setresolverid(resolverid);
                       
                             
                         
                             
                       t.setevaluate(evaluate.substring(0,1));
                         
                         
                        //aaa
                        
                        
  
                        result.add(t);
                       // System.out.println(c.getIdcategorie()+" "+c.getNamecateg());
                    }
                }
                catch(Exception ex)
                {
                    ex.printStackTrace();
                }

            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);

        return result;
    
    }

    
    
    
    
            public boolean SupprimerTicket(int id)
    {
        String url = Statics.BASE_URL+"/SupprimerTicketMobile?id="+id;

        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) 
            {
                req.removeResponseCodeListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);

        return resultOk;
        
    }
            
            
            
            
            
            
            
   public boolean ModifierStatus(Ticket t)
    {
        String url = Statics.BASE_URL+"/ModifierStatusTicketMobile?id="+t.getid()+"&status="+t.getstatus();
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) 
            {
                resultOk = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);

        return resultOk;

    }
   
   
   
     public boolean ModifierEvaluate(int id,int evaluate)
    {
        String url = Statics.BASE_URL+"/ModifierEvaluateTicketMobile?id="+id+"&evaluate="+evaluate;
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) 
            {
                resultOk = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);

        return resultOk;

    }
   
   
  
        
        
        
        
        
        
        
        
        
        
        
        
            public ArrayList<Ticket>AfficherCons(int t)
    {

        ArrayList<Ticket> result = new ArrayList<>();
        String url = Statics.BASE_URL+"/AfficherTicketMobile?userid="+t;
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                
                JSONParser jsonp;
                jsonp = new JSONParser();
                
                try 
                {
                    Map<String,Object>mapTicket = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String,Object>> ListOfMaps = (List<Map<String,Object>>) mapTicket.get("root");
                    for(Map<String, Object> obj : ListOfMaps)
                    {
                        
                        
                        
                        
                        Ticket t = new Ticket();
                         
                        float id=Float.parseFloat(obj.get("id").toString());
                         String subject=obj.get("subject").toString();
                         String description=obj.get("description").toString();
//                         String creationdate=obj.get("creationdate").toString();
                        // String resolverid=obj.get("resolverid").toString();
                         float userid=Float.parseFloat(obj.get("userid").toString());
                         String status=obj.get("status").toString();
//                         String evaluate=obj.get("evaluate").toString();
                         
                         t.setid((int)id);
                         t.setuserid((int)userid);
                         t.setsubject(subject);
                         t.setdescription(description);
                         //t.setcreationdate(creationdate);
                        // t.setresolverid(resolverid);
                         t.setstatus(status);
                        // t.setevaluate(evaluate);
                        //aaa
                        
                        
  
                        result.add(t);
                       // System.out.println(c.getIdcategorie()+" "+c.getNamecateg());
                    }
                }
                catch(Exception ex)
                {
                    ex.printStackTrace();
                }

            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);

        return result;
    
    }
        
        

    }
                
    

