package com.revature.repository.Exception;

public class EmptyUserCredentialDataException extends RuntimeException{
    public EmptyUserCredentialDataException() {
    }

    public EmptyUserCredentialDataException(String message) {
        super(message);
    }
}
