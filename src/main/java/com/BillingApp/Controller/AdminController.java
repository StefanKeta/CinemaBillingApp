package com.BillingApp.Controller;

import com.BillingApp.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
    @FXML private Label welcomeLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        welcomeLabel.setText("Welcome, " + Main.getCurrentAdmin().getCinemaName());
    }

    @FXML
    void onLogOut(ActionEvent event) throws IOException {
        Stage stage = (Stage)welcomeLabel.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/Login.fxml"));
        Scene scene= new Scene(root,750,500);
        stage.setScene(scene);
        stage.show();
    }

    public void onEditMovies (ActionEvent event) throws IOException {
        Stage stage = (Stage)welcomeLabel.getScene().getWindow();
        Parent newWindow = FXMLLoader.load(getClass().getResource("/AdminEditMoviesScreen.fxml"));
        Scene scene= new Scene(newWindow,750,500);
        stage.setScene(scene);
        stage.show();
    }

    public void onStatistics(ActionEvent event) throws IOException {
        Stage stage = (Stage)welcomeLabel.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/Statistics.fxml"));
        Scene scene= new Scene(root,750,500);
        stage.setScene(scene);
        stage.show();
    }

    public void onBuyingRequests(ActionEvent event) throws IOException {
        Stage stage = (Stage) welcomeLabel.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/AdminSeeRequests.fxml"));
        Scene scene = new Scene(root,750,500);
        stage.setScene(scene);
        stage.show();
    }
}
