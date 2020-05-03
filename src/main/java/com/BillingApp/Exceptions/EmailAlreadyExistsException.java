package com.BillingApp.Exceptions;

public class EmailAlreadyExistsException extends Exception {
    private String email;

    public EmailAlreadyExistsException(String email) {
        super(String.format("An account with this email already exists!"));
        this.email = email;

    }
}
