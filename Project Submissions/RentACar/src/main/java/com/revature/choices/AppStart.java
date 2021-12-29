package com.revature.choices;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.revature.logging.Logging;
import com.revature.repository.UsersRepository;
import com.revature.repository.UsersRepositoryImplement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppStart {

    Logger myLogger = LoggerFactory.getLogger(Logging.class);

    UsersRepository usersRepository = new UsersRepositoryImplement();
    Scanner scan = new Scanner(System.in);

    public void start() {
        // logging

        myLogger.info("This is where they sign in.");
        int signRegister = 0;

        do {
            try {
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println("=================================");
                System.out.println("=================================");
                System.out.println("Welcome to Rent-A-Car!");
                System.out.println("=================================");
                System.out.println("=================================");
                System.out.println("Type '1' to Register: ");
                System.out.println("Type '2' to Sign in: ");
                System.out.println("Type '3' for Employees: ");
                System.out.println("Type '4' for Admin: ");
                System.out.println("Type '5' to EXIT: ");
                System.out.println();
                signRegister = scan.nextInt();
            } catch (InputMismatchException e) {

                System.out.println("Oops. Please type a number.");
                System.out.println();

            }
            scan.nextLine(); // clears the buffer
        } while (signRegister <= 0 || signRegister > 6);

        SignedIn signIn = new SignedIn();
        if (signRegister == 1) {
            // Register
            myLogger.info("Register sign in");
            Register register = new Register();
            register.registration();
            signIn.signInChoice();

        }

        if (signRegister == 2) {
            // signed into account
            myLogger.info("Customer sign in");
            signIn.signInChoice();

        }

        if (signRegister == 3) {
            // employee sign in
            myLogger.info("Employee sign in");
            EmployeeSignIn employee = new EmployeeSignIn();
            employee.employee();
        }

        if (signRegister == 4) {
            // admin
            myLogger.info("Admin sign in");
            Admin admin = new Admin();
            admin.admin();
        }

        if (signRegister == 5) {
            myLogger.info("exited application");

            System.out.println();
            System.out.println("Goodbye!");
            System.exit(0);
            scan.close();
        }
    }
}