package com.BillingApp.Controller;

import com.BillingApp.Main;
import com.BillingApp.Model.Admin;
import com.BillingApp.Model.Movie;
import com.BillingApp.Services.AdminService;
import com.BillingApp.Services.FileService;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
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
    private @FXML ListView<Movie> listView;

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

    public void onBackClick(ActionEvent event){
    }

    public void onEditClick(ActionEvent event){
    }

    public void onAddClick(ActionEvent event){

    }

    public void onDeleteClick(ActionEvent event){

    }
}
