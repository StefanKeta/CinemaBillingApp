package com.BillingApp.Controller;

import com.BillingApp.Model.Admin;
import com.BillingApp.Services.AdminService;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.SingleSelectionModel;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ClientModeController implements Initializable {
    @FXML private ChoiceBox<String> city;
    @FXML private ChoiceBox<String> cinema;

    private List<Admin> admins= new ArrayList<Admin>();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            AdminService.loadAdmins();
        } catch (IOException e) {
            e.printStackTrace();
        }
        admins.addAll(AdminService.getAdminList());
        ObservableList<String> cities = FXCollections.observableArrayList();
        for(Admin admin:admins){
            if(!cities.contains(admin.getCity())) {
                cities.add(admin.getCity());
            }
        }
        city.getItems().addAll(cities);
        city.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                cinema.getItems().clear();
                for(Admin admin : admins){
                    if(admin.getCity().equals(city.getSelectionModel().getSelectedItem())) {
                        cinema.getItems().add(admin.getCinemaName());
                    }
                }
            }
        });
    }

    public void onSelectClick(){
        //Redirects to Movie selection screen
    }

}
