package com.revature;

import com.revature.model.Administrator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
public class AdminTest {

    private Administrator admin;

    @BeforeAll
    public void setup() {
        admin = new Administrator();
    }

    @Test
    public void testPass() {
        int retrievePass = admin.getAdmin_id();

        Assertions.assertNull(retrievePass);
    }

    @Test
    public void classes() {
        Class<? extends Administrator> retrievePass = admin.getClass();

        Assertions.assertNull(retrievePass);

    }

    @Test
    public void toStringg() {
        String retrievePass = admin.toString();

        Assertions.assertNull(retrievePass);

    }

}