package com.BillingApp.Controller;

import com.BillingApp.Exceptions.EmailAlreadyExistsException;
import com.BillingApp.Model.Client;
import com.BillingApp.Services.AdminService;
import com.BillingApp.Services.ClientService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import jdk.nashorn.internal.ir.annotations.Ignore;

import java.io.IOException;
import java.net.URL;
import java.time.Clock;
import java.util.ResourceBundle;

public class RegistrationController implements Initializable {
    @FXML  TextField email;
    @FXML  PasswordField password;
    @FXML  ChoiceBox choiceBox;
    @FXML  TextField userDependent1;
    @FXML  TextField userDependent2;
    @FXML  Label errorLabel;


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

    public void onRegisterClick(ActionEvent event) throws IOException {
        if(email.getText().isEmpty()||password.getText().isEmpty()||userDependent1.getText().isEmpty()||userDependent2.getText().isEmpty()){
            errorLabel.setText("All credentials required");
            return;
        }

        if(!email.getText().contains("@")){
            errorLabel.setText("Please enter a valid email address");
            return;
        }

        if(password.getText().length()<8){
            errorLabel.setText("Password must be longer than 8 characters");
            return;
        }

        if(choiceBox.getSelectionModel().getSelectedItem().toString().equals("Client")){
            try{
                ClientService.addClient(email.getText(),password.getText(),Integer.parseInt(userDependent2.getText()),userDependent1.getText());
            }catch (EmailAlreadyExistsException e){
                errorLabel.setText(e.getMessage());
            }
        }

        if(choiceBox.getSelectionModel().getSelectedItem().toString().equals("Admin")){
            try{
                AdminService.addAdmin(email.getText(),password.getText(),userDependent1.getText(),userDependent2.getText());
            }catch(EmailAlreadyExistsException e){
                errorLabel.setText(e.getMessage());
            }
        }

        errorLabel.setText("Account Successfully Created!");

        Stage stage =(Stage)email.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/Login.fxml"));
        stage.setScene(new Scene(root,750,500));
        stage.show();

    }

    public void onSignInClick(ActionEvent event) throws  IOException{
        Stage stage =(Stage)email.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/Login.fxml"));
        stage.setScene(new Scene(root,750,500));
        stage.show();
    }

}
