package com.revature.presentation;

import com.revature.presentation.manageLogin.LoginController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainDriver {
    private final Logger logger = LoggerFactory.getLogger(MainDriver.class);
    private final Logger transactionLogger = LoggerFactory.getLogger("transactionLogger");

    public static void main(String[] args) {
        LoginController loginController = new LoginController();
        loginController.manageLogin();
    }



}

