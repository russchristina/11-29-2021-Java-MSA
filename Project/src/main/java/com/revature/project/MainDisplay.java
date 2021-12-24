package com.revature.project;//package com.revature.project;

import java.util.Scanner;

public class MainDisplay {
    //Classes being used for data passes, and Scanner instance
    UserAccounts allAccounts = new UserAccounts();
    GetValue value = new GetValue();
    GetKey key = new GetKey();
    UserOptions userOptions = new UserOptions();
    Scanner userInput = new Scanner(System.in);

    //    static variables and methods;
    private static String username;
    private static int unregisteredDecision;

    public static void setPassword(String password) {
        MainDisplay.password = password;
    }

    private static String password;
    private static int registeredDecision;
    int attempts = 3;
    public static int getUnregisteredDecision() {return unregisteredDecision;}

    public static int getRegisteredDecision() {
        return registeredDecision;
    }

    public static String getUsername() {
        return username;
    }

    public static String getPassword() {
        return password;
    }

    public static void main(String[] args) {
        new MainDisplay();
    }


    public MainDisplay() {

//        Scanner Start and username declaration
        System.out.println("Welcome to the Glizzy Gallery. Please enter your username, or select a number from " +
                "the choices below : \n 1: Create a new account \n 2: Reload Application \n 3: Exit Application");
        if (userInput.hasNextInt()) {
            unregisteredDecision = userInput.nextInt();
            userOptions.newAccount();
        } else {
            username = userInput.nextLine();
            value.passwordCheck();
            //userInput.nextLine();

            if (!key.hasKey(username, allAccounts)) {
                System.out.println("That username was not recognized. What would you like to do?: \n " +
                        " \n 1: Create a new account \n 2: Re-enter username \n  3: Exit Application");



                if (userInput.hasNextInt()) {
                    unregisteredDecision = userInput.nextInt();
                    userOptions.newAccount();

                }


                while (!userInput.hasNextInt()) {
                    //Loop: if it isn't a string, print
                    System.out.println("Invalid entry. please enter a number corresponding with the options below : \n " +
                            " \n 1: Create a new account \n 2: Re-enter username \n  3: Exit Application");
                    userInput.nextLine();


                }


            }
//            // Testing Username Input
////            if (key.hasKey(username, allAccounts)) {
////                value.getValue(username);
////                System.out.println("Enter Password: ");
////                password = userInput.nextLine();
////                if (password.equals(GetValue.getAccountPassword())) {
////                    System.out.println("Welcome, " + username + "! Type in number corresponding to the desired" +
////                            " action: ");
//
//                } else {
////                    while (!password.equals(GetValue.getAccountPassword())) {
////
////                        attempts--;
////                        if(attempts == 0){
////                            System.out.println("Too many attempts foo :P");
////                            break;
////                        }
////                        System.out.println("Password incorrect. Please try again, or " +
////                                "please enter a number corresponding with the options " +
////                                "below : \n " +
////                                " \n 1: Create a new account \n 2: Re-enter username \n  3: Exit Application \n Attempts " +
////                                "remaining :  " + attempts  );
////
////
////                        userInput.nextLine();



                    }


                }
            }


