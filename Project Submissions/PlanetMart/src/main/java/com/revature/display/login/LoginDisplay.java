package com.revature.display.login;

public class LoginDisplay {

    public void printWelcomeOptions() {
        System.out.println("1. LOGIN TO ACCOUNT");
        System.out.println("2. CREATE NEW CUSTOMER ACCOUNT");
        System.out.println("3. EXIT APPLICATION");

    }

    public void printLoginDisplayUsername() {
        System.out.println("LOGIN");
        System.out.println("TYPE N TO RETURN");
        System.out.print("USERNAME:");
    }

    public void printLoginDisplayPassword() {
        System.out.print("PASSWORD:");
    }

    public void printCreateAccountDisplay() {
        System.out.println("CREATING CUSTOMER ACCOUNT");
        System.out.println("1. CONTINUE");
        System.out.println("2. RETURN TO LOGIN");

    }

    public void printCreateEmployeeAccountDisplay() {
        System.out.println("CREATE EMPLOYEE ACCOUNT");
        System.out.println("Input options");
        System.out.println("1. Continue to account Creation");
        System.out.println("2. Return");

    }


    public void printCreateAdminAccountDisplay() {
        System.out.println("CREATE ADMIN ACCOUNT");
        System.out.println("Input options");
        System.out.println("1. Continue to account Creation");
        System.out.println("2. Return");
    }
}
