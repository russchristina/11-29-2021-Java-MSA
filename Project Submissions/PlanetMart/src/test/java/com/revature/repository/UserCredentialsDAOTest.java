package com.revature.repository;

import com.revature.models.users.UserCredential;
import com.revature.repository.Exception.DuplicateUsernameException;
import com.revature.repository.Exception.InvalidUserCredentialException;
import com.revature.repository.Exception.InvalidUserCredentialIdException;
import com.revature.service.exceptions.EmptyInputException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserCredentialsDAOTest {

    UserCredentialsDAO userCredentialsDAO = new UserCredentialsDAO();

    @Test
    void getUserCredentialByUsernameEmptyUsernameExceptionTest() {
        Assertions.assertThrows(EmptyInputException.class, () -> userCredentialsDAO.getUserCredentialByUsername(""));
    }

    @Test
    void updateUserCredentialUsernameEmptyUsernameExceptionTest() {
        Assertions.assertThrows(EmptyInputException.class, () -> userCredentialsDAO.updateUserCredentialUsername(1, ""));
    }

    @Test
    void updateUserCredentialInvalidUserCredentialIdExceptionTest() {
        Assertions.assertThrows(InvalidUserCredentialIdException.class, () -> userCredentialsDAO.updateUserCredentialUsername(-1, "hello"));
    }

    @Test
    void updateUserCredentialFirstnameEmptyFirstNameExceptionTest() {
        Assertions.assertThrows(EmptyInputException.class, () -> userCredentialsDAO.updateUserCredentialFirstname(1, ""));
    }

    @Test
    void updateUserCredentialFirstnameInvalidUserCredentialsIdExceptionTest() {
        Assertions.assertThrows(InvalidUserCredentialIdException.class, () -> userCredentialsDAO.updateUserCredentialFirstname(-1, "asdfasf"));
    }

    @Test
    void updateUserCredentialLastnameEmptyLastNameExceptionTest() {
        Assertions.assertThrows(EmptyInputException.class, () -> userCredentialsDAO.updateUserCredentialLastname(1, ""));
    }

    @Test
    void updateUserCredentialLastnameInvalidUserCredentialsIdExceptionTest() {
        Assertions.assertThrows(InvalidUserCredentialIdException.class, () -> userCredentialsDAO.updateUserCredentialLastname(-1, "asdfasf"));
    }

    @Test
    void updateUserCredentialPasswordEmptyPasswordExceptionTest() {
        Assertions.assertThrows(EmptyInputException.class, () -> userCredentialsDAO.updateUserCredentialPassword(1, ""));
    }

    @Test
    void updateUserCredentialPasswordInvalidUserCredentialsIdExceptionTest() {
        Assertions.assertThrows(InvalidUserCredentialIdException.class, () -> userCredentialsDAO.updateUserCredentialPassword(-1, "asdfasf"));
    }

    @Test
    void addUserCredentialEmptyUsernameExceptionTest() {
        UserCredential emptyUsernameCredential = new UserCredential(0, "","password", "firstname", "lastname");
        Assertions.assertThrows(EmptyInputException.class, () -> userCredentialsDAO.addUserCredential(emptyUsernameCredential));
    }

    @Test
    void addUserCredentialEmptyPasswordExceptionTest() {
        UserCredential emptyPasswordCredential = new UserCredential(0, "username","", "firstname", "lastname");
        Assertions.assertThrows(EmptyInputException.class, () -> userCredentialsDAO.addUserCredential(emptyPasswordCredential));
    }

    @Test
    void addUserCredentialEmptyFirstnameExceptionTest() {
        UserCredential emptyFirstnameCredential = new UserCredential(0, "username","password", "", "lastname");
        Assertions.assertThrows(EmptyInputException.class, () -> userCredentialsDAO.addUserCredential(emptyFirstnameCredential));
    }

    @Test
    void addUserCredentialEmptyLastnameExceptionTest() {
        UserCredential emptyLastnameCredential = new UserCredential(0, "username","password", "firstname", "");
        Assertions.assertThrows(EmptyInputException.class, () -> userCredentialsDAO.addUserCredential(emptyLastnameCredential));
    }

    @Test
    void addUserCredentialDuplicateUsernameExceptionTest() {
        UserCredential duplicateUsernameCredential = new UserCredential(0, "user1","password", "firstname", "lastname");
        Assertions.assertThrows(DuplicateUsernameException.class, () -> userCredentialsDAO.addUserCredential(duplicateUsernameCredential));
    }
}