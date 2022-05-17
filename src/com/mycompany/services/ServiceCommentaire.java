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
import com.mycompany.entities.Commentaire;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ServiceCommentaire {

    public static ServiceCommentaire instance = null;

    public static boolean resultOk = true;

    private ConnectionRequest req;

    public static ServiceCommentaire getInstance() {

        if (instance == null) {
            instance = new ServiceCommentaire();
        }
        return instance;

    }

    public ServiceCommentaire() {
        req = new ConnectionRequest();
    }

    public void ajouterCommentaire(Commentaire c, int articleid) {

        String url = Statics.BASE_URL + "/AjouterCommentaireMobile?id=" + articleid + "&message=" + c.getMessage() + "";
        req.setUrl(url);
        req.addResponseListener((e) -> {

            String str = new String(req.getResponseData());
            System.out.println("data == " + str);

        });

        NetworkManager.getInstance().addToQueueAndWait(req);

    }

    public void supprimerCommentaire(int id) {

        String url = Statics.BASE_URL + "/SupprimerCommentaireMobile?id=" + id;
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String str = new String(req.getResponseData());
                System.out.println("data == " + str);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);

    }

    public ArrayList<Commentaire> afficherCommentaire(int id) {
        ArrayList<Commentaire> result = new ArrayList<>();

        String url = Statics.BASE_URL + "/AfficherCommentairesMobile?id=" + id;
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp = new JSONParser();
                try {
                    Map<String, Object> mapCategorie = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String, Object>> ListOfMaps = (List<Map<String, Object>>) mapCategorie.get("root");
                    for (Map<String, Object> obj : ListOfMaps) {

                        Map<String, Object> article = (Map<String, Object>) obj.get("articleid");
                        Map<String, Object> user = (Map<String, Object>) obj.get("userid");

                        Commentaire c = new Commentaire((int) Float.parseFloat(obj.get("id").toString()), (int) Float.parseFloat(article.get("id").toString()), (int) Float.parseFloat(user.get("id").toString()), String.valueOf(obj.get("message")));
                        result.add(c);

                    }

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);

        return result;
    }

}
