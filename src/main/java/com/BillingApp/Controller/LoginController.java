package com.BillingApp.Controller;

import com.BillingApp.Main;
import com.BillingApp.Model.Admin;
import com.BillingApp.Model.Client;
import com.BillingApp.Services.AdminService;
import com.BillingApp.Services.ClientService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    protected Button button;
    @FXML
    protected TextField email;
    @FXML
    protected PasswordField password;
    @FXML
    protected Label message;

    @FXML
    public void onLoginClick(ActionEvent event) throws IOException {

        if (email.getText().isEmpty() || password.getText().isEmpty()) {
            message.setText("All fields must be filled");
            return;
        }
        for (Admin admin : AdminService.getAdminList())
            if (email.getText().equals(admin.getEmail()) && AdminService.encodePassword(String.valueOf(email.getText()), password.getText()).equals(admin.getPassword())) {
                message.setText("You will be logged in as an admin");
                Main.setCurrentAdmin(admin);
                Stage stage = (Stage) email.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("/AdminMode.fxml"));
                stage.setScene(new Scene(root, 750, 500));
                stage.show();
            }
        for (Client client : ClientService.getClientList())
            if (email.getText().equals(client.getEmail()) && AdminService.encodePassword(email.getText(),password.getText()).equals(client.getPassword())) {
                message.setText("You will be logged in as a client");
                Main.setCurrentClient(client);
                Stage stage = (Stage) email.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("/ClientMode.fxml"));
                stage.setScene(new Scene(root, 750, 500));
                stage.show();
            }
        message.setText("Incorrect password or e-mail");
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