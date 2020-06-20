package com.BillingApp.Services;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetup;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Properties;

import static org.junit.Assert.assertEquals;

public class SendEmailTest extends ApplicationTest {

    private SendEmail mailSender = new SendEmail();

    @Test(expected = IllegalStateException.class)
    public void sendingTest() throws MessagingException, InterruptedException, IOException {
        ServerSetup setup = new ServerSetup(3025, "localhost", "smtp");
        GreenMail greenMail = new GreenMail(setup);
        greenMail.start();

        String text="Test1";
        String subject="Feedback";
        final String username = "proiectfis.2020@gmail.com";
        final String password = "proiectcinema";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.host", "localhost");
        props.put("mail.smtp.port", "3025");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        mailSender.sendEmail(username, "feedback",text,null);
        Message message = new MimeMessage(session);
        message.setSubject("Feedback");
        message.setText(text);
        message.setFrom(new InternetAddress("proiectfis.2020@gmail.com"));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("proiectfis.2020@gmail.com"));
        Transport.send(message);
        greenMail.waitForIncomingEmail(5000, 1);
        Message[] messages = greenMail.getReceivedMessages();
        assertEquals(1, messages.length);
        assertEquals(subject.length(), messages[0].getSubject().toString().length());
        assertEquals(text.length()+2,messages[0].getContent().toString().length());
        greenMail.stop();
    }
}