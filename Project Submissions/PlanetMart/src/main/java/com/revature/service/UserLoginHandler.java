package com.revature.service;

import com.revature.database.DummyData;
import com.revature.database.exceptions.FailedToCreateAccountException;
import com.revature.database.exceptions.IncorrectAccountCredentialsException;
import com.revature.service.exceptions.EmptyInputException;

import java.util.Objects;

public class UserLoginHandler {

    private String username;
    private String password;

    public UserLoginHandler(String username, String password) {
        if(username.trim().contentEquals("") || username.isEmpty()) throw new EmptyInputException("Empty Username");
        if(password.trim().contentEquals("") || password.isEmpty()) throw new EmptyInputException("Empty Password");
        this.username = username;
        this.password = password;
    }

    public UserLoginHandler() {

    }

    public String getUsername() {return username;}
    public String getPassword() {return password;}

    public void setUsername(String username) {
        if(username.trim().contentEquals("") || username.isEmpty()) throw new EmptyInputException("Empty Username");
        this.username = username;
    }

    public void setPassword(String password) {
        if(password.trim().contentEquals("") || password.isEmpty()) throw new EmptyInputException("Empty Password");
        this.password = password;
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

    @Override
    public String toString() {
        return "{\"Login\":{"
                + "\"username\":\"" + username + "\""
                + ", \"password\":\"" + password + "\""
                + "}}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserLoginHandler)) return false;
        UserLoginHandler userLoginHandler = (UserLoginHandler) o;
        return getUsername().equals(userLoginHandler.getUsername()) && getPassword().equals(userLoginHandler.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getPassword());
    }

}
