package com.revature.repository.Exception;

public class InvalidUserCredentialException extends Exception{
    public InvalidUserCredentialException() {
    }

    public InvalidUserCredentialException(String message) {
        super(message);
    }
}
