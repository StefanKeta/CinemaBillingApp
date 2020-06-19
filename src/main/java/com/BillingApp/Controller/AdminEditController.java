package com.BillingApp.Controller;

import com.BillingApp.Exceptions.CouldNotWriteUsersException;
import com.BillingApp.Main;
import com.BillingApp.Model.Admin;
import com.BillingApp.Model.Movie;
import com.BillingApp.Model.Seat;
import com.BillingApp.Services.AdminService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class AdminEditController implements Initializable {
    @FXML  ListView<Movie> listView = new ListView<>();
    @FXML  TextField title;
    @FXML  TextField trailer;
    @FXML  TextField price;
    @FXML  TextField duration;
    @FXML  TextArea description;
    @FXML  Label movieAdded;
    @FXML  Label movieEdited;
    @FXML  DatePicker fromDate;
    @FXML  DatePicker toDate;
    @FXML  TextField times;
    @FXML  Label error;

    private int position;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listView.getItems().addAll(Main.getCurrentAdmin().getMovieList());
    }


    public void onBackClick(ActionEvent event) throws IOException{
        Stage stage = (Stage)title.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/AdminEditMoviesScreen.fxml"));
        Scene scene= new Scene(root,750,500);
        stage.setScene(scene);
        stage.show();
    }

    public void onBack(ActionEvent event) throws IOException {
        Stage stage = (Stage)listView.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/AdminMode.fxml"));
        Scene scene= new Scene(root,750,500);
        stage.setScene(scene);
        stage.show();
    }

    public void onEditClick(ActionEvent event) throws IOException{
        if(!listView.getSelectionModel().isEmpty()){
            position= listView.getSelectionModel().getSelectedIndex();

        }
        if(listView.getItems().isEmpty()) {
            error.setText("No movie available");
            return;
        }
        if(listView.getSelectionModel().isEmpty()) {
            error.setText("No movie selected");
            return;
        }
        Stage stage = (Stage)listView.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/EditMovie.fxml"));
        Scene scene= new Scene(root,750,500);
        stage.setScene(scene);
        stage.show();

    }

    public void onAddClick(ActionEvent event) throws IOException{
        Stage stage = (Stage) listView.getScene().getWindow();
        Parent root= FXMLLoader.load(getClass().getResource("/AddMovie.fxml"));
        Scene scene = new Scene(root,750,500);
        stage.setScene(scene);
        stage.show();

    }

    public void onDeleteClick(ActionEvent event) throws IOException {
        listView.getItems().remove(listView.getSelectionModel().getSelectedItem());
        Main.getCurrentAdmin().setMovieList(listView.getItems());
        AdminService.persistAdmins();
    }

    public void onEditMovieClick(ActionEvent event) throws IOException {
        listView.getItems().get(position).setName(title.getText());
        listView.getItems().get(position).setPrice(Double.parseDouble(price.getText()));
        listView.getItems().get(position).setDuration(duration.getText());
        listView.getItems().get(position).setDescription(description.getText());
        listView.getItems().get(position).setStartDate(fromDate.getValue().toString());
        listView.getItems().get(position).setEndDate(toDate.getValue().toString());
        listView.getItems().get(position).setTimes(Arrays.asList(times.getText().split(",")));
        Main.getCurrentAdmin().setMovieList(listView.getItems());
        movieEdited.setText("Movie Edited");
        AdminService.persistAdmins();
    }

    public void onAddMovieClick(ActionEvent event){
        Movie movie = new Movie(title.getText(),duration.getText(),description.getText(),trailer.getText(),Double.parseDouble(price.getText()),fromDate.getValue().toString(),toDate.getValue().toString(), Arrays.asList(times.getText().split(",")));
        listView.getItems().add(movie);
        Main.getCurrentAdmin().setMovieList(listView.getItems());
        AdminService.persistAdmins();
        movieAdded.setText("Movie added");

    }



}
