package com.revature.models.exceptions;

public class NegativeAmountException extends RuntimeException {
    public NegativeAmountException() {
    }

    public NegativeAmountException(String message) {
        super(message);
    }
}
