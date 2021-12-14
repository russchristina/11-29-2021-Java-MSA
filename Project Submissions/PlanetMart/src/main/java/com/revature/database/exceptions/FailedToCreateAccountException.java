package com.revature.database.exceptions;

public class FailedToCreateAccountException extends Throwable {
    public FailedToCreateAccountException() {
    }

    public FailedToCreateAccountException(String message) {
        super(message);
    }
}
