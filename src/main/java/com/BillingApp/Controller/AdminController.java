package com.BillingApp.Controller;

import com.BillingApp.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
    @FXML private Label welcomeLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        welcomeLabel.setText("Welcome, " + Main.getCurrentAdmin().getCinemaName());
    }

    public void onEditMovies (ActionEvent event){

    }

    public void onStatistics(ActionEvent event){

    }

    public void onBuyingRequests(ActionEvent event){

    }
}
