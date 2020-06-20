package com.BillingApp.Controller;

import com.BillingApp.Exceptions.EmailAlreadyExistsException;
import com.BillingApp.Model.Admin;
import com.BillingApp.Model.Client;
import com.BillingApp.Services.AdminService;
import com.BillingApp.Services.ClientService;
import com.BillingApp.Services.FileService;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import java.io.IOException;

import static org.junit.Assert.*;

public class LoginControllerTest extends ApplicationTest {

    public static final String TEST_USER = "testUser";
    public static final String TEST_PASSWORD = "testPassword";
    private LoginController controller;
    ActionEvent event = new ActionEvent();

    @BeforeClass
    public static void setupClass() throws Exception {
        FileService.APP_FOLDER = ".BillingApp";
        FileService.getPathToFile("config","admins.json");
        FileService.getPathToFile("config","clients.json");
    }

    @Before
    public void setUp() throws Exception {
        AdminService.loadAdmins();
        ClientService.loadClients();;
        controller = new LoginController();
        controller.email = new javafx.scene.control.TextField();
        controller.password = new PasswordField();
        controller.message = new javafx.scene.control.Label();
        controller.button= new Button();
        controller.password.setText(TEST_PASSWORD);
        controller.email.setText(TEST_USER);
    }

    @Test
    public void NotAdminTest() throws IOException {
        controller.onLoginClick(event);
        boolean isValidUser = AdminService.getAdminList().contains(new Admin("","", TEST_USER, TEST_PASSWORD));
        assertFalse(isValidUser);
        assertEquals("Incorrect password or e-mail", controller.message.getText());
    }

    @Test
    public void NotClientTest() throws IOException {
        ActionEvent event = new ActionEvent();
        controller.onLoginClick(event);
        boolean isValidUser = ClientService.getClientList().contains(new Client("",0, TEST_USER, TEST_PASSWORD));
        assertFalse(isValidUser);
        assertEquals("Incorrect password or e-mail", controller.message.getText());
    }

    @Test
    public void onBackClickTest() throws IOException {
        try {
            controller.onBackClick(event);
            fail("Should have thrown an exception");
        } catch (final RuntimeException e) {
            assertTrue(true);
        }
    }

    @Test(expected = NullPointerException.class)
    public void isAdminTest() throws EmailAlreadyExistsException, IOException {
        Admin ad = new Admin("","", "testAdmin@mail.com", "password123");
        AdminService.getAdminList().add(ad);
        controller.password.setText("password123");
        controller.email.setText("testAdmin@mail.com");
        controller.onLoginClick(event);
        boolean isValidUser = AdminService.getAdminList().contains(ad);
        assertTrue(isValidUser);
    }
}