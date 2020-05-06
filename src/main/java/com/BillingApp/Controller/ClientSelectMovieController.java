package com.BillingApp.Controller;

import com.BillingApp.Model.Movie;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ClientSelectMovieController implements Initializable {
    @FXML private ListView<Movie> listView;
    @FXML private Label noMovie;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listView.getItems().addAll(ClientModeController.getSelectedCinema().getMovieList());

    }

    public void onBackClick() throws IOException {
        Stage stage= (Stage) listView.getScene().getWindow();
        Parent root= FXMLLoader.load(getClass().getResource("/ClientMode.fxml"));
        Scene scene = new Scene(root,600,400);
        stage.setScene(scene);
        stage.show();
    }

    public void onContactClick(ActionEvent event){
        //TO-DO
    }

    public void onSeeDetailsClick(ActionEvent event){
        //TO-DO
    }

    public void onBuyClick(ActionEvent event){
        //TO-DO
    }
}
