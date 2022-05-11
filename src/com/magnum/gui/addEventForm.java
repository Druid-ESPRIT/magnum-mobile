/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */
package com.magnum.gui;

import com.codename1.capture.Capture;
import com.codename1.components.ImageViewer;
import com.codename1.components.InfiniteProgress;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Display;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.magnum.entities.Event;
import com.magnum.services.EventService;
import java.io.IOException;
import java.util.Date;


import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Image;
import com.codename1.ui.URLImage;
import com.codename1.ui.plaf.Style;
import com.magnum.utils.Statics;

public class addEventForm extends BaseForm {

    private EventService eventService = new EventService();
    private Event e = new Event();
    private String image = "";
    private Event ev;
    

    public addEventForm(Resources res, int id) {
        super("Add Event", BoxLayout.yCenter());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Add Event");
        getContentPane().setScrollVisible(false);

        //super.addSideMenu(res);
      
        if (id != 0) {
            
        tb.addCommandToOverflowMenu("Participate", null, ev -> {
            eventService.participateEvent(id);
        });
            e = eventService.getEvent(id);
            
        
        }

        Label imgName = new Label();

        Button uploadImage = new Button("Upload Image");
        Label lbl_Image = new Label();
        Button add = new Button(id == 0 ? "Create" : "Update");

        TextField name = new TextField(id == 0 ? "" : e.getName(), "Name");
        TextField description = new TextField(id == 0 ? "" : e.getDescription(), "Description");
        TextField location = new TextField(id == 0 ? "" : e.getLocation(), "Location");

        Picker TypePicker = new Picker();
        TypePicker.setType(Display.PICKER_TYPE_STRINGS);
        TypePicker.setStrings("Online", "On-site");
        TypePicker.setSelectedString(id == 0 ? "Online" : e.getType());

        Picker StatusPicker = new Picker();
        StatusPicker.setType(Display.PICKER_TYPE_STRINGS);
        StatusPicker.setStrings("FINISHED", "NOT_FINISHED");
        StatusPicker.setSelectedString(id == 0 ? "NOT_FINISHED" : e.getStatus());

        Picker datePicker = new Picker();
        datePicker.setType(Display.PICKER_TYPE_DATE);
        datePicker.setDate(id == 0 ? new Date() : e.getDate());

        TextField Maxp = new TextField(id == 0 ? "" : String.valueOf(e.getMaxP()), "Max Participants");

        TextField Price = new TextField(id == 0 ? "" : String.valueOf(e.getPrix()), "Price");
        Label l = new Label(" ");
l.setUIID("Separator");



        //ImageViewer image = new ImageViewer();
        // name,description,location,TypePicker,StatusPicker,datePicker,Maxp,Price,uploadImage,add
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                ev = new Event(name.getText(), description.getText(), TypePicker.getSelectedString(), StatusPicker.getSelectedString(), location.getText(), datePicker.getDate(), Integer.valueOf(Maxp.getText()), (Float.valueOf(Price.getText()) > 0), Float.valueOf(Price.getText()), e.getImage());

                if (id == 0) {
                    eventService.addEvent(ev);
                } else {
                    ev.setId(id);
                    ev.setImage(e.getImage());
                    eventService.updateEvent(ev);
                }
                new EventsForm(res).show();
            }

        });

        uploadImage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                MultipartRequest cr = new MultipartRequest();

                String filePath = Capture.capturePhoto(Display.getInstance().getDisplayWidth(), -1);
                String filename = filePath.substring(filePath.lastIndexOf("/") + 1, filePath.indexOf("."));
                String ext = filePath.substring(filePath.lastIndexOf("."), filePath.length());
                image = filename + "" + ext;
                
                e.setImage(image);

                cr.setUrl(Statics.BASE_URL + "/event/uploadImage");
                cr.setPost(true);
                String mime = "image/*";
                try {
                    cr.addData("file", filePath, mime);
                } catch (IOException ex) {
                    Dialog.show("Error", ex.getMessage(), "OK", null);
                }

                cr.setFilename("file", filename.concat(ext));//any unique name you want

                InfiniteProgress prog = new InfiniteProgress();
                Dialog dlg = prog.showInifiniteBlocking();
                cr.setDisposeOnCompletion(dlg);
                NetworkManager.getInstance().addToQueueAndWait(cr);
                Dialog.show("Success", "Image uploaded", "OK", null);
            }
        });
        this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
            new HomeForm(res).showBack();
        });
           
        
        addAll(l, name, description, location, TypePicker, StatusPicker, datePicker, Maxp, Price, uploadImage, add);
    }

}
