package com.revature.service;

import com.revature.database.DummyData;
import com.revature.database.exceptions.FailedToCreateAccountException;
import com.revature.database.exceptions.IncorrectAccountCredentialsException;
import com.revature.service.exceptions.EmptyStringInputException;

public class Login {

    private String username;
    private String password;

    public Login(String username, String password) {
        if(username.trim().contentEquals("")) throw new EmptyStringInputException();
        if(password.trim().contentEquals("")) throw new EmptyStringInputException();
        this.username = username;
        this.password = password;
    }

    public Login() {

    }

    public String getUsername() {
        return username;
    }


    public String getPassword() {
        return password;
    }

    public boolean authenticateAccountCredentials() {
        if(!DummyData.userCredentialCheck(username, password)) throw new IncorrectAccountCredentialsException();
        return true;
    }

    public boolean createAccount(String newUser, String password) throws FailedToCreateAccountException{
        try{
            DummyData.usernameDuplicateCheck(newUser, password);
            return true;
        }catch (FailedToCreateAccountException e){
            throw e;
        }
    }
}
