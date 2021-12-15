package com.revature.display;

import com.revature.service.UserLoginHandler;

public class LoginDisplay {

    public boolean printWelcomeDisplay() {
        System.out.println("Welcome to PlanetMart");
        return true;
    }

    public void printWelcomeOptions() {
        System.out.println("1. Login to Account");
        System.out.println("2. Create Account");

    }

    public void printLoginDisplayUsername() {
        System.out.println("LOGIN");
        System.out.print("Username:");
    }

    public void printLoginDisplayPassword() {
        System.out.print("Password:");
    }

    public void printCreateAccountDisplayUsername() {
        System.out.println("CREATE ACCOUNT");
        System.out.print("Unique Username:");
    }

    public void printCreateAccountDisplayPassword() {
        System.out.print("Password:");
    }


}
