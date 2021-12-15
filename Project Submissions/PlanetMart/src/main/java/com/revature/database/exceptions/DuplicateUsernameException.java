package com.revature.database.exceptions;

public class DuplicateUsernameException extends Exception {
    public DuplicateUsernameException() {
    }

    public DuplicateUsernameException(String message) {
        super(message);
    }
}
