package com.revature.database.exceptions;

public class IncorrectAccountCredentialsException extends Exception {
    public IncorrectAccountCredentialsException() {
    }

    public IncorrectAccountCredentialsException(String message) {
        super(message);
    }
}
