package com.BillingApp.Controller;

import com.BillingApp.Model.Admin;
import com.BillingApp.Model.Booking;
import com.BillingApp.Model.Movie;
import com.BillingApp.Services.AdminService;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TextField;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.List;

import static com.BillingApp.Main.getCurrentAdmin;

public class StatisticsController implements Initializable {
    @FXML PieChart pieChart;
    @FXML TextField noTickets;
    @FXML TextField income;

    int count=0;
    double sum=0;

     List<String> names = new ArrayList<>();

    @FXML
    void onBackClick(ActionEvent event) throws IOException {
        Stage stage = (Stage)income.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/AdminMode.fxml"));
        Scene scene= new Scene(root,750,500);
        stage.setScene(scene);
        stage.show();
    }

    public void readBookings() throws FileNotFoundException{
            for (Admin admin : AdminService.getAdminList())
                for (Booking book: admin.getBookings()) {
                    names.add(book.getMovieName());
                }
    }

    public void doPieChart() throws FileNotFoundException {
        count=0;
        sum=0;
        readBookings();
        for (Movie x: getCurrentAdmin().getMovieList()){
            PieChart.Data slice = new PieChart.Data(x.getName(), Collections.frequency(names, x.getName()));
            pieChart.getData().add(slice);
            count+= Collections.frequency(names,x.getName());
        }
        for (Booking book: getCurrentAdmin().getBookings()) {
            sum+=book.getPrice();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            doPieChart();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        pieChart.setData(pieChart.getData());
        pieChart.setVisible(true);
        noTickets.setText(String.valueOf(count));
        income.setText(String.valueOf(sum));
    }
}
