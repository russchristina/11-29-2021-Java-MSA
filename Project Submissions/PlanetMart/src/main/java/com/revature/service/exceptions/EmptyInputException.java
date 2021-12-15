package com.revature.service.exceptions;

public class EmptyInputException extends RuntimeException{

    public EmptyInputException(){
        super();
    }

    public EmptyInputException(String message) {
        super(message);
    }

}
