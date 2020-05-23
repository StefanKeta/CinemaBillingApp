package com.BillingApp.Controller;

import com.BillingApp.Main;
import com.BillingApp.Model.Booking;
import com.BillingApp.Model.Seat;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminSeeRequestsController implements Initializable {
    @FXML private TableView<Booking> bookings;
    @FXML private TableColumn<Booking, String> movie;
    @FXML private TableColumn<Booking,String> client;
    @FXML private TableColumn<Booking,Boolean> send;
    @FXML private TableColumn<Booking,String> date;
    @FXML private TableColumn<Booking, Seat> seat;
    @FXML private TableColumn<Booking,String> hour;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        movie.setCellValueFactory(new PropertyValueFactory<>("movieName"));
        client.setCellValueFactory(new PropertyValueFactory<>("client"));
        send.setCellValueFactory(new PropertyValueFactory<>("sendViaMail"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        seat.setCellValueFactory(new PropertyValueFactory<>("seat"));
        hour.setCellValueFactory(new PropertyValueFactory<>("hour"));
        bookings.getItems().addAll(Main.getCurrentAdmin().getBookings());
    }
}
