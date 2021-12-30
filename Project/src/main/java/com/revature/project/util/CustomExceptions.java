package com.revature.project.util;

public class CustomExceptions extends Throwable {

    public void throwException(int value) throws ZeroException{
        if (value < 0){
            throw new ZeroException();
        }
    }
}

class ZeroException extends RuntimeException{
    public ZeroException(){

    }
}
