//package com.revature.project;
//
//import org.junit.jupiter.api.*;
//import org.junit.jupiter.api.TestInstance.Lifecycle;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.ValueSource;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@TestInstance(Lifecycle.PER_CLASS)
//
//public class UserAccountTests {
//    MainDisplay display;
//    UserAccounts account;
//
//
//    @Test
//    @Disabled
//    void accountTest(){
//
//        Map< String,String> testMap = new HashMap<>();
//
//            UserAccounts.setAccountHash("Hello", "Govnah");
//          if(UserAccounts.getAccountHash().isEmpty()) Assertions.fail();
//
//
//    }
//    @ParameterizedTest
//    @ValueSource(strings = "Hello")
//    void testUsername(String userInputs){
//        new MainDisplay();
//
//
//
//    }
//
//    }
//
