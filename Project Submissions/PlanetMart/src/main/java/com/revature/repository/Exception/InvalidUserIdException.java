package com.revature.repository.Exception;

public class InvalidUserIdException extends Exception{
    public InvalidUserIdException() {
    }

    public InvalidUserIdException(String message) {
        super(message);
    }
}
