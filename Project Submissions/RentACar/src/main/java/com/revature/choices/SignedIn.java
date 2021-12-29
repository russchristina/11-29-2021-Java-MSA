package com.revature.choices;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.revature.logging.Logging;
import com.revature.repository.UsersRepository;
import com.revature.repository.UsersRepositoryImplement;
import com.revature.repository.VehicleRepository;
import com.revature.repository.VehicleRepositoryImplement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SignedIn {

    Scanner scan = new Scanner(System.in);
    UsersRepository usersRepository = new UsersRepositoryImplement();
    Logger myLogger = LoggerFactory.getLogger(Logging.class);

    AppStart start = new AppStart();

    String signedUser;

    String otherAccountUser;
    float transferAmount;
    float balance;

    public void signInChoice() {

        System.out.println();
        System.out.print("Please enter your username (case sensitive): ");
        System.out.println();

        signedUser = scan.nextLine();

        String userInDB = usersRepository.retrieveUser(signedUser);

        while (!signedUser.equals(userInDB)) {
            System.out.println();
            System.out.println("Oops. We don't have that username, try again.");
            System.out.println();

            signedUser = scan.nextLine();
            userInDB = usersRepository.retrieveUser(signedUser);

        }

        System.out.print("Password: ");
        System.out.println();

        String password = scan.nextLine();

        String passwordInDB = usersRepository.retrievePassword(signedUser);

        System.out.println();

        while (!password.equals(passwordInDB)) {

            System.out.println("Oops. Wrong password, please try again.");
            System.out.println();

            password = scan.nextLine();

        }

        // ========================================================
        // ========================================================
        // MAIN MENU
        System.out.println("-------------------");
        System.out.println("Welcome back!");
        System.out.println("-------------------");
        myLogger.info(signedUser + " is signed in");

        userChoice();
    }

    public void userChoice() {

        String secondUser = usersRepository.viewSecondaryUser(signedUser);
        int chosenUser = -1;
        if (!secondUser.equals("none")) {
            do {

                try {

                    System.out.println("Which user are you?");
                    System.out.println("Type '1' for " + signedUser);
                    System.out.println("Type '2' for " + secondUser);
                    System.out.println();
                    chosenUser = scan.nextInt();

                } catch (InputMismatchException e) {
                    System.out.println("Oops.  Please type 1 or 2");
                    System.out.println();
                }
                scan.nextLine();
            } while (chosenUser < 0 || chosenUser > 3);

            if (chosenUser == 1) {
                System.out.println("=======================");
                System.out.println("Hello, " + signedUser);
                System.out.println("=======================");
            } else {
                System.out.println("=======================");
                System.out.println("Hello, " + secondUser);
                System.out.println("=======================");

                myLogger.info("The secondary user is being used.");

            }

        } else {
            System.out.println("=======================");
            System.out.println("Hello, " + signedUser);
            System.out.println("=======================");

        }
        menu();
    }

    public void menu() {
        // If there are users, display them.

        System.out.println();
        System.out.println("What would you like to do?");
        System.out.println("Type '1' to look at our vehicles ");
        System.out.println("Type '2' for accounting ");
        System.out.println("Type '3' to sign out ");
        System.out.println();

        int userChoice = scan.nextInt();

        scan.nextLine();

        if (userChoice == 1) {
            VehicleRepository vehicle = new VehicleRepositoryImplement();
            vehicle.findAll();
            System.out.println();
            System.out.println("All vehicles have been rented.");
            System.out.println();

            int oneOrTwo = 0;
            do {

                try {
                    System.out.println();
                    System.out.println("Press '1' to go back");
                    System.out.println();

                    oneOrTwo = scan.nextInt();

                } catch (InputMismatchException e) {
                    System.out.println();
                    System.out.println("Oops.  Please type '1'");
                    System.out.println();
                }
                scan.nextLine();
            } while (oneOrTwo != 1);

            menu();
        }

        if (userChoice == 2) {
            // ==============================================================
            // ==============================================================
            // ACCOUNTING

            System.out.println("--ACCOUNTING--");
            int oneOrTwo = 0;
            do {

                try {
                    System.out.println();
                    System.out.println("Type '1' to add funds");
                    System.out.println("Type '2' to remove funds");
                    System.out.println("Type '3' to check balance");
                    System.out.println("Type '4' to transfer funds");
                    System.out.println("Type '5' to go back");
                    System.out.println();

                    oneOrTwo = scan.nextInt();

                } catch (InputMismatchException e) {
                    System.out.println();
                    System.out.println("Oops.  Please type 1 - 5");
                    System.out.println();
                }
                scan.nextLine();
            } while (oneOrTwo < 0 || oneOrTwo > 6);

            int accountAddRemoveTransfer = oneOrTwo;

            if (accountAddRemoveTransfer == 1) {
                // add funds
                System.out.println();
                System.out.print("How much would you like to add $ ");
                System.out.println();
                float amountToAdd = scan.nextFloat();

                float balance = usersRepository.viewUserAccountBalance(signedUser);

                float total = amountToAdd + balance;

                usersRepository.updateBalance(total, signedUser);

                System.out.println("Your new total is: $" + total);
                myLogger.info("Your new total is: $" + total);
                System.out.println();

                oneOrTwo = 0;
                do {

                    try {
                        System.out.println();
                        System.out.println("Press '1' to go back");
                        System.out.println();

                        oneOrTwo = scan.nextInt();

                    } catch (InputMismatchException e) {
                        System.out.println();
                        System.out.println("Oops.  Please type '1'");
                        System.out.println();
                    }
                    scan.nextLine();
                } while (oneOrTwo != 1);
                userChoice();

            } else if (accountAddRemoveTransfer == 2) {
                // remove funds
                float balance = usersRepository.viewUserAccountBalance(signedUser);
                System.out.println();
                System.out.print("Your balance is $" + balance + ", how much would you like to take out $ ");
                System.out.println();

                float amountToSubtract = scan.nextFloat();

                myLogger.info(signedUser + "account took out " + amountToSubtract);

                float total = amountToSubtract - balance;

                if (total < 0) {
                    System.out.println();
                    System.out.println("You took too much, your balance is " + balance);
                    System.out.println();
                } else {

                    usersRepository.updateBalance(total, signedUser);
                    System.out.println();
                    System.out.println("Your new total is: " + total);
                    myLogger.info(signedUser + " account's new total is " + total);
                }
                oneOrTwo = 0;
                do {

                    try {
                        System.out.println();
                        System.out.println("Press '1' to go back");
                        System.out.println();

                        oneOrTwo = scan.nextInt();

                    } catch (InputMismatchException e) {
                        System.out.println();
                        System.out.println("Oops.  Please type '1'");
                        System.out.println();
                    }
                    scan.nextLine();
                } while (oneOrTwo != 1);

                userChoice();

            } else if (accountAddRemoveTransfer == 3) {
                // check balance
                float balance = usersRepository.viewUserAccountBalance(signedUser);

                System.out.println();
                System.out.println("Your account total is $" + balance);
                System.out.println();

                oneOrTwo = 0;
                do {

                    try {
                        System.out.println();
                        System.out.println("Press '1' to go back");
                        System.out.println();

                        oneOrTwo = scan.nextInt();

                    } catch (InputMismatchException e) {
                        System.out.println();
                        System.out.println("Oops.  Please type '1'");
                        System.out.println();
                    }
                    scan.nextLine();
                } while (oneOrTwo != 1);

                userChoice();

            } else if (accountAddRemoveTransfer == 4) {

                transfer();

                float otherBalance = usersRepository.viewUserAccountBalance(otherAccountUser);

                float newTransferBalance = otherBalance + transferAmount;

                float originalAccountBalance = balance - transferAmount;

                // update transferred account.

                usersRepository.updateBalance(newTransferBalance, otherAccountUser);
                myLogger.info("Updated balance for " + otherAccountUser);

                // update account taken from.

                usersRepository.updateBalance(originalAccountBalance, signedUser);

                myLogger.info("Updated balance for " + signedUser);

                System.out.println();
                System.out.println(otherAccountUser + " has a new total of $" + newTransferBalance);
                System.out.println("You now have " + originalAccountBalance);
                System.out.println();

                oneOrTwo = 0;
                do {

                    try {
                        System.out.println();
                        System.out.println("Press '1' to go back");
                        System.out.println();

                        oneOrTwo = scan.nextInt();

                    } catch (InputMismatchException e) {
                        System.out.println();
                        System.out.println("Oops.  Please type '1'");
                        System.out.println();
                    }
                    scan.nextLine();
                } while (oneOrTwo != 1);

                userChoice();
            } else if (accountAddRemoveTransfer == 5) {

                userChoice();

            }
        }
        if (userChoice == 3) {
            start.start();
        }

    }

    public void transfer() {
        // transfer money to other account
        balance = usersRepository.viewUserAccountBalance(signedUser);

        do {
            try {
                System.out.println();
                System.out.print("You have $" + balance + ", how much would you like to transfer in dollars/cents $");
                System.out.println();

                transferAmount = scan.nextFloat();
            } catch (InputMismatchException e) {
                System.out.println();
                System.out.println("Oops.  Please type dollar amount.");
                System.out.println();
            }
            scan.nextLine();
        } while (transferAmount < 0);

        if (transferAmount > balance) {
            System.out.println();
            System.out.println("You don't have that much to transfer.  Try again.");
            System.out.println();
            transfer();
        }
        myLogger.info(signedUser + "'s account transfered " + transferAmount);
        // ====================================
        // SIGN IN TO OTHER ACCOUNT

        System.out.print("Username for other account(case sensitive): ");
        System.out.println();

        otherAccountUser = scan.nextLine();

        String otherUserInDB = usersRepository.retrieveUser(otherAccountUser);

        System.out.println();

        while (!otherAccountUser.equals(otherUserInDB)) {

            System.out.println("Oops. We don't have that username, try again.");

            System.out.println();
            otherAccountUser = scan.nextLine();
            otherUserInDB = usersRepository.retrieveUser(otherAccountUser);
        }

        System.out.print("Password: ");

        String otherPassword = scan.nextLine();

        String otherPasswordInDB = usersRepository.retrievePassword(otherAccountUser);

        System.out.println();

        while (!otherPassword.equals(otherPasswordInDB)) {
            System.out.println();
            System.out.println("Oops. Wrong password, please try again.");
            System.out.println();
            otherPassword = scan.nextLine();
            otherPasswordInDB = usersRepository.retrievePassword(otherAccountUser);
        }
    }
}
