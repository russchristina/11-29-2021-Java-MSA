package com.revature.service.exceptions;

public class EmptyStringInputException extends RuntimeException{

    public EmptyStringInputException(){
        super();
    }

    public EmptyStringInputException(String message) {
        super(message);
    }

}
