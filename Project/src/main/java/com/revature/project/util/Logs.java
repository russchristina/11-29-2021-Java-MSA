package com.revature.project.util;

import com.revature.project.MainDisplay;
import com.revature.project.UserOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Logs {
    Logger transactionLog;

    public static void logDeposits(int depositedFunds, int startingFunds, int newTotal) {
        Logger transactionLog = LoggerFactory.getLogger(UserOptions.class);

        transactionLog.info(MainDisplay.getUsername() + " deposited " + String.format("%,d", depositedFunds)
                + " Glizzies to the account.\n" +
                "Starting balance: "
                + String.format("%,d", startingFunds) + " Glizzies.\n" +
                "Ending balance: "
                + String.format("%,d", newTotal) + " Glizzies.");
    }

    public static void logWithdraws(int withdrawnFunds, int startingFunds, int newTotal) {
        Logger transactionLog = LoggerFactory.getLogger(UserOptions.class);

        transactionLog.info(MainDisplay.getUsername() + " withdrew " + String.format("%,d", withdrawnFunds)
                + " Glizzies to the account.\n" +
                "Starting balance: "
                + String.format("%,d", startingFunds) + " Glizzies.\n" +
                "Ending balance: "
                + String.format("%,d", newTotal) + " Glizzies.");
    }

    public static void logChildWithdraws(int withdrawnFunds, int startingFunds, int newTotal) {
        Logger transactionLog = LoggerFactory.getLogger(UserOptions.class);

        transactionLog.info("Child user " + MainDisplay.getUsername() + " withdrew " + String.format("%,d", withdrawnFunds)
                + " Glizzies from the account.\n" +
                "Starting balance: "
                + String.format("%,d", startingFunds) + " Glizzies.\n" +
                "Ending balance: "
                + String.format("%,d", newTotal) + " Glizzies.");
    }

    public static void adminLogDeposits(int depositedFunds, int startingFunds, int newTotal, String accountHolder) {
        Logger transactionLog = LoggerFactory.getLogger(UserOptions.class);

        transactionLog.info(MainDisplay.getUsername() + " deposited " + String.format("%,d", depositedFunds)
                + " Glizzies to " + accountHolder + "'s account.\n" +
                "Starting balance: "
                + String.format("%,d", startingFunds) + " Glizzies.\n" +
                "Ending balance: "
                + String.format("%,d", newTotal) + " Glizzies.");
    }

    public static void adminLogWithdraws(int withdrawnFunds, int startingFunds, int newTotal, String accountHolder) {
        Logger transactionLog = LoggerFactory.getLogger(UserOptions.class);

        transactionLog.info(MainDisplay.getUsername() + " withdrew " + String.format("%,d", withdrawnFunds)
                + " Glizzies to " + accountHolder + "'s account.\n" +
                "Starting balance: "
                + String.format("%,d", startingFunds) + " Glizzies.\n" +
                "Ending balance: "
                + String.format("%,d", newTotal) + " Glizzies.");
    }
}