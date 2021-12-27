package com.revature.repository.Exception;

public class NoPlanetFoundException extends Exception{
    public NoPlanetFoundException() {
    }

    public NoPlanetFoundException(String message) {
        super(message);
    }
}
