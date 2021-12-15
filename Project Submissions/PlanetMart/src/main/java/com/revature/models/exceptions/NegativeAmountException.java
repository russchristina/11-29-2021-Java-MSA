package com.revature.models.exceptions;

public class NegativeAmountException extends Exception {
    public NegativeAmountException() {
    }

    public NegativeAmountException(String message) {
        super(message);
    }
}
