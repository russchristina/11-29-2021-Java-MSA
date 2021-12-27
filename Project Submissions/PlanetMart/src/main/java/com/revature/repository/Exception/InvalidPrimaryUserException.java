package com.revature.repository.Exception;

public class InvalidPrimaryUserException extends Exception{
    public InvalidPrimaryUserException() {
    }

    public InvalidPrimaryUserException(String message) {
        super(message);
    }
}
