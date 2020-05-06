package com.BillingApp;

import com.BillingApp.Model.Admin;
import com.BillingApp.Model.Client;
import com.BillingApp.Services.AdminService;
import com.BillingApp.Services.ClientService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private static Admin currentAdmin;
    private static Client currentClient;

    @Override
    public void start(Stage primaryStage) throws Exception{
        ClientService.loadClients();
        AdminService.loadAdmins();
        Parent root= FXMLLoader.load(getClass().getResource("/RegistrationScreen.fxml"));
        primaryStage.setTitle("Cinema Billing App");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static Admin getCurrentAdmin() {
        return currentAdmin;
    }

    public static void setCurrentAdmin(Admin currentAdmin) {
        Main.currentAdmin = currentAdmin;
    }

    public static Client getCurrentClient() {
        return currentClient;
    }

    public static void setCurrentClient(Client currentClient) {
        Main.currentClient = currentClient;
    }
}

