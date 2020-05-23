package com.BillingApp.Controller;


import com.BillingApp.Main;
import com.BillingApp.Model.Admin;
import com.BillingApp.Model.Booking;
import com.BillingApp.Model.Seat;
import com.BillingApp.Services.AdminService;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

public class PaymentController implements Initializable {
    @FXML private ImageView seatView;
    @FXML private ChoiceBox<Seat> seats;
    @FXML private ChoiceBox<String> hours;
    @FXML private DatePicker datePicker;
    @FXML private TextField cardText;
    @FXML private Label errorLabel;
    private Seat selectedSeat;
    private List<Seat> availableSeats=new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Seat> unavailableSeats= new ArrayList<>();
        Image image= new Image("/seats.JPG");
        seatView.setImage(image);
        hours.getItems().setAll(ClientSelectMovieController.getSelectedMovie().getTimes());
        hours.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                seats.getItems().clear();
                availableSeats.clear();
                unavailableSeats.clear();
                availableSeats.addAll(ClientSelectMovieController.getSelectedMovie().getSeats());
                for(Booking booking : ClientModeController.getSelectedCinema().getBookings()){
                    if(hours.getSelectionModel().getSelectedItem().equals(booking.getHour())&&booking.getDate().equals(datePicker.getValue().toString()))
                        unavailableSeats.add(booking.getSeat());
                    for(Seat seat : unavailableSeats)
                        System.out.println(seat);;
                }
                for(Iterator<Seat> iterator= availableSeats.iterator();iterator.hasNext();) {
                    Seat seat = iterator.next();
                    for (Seat seat1 : unavailableSeats)
                        if (seat.equals(seat1))
                            iterator.remove();
                }
                    seats.getItems().addAll(availableSeats);

            }
        });

        seats.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                selectedSeat = seats.getSelectionModel().getSelectedItem();
            }
        });

    }

    public void onBookClick(ActionEvent event){
        if(!(cardText.getText().length()==12)){
            errorLabel.setText("Not a valid card");
            return;
        }
        Booking booking = new Booking(ClientSelectMovieController.getSelectedMovie().getName(),datePicker.getValue().toString(),hours.getSelectionModel().getSelectedItem(),selectedSeat,ClientSelectMovieController.getSelectedMovie().getPrice());
        ClientModeController.getSelectedCinema().addBooking(booking);
        AdminService.persistAdmins();
        errorLabel.setText("Booking successful!");


    }

}

