package com.revature;

import com.revature.model.Users;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
public class SignedInTest {

    private Users users;

    @BeforeAll
    public void signedIn() {
        users = new Users();
    }

    @Test
    public void testName() {
        String name = users.getUsers_name();

        Assertions.assertNull(name);
    }

    @Test
    public void pass() {
        String pass = users.getUsers_password();

        Assertions.assertNull(pass);

    }

    @Test
    public void toStringg() {
        String toStringg = users.toString();

        Assertions.assertNull(toStringg);

    }

    @Test
    public void secondary() {
        String seconds = users.getSecondary_users();

        Assertions.assertNull(seconds);

    }

    @Test
    public void balance() {
        float balance = users.getUsers_balance();

        Assertions.assertNull(balance);

    }

    @Test
    public void address() {
        String toStringg = users.getUsers_address();

        Assertions.assertNull(toStringg);

    }

    @Test
    public void dob() {
        String dob = users.getUsers_DOB();

        Assertions.assertNull(dob);

    }

}