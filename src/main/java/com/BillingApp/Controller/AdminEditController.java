package com.BillingApp.Controller;

import com.BillingApp.Exceptions.CouldNotWriteUsersException;
import com.BillingApp.Main;
import com.BillingApp.Model.Admin;
import com.BillingApp.Model.Movie;
import com.BillingApp.Services.AdminService;
import com.BillingApp.Services.FileService;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.util.List;
import java.util.ResourceBundle;

public class AdminEditController implements Initializable {
    @FXML
    private ListView<Movie> listView = new ListView<>();
    @FXML private TextField title;
    @FXML private TextField trailer;
    @FXML private TextField price;
    @FXML private TextField duration;
    @FXML private TextArea description;
    @FXML private Label movieAdded;
    @FXML private Label movieEdited;
    @FXML private DatePicker fromDate;
    @FXML private DatePicker toDate;
    @FXML private TextField times;


    private static Path MOVIE_PATH = FileService.getPathToFile("Movies", Main.getCurrentAdmin().getCinemaName() + ".json");
    private ObservableList<Movie> movies;
    private int position;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObjectMapper objectMapper = new ObjectMapper();
        if (!Files.exists(MOVIE_PATH)) {
            try {
                String aux = "[]";
                objectMapper.writeValue(MOVIE_PATH.toFile(), aux);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        try {
            movies = FXCollections.observableArrayList(
                    objectMapper.readValue(MOVIE_PATH.toFile(), new TypeReference<List<Movie>>() {
                    }));
            listView.setItems(movies);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        listView.setEditable(true);
    }


    public void onBackClick(ActionEvent event) throws IOException{
        Stage stage = (Stage)title.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/AdminMode.fxml"));
        Scene scene= new Scene(root,600,400);
        stage.setScene(scene);
        stage.show();
    }

    public void onBack(ActionEvent event) throws IOException {
        Stage stage = (Stage)title.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/AdminMode.fxml"));
        Scene scene= new Scene(root,600,400);
        stage.setScene(scene);
        stage.show();
    }

    public void onEditClick(ActionEvent event) throws IOException{
        if(!listView.getSelectionModel().isEmpty()){
            position= listView.getSelectionModel().getSelectedIndex();

        }
        Stage stage = (Stage)listView.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/EditMovie.fxml"));
        Scene scene= new Scene(root,600,400);
        stage.setScene(scene);
        stage.show();
        if(listView.getItems().isEmpty()) {
            stage.close();
        }
        if(listView.getSelectionModel().isEmpty())
            stage.close();

    }

    public void onAddClick(ActionEvent event) throws IOException{
        Stage stage = (Stage) listView.getScene().getWindow();
        Parent root= FXMLLoader.load(getClass().getResource("/AddMovie.fxml"));
        Scene scene = new Scene(root,600,400);
        stage.setScene(scene);
        stage.show();

    }

    public void onDeleteClick(ActionEvent event) throws IOException {
        listView.getItems().remove(listView.getSelectionModel().getSelectedItem());
        persistMovies();
    }

    public void onEditMovieClick(ActionEvent event) throws IOException {
        listView.getItems().get(position).setName(title.getText());
        listView.getItems().get(position).setPrice(Double.parseDouble(price.getText()));
        listView.getItems().get(position).setDuration(duration.getText());
        listView.getItems().get(position).setDescription(description.getText());
        listView.getItems().get(position).setStartDate(fromDate.toString());
        listView.getItems().get(position).setEndDate(toDate.toString());
        listView.getItems().get(position).setTimes(times.getText().split(","));
        movieEdited.setText("Movie Edited");
        persistMovies();

    }

    public void onAddMovieClick(ActionEvent event){
        Movie movie = new Movie(title.getText(),duration.getText(),description.getText(),trailer.getText(),Double.parseDouble(price.getText()),fromDate.getValue().toString(),toDate.getValue().toString(),times.getText().split(","));
        listView.getItems().add(movie);
        persistMovies();
        movieAdded.setText("Movie added");

    }

    private void persistMovies(){
        {
            try {
                ObjectMapper objectMapper= new ObjectMapper();
                objectMapper.writerWithDefaultPrettyPrinter().writeValue(MOVIE_PATH.toFile(),listView.getItems());
            }catch (IOException e){
                throw new CouldNotWriteUsersException();
            }
        }
    }

}
