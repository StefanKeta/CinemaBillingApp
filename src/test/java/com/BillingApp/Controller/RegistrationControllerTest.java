package com.BillingApp.Controller;

import com.BillingApp.Services.AdminService;
import com.BillingApp.Services.ClientService;
import com.BillingApp.Services.FileService;
import javafx.event.ActionEvent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import sun.tools.jar.CommandLine;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

public class RegistrationControllerTest extends ApplicationTest {

    private RegistrationController controller;
    public static final String CLIENT_EMAIL = "test@mail.com";
    public static final String ADMIN_EMAIL = "test2@mail.com";
    public static final String PASSWORD = "password123";

    @BeforeClass
    public static void setUpClass() throws IOException {
        FileService.APP_FOLDER=".test-BillingApp";
        FileService.initApplicationHomeDirIfNeeded();
        AdminService.loadAdmins();
        ClientService.loadClients();
    }

    @Before
    public void setUp() throws IOException {
        FileUtils.cleanDirectory(FileService.getApplicationHomePath().toFile());
        ClientService.loadClients();
        AdminService.loadAdmins();

        controller= new RegistrationController();
        controller.email = new TextField();
        controller.password=new PasswordField();
        controller.errorLabel= new Label();
        controller.choiceBox = new ChoiceBox();
        controller.userDependent2 = new TextField();
        controller.userDependent1 = new TextField();
        controller.password.setText(PASSWORD);

    }

    @Test
    public void testAddClients() throws IOException {
        controller.choiceBox.setValue("Client");
        controller.email.setText(CLIENT_EMAIL);
        controller.userDependent1.setText("NAME");
        controller.userDependent2.setText("20");
        controller.onRegisterClick(new ActionEvent());
        assertEquals(1, ClientService.getClientList().size());
        assertEquals("Account Successfully Created!", controller.errorLabel.getText());
    }
    @Test
    public void testAddAdmins() throws IOException {
        controller.choiceBox.setValue("Admin");
        controller.userDependent1.setText("CITY");
        controller.userDependent2.setText("CINEMA NAME");
        controller.email.setText(ADMIN_EMAIL);
        controller.onRegisterClick(new ActionEvent());
        assertEquals(1, AdminService.getAdminList().size());
        assertEquals("Account Successfully Created!", controller.errorLabel.getText());
    }


}