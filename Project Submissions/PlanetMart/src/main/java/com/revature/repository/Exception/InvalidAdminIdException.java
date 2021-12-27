package com.revature.repository.Exception;

public class InvalidAdminIdException extends Exception{
    public InvalidAdminIdException() {
    }

    public InvalidAdminIdException(String message) {
        super(message);
    }
}
