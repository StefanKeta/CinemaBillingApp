package com.BillingApp.Controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class MovieDetailsController implements Initializable {
    @FXML private Label title;
    @FXML private Label price;
    @FXML private Hyperlink trailer;
    @FXML private Label duration;
    @FXML private TextArea description;
    @FXML private Label fromDate;
    @FXML private Label toDate;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        title.setText(ClientSelectMovieController.getSelectedMovie().getName());
        duration.setText(ClientSelectMovieController.getSelectedMovie().getDuration());
        trailer.setText(ClientSelectMovieController.getSelectedMovie().getTrailer());
        price.setText(String.valueOf(ClientSelectMovieController.getSelectedMovie().getPrice()));
        description.setText(ClientSelectMovieController.getSelectedMovie().getDescription());
        fromDate.setText(fromDate.getText() + ClientSelectMovieController.getSelectedMovie().getStartDate());
        toDate.setText(toDate.getText()+ClientSelectMovieController.getSelectedMovie().getEndDate());
        description.setEditable(false);
        WebView webView= new WebView();
        WebEngine webEngine=webView.getEngine();
        trailer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Desktop.getDesktop().browse(new URI(trailer.getText()));
                }
                catch (IOException i){
                    i.printStackTrace();
                }
                catch (URISyntaxException i){
                    i.printStackTrace();
                }
            }
        });


    }
}
