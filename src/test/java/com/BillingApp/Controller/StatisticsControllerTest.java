package com.BillingApp.Controller;

import com.BillingApp.Model.Admin;
import com.BillingApp.Model.Booking;
import com.BillingApp.Model.Movie;
import com.BillingApp.Services.AdminService;
import com.BillingApp.Services.ClientService;
import com.BillingApp.Services.FileService;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TextField;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;

import static org.junit.Assert.*;

public class StatisticsControllerTest extends ApplicationTest {

    private StatisticsController controller;


    @BeforeClass
    public static void setUpClass() throws IOException {
        FileService.APP_FOLDER = ".test-BillingApp";
        FileService.initApplicationHomeDirIfNeeded();
        ClientService.loadClients();
        AdminService.loadAdmins();
    }

    @Before
    public void setUp(){

        controller = new StatisticsController();
        controller.income = new TextField();
        controller.noTickets = new TextField();
        controller.pieChart = new PieChart();


    }

    @Test
    public void testReadBookings(){
        assertNotNull(AdminService.getAdminList().get(0).getBookings());
    }


    @Test
    public void pieChartTest(){
        Movie movie2 = AdminService.getAdminList().get(0).getMovieList().get(0);
        AdminService.getAdminList().get(0).addMovie(movie2);
        Booking booking = AdminService.getAdminList().get(0).getBookings().get(0);
        assertNotNull(AdminService.getAdminList().get(0).getMovieList());
        assertNotEquals(booking.getPrice(),0);
    }




}