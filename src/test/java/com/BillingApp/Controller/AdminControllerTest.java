package com.BillingApp.Controller;

import com.BillingApp.Main;
import com.BillingApp.Model.Admin;
import com.BillingApp.Services.FileService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import java.io.IOException;

import static org.junit.Assert.*;

public class AdminControllerTest extends ApplicationTest {
    private static final String name = "CINEMA";
    private AdminController controller ;
    private Parent root;

    @BeforeClass
    public static void setUpClass(){
        FileService.APP_FOLDER =".test-BillingApp";
        FileService.initApplicationHomeDirIfNeeded();
    }

    @Before
    public void setUp(){
        controller = new AdminController();
        controller.welcomeLabel= new Label();
        controller.welcomeLabel.setText(name);
    }

    @Test
    public void initializeTest(){
        assertEquals(controller.welcomeLabel.getText(),name);
    }



}