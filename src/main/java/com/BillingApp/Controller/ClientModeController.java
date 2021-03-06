package com.BillingApp.Controller;

import com.BillingApp.Main;
import com.BillingApp.Model.Admin;
import com.BillingApp.Model.Movie;
import com.BillingApp.Services.AdminService;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SingleSelectionModel;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ClientModeController implements Initializable {

    @FXML private Label welcomeLabel;
    @FXML private ChoiceBox<String> city=new ChoiceBox<>();
    @FXML private ChoiceBox<String> cinema=new ChoiceBox<>();
    @FXML private Label errorLabel;

    private List<Admin> admins= new ArrayList<Admin>();
    private List<Movie> movies = new ArrayList<Movie>();
    private static Admin selectedCinema ;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        welcomeLabel.setText("Welcome, " + Main.getCurrentClient().getName()+"!");
        AdminService.persistAdmins();
        admins.addAll(AdminService.getAdminList());
        List<String> cities=new ArrayList<>();
        List<String> cinemas= new ArrayList<>();

        for(Admin admin: admins){
            if(!cities.contains(admin.getCity()))
                cities.add(admin.getCity());
        }
        city.getItems().addAll(cities);
        city.setOnAction(e->{
            cinema.getItems().clear();
            cinemas.clear();
            for(Admin admin:admins){
                if(admin.getCity().equals(city.getSelectionModel().getSelectedItem()))
                    cinemas.add(admin.getCinemaName());
            }
            cinema.getItems().addAll(cinemas);
        });

        cinema.setOnAction(e->{
            for(Admin admin:admins){
                if(admin.getCinemaName().equals(cinema.getSelectionModel().getSelectedItem()))
                    selectedCinema=admin;
            }
        });
    }


    public void onSelectClick() throws IOException{
        if(city.getSelectionModel().getSelectedItem()==null || cinema.getSelectionModel().getSelectedItem()==null){
            errorLabel.setText("Please select both the city and the cinema!");
            return;
        }
        if(selectedCinema.getMovieList().isEmpty()){
            errorLabel.setText("The selected cinema has no movies available!");
            return;
        }
        Stage stage= (Stage) welcomeLabel.getScene().getWindow();

        Parent root= FXMLLoader.load(getClass().getResource("/ClientSelectMovieScreen.fxml"));
        Scene scene= new Scene(root,750,500);
        stage.setScene(scene);
        stage.show();
    }

    public void onLogOut() throws IOException{
        Stage stage= (Stage) city.getScene().getWindow();
        Parent root=FXMLLoader.load(getClass().getResource("/Login.fxml"));
        Scene scene = new Scene(root,750,500);
        stage.setScene(scene);
        stage.show();

    }

    public static Admin getSelectedCinema() {
        return selectedCinema;
    }

    public static void setSelectedCinema(Admin selectedCinema) {
        ClientModeController.selectedCinema = selectedCinema;
    }
}
