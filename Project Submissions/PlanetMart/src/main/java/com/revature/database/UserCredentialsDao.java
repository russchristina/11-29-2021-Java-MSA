package com.revature.database;

import com.revature.database.exceptions.DuplicateUsernameException;
import com.revature.database.exceptions.EmptyUserCredentialDataException;
import com.revature.database.exceptions.IncorrectAccountCredentialsException;
import com.revature.models.exceptions.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class UserCredentialsDao {
    private final Logger log = LoggerFactory.getLogger(UserCredentialsDao.class);

    public boolean userCredentialCheck(String username, String password) throws EmptyUserCredentialDataException {
        try {
            if(userCheck(username, password) != null) return true;
        } catch (UserNotFoundException e) {
            log.debug(e.toString());
            System.out.println("\nUser not found. Please try again.\n");
            return false;
        }
        return false;
    }



    public String userCheck(String username, String password) throws EmptyUserCredentialDataException, UserNotFoundException {
        if(DummyCustomerData.userCredentials.isEmpty()) throw new EmptyUserCredentialDataException();

        if(DummyCustomerData.userCredentials.containsKey(username)){
            if(DummyCustomerData.userCredentials.get(username).contentEquals(password)) return username;
        }

        throw new UserNotFoundException();
    }

    public boolean usernameDuplicateCheck(String newUser, String password) throws DuplicateUsernameException {
        if(DummyCustomerData.userCredentials.containsKey(newUser)) throw new DuplicateUsernameException("Duplicate username");

        addNewAccount(newUser, password);
        return true;
    }

    public void addNewAccount(String newUser, String password) {
        DummyCustomerData.userCredentials.put(newUser, password);
    }
}
