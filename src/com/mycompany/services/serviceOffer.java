/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.mycompany.entities.Offer;

import com.mycompany.utils.Statics;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.io.ConnectionRequest;
import com.codename1.ui.events.ActionListener;
import java.util.ArrayList;
import com.codename1.io.JSONParser;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author asus
 */
public class serviceOffer {
       public static serviceOffer instance = null ;
    public ArrayList<Offer> offer;
    public static boolean resultOK;
    
    //inisialisation connection request
    private ConnectionRequest req;
    
    
    public static serviceOffer getInstance(){
        if(instance == null )
            instance = new serviceOffer();
        return instance;
        
    }

        
    
    public serviceOffer(){
        req = new ConnectionRequest();
       
        
}
    
    //ajout
    public boolean addOffer(Offer offer){
        
        String url =Statics.BASE_URL+"/addoffermobile?user="+1+"&description="+offer.getDescription()+"&price="+offer.getPrice()+"&image="+offer.getImage();
  
            req.setUrl(url);
            req.addResponseListener((e) -> {
        
      String str = new String(req.getResponseData());
      System.out.println("data == "+str);
      
    });
    
    NetworkManager.getInstance().addToQueueAndWait(req);
    return resultOK;
}
    //affichage
    public ArrayList<Offer>manageOffers() {
        ArrayList<Offer> result = new ArrayList<>();
        
        String url = Statics.BASE_URL+"/offermanagermobile";
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>(){
            public void actionPerformed(NetworkEvent evt){
                JSONParser jsonp ;
                jsonp = new JSONParser();
                
                
                try {
                    Map<String,Object>mapOffer = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String,Object>>  listOfMaps  =   (List<Map<String,Object>>) mapOffer.get("root");
                    for(Map<String, Object> obj : listOfMaps){
                        Offer ofs = new Offer();
                        
                        float id = Float.parseFloat(obj.get("id").toString());
                        float price = Float.parseFloat(obj.get("price").toString());                       
                        String description = obj.get("description").toString();
                        String image = obj.get("image").toString();
                        
                        ofs.setId((int)id);
                        ofs.setPrice(price);
                        ofs.setDescription(description);
                        ofs.setImage(image);
                     
                        
                        //insert data into ArrayList result
                        result.add(ofs);
                        
                    }
                }catch(Exception ex){
                    ex.printStackTrace();
                }
                
            }
            
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        
        return result;
        
    }
    public ArrayList<Offer>offersListing() {
        ArrayList<Offer> result = new ArrayList<>();
        
        String url = Statics.BASE_URL+"/offerlistmobile";
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>(){
            public void actionPerformed(NetworkEvent evt){
                JSONParser jsonp ;
                jsonp = new JSONParser();
                
                
                try {
                    Map<String,Object>mapOffer = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String,Object>>  listOfMaps  =   (List<Map<String,Object>>) mapOffer.get("root");
                    for(Map<String, Object> obj : listOfMaps){
                        Offer ofs = new Offer();
                        
                        float id = Float.parseFloat(obj.get("id").toString());
                        float price = Float.parseFloat(obj.get("price").toString());                       
                        String description = obj.get("description").toString();
                        String image = obj.get("image").toString();
                        
                        ofs.setId((int)id);
                        ofs.setPrice(price);
                        ofs.setDescription(description);
                        ofs.setImage(image);
                     
                        
                        //insert data into ArrayList result
                        result.add(ofs);
                        
                    }
                }catch(Exception ex){
                    ex.printStackTrace();
                }
                
            }
            
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        
        return result;
        
    }
    
    //update
    
    public boolean editOffer(Offer offer){
        String url = Statics.BASE_URL +"/editoffermobile?id="+offer.getId()+"&description="+offer.getDescription()+"&price="+offer.getPrice()+"&image="+offer.getImage();
    req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
              resultOK = req.getResponseCode() == 200 ; // code response http 200 
               req.removeResponseCodeListener(this);
              
            }
        });
         NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
}
