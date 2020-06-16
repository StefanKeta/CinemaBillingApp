package com.BillingApp.Controller;

import com.BillingApp.Model.Admin;
import com.BillingApp.Model.Booking;
import com.BillingApp.Model.Movie;
import com.BillingApp.Services.AdminService;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TextField;
import javafx.fxml.Initializable;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.*;
import java.util.List;

import static com.BillingApp.Main.getCurrentAdmin;

public class StatisticsController implements Initializable {
    @FXML private PieChart pieChart;
    @FXML private TextField noTickets;
    @FXML private TextField income;

    int count=0;
    double sum=0;

    public List<String> names = new ArrayList<>();

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
