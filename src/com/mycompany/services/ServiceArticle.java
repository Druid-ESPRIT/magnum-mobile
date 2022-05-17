/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Article;
import com.mycompany.utils.Statics;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author zeineb
 */
public class ServiceArticle {
    public static ServiceArticle instance = null;

    public static boolean resultOk = true;

    private ConnectionRequest req;

    public static ServiceArticle getInstance()
    {
        if(instance == null)
            instance = new ServiceArticle();
        return instance;  

    }

    public ServiceArticle()
    {
        req = new ConnectionRequest();
    }

    public void AjouterArticle(Article A)
    {
        String url = Statics.BASE_URL+"/AjouterArticlesMobile?title="+A.getTitle()+"&content="+A.getContent()+"&url="+A.getUrl();
        req.setUrl(url);
        req.addResponseListener ((e) -> {
             
            String str = new String(req.getResponseData());
            System.out.println("data == "+str);
  
        });

        NetworkManager.getInstance().addToQueueAndWait(req);

    }

   public ArrayList<Article> AfficherArticles()
    {

        ArrayList<Article> result = new ArrayList<>();
        String url = Statics.BASE_URL+"/AfficherArticlesMobile";
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                
                JSONParser jsonp;
                jsonp = new JSONParser();
                
                try 
                {
                    Map<String,Object>mapCategorie = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String,Object>> ListOfMaps = (List<Map<String,Object>>) mapCategorie.get("root");
                    for(Map<String, Object> obj : ListOfMaps)
                    {
                        Article A = new Article();
                       
                        String title = obj.get("title").toString();
                        String content = obj.get("content").toString();
                        String url = obj.get("url").toString();

                       
                        //c.setProduit(produit.getId());
                        //c.setQuantite((int)quantite);
                         A.setId((int)Float.parseFloat(obj.get("id").toString()));
                        A.setTitle(title);
                        A.setContent(content);
                        A.setUrl(url);
                        
                        result.add(A);
                        System.out.println(A.getId()+" "+A.getTitle()+" "+A.getContent()+""+A.getUrl());
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
   
    public void SupprimerArticle(int id)
    {
        String url = Statics.BASE_URL+"/SupprimerArticlesMobile?id="+id;
        req.setUrl(url);
        req.addResponseListener ((e) -> {
             
            String str = new String(req.getResponseData());
            System.out.println("data == "+str);
  
        });

        NetworkManager.getInstance().addToQueueAndWait(req);

    }
    
    
      /*  public boolean SupprimerCategorie(int id)
    {
        String url = Statics.BASE_URL+"/SupprimerCategorieMobile?id="+id;

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
        
    public boolean ModifierCategorie(Categorie categorie)
    {
        String url = Statics.BASE_URL+"/ModifierCategorieMobile?id="+categorie.getIdcategorie()+"&namecateg="+categorie.getNamecateg()+"&Descriptioncateg="+categorie.getDescriptioncateg();
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
    
}*/

    
}
