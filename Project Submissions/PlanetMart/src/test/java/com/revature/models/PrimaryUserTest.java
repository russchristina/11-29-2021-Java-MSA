package com.revature.models;

import com.revature.models.exceptions.MaxSecondaryUsersException;
import com.revature.models.exceptions.RepeatedNameOfUserException;
import com.revature.models.exceptions.UserNotFoundException;
import com.revature.service.exceptions.EmptyInputException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PrimaryUserTest {

    private PrimaryUser genericPrimary;
    private Map<String, User> secondaryUsers;
    private CustomerAccount account;


    @BeforeAll
    public void instantiateVariables(){
        this.genericPrimary = new PrimaryUser("primary", 10,"user1");
        this.secondaryUsers = new HashMap<>();
        secondaryUsers.put("name1", new User("name1", 0));
        secondaryUsers.put("name2", new User("name2", 0));
        secondaryUsers.put("name3", new User("name3", 0));
        this.account = new CustomerAccount(secondaryUsers, "user1", genericPrimary);
    }

    @Test
    void addSecondaryUserTest() {
        try {
            Assertions.assertTrue(genericPrimary.addSecondaryUser("generic", account) instanceof User);
        } catch (RepeatedNameOfUserException | MaxSecondaryUsersException | EmptyInputException e) {
            e.printStackTrace();
        }
    }

    @Test
    void emptyInputNameForSecondaryUserExceptionTest(){
        Assertions.assertThrows(EmptyInputException.class, () -> genericPrimary.addSecondaryUser(" ",account));
    }

    @Test
    void repeatedInputNameForSecondaryUserExceptionTest(){
        Assertions.assertThrows(RepeatedNameOfUserException.class, () -> genericPrimary.addSecondaryUser("name1",account));
    }

    @Test
    void maxSecondaryUserExceptionTest(){
        secondaryUsers.put("quick1" , new User());
        secondaryUsers.put("quick2" , new User());
        CustomerAccount fullAccount = new CustomerAccount(secondaryUsers, "user1", genericPrimary);
        Assertions.assertThrows(MaxSecondaryUsersException.class, () -> genericPrimary.addSecondaryUser("FULL",fullAccount));
    }

    @Test
    void removeSecondaryUserFromAccountTest(){
        try {
            Assertions.assertTrue(genericPrimary.removeSecondaryUser("name1",account));
        } catch (EmptyInputException | UserNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    void removeSecondaryUserEmptyInputExceptionTest(){
        Assertions.assertThrows(EmptyInputException.class, () -> genericPrimary.removeSecondaryUser("", account));
    }

    @Test
    void removeSecondaryUserUserNotFoundExceptionTest(){
        Assertions.assertThrows(UserNotFoundException.class, () -> genericPrimary.removeSecondaryUser("NOTREAL", account));
    }

    @Test
    void transferFundsToUser() {

    }

    @Test
    void transferFundsFromUser() {
    }
}