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
package com.mycompany.gui;

import com.codename1.components.MultiButton;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Event;
import com.mycompany.services.EventService;
import com.mycompany.utils.Statics;
import java.util.ArrayList;

public class EventsForm extends BaseForm {

    public ArrayList<Event> events;

    public EventService eventService = new EventService();

    public EventsForm(Resources res) {
        super("Events List", BoxLayout.yCenter());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Events List");
        getContentPane().setScrollVisible(false);

        super.addSideMenu(res);

        tb.addCommandToOverflowMenu("Add", null, ev -> {
            new addEventForm(res, 0).show();
        });
        
        

        Container list = new Container(BoxLayout.y());
        list.setScrollableY(true);
        events = eventService.getEvents();
        for (Event event : events) {
            MultiButton mb = new MultiButton(event.getName());
            EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(50, 50, 0xffff0000), true);
            Image i = URLImage.createToStorage(placeholder, event.getImage(), Statics.BASE_URL + "/uploads/" + event.getImage());
            mb.setIcon(i.fill(200, 200));
            mb.setTextLine2(event.getPrix() > 0 ? String.valueOf(event.getPrix()) + " TND" : "FREE");
            list.add(mb);
            mb.addLongPressListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    eventService.deleteEvent(event.getId());
                    new EventsForm(res).show();
                }
            });

            mb.addActionListener((evt) -> {
                new addEventForm(res, event.getId()).show();
            });

        }
        this.add(list);

    }

}
