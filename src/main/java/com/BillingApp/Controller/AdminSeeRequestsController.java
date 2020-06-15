package com.BillingApp.Controller;

import com.BillingApp.Main;
import com.BillingApp.Model.Booking;
import com.BillingApp.Model.PDF;
import com.BillingApp.Model.Seat;
import com.BillingApp.Services.SendEmail;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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

    public void onGenerateClick(ActionEvent actionEvent) throws DocumentException, IOException {
        Desktop desktop = Desktop.getDesktop();
        Booking cerere = bookings.getSelectionModel().getSelectedItem();
        String text =
                "Film: " + cerere.getMovieName() + "\n" +
                        cerere.getDate() + "\n" +
                        cerere.getHour() + '\n' +
                        cerere.getSeat() + '\n' +
                        cerere.hashCode();
        Document document = new Document();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save your PDF File");
        Stage stage = new Stage();
        File FILE = fileChooser.showSaveDialog(stage);
        if (FILE != null) {
            try {
                PdfWriter.getInstance(document, new FileOutputStream(FILE));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            document.open();
            PDF.addMetaData(document);
            PDF.addPage(document, text);
            document.close();
        }
        desktop.open(FILE);
    }

    /*public void onAcceptClick(ActionEvent event) throws  Exception{
        bookings.getItems().remove(bookings.getSelectionModel().getSelectedItem());
    }*/
}

