package com.BillingApp.Controller;

import com.BillingApp.Model.Movie;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ClientSelectMovieController implements Initializable {
    @FXML private ListView<Movie> listView = new ListView<>();
    @FXML private Label noMovie;

    private static Movie selectedMovie;

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

    public void onSeeDetailsClick(ActionEvent event) throws IOException {
        if(listView.getSelectionModel().getSelectedItem()==null){
            noMovie.setText("No movie selected!");
            return;
        }
        selectedMovie=listView.getSelectionModel().getSelectedItem();
        Stage stage = new Stage();
        Parent root= FXMLLoader.load(getClass().getResource("/MovieDetailsScreen.fxml"));
        Scene scene = new Scene(root,400,400);
        stage.setScene(scene);
        stage.setTitle("Details");
        stage.show();
    }



    public static Movie getSelectedMovie() {
        return selectedMovie;
    }
}
