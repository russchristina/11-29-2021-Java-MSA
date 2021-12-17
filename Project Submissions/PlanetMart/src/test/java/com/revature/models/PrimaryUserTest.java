package com.revature.models;

import com.revature.models.accounts.CustomerAccount;
import com.revature.models.exceptions.RepeatedNameOfUserException;
import com.revature.models.shop.Inventory;
import com.revature.models.users.PrimaryUser;
import com.revature.models.users.User;
import com.revature.service.exceptions.EmptyInputException;
import com.revature.models.exceptions.FailedToTransferFundsException;
import com.revature.models.exceptions.MaxSecondaryUsersException;
import com.revature.models.exceptions.UserNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PrimaryUserTest {

    private PrimaryUser genericPrimary;
    private Map<String, User> secondaryUsers;
    private CustomerAccount account;


    @BeforeAll
    public void instantiateVariables(){
        this.genericPrimary = new PrimaryUser("primary", 100,"user1");
        this.secondaryUsers = new HashMap<>();
        secondaryUsers.put("name1", new User("name1", 0,  "user1"));
        secondaryUsers.put("name2", new User("name2", 0,  "user1"));
        secondaryUsers.put("name3", new User("name3", 0,  "user1"));
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
        Map<String, User> justForThis = new HashMap<>();
        justForThis.put("testingtest" , new User("testingtest", 0,  "user1"));

        PrimaryUser primaryUser = new PrimaryUser("BOb", 100, "primary");
        CustomerAccount transferringFundsAccount = new CustomerAccount(justForThis, "primary", primaryUser);
        try {
            Assertions.assertEquals(50, primaryUser.transferFundsToUser(50, "testingtest",transferringFundsAccount));
        } catch (FailedToTransferFundsException | UserNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    void failedToTransferFundsExceptionTest() {
        secondaryUsers.put("test1" , new User("test1", 100,  "primary"));
        secondaryUsers.put("test2" , new User("test2", 0,  "primary"));
        CustomerAccount fullAccount = new CustomerAccount(secondaryUsers, "primary", genericPrimary);

        Assertions.assertThrows(FailedToTransferFundsException.class, () -> genericPrimary.transferFundsToUser(10000, "test1", fullAccount));
    }

    @Test
    void transferFundsFromUserTest() {
        secondaryUsers.put("test1" , new User("test1", 100,  "primary"));
        secondaryUsers.put("test2" , new User("test2", 0,  "primary"));
        CustomerAccount fullAccount = new CustomerAccount(secondaryUsers, "primary", genericPrimary);
        try {
            Assertions.assertEquals(50, genericPrimary.transferFundsFromUserToUser(50, "test1", "test2", fullAccount));
        } catch (FailedToTransferFundsException | UserNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    void transferFundsBetweenSecondaryUsersFailedExceptionInsufficientFundsTest() {
        secondaryUsers.put("test1" , new User("test1", 100,  "primary"));
        secondaryUsers.put("test2" , new User("test2", 0,  "primary"));
        CustomerAccount fullAccount = new CustomerAccount(secondaryUsers, "primary", genericPrimary);

        Assertions.assertThrows(FailedToTransferFundsException.class, () -> genericPrimary.transferFundsFromUserToUser(3000, "test1", "test2",fullAccount));

    }

    @Test
    void transferFundsBetweenSecondaryUsersFailedExceptionNegativeTest() {
        secondaryUsers.put("test1" , new User("test1", 100,  "primary"));
        secondaryUsers.put("test2" , new User("test2", 0,  "primary"));
        CustomerAccount fullAccount = new CustomerAccount(secondaryUsers, "primary", genericPrimary);
        Assertions.assertThrows(FailedToTransferFundsException.class, () -> genericPrimary.transferFundsFromUserToUser(-100, "test1", "test2",fullAccount));

    }

    @Test
    void transferFundsFromUserToPrimary(){
        secondaryUsers.put("test1" , new User("test1", 100,  "primary"));
        secondaryUsers.put("test2" , new User("test2", 0,  "primary"));
        CustomerAccount fullAccount = new CustomerAccount(secondaryUsers, "primary", genericPrimary);
        try {
            Assertions.assertEquals(150, genericPrimary.transferFundsFromUserToPrimary(50, "test1", fullAccount));
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        } catch (FailedToTransferFundsException e) {
            e.printStackTrace();
        }
    }

    @Test
    void changeNameOfUser() {
        secondaryUsers.put("test1" , new User("test1", 100,  "primary"));
        secondaryUsers.put("test2" , new User("test2", 0,  "primary"));
        CustomerAccount fullAccount = new CustomerAccount(secondaryUsers, "primary", genericPrimary);
        try {
            Assertions.assertEquals("namechange", genericPrimary.changeNameOfUser("namechange", "test1", fullAccount));
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        } catch (RepeatedNameOfUserException e) {
            e.printStackTrace();
        } catch (EmptyInputException e) {
            e.printStackTrace();
        }
    }

    @Test
    void emptyUserForNameChangeException() {
        Assertions.assertThrows(EmptyInputException.class, () -> genericPrimary.changeNameOfUser(" ", "name1", account));
    }
    @Test
    void emptyNameChangeOfUserException() {
        Assertions.assertThrows(EmptyInputException.class, () -> genericPrimary.changeNameOfUser("Greg", " ", account));
    }

    @Test
    void repeatNameChangeOfUserException() {
        Assertions.assertThrows(RepeatedNameOfUserException.class, () -> genericPrimary.changeNameOfUser("name1", "name1", account));
    }

    @Test
    void userNotFoundNameChangeOfUserException() {
        Assertions.assertThrows(UserNotFoundException.class, () -> genericPrimary.changeNameOfUser("Greg", "Varry", account));
    }



    @Test
    void successfulNameChangeTest() {

        try {
            Assertions.assertEquals("ChangedName", genericPrimary.changeName("ChangedName", account));
        } catch (EmptyInputException e) {
            e.printStackTrace();
        } catch (RepeatedNameOfUserException e) {
            e.printStackTrace();
        }
    }

    @Test
    void emptyNameChangeExceptionTest(){
        Assertions.assertThrows(EmptyInputException.class, () -> genericPrimary.changeName("", account));
    }

    @Test
    void repeatNameChangeExceptionTest(){
        Assertions.assertThrows(RepeatedNameOfUserException.class, () -> genericPrimary.changeName("name1", account));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PrimaryUserTest)) return false;
        PrimaryUserTest that = (PrimaryUserTest) o;
        return genericPrimary.equals(that.genericPrimary) && secondaryUsers.equals(that.secondaryUsers) && account.equals(that.account);
    }

    @Override
    public int hashCode() {
        return Objects.hash(genericPrimary, secondaryUsers, account);
    }

    @Override
    public String toString() {
        return "{\"PrimaryUserTest\":{"
                + "\"genericPrimary\":" + genericPrimary
                + ", \"secondaryUsers\":" + secondaryUsers
                + ", \"account\":" + account
                + "}}";
    }
}