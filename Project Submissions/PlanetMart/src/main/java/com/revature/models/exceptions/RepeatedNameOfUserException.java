package com.revature.models.exceptions;

public class RepeatedNameOfUserException extends Exception {
    public RepeatedNameOfUserException() {
    }

    public RepeatedNameOfUserException(String message) {
        super(message);
    }
}
