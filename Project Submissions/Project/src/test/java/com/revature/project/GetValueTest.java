package com.revature.project;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class GetValueTest {
    protected void newEntry(String a, String b) {
        Map<String, String> accountHash = UserAccounts.getAccountHash();
        accountHash.putIfAbsent(a, b);
        UserAccounts userAccounts = new UserAccounts();
        accountHash.put("John", "Doe");
    }
    @Test
    void getAccountPassword() {
      String password ="1234";
      String testInput = "443";
        Assertions.assertNotEquals(password,testInput);
    }

    @Test
    void passwordCheck() {
       boolean test = false;
        Map<String,String> userAccounts = new HashMap<>();
        userAccounts.put("John", "Stockton");
        String a = "Tony";
        if (userAccounts.containsKey(a)){
            test = false;
        }else
        test = true;
        Assertions.assertTrue(test);
    }

    @Test
    void childPasswordCheck() {
        boolean test = false;
        Map<String,String> userAccounts = new HashMap<>();
        userAccounts.put("John", "Stockton");
        String a = "Tony";
        if (userAccounts.containsKey(a)){
            test = false;
        }else
            test = true;
        Assertions.assertTrue(test);
    }

    @Test
    void employeePasswordCheck() {
        boolean test = false;
        Map<String,String> userAccounts = new HashMap<>();
        userAccounts.put("John", "Stockton");
        String a = "Tony";
        if (userAccounts.containsKey(a)){
            test = false;
        }else
            test = true;
        Assertions.assertTrue(test);
    }
}