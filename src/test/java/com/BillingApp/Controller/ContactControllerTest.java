package com.BillingApp.Controller;

import com.BillingApp.Services.SendEmail;
import javafx.event.ActionEvent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import javax.mail.*;
import java.io.IOException;
import java.util.Properties;

import static org.junit.Assert.*;

public class ContactControllerTest extends ApplicationTest {

    private SendEmail mailSender = new SendEmail();
    private ContactController controller = new ContactController();
    ActionEvent event = new ActionEvent();

    @Before
    public void setUp() throws Exception {
        controller.message= new TextArea();
        controller.option1= new CheckBox();
        controller.option2= new CheckBox();
        controller.option3= new CheckBox();
    }

    @Test
    public void onCheck1Test(){
        controller.onCheck1(event);
        assertFalse(controller.option2.isSelected());
        assertFalse(controller.option3.isSelected());
    }

    @Test
    public void onCheck2Test(){
        controller.onCheck2(event);
        assertFalse(controller.option1.isSelected());
        assertFalse(controller.option3.isSelected());
    }

    @Test
    public void onCheck3Test(){
        controller.onCheck3(event);
        assertFalse(controller.option2.isSelected());
        assertFalse(controller.option1.isSelected());
    }


    @Test (expected = IllegalStateException.class)
    public void onSubmitClickTest() throws MessagingException, IOException {
        String body = "Test Message1";
        mailSender.sendEmail("proiectfis.2020@gmail.com", "Feedback", body, null);
        Session session = Session.getDefaultInstance(new Properties());
        Store store = session.getStore("imaps");
        store.connect("smtp.gmail.com", "proiectfis.2020@gmail.com", "proiectcinema");
        Folder folder = store.getFolder("inbox");
        folder.open(Folder.READ_ONLY);
        Message[] msg = folder.getMessages();
        //assertEquals(1, msg.length);
        assertEquals("Feedback from client", msg[msg.length-1].getSubject());
        assertEquals(body.length()+2, msg[msg.length-1].getContent().toString().length());
        folder.close(true);
        store.close();
    }
}