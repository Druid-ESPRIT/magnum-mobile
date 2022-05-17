/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.components.InfiniteProgress;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Event;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class EventService {

    public ArrayList<Event> events;
    public Event event;
    public int total=0;
    public int totalParticipants=0;
    public boolean resultOk;
    private ConnectionRequest req;
    
    
    public static EventService instance = null;

    public static EventService getInstance()
    {
        if(instance == null)
            instance = new EventService();
        return instance;  

    }
    

    public EventService() {
        req = new ConnectionRequest();
    }

    public boolean addEvent(Event e) {
        String url = Statics.BASE_URL + "/event/newJson";
        req.setUrl(url);
        req.addArgument("status", e.getStatus());
        req.addArgument("prix", String.valueOf(e.getPrix()));
        req.addArgument("type", e.getType());
        req.addArgument("date", e.getDate().toString());
        req.addArgument("description", e.getDescription());
        req.addArgument("location", e.getLocation());
        req.addArgument("maxP", String.valueOf(e.getMaxP()));
        req.addArgument("name", e.getName());
        req.addArgument("payant", String.valueOf(e.isPayant()));

        //image
        req.addArgument("image", e.getImage());

        InfiniteProgress prog = new InfiniteProgress();
        Dialog d = prog.showInfiniteBlocking();
        req.setDisposeOnCompletion(d);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOk;

    }
    
    
    public boolean deleteEvent(int id){
        String url = Statics.BASE_URL+"/event/deleteJson/"+id;
        req.setUrl(url);
        InfiniteProgress prog = new InfiniteProgress();
        Dialog d = prog.showInfiniteBlocking();
        req.setDisposeOnCompletion(d);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOk;
    }
    
    public boolean participateEvent(int id){
        String url = Statics.BASE_URL+"/event/"+id+"/participateJson";
        req.setUrl(url);
        InfiniteProgress prog = new InfiniteProgress();
        Dialog d = prog.showInfiniteBlocking();
        req.setDisposeOnCompletion(d);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOk;
    }
    

    public boolean updateEvent(Event e) {
        String url = Statics.BASE_URL + "/event/" + e.getId() + "/editJson";
        req.setUrl(url);
        req.addArgument("status", e.getStatus());
        req.addArgument("prix", String.valueOf(e.getPrix()));
        req.addArgument("type", e.getType());
        req.addArgument("date", e.getDate().toString());
        req.addArgument("description", e.getDescription());
        req.addArgument("location", e.getLocation());
        req.addArgument("maxP", String.valueOf(e.getMaxP()));
        req.addArgument("name", e.getName());
        req.addArgument("payant", String.valueOf(e.isPayant()));

        //image
        req.addArgument("image", e.getImage());

        InfiniteProgress prog = new InfiniteProgress();
        Dialog d = prog.showInfiniteBlocking();
        req.setDisposeOnCompletion(d);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOk;

    }

    public Event getEvent(int id) {
        String url = Statics.BASE_URL + "/event/showJson/" + id;
        req.removeAllArguments();
        req.setUrl(url);
        req.setPost(false);
        InfiniteProgress prog = new InfiniteProgress();
        Dialog d = prog.showInfiniteBlocking();
        req.setDisposeOnCompletion(d);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    event = parseEvent(new String(req.getResponseData()));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return event;

    }
    
    public int getTotal()
    {
        
        String url = Statics.BASE_URL + "/event/statsJson";
        req.removeAllArguments();
        req.setUrl(url);
        req.setPost(false);
        InfiniteProgress prog = new InfiniteProgress();
        Dialog d = prog.showInfiniteBlocking();
        req.setDisposeOnCompletion(d);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    total = parseTotal(new String(req.getResponseData()));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return total;
    }
    
    public int getTotalParticipants()
    {
        
        String url = Statics.BASE_URL + "/event/statsJson";
        req.removeAllArguments();
        req.setUrl(url);
        req.setPost(false);
        InfiniteProgress prog = new InfiniteProgress();
        Dialog d = prog.showInfiniteBlocking();
        req.setDisposeOnCompletion(d);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    totalParticipants = parseTotalParticipants(new String(req.getResponseData()));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return totalParticipants;
    }
    
    
    public int parseTotal(String jsonText) throws IOException
    {
          JSONParser a= new JSONParser();
        Map<String, Object> objt = a.parseJSON(new CharArrayReader(jsonText.toCharArray()));
        List<Map<String,Object>> list = (List<Map<String,Object>>)objt.get("root");
         for(Map<String,Object> obj : list){
         return (int) Float.parseFloat(obj.get("total").toString());
         }
        return 0;

    }
    
    public int parseTotalParticipants(String jsonText) throws IOException{
        JSONParser a= new JSONParser();
        Map<String, Object> objt = a.parseJSON(new CharArrayReader(jsonText.toCharArray()));
         List<Map<String,Object>> list = (List<Map<String,Object>>)objt.get("root");
         for(Map<String,Object> obj : list){
         return (int) Float.parseFloat(obj.get("totalP").toString());
         }
          return 0;
    }
    
    public ArrayList<Event> getEvents() {
        String url = Statics.BASE_URL + "/event/findAllJson";
        req.removeAllArguments();
        req.setUrl(url);
        req.setPost(false);
        InfiniteProgress prog = new InfiniteProgress();
        Dialog d = prog.showInfiniteBlocking();
        req.setDisposeOnCompletion(d);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    events = parseEvents(new String(req.getResponseData()));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return events;

    }
  
    public ArrayList<Event> parseEvents(String jsonText) throws IOException {
        
         events = new ArrayList<>();
        JSONParser j = new JSONParser();
        Map<String, Object> listEvents = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
        List<Map<String,Object>> list = (List<Map<String,Object>>)listEvents.get("root");
        
        for(Map<String,Object> obj : list){
             int id = (int) Float.parseFloat(obj.get("id").toString());

        Event e = new Event();
        e.setId(id);

         Map<String,Object> date = (Map<String,Object>)obj.get("Date");
        e.setDate(new Date((long)Float.parseFloat(date.get("timestamp").toString())*1000));

        e.setDescription(obj.get("Description").toString());

        e.setImage(obj.get("Image").toString());

        e.setLocation(obj.get("Location").toString());

        e.setMaxP((int) Float.parseFloat(obj.get("MaxParticipants").toString()));

        e.setName(obj.get("Name").toString());

        e.setPayant(Boolean.valueOf(obj.get("Payant").toString()));

        e.setPrix(Float.parseFloat(obj.get("Prix").toString()));

        e.setStatus(obj.get("Status").toString());

        e.setType(obj.get("Type").toString());
        
        events.add(e);
            
        }
       
        return events;

        
    }

    public Event parseEvent(String jsonText) throws IOException {
        event = new Event();
        JSONParser j = new JSONParser();
        Map<String, Object> obj = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
        int id = (int) Float.parseFloat(obj.get("id").toString());

        Event e = new Event();
        e.setId(id);

         Map<String,Object> date = (Map<String,Object>)obj.get("Date");
        e.setDate(new Date((long)Float.parseFloat(date.get("timestamp").toString())*1000));

        e.setDescription(obj.get("Description").toString());

        e.setImage(obj.get("Image").toString());

        e.setLocation(obj.get("Location").toString());

        e.setMaxP((int) Float.parseFloat(obj.get("MaxParticipants").toString()));

        e.setName(obj.get("Name").toString());

        e.setPayant(Boolean.valueOf(obj.get("Payant").toString()));

        e.setPrix(Float.parseFloat(obj.get("Prix").toString()));

        e.setStatus(obj.get("Status").toString());

        e.setType(obj.get("Type").toString());
        return e;
    }

}
