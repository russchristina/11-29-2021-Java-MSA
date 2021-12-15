package com.revature.models;

import com.revature.models.exceptions.FailedToTransferFundsException;
import com.revature.models.exceptions.MaxSecondaryUsersException;
import com.revature.models.exceptions.RepeatedNameOfUserException;
import com.revature.models.exceptions.UserNotFoundException;
import com.revature.service.exceptions.EmptyInputException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.HashMap;
import java.util.Map;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PrimaryUserTest {

    private PrimaryUser genericPrimary;
    private Map<String, User> secondaryUsers;
    private CustomerAccount account;


    @BeforeAll
    public void instantiateVariables(){
        this.genericPrimary = new PrimaryUser("primary", 100,"user1");
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
        CustomerAccount fullAccount = new CustomerAccount(secondaryUsers, "primary", genericPrimary);
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
        secondaryUsers.put("test1" , new User("test1", 0));
        CustomerAccount fullAccount = new CustomerAccount(secondaryUsers, "primary", genericPrimary);

        try {
            Assertions.assertEquals(50, genericPrimary.transferFundsToUser(50, "test1",fullAccount));
        } catch (FailedToTransferFundsException | UserNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    void failedToTransferFundsExceptionTest() {
        secondaryUsers.put("test1" , new User("test1", 100));
        secondaryUsers.put("test2" , new User("test2", 0));
        CustomerAccount fullAccount = new CustomerAccount(secondaryUsers, "primary", genericPrimary);

        Assertions.assertThrows(FailedToTransferFundsException.class, () -> genericPrimary.transferFundsToUser(10000, "test1", fullAccount));
    }

    @Test
    void transferFundsFromUserTest() {
        secondaryUsers.put("test1" , new User("test1", 100));
        secondaryUsers.put("test2" , new User("test2", 0));
        CustomerAccount fullAccount = new CustomerAccount(secondaryUsers, "primary", genericPrimary);
        try {
            Assertions.assertEquals(50, genericPrimary.transferFundsFromUserToUser(50, "test1", "test2", fullAccount));
        } catch (FailedToTransferFundsException | UserNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    void transferFundsBetweenSecondaryUsersFailedExceptionInsufficientFundsTest() {
        secondaryUsers.put("test1" , new User("test1", 100));
        secondaryUsers.put("test2" , new User("test2", 0));
        CustomerAccount fullAccount = new CustomerAccount(secondaryUsers, "primary", genericPrimary);

        Assertions.assertThrows(FailedToTransferFundsException.class, () -> genericPrimary.transferFundsFromUserToUser(3000, "test1", "test2",fullAccount));

    }

    @Test
    void transferFundsBetweenSecondaryUsersFailedExceptionNegativeTest() {
        secondaryUsers.put("test1" , new User("test1", 100));
        secondaryUsers.put("test2" , new User("test2", 0));
        CustomerAccount fullAccount = new CustomerAccount(secondaryUsers, "primary", genericPrimary);
        Assertions.assertThrows(FailedToTransferFundsException.class, () -> genericPrimary.transferFundsFromUserToUser(-100, "test1", "test2",fullAccount));

    }

    @Test
    void transferFundsFromUserToPrimary(){
        secondaryUsers.put("test1" , new User("test1", 100));
        secondaryUsers.put("test2" , new User("test2", 0));
        CustomerAccount fullAccount = new CustomerAccount(secondaryUsers, "primary", genericPrimary);
        try {
            Assertions.assertEquals(150, genericPrimary.transferFundsFromUserToPrimary(50, "test1", fullAccount));
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        } catch (FailedToTransferFundsException e) {
            e.printStackTrace();
        }
    }






}