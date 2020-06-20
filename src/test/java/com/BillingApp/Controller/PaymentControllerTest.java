package com.BillingApp.Controller;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import java.io.IOException;

import static org.junit.Assert.*;

public class PaymentControllerTest extends ApplicationTest {
    public PaymentController controller = new PaymentController();
    ActionEvent event = new ActionEvent();

    @Before
    public void setUp() throws Exception {
        controller.finalPrice= new TextField();
        controller.total= new TextField();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void onBackClick() {
        try {
            controller.onBackClick(event);
            fail("Should have thrown an exception");
        } catch (final RuntimeException | IOException e) {
            assertTrue(true);
        }
    }

    @Test
    public void discountTwentyTest() {
        controller.calculateDiscount(100,"MOVIE20");
        assertEquals(80.0, Double.parseDouble(controller.finalPrice.getText()),0);
    }

    @Test
    public void discountTwentyFiveTest() {
        controller.calculateDiscount(100,"MOVIE25");
        assertEquals(75.0, Double.parseDouble(controller.finalPrice.getText()),0);
    }

    @Test
    public void discountThirtyTest() {
        controller.calculateDiscount(100,"MOVIE30");
        assertEquals(70, Double.parseDouble(controller.finalPrice.getText()),0);
    }
}