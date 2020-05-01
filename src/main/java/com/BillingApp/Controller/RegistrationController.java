package com.BillingApp.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class RegistrationController implements Initializable {
    @FXML private TextField email;
    @FXML private PasswordField password;
    @FXML private ChoiceBox choiceBox;
    @FXML private TextField userDependent1;
    @FXML private TextField userDependent2;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
            choiceBox.getItems().addAll("Client","Admin");
    }

    public void onSelectRole(ActionEvent event){
        if (choiceBox.getSelectionModel().getSelectedItem().equals("Client")){
            userDependent1.setPromptText("Name");
            userDependent2.setPromptText("Age");
        }
        else {
            userDependent1.setPromptText("City");
            userDependent2.setPromptText("Cinema Name");
        }
    }

    public void onRegisterClick(ActionEvent event){
        //TO-DO email checking and json writing
    }

    public void onSignInClick(ActionEvent event){
        //Go to the login screen (TO BE IMPLEMENTED)

    }






}
