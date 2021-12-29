package com.revature;

import com.revature.choices.AppStart;
import com.revature.logging.Logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Driver {

    public static void main(String[] args) {

        Logger myLogger = LoggerFactory.getLogger(Logging.class);

        myLogger.info("App starts");
        AppStart start = new AppStart();
        start.start();

    }
}