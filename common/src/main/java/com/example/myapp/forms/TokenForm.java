
package com.example.myapp;

import static com.codename1.ui.CN.*;
import com.codename1.ui.*;
import com.codename1.ui.layouts.*;
import com.codename1.io.*;
import com.codename1.ui.plaf.*;
import com.codename1.ui.util.Resources;

import com.example.myapp.forms.*;

public class TokenForm extends Form {
    private Form resetForm = new Form("Reset your password", BoxLayout.y());
    private Form inputForm = new Form("Input your token", BoxLayout.y());
    private Form generateForm = new Form("Generate a token", BoxLayout.y());

    public void showResetForm() {
        Label passwordLabel = new Label();
        TextField password = new TextField();
        Label passwordConfirmLabel = new Label();
        TextField passwordConfirm = new TextField();
        Button resetButton = new Button("Reset");

        resetButton.addActionListener(e -> {
            LoginForm login = new LoginForm();
            login.showLoginForm();
        });

        passwordLabel.setText("New password");
        passwordConfirmLabel.setText("Confirm password");

        resetForm.add(passwordLabel);
        resetForm.add(password);
        resetForm.add(passwordConfirmLabel);
        resetForm.add(passwordConfirm);
        resetForm.add(resetButton);

        resetForm.show();
    }

    public void showInputForm() {
        Label tokenLabel = new Label();
        TextField token = new TextField();
        Button sendButton = new Button("Send");

        sendButton.addActionListener(e -> showResetForm());
        tokenLabel.setText("Have a look at your email,\nwe sent you a token!");

        inputForm.add(tokenLabel);
        inputForm.add(token);
        inputForm.add(sendButton);
        inputForm.show();
    }

    public void showGenerateForm() {
        Label title = new Label();
        TextField username = new TextField();
        Button reset = new Button("Generate");
        Button bypass = new Button("Already have a token?");

        reset.addActionListener(e -> showInputForm());
        bypass.addActionListener(e -> showInputForm());

        title.setText("Tell us your username and we'll help you fix that.");
        title.getAllStyles().setAlignment(Component.CENTER);

        generateForm.add(title);
        generateForm.add(username);
        generateForm.add(reset);
        generateForm.add(bypass);

        generateForm.show();
    }

}
