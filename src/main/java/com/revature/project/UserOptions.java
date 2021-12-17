package com.revature.project;

import sun.applet.Main;

import java.util.Scanner;

public class UserOptions {


    public void newAccount() {
        Scanner test = new Scanner(System.in);
        UserAccounts allAccounts= new UserAccounts();
        switch (MainDisplay.getUnregisteredDecision()) {
            case 1: //Create New Account and return to login screen
                System.out.println("Enter desired username.");
                String newUsername = test.nextLine();
                System.out.println("Enter desired password");
                String newPassword = test.nextLine();
                allAccounts.newEntry(newUsername, newPassword);
                System.out.println("Thank you for signing up! Returning to login page.");
                MainDisplay.getUsername();
                MainDisplay mainDisplay = new MainDisplay();

                break;


            case 2: //Return to login page
                mainDisplay = new MainDisplay();

                break;
            case 3: //Return to login page
                System.out.println("Thank you for visiting. Goodbye! ");
                test.close();

                break;



        }
    }
    public void loggedIn(){
        switch (MainDisplay.getRegisteredDecision()){
            case 1: //link new account

                break;
            case 2: //manage funds (add or remove)
                break;
            case 3: //Transfer funds to linked accounts
                break;
            case 4: //Log Out
                break;
        }
    }
}
//package com.revature.project;

//
//import java.util.Scanner;
//
//public class UserOptions {
//    String choice = "1: Create a new account \n 2: Return to login page 3: Exit Application  ";
//    GetValue value = new GetValue();
//    GetKey key = new GetKey();
//
//    public void newAccount() {
//        Scanner test = new Scanner(System.in);
//        UserAccounts allAccounts = new UserAccounts();
//        switch (MainDisplay.getUnregisteredDecision()) {
//            case 1:
//                MainDisplay.getUsername() = test.nextLine();
//                value.getValue(MainDisplay.getUsername());
//                // Testing Username Input
//                if (key.hasKey(MainDisplay.getUsername(), allAccounts)) {
//                    value.getValue(MainDisplay.getUsername());
//                    System.out.println("Enter Password: ");
//                    MainDisplay.getPassword() = test.nextLine();
//                    }
//                    if (MainDisplay.getPassword().equals(GetValue.getAccountPassword()))
//                        System.out.println("Welcome, " + MainDisplay.getUsername() + "! Type in number corresponding to the desired" +
//                                "action: ");
//
//                    break;
//                    case 2: //Create New Account and return to login screen
//                        System.out.println("Enter desired username.");
//                        String newUsername = test.nextLine();
//                        System.out.println("Enter desired password");
//                        String newPassword = test.nextLine();
//                        allAccounts.newEntry(newUsername, newPassword);
//                        System.out.println("Thank you for signing up! Returning to login page.");
//                        MainDisplay.getUsername();
//                        System.out.println(MainDisplay.getUsername());
//                        MainDisplay mainDisplay = new MainDisplay();
//                        test.close();
//                        break;
//                    case 3: //Return to login page
//                        System.out.println("Returning to Login Page: ");
//                        mainDisplay = new MainDisplay();
//                        break;
//                    case 4:
//                        mainDisplay = new MainDisplay();
//                        break;
//
//
//
//        }
//    }
//
//}
//        //Not yet implemented
////    public void loggedIn() {
////        switch (MainDisplay.getRegisteredDecision()) {
////            case 1: //link new account
////                break;
////            case 2: //manage funds (add or remove)
////                break;
////            case 3: //Transfer funds to linked accounts
////                break;
////            case 4: //Log Out
////                break;
////        }
//
//
