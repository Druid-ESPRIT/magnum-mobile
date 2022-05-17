/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.mycompany.entities.Offer;
import com.mycompany.entities.Order;
import com.mycompany.enums.OrderStatus;
import com.mycompany.utils.Statics;
import java.util.ArrayList;

/**
 *
 * @author asus
 */
public class serviceOrder {
       public static serviceOrder instance = null ;
    public ArrayList<Order> offer;
    public static boolean resultOK;
    
    //inisialisation connection request
    private ConnectionRequest req;
    
    
    public static serviceOrder getInstance(){
        if(instance == null )
            instance = new serviceOrder();
        return instance;
        
    }

        
    
    public serviceOrder(){
        req = new ConnectionRequest();
       
        
}
   
    public boolean createOrder(Order order){
        
        String url =Statics.BASE_URL+"/placeordermobile?id="+order.getOfferId()+"&plan="+order.getPlan()+"&total="+order.getTotal();
  
            req.setUrl(url);
            req.addResponseListener((e) -> {
        
      String str = new String(req.getResponseData());
      System.out.println("data == "+str);
      
    });
    
    NetworkManager.getInstance().addToQueueAndWait(req);
    return resultOK;
}
    
}
