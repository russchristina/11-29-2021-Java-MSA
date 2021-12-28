//Previous code if i goof
//    public void passwordCheck() {
//        Scanner passwordStream = new Scanner(System.in);
//        if (key.hasKey(MainDisplay.getUsername(), allAccounts)) {
//            getValue(MainDisplay.getUsername());
//            System.out.println("Enter Password: ");
//            String holdPassword = passwordStream.nextLine();
//            MainDisplay.setPassword(holdPassword);
//            if (MainDisplay.getPassword().equals(GetValue.getAccountPassword())) {
//                System.out.println("Welcome, " + MainDisplay.getUsername() + "! Type in number corresponding to the desired" +
//                        " action: ");
//            } else {
//                while (!MainDisplay.getPassword().equals(GetValue.getAccountPassword())) {
//                        String breakNum = "3";
//                    attempts--;
//                    if (attempts == 0 ) {
//                        System.out.println("Too many attempts foo :P");
//                        break;
//
//                    }
//
package com.revature.project;

import com.revature.project.util.UserDB;
import com.revature.project.util.UserDBImplementation;

import java.util.Scanner;

public class GetValue {
    int attempts = 3;
    GetKey key = new GetKey();
    UserAccounts allAccounts = new UserAccounts();
    UserDB userDB = new UserDBImplementation();
    Scanner userScanner = new Scanner(System.in);
    UserOptions userOptions = new UserOptions();

    public static String getAccountPassword() {
        return accountPassword;
    }

    private static String accountPassword = "null";

    public void passwordCheck() {
        System.out.println("Enter password :D");
        MainDisplay.setPassword(userScanner.nextLine());
        String databasePassword = userDB.findInfo(MainDisplay.getUsername()).get(2);
        while (!databasePassword.equals(MainDisplay.getPassword())) {
            String breakNum = "3";
            attempts--;
            if (attempts == 0) {
                System.out.println("Too many attempts foo :P");
                break;
            } else {
                System.out.println("Password incorrect. Please try again, or " +
                        "please enter a number corresponding with the options " +
                        "below : \n " +
                        " \n 1: Create a new account \n 2: Re-enter username \n  3: Exit Application \n Attempts " +
                        "remaining :  " + attempts);

                MainDisplay.setPassword(userScanner.nextLine());
            }
        }
        if (databasePassword.equals(MainDisplay.getPassword())) {
            userOptions.loggedIn();
        }
        userScanner.nextLine();
    }
    public void childPasswordCheck() {
        System.out.println("Enter password :D" +  "BUG: switch options are not working here. If they put in an incorrect" +
                "password, they cannot use the follow-up ints to select another option ");
        MainDisplay.setPassword(userScanner.nextLine());
        String databasePassword = userDB.findChildInfo(MainDisplay.getUsername()).get(2);
        while (!databasePassword.equals(MainDisplay.getPassword())) {


            attempts--;
            if (attempts == 0) {
                System.out.println("Too many attempts foo :P");
                break;
            } else {
                System.out.println("Password incorrect. Please try again, or " +
                        "please enter a number corresponding with the options " +
                        "below : \n " +
                        " \n 1: Create a new account \n 2: Re-enter username \n  3: Exit Application \n Attempts " +
                        "remaining :  " + attempts);

                MainDisplay.setPassword(userScanner.nextLine());

            }
        }
        if (databasePassword.equals(MainDisplay.getPassword())) {
            userOptions.childLoggedIn();
        }
        userScanner.nextLine();
    }
    public void employeePasswordCheck() {
        System.out.println("Ah, Glizzy Goblin " + MainDisplay.getUsername() + ". Provide your credentials to proceed.");
        MainDisplay.setPassword(userScanner.nextLine());
        String databasePassword = userDB.findEmployeeInfo(MainDisplay.getUsername()).get(2);
        while (!databasePassword.equals(MainDisplay.getPassword())) {
            String breakNum = "3";
            attempts--;
            if (attempts == 0) {
                System.out.println("Too many attempts foo :P");
                break;
            } else {
                System.out.println("Password incorrect. Please try again, or " +
                        "please enter a number corresponding with the options " +
                        "below : \n " +
                        " \n 1: Create a new account \n 2: Re-enter username \n  3: Exit Application \n Attempts " +
                        "remaining :  " + attempts);

                MainDisplay.setPassword(userScanner.nextLine());
            }
        }
        if (databasePassword.equals(MainDisplay.getPassword())) {
            userOptions.employeeLoggedIn();
        }
        userScanner.nextLine();
    }

}





