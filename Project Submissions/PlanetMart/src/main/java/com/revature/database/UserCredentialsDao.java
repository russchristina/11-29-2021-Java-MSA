package com.revature.database;

import com.revature.database.exceptions.DuplicateUsernameException;
import com.revature.database.exceptions.EmptyUserCredentialDataException;
import com.revature.database.exceptions.IncorrectAccountCredentialsException;

public class UserCredentialsDao {

    public boolean userCredentialCheck(String username, String password) throws IncorrectAccountCredentialsException, EmptyUserCredentialDataException {
        if(usernameCheck(username) && passwordCheck(password)) return true;
        throw new IncorrectAccountCredentialsException();
    }

    public boolean passwordCheck(String password) throws EmptyUserCredentialDataException {
        if(DummyCustomerData.passwords.isEmpty()) throw new EmptyUserCredentialDataException();
        for(String s : DummyCustomerData.passwords) if(s.contentEquals(password)) return true;
        return false;
    }

    public boolean usernameCheck(String username) throws EmptyUserCredentialDataException {
        if(DummyCustomerData.usernames.isEmpty()) throw new EmptyUserCredentialDataException();
        for(String s : DummyCustomerData.usernames) if(s.contentEquals(username)) return true;
        return false;
    }

    public boolean usernameDuplicateCheck(String newUser, String password) throws DuplicateUsernameException {
        if(DummyCustomerData.usernames.contains(newUser)) throw new DuplicateUsernameException("Duplicate username");

        if(addNewAccount(newUser, password)) return true;
        return false;
    }

    public boolean addNewAccount(String newUser, String password) {
        if(DummyCustomerData.usernames.add(newUser) && DummyCustomerData.passwords.add(password)) return true;
        return false;
    }
}
