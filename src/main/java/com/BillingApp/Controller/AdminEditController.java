package com.BillingApp.Controller;

import com.BillingApp.Main;
import com.BillingApp.Model.Admin;
import com.BillingApp.Model.Movie;
import com.BillingApp.Services.AdminService;
import com.BillingApp.Services.FileService;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.lang.ref.Reference;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class AdminEditController implements Initializable {
     @FXML private ListView<Movie> listView;
     @FXML private TextField title;
     @FXML private TextField trailer;
     @FXML private TextField price;
     @FXML private TextField duration;
     @FXML private TextArea description;
     @FXML private Label movieAdded;
     @FXML private Label movieEdited;

    private List<Movie> movies= new ArrayList<>();
    private  static  Path MOVIE_PATH = FileService.getPathToFile("Movies", Main.getCurrentAdmin().getCinemaName()+".json");

    @Override
    public void initialize(URL location, ResourceBundle resources)  {
        if(!Files.exists(MOVIE_PATH)){
            try {
                FileUtils.copyURLToFile(Objects.requireNonNull(AdminService.class.getClassLoader().getResource("admins.json")),MOVIE_PATH.toFile());
                ObjectMapper aux= new ObjectMapper();
                aux.writerWithDefaultPrettyPrinter().writeValue(MOVIE_PATH.toFile(),"[]");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        ObjectMapper objectMapper= new ObjectMapper();
        try {
            movies= objectMapper.readValue(MOVIE_PATH.toFile(), new TypeReference<List<Movie>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onBackClick(ActionEvent event) throws IOException{
        Stage stage = (Stage)listView.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/AdminMode.fxml"));
        Scene scene= new Scene(root,600,400);
        stage.setScene(scene);
        stage.show();
    }

    public void onEditClick(ActionEvent event) throws IOException{
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/EditMovie.fxml"));
        Scene scene= new Scene(root,600,400);
        stage.setScene(scene);
        stage.show();
    }

    public void onAddClick(ActionEvent event) throws IOException{
        Stage stage = new Stage();
        Parent root= FXMLLoader.load(getClass().getResource("/AddMovie.fxml"));
        Scene scene = new Scene(root,600,400);
        stage.setScene(scene);
        stage.show();

    }

    public void onDeleteClick(ActionEvent event){

    }

    public void onEditMovieClick(ActionEvent event){
        movieEdited.setText("Movie Edited");
    }

    public void onAddMovieClick(ActionEvent event){
        movieAdded.setText("Movie Added");
    }

}
