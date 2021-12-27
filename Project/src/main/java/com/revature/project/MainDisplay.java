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
    private static int registeredDecision;
    private static String password;
    int attempts = 3;

    public static int getUnregisteredDecision() {
        return unregisteredDecision;
    }

    public static int getRegisteredDecision() {
        return registeredDecision;
    }
    public static void setRegisterdDecision(int registeredDecision)
    {registeredDecision = MainDisplay.registeredDecision = registeredDecision;}

    public static String getUsername() {
        return username;
    }

    public static String getPassword() {
        return password;
    }
    public static void setPassword(String password) {
        MainDisplay.password = password;
    }

    public static void main(String[] args) {
        new MainDisplay();
    }


    public MainDisplay() {

//        Scanner Start and username declaration
        System.out.println("Welcome to the Glizzy Gallery. Please enter your username(case sensitive)" +
                ", or select a number from the choices below :" +
                " \n 1: Create a new account \n 2: Reload Application \n 3: Exit Application");
        if (userInput.hasNextInt()) {
            unregisteredDecision = userInput.nextInt();
            userOptions.newAccount();
        } else {
            username = userInput.nextLine();
            key.findUsername(new GetValue());


        }
    }




}



