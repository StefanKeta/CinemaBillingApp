package com.BillingApp.Controller;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class PaymentController implements Initializable {
    @FXML private ImageView seatView;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image image= new Image("/seats.JPG");
        seatView.setImage(image);
    }
}

