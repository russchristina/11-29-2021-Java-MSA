package com.revature.repository.Exception;

public class DuplicateInventoryIdException extends RuntimeException{
    public DuplicateInventoryIdException() {
    }

    public DuplicateInventoryIdException(String message) {
        super(message);
    }
}
