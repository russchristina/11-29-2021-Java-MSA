package com.revature.models;

import com.revature.database.DummyCustomerData;
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

import java.util.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PrimaryUserTest {

    private PrimaryUser genericPrimary;
    private List<User> secondaryUsers = new ArrayList<>();
    private CustomerAccount account;


    @BeforeAll
    public void instantiateVariables(){
        genericPrimary = new PrimaryUser(10000,"primary", 100, "primary");
        secondaryUsers.add(genericPrimary);


        account = new CustomerAccount( 110000, "user1", secondaryUsers, 10000, new ArrayList<>());
    }

    @Test
    void addSecondaryUserTest() {
        try {
            Assertions.assertTrue(genericPrimary.addSecondaryUser("generic", account) != null);
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
        secondaryUsers.add(new User(1, "name1", 0));
        Assertions.assertThrows(RepeatedNameOfUserException.class, () -> genericPrimary.addSecondaryUser("name1",account));
    }

    @Test
    void maxSecondaryUserExceptionTest(){
        try {
            secondaryUsers.add(new User(1,"filler1", 0));
            secondaryUsers.add(new User(2,"filler2", 0));
            secondaryUsers.add(new User(3,"filler3", 0));
            secondaryUsers.add(new User(4,"filler4", 0));
            genericPrimary.addSecondaryUser("filled", account);
        } catch (EmptyInputException e) {
            e.printStackTrace();
        } catch (RepeatedNameOfUserException e) {
            e.printStackTrace();
        } catch (MaxSecondaryUsersException e) {
            e.printStackTrace();
        }

        Assertions.assertThrows(MaxSecondaryUsersException.class, () -> genericPrimary.addSecondaryUser("FULL",account));
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
        List<User> justForThis = new ArrayList<>();
        justForThis.add(new User(0, "testingtest", 0));

        PrimaryUser primaryUser = new PrimaryUser(0,"BOb", 100, "primary");
        CustomerAccount transferringFundsAccount = new CustomerAccount(0, "primary" ,justForThis, 0, new ArrayList<>());
        try {
            Assertions.assertEquals(50, primaryUser.transferFundsToUser(50, "testingtest",transferringFundsAccount));
        } catch (FailedToTransferFundsException | UserNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    void failedToTransferFundsExceptionTest() {
        secondaryUsers.add(new User(6, "test1", 0));
        Assertions.assertThrows(FailedToTransferFundsException.class, () -> genericPrimary.transferFundsToUser(10000, "test1", account));
    }

    @Test
    void transferFundsFromUserTest() {
        secondaryUsers.add(new User(DummyCustomerData.users3.size(),"test1", 100));
        secondaryUsers.add(new User(DummyCustomerData.users3.size(),"test2", 0));
        try {
            Assertions.assertEquals(50, genericPrimary.transferFundsFromUserToUser(50, "test1", "test2", account));
        } catch (FailedToTransferFundsException | UserNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    void transferFundsBetweenSecondaryUsersFailedExceptionInsufficientFundsTest() {
        secondaryUsers.add(new User(DummyCustomerData.users3.size(),"test1", 100));
        secondaryUsers.add(new User(DummyCustomerData.users3.size(),"test2", 0));
        Assertions.assertThrows(FailedToTransferFundsException.class, () -> genericPrimary.transferFundsFromUserToUser(3000, "test1", "test2",account));

    }

    @Test
    void transferFundsBetweenSecondaryUsersFailedExceptionNegativeTest() {
        secondaryUsers.add(new User(DummyCustomerData.users3.size(),"test1", 100));
        secondaryUsers.add(new User(DummyCustomerData.users3.size(),"test2", 0));
        Assertions.assertThrows(FailedToTransferFundsException.class, () -> genericPrimary.transferFundsFromUserToUser(-100, "test1", "test2",account));

    }

    @Test
    void transferFundsFromUserToPrimary(){
        secondaryUsers.add(new User(DummyCustomerData.users3.size(),"test1", 100));
        secondaryUsers.add(new User(DummyCustomerData.users3.size(),"test2", 0));
        try {
            Assertions.assertEquals(150, genericPrimary.transferFundsFromUserToPrimary(50, "test1", account));
        } catch (UserNotFoundException | FailedToTransferFundsException e) {
            e.printStackTrace();
        }
    }

    @Test
    void changeNameOfUser() {
        secondaryUsers.add(new User(DummyCustomerData.users3.size(),"test1", 100));
        secondaryUsers.add(new User(DummyCustomerData.users3.size(),"test2", 0));
        try {
            Assertions.assertEquals("namechange", genericPrimary.changeNameOfUser("namechange", "test1", account));
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
        secondaryUsers.add(new User(DummyCustomerData.users3.size(),"name1", 100));
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

        Assertions.assertThrows(RepeatedNameOfUserException.class, () -> genericPrimary.changeName(genericPrimary.getName(), account));
    }
}