package com.BillingApp.Controller;

import com.BillingApp.Model.Booking;
import com.BillingApp.Model.Seat;
import com.BillingApp.Services.SendEmail;
import javafx.event.ActionEvent;
import javafx.scene.control.TableView;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import javax.mail.*;
import java.io.IOException;
import java.util.Properties;

import static org.junit.Assert.*;

public class AdminSeeRequestsControllerTest extends ApplicationTest {
    private AdminSeeRequestsController controller = new AdminSeeRequestsController();
    ActionEvent event = new ActionEvent();
    private SendEmail mailSender = new SendEmail();

    @Before
    public void setUp(){
        Seat seat = new Seat('A',6);
        controller.bookings = new TableView<Booking>();
        Booking booking = new Booking("@mail.com","Matrix","24.06","13:00",seat,30.0,false);
        controller.bookings.getItems().addAll(booking);
        controller.bookings.getSelectionModel().selectFirst();
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

    @Test(expected = IllegalStateException.class)
    public void onSendClick() throws MessagingException, IOException {
        String body = "Test Message1";
        mailSender.sendEmail("proiectfis.2020@gmail.com", "no-ticket", body, null);
        Session session = Session.getDefaultInstance(new Properties());
        Store store = session.getStore("imaps");
        store.connect("smtp.gmail.com", "proiectfis.2020@gmail.com", "proiectcinema");
        Folder folder = store.getFolder("inbox");
        folder.open(Folder.READ_ONLY);
        Message[] msg = folder.getMessages();
        //assertEquals(1, msg.length);
        assertEquals("Booking confirmation", msg[msg.length-1].getSubject());
        assertEquals(body.length()+2, msg[msg.length-1].getContent().toString().length());
        folder.close(true);
        store.close();
    }
}