package com.revature.service.login;


import com.revature.service.exceptions.EmptyInputException;
import com.revature.database.UserCredentialsDao;
import com.revature.database.exceptions.DuplicateUsernameException;
import com.revature.database.exceptions.EmptyUserCredentialDataException;
import com.revature.database.exceptions.IncorrectAccountCredentialsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class UserLoginHandler {

    private final Logger log = LoggerFactory.getLogger(UserLoginHandler.class);

    private String username;
    private String password;
    private UserCredentialsDao userCredentialsDao = new UserCredentialsDao();

    public UserLoginHandler(String username, String password) throws EmptyInputException {
        if(username.trim().contentEquals("") || username.isEmpty()) throw new EmptyInputException("Empty Username");
        if(password.trim().contentEquals("") || password.isEmpty()) throw new EmptyInputException("Empty Password");
        this.username = username;
        this.password = password;
    }

    public UserLoginHandler(){ }

    public String getUsername() {return username;}
    public String getPassword() {return password;}

    public void setUsername(String username) throws EmptyInputException {
        if(username.trim().contentEquals("") || username.isEmpty()) throw new EmptyInputException("Empty Username");
        this.username = username;
    }

    public void setPassword(String password) throws EmptyInputException {
        if(password.trim().contentEquals("") || password.isEmpty()) throw new EmptyInputException("Empty Password");
        this.password = password;
    }

    public boolean authenticateAccountCredentials() throws IncorrectAccountCredentialsException, EmptyUserCredentialDataException {
        if(!userCredentialsDao.userCredentialCheck(username, password)) throw new IncorrectAccountCredentialsException();
        return true;
    }

    public boolean createAccount(String newUser, String password) throws DuplicateUsernameException {
        if(userCredentialsDao.usernameDuplicateCheck(newUser, password)) return true;
        else throw new DuplicateUsernameException("Duplicate Username");
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
