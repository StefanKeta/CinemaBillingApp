package com.BillingApp.Controller;

import com.BillingApp.Services.SendEmail;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class ContactController extends SendEmail {

    @FXML private TextField message;
    @FXML private Button submit;
    @FXML private CheckBox option1;
    @FXML private CheckBox option2;
    @FXML private CheckBox option3;

    @FXML
    void onCheck1(ActionEvent event) {
        option2.setSelected(false);
        option3.setSelected(false);
    }

    @FXML
    void onCheck2(ActionEvent event) {
        option1.setSelected(false);
        option3.setSelected(false);
    }

    @FXML
    void onCheck3(ActionEvent event) {
        option1.setSelected(false);
        option2.setSelected(false);
    }

    @FXML
    void onSubmitClick(ActionEvent event) {
        String text = "Are you likely to recommend us? \n ";
        if (option1.isSelected())
            text+=option1.getText();
        else if (option2.isSelected())
            text+=option2.getText();
        else text+=option3.getText();
        text+="\n Comments or questions: \n" + message.getText() + "\n\n";
        sendEmail("ale.goldea@gmail.com","feedback", text,null);
        message.clear();
    }

}