package com.BillingApp.Controller;


import com.BillingApp.Main;
import com.BillingApp.Model.Admin;
import com.BillingApp.Model.Booking;
import com.BillingApp.Model.Client;
import com.BillingApp.Model.Seat;
import com.BillingApp.Services.AdminService;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.net.URL;
import java.util.*;

public class PaymentController implements Initializable {
    @FXML private ImageView seatView;
    @FXML private ChoiceBox<Seat> seats;
    @FXML private ChoiceBox<String> hours;
    @FXML private DatePicker datePicker;
    @FXML private TextField cardText;
    @FXML private Label errorLabel;
    @FXML private CheckBox sendViaMail;
    @FXML private TextField total;
    @FXML private Button voucher;
    @FXML private TextField finalPrice;
    @FXML private Label badCode;

    private Seat selectedSeat;
    private List<Seat> availableSeats=new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        total.setText(String.valueOf(ClientSelectMovieController.getSelectedMovie().getPrice()));
        finalPrice.setText(total.getText());
        sendViaMail.setSelected(false);
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

    @FXML
    void onVoucherClick(ActionEvent event) {
        double price = ClientSelectMovieController.getSelectedMovie().getPrice();
        TextInputDialog cod = new TextInputDialog();
        cod.setHeaderText("Voucher Section");
        cod.getDialogPane().setContentText("Type your voucher code: ");
        cod.getDialogPane().lookupButton(ButtonType.CANCEL).setDisable(true);
        Optional<String> result = cod.showAndWait();
        TextField input = cod.getEditor();
        if (input.getText().equals("MOVIE20"))
            finalPrice.setText(String.valueOf(price - 20.0/100.0* price));
        else
        if (input.getText().equals("MOVIE25")) {
            finalPrice.setText(String.valueOf(price - 25.0/100.0* price));
        }
        else
        if (input.getText().equals("MOVIE30")){
            finalPrice.setText(String.valueOf(price - 30.0/100.0* price));
        }
        else
        if (!input.getText().equals("")) {
            badCode.setText("The voucher code used is unavailable");
        }
    }

    public void onBookClick(ActionEvent event){
        if(!(cardText.getText().length()==12)){
            errorLabel.setText("Not a valid card");
            return;
        }
        Booking booking = new Booking(Main.getCurrentClient().getEmail(),
                ClientSelectMovieController.getSelectedMovie().getName(),datePicker.getValue().toString(),
                hours.getSelectionModel().getSelectedItem(),selectedSeat,
                ClientSelectMovieController.getSelectedMovie().getPrice(),sendViaMail.isSelected());
        ClientModeController.getSelectedCinema().addBooking(booking);
        AdminService.persistAdmins();
        errorLabel.setText("Booking successful!");

    }
}

