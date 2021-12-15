package com.revature.database;

import com.revature.database.exceptions.DuplicateUsernameException;
import com.revature.database.exceptions.EmptyUserCredentialDataException;
import com.revature.database.exceptions.IncorrectAccountCredentialsException;

import static com.revature.database.DummyData.*;

public class UserCredentialsDao {

    public boolean userCredentialCheck(String username, String password) throws IncorrectAccountCredentialsException, EmptyUserCredentialDataException {
        if(usernameCheck(username) && passwordCheck(password)) return true;
        throw new IncorrectAccountCredentialsException();
    }

    public boolean passwordCheck(String password) throws EmptyUserCredentialDataException {
        if(passwords.isEmpty()) throw new EmptyUserCredentialDataException();
        for(String s : passwords) if(s.contentEquals(password)) return true;
        return false;
    }

    public boolean usernameCheck(String username) throws EmptyUserCredentialDataException {
        if(usernames.isEmpty()) throw new EmptyUserCredentialDataException();
        for(String s : usernames) if(s.contentEquals(username)) return true;
        return false;
    }

    public boolean usernameDuplicateCheck(String newUser, String password) throws DuplicateUsernameException {
        if(usernames.contains(newUser)) throw new DuplicateUsernameException("Duplicate username");

        if(addNewAccount(newUser, password)) return true;
        return false;
    }

    public boolean addNewAccount(String newUser, String password) {
        if(usernames.add(newUser) && passwords.add(password)) return true;
        return false;
    }
}
