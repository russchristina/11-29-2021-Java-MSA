package com.revature.service.login;

import com.revature.repository.Exception.DuplicateUsernameException;
import com.revature.repository.Exception.EmptyUserCredentialDataException;
import com.revature.repository.Exception.InvalidUserCredentialException;
import com.revature.service.exceptions.InvalidPasswordException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DriverTest {

    Driver driver = new Driver();

    @Test
    void authenticateAccountCredentialsEmptyUsernameExceptionTest() {
        Assertions.assertThrows(EmptyUserCredentialDataException.class, () -> driver.authenticateAccountCredentials("", "pass"));
    }

    @Test
    void authenticateAccountCredentialsEmptyPasswordExceptionTest() {
        Assertions.assertThrows(EmptyUserCredentialDataException.class, () -> driver.authenticateAccountCredentials("asdf", ""));
    }

    @Test
    void authenticateAccountCredentialsIncorrectPasswordExceptionTest() {
        Assertions.assertThrows(InvalidPasswordException.class, () -> driver.authenticateAccountCredentials("user1", "no"));
    }


    @Test
    void createAccountDuplicateUsernameExceptionTest() {
        Assertions.assertThrows(DuplicateUsernameException.class, () -> driver.createAccount(new StringBuilder("user1"), new StringBuilder("Pass")));
    }

    @Test
    void createAccountEmptyUsernameExceptionTest() {
        Assertions.assertThrows(EmptyUserCredentialDataException.class, () -> driver.createAccount(new StringBuilder(" "), new StringBuilder("Pass")));
    }

    @Test
    void createAccountEmptyPasswordExceptionTest() {
        Assertions.assertThrows(EmptyUserCredentialDataException.class, () -> driver.createAccount(new StringBuilder("asdfs"), new StringBuilder(" ")));
    }

    @Test
    void createAccountShortUsernameExceptionTest() {
        Assertions.assertThrows(InvalidUserCredentialException.class, () -> driver.createAccount(new StringBuilder("a"), new StringBuilder("sdfasdf")));
    }

    @Test
    void createAccountShortPasswordExceptionTest() {
        Assertions.assertThrows(InvalidUserCredentialException.class, () -> driver.createAccount(new StringBuilder("asdfasdf"), new StringBuilder("a")));
    }

}