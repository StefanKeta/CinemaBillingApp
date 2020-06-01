package com.BillingApp.Controller;

import com.BillingApp.Main;
import com.BillingApp.Model.Booking;
import com.BillingApp.Model.Seat;
import com.BillingApp.Services.AdminService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.Properties;
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

    public void onDeleteClick(ActionEvent event) throws Exception{
        String message = "Your booking has been rejected";
        Booking toDelete= bookings.getSelectionModel().getSelectedItem();
        if(!bookings.getSelectionModel().isEmpty()) {
            sendMail(toDelete.getClient(),Main.getCurrentAdmin().getEmail(),message);
            bookings.getItems().remove(bookings.getSelectionModel().getSelectedItem());
        }
        AdminService.persistAdmins();
    }

    public void onAcceptClick(ActionEvent event) throws  Exception{
        Booking toAccept = bookings.getSelectionModel().getSelectedItem();
        String ticket = toAccept.getMovieName() + "\n"+ toAccept.getDate()+"\n" +toAccept.getHour()+'\n'+toAccept.getSeat()+'\n'+ toAccept.hashCode();
        if(!toAccept.isSendViaMail()){
            sendMail(toAccept.getClient(),Main.getCurrentAdmin().getEmail(),"Hello, you can pick up your tickets at the cinema");
        }
        if(toAccept.isSendViaMail()){
            sendMail(toAccept.getClient(),Main.getCurrentAdmin().getEmail(),ticket);
        }
        bookings.getItems().remove(bookings.getSelectionModel().getSelectedItem());
    }

    private static void sendMail(String recipient,String from,String messageToSend) throws Exception{
        //TODO
        }


}

