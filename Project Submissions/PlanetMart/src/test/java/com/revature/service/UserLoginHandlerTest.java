package com.revature.service;

import com.revature.database.exceptions.DuplicateUsernameException;
import com.revature.database.exceptions.EmptyUserCredentialDataException;
import com.revature.database.exceptions.IncorrectAccountCredentialsException;
import com.revature.service.UserLoginHandler;
import com.revature.service.exceptions.EmptyInputException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class UserLoginHandlerTest {

    public UserLoginHandler loginBuilder(String username, String password) throws EmptyInputException {
            return new UserLoginHandler(username, password);
    }

    @Test
    void userInputConversionForUsernameTest(){
        try {
            Assertions.assertTrue(loginBuilder("user", "pass" ).getUsername() instanceof String, "The class method should always return a String");
        } catch (EmptyInputException e) {
            e.printStackTrace();
        }
    }

    @Test
    void userInputConversionForPasswordTest(){
        try {
            Assertions.assertTrue(loginBuilder("user", "pass" ).getPassword() instanceof String, "The class method should always return a String");
        } catch (EmptyInputException e) {
            e.printStackTrace();
        }
    }

    @Test
    void spaceInputUsernameStringExceptionTest(){
        Assertions.assertThrows(EmptyInputException.class, () -> loginBuilder("   ", "pass"), "An empty string username will throw an exception");
    }

    @Test
    void emptyInputUsernameStringExceptionTest(){
        Assertions.assertThrows(EmptyInputException.class, () -> loginBuilder("", "pass"), "An empty string username will throw an exception");
    }

    @Test
    void spaceInputPasswordStringExceptionTest(){
        Assertions.assertThrows(EmptyInputException.class, () -> loginBuilder("user", "    "), "An empty string password will throw an exception");
    }

    @Test
    void emptyInputPasswordStringExceptionTest(){
        Assertions.assertThrows(EmptyInputException.class, () -> loginBuilder("user", ""), "An empty string password will throw an exception");
    }

    @Test
    void incorrectCredentialExceptionTest(){
        Assertions.assertThrows(IncorrectAccountCredentialsException.class, () -> loginBuilder("not", "real").authenticateAccountCredentials(),
                "An incorrect username or password will throw an exception");
    }

    @Test
    void correctCredentialLoginTest() {
        try {
            Assertions.assertTrue(loginBuilder("user1", "pass1").authenticateAccountCredentials());
        } catch (IncorrectAccountCredentialsException | EmptyUserCredentialDataException | EmptyInputException e) {
            e.printStackTrace();
        }
    }

    @Test
    void failedToCreateAccountExceptionTest(){
        UserLoginHandler userLoginHandler = new UserLoginHandler();
        Assertions.assertThrows(DuplicateUsernameException.class, () -> userLoginHandler.createAccount("user1","password"));
    }

    @Test
    void successfulAccountCreationTest(){
        UserLoginHandler userLoginHandler = new UserLoginHandler();
        try {
            Assertions.assertTrue(userLoginHandler.createAccount("newUser", "pass"));
        } catch (DuplicateUsernameException e) {
            e.printStackTrace();
        }
    }



}
