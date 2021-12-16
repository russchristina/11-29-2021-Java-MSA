package com.revature.models;

import com.revature.models.exceptions.InsufficientFundsException;
import com.revature.models.exceptions.NegativeAmountException;
import com.revature.models.shop.Inventory;
import com.revature.models.users.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserTest {

    private User genericUser;

    @BeforeAll
    public void userGenerator(){
        this.genericUser = new User("name", 10, new Inventory(), "primary");
    }

    @Test
    void addNegativeFundsThrowExceptionTest() {
        Assertions.assertThrows(NegativeAmountException.class, () -> genericUser.addFunds(-100));
    }

    @Test
    void addFundsReturnsCorrectBalanceTest() {
        User user = new User("name", 10, new Inventory(), "primary");
        try {
            Assertions.assertEquals(110 ,user.addFunds(100));
        } catch (NegativeAmountException e) {
            e.printStackTrace();
        }
    }

    @Test
    void removeNegativeFundsThrowExceptionTest() {
        User user = new User("name", 10, new Inventory(), "primary");
        Assertions.assertThrows(NegativeAmountException.class, () -> user.removeFunds(-100));
    }

    @Test
    void removeFundsInsufficientExceptionTest() {
        User user = new User("name", 10, new Inventory(), "primary");
        Assertions.assertThrows(InsufficientFundsException.class, () -> user.removeFunds(100));
    }


}