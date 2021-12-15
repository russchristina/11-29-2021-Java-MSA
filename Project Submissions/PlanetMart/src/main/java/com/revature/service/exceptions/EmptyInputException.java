package com.revature.service.exceptions;

public class EmptyInputException extends Exception{

    public EmptyInputException(){
        super();
    }

    public EmptyInputException(String message) {
        super(message);
    }

}
