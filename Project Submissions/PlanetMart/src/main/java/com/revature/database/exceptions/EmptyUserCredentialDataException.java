package com.revature.database.exceptions;

public class EmptyUserCredentialDataException extends Exception{

    public EmptyUserCredentialDataException() {
    }

    public EmptyUserCredentialDataException(String message) {
        super(message);
    }
}
