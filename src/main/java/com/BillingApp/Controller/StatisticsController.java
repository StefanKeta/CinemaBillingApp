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
    @FXML
    private PieChart pieChart;
    @FXML
    private TextField noTickets;
    @FXML
    private TextField income;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
