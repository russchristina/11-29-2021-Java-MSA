package com.revature.database.exceptions;

public class IncorrectAccountCredentialsException extends RuntimeException {
    public IncorrectAccountCredentialsException() {
    }

    public IncorrectAccountCredentialsException(String message) {
        super(message);
    }
}
