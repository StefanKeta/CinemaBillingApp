package com.BillingApp.Controller;

import com.BillingApp.Exceptions.EmailAlreadyExistsException;
import com.BillingApp.Main;
import com.BillingApp.Model.Admin;
import com.BillingApp.Model.Client;
import com.BillingApp.Services.AdminService;
import com.BillingApp.Services.ClientService;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private Button button;
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private Label message;

    @FXML
    public void onLoginClick(ActionEvent event) throws IOException {

        if (email.getText().isEmpty() || password.getText().isEmpty()) {
            message.setText("All fields must be filled");
            return;
        }
        for (Admin admin : AdminService.getAdminList())
            if (email.getText().equals(admin.getEmail()) && encodePassword(String.valueOf(email.getText()), password.getText()).equals(admin.getPassword())) {
                message.setText("You will be logged in as an admin");
                Main.setCurrentAdmin(admin);
                Stage stage = (Stage) email.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("/AdminMode.fxml"));
                stage.setScene(new Scene(root, 750, 500));
                stage.show();
            }
        for (Client client : ClientService.getClientList())
            if (email.getText().equals(client.getEmail()) && encodePassword(email.getText(),password.getText()).equals(client.getPassword())) {
                message.setText("You will be logged in as a client");
                Main.setCurrentClient(client);
                Stage stage = (Stage) email.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("/ClientMode.fxml"));
                stage.setScene(new Scene(root, 750, 500));
                stage.show();
            }
        message.setText("Incorrect password or e-mail");
        }

    public static String encodePassword(String salt, String password) {
        MessageDigest md = getMessageDigest();
        md.update(salt.getBytes(StandardCharsets.UTF_8));

        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));

        return new String(hashedPassword, StandardCharsets.UTF_8)
                .replace("\"", "");
    }

    private static MessageDigest getMessageDigest() {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-512 does not exist!");
        }
        return md;
    }

    @FXML
    void onBackClick(ActionEvent event) throws IOException {
        Stage stage = (Stage)button.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/RegistrationScreen.fxml"));
        Scene scene= new Scene(root,750,600);
        stage.setScene(scene);
        stage.show();
    }

    }