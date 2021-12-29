package com.revature.choices;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.revature.logging.Logging;
import com.revature.repository.EmployeeRepository;
import com.revature.repository.EmployeeRepositoryImplement;
import com.revature.repository.UsersRepository;
import com.revature.repository.UsersRepositoryImplement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmployeeSignIn {

    Scanner scan = new Scanner(System.in);
    EmployeeRepository employeeRepository = new EmployeeRepositoryImplement();
    UsersRepository usersRepository = new UsersRepositoryImplement();
    Logger myLogger = LoggerFactory.getLogger(Logging.class);

    String signedUser;
    String user_name;

    public void employee() {

        System.out.println("Please enter your employee name (case sensitive): ");

        signedUser = scan.nextLine();

        String userInDB = employeeRepository.retrieveEmployee(signedUser);

        while (!signedUser.equals(userInDB)) {
            System.out.println("Oops. We don't have that employee name, try again.");
            signedUser = scan.nextLine();
            userInDB = employeeRepository.retrieveEmployee(signedUser);

        }

        System.out.println("Password: ");

        String password = scan.nextLine();

        String passwordInDB = employeeRepository.retrievePassword(signedUser);

        System.out.println();

        while (!password.equals(passwordInDB)) {
            System.out.println("Oops. Wrong password, please try again.");
            password = scan.nextLine();

        }

        // MAIN MENU
        System.out.println();
        System.out.println("Welcome " + signedUser + ".");
        myLogger.info(signedUser + " is signed in");

        options();
    }

    public void options() {

        int choice = 0;
        do {
            try {
                System.out.println();
                System.out.println("What would you like to do?");
                System.out.println("Type '1' to look at users on an account");
                System.out.println("Type '2' for personal info on an account");
                System.out.println("Type '3' for account balance of an account");
                System.out.println("Type '4' to cancel an existing account");
                System.out.println("Type '5' to sign out");
                System.out.println();

                choice = scan.nextInt();
            } catch (InputMismatchException e) {

                System.out.println("Oops. Please type an option.");
                System.out.println();

            }
        } while (choice < 0 || choice > 6);

        if (choice != 5) {

            scan.nextLine();

            System.out.println();
            System.out.println("Please type in a users account name (case sensitive): ");
            System.out.println();

            user_name = scan.nextLine();
            myLogger.info(signedUser + " signed in to " + user_name + "'s account");

            String user_name_InDB = usersRepository.retrieveUser(user_name);

            while (!user_name.equals(user_name_InDB)) {
                System.out.println();
                System.out.println("Oops. We don't have that username, try again.");
                System.out.println();
                user_name = scan.nextLine();
                user_name_InDB = usersRepository.retrieveUser(user_name);

            }
        }

        if (choice == 1) {
            // look at users on an account
            String secondary_user = usersRepository.viewSecondaryUser(user_name);
            if (secondary_user.equals("none")) {
                System.out.println();
                System.out.println(user_name + " has 0 users on their account.");
                System.out.println();
            } else {
                System.out.println();
                System.out.println(user_name + " has " + secondary_user + " as their secondary user.");
                System.out.println();
            }

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
            options();
        }

        if (choice == 2) {
            // personal info on an account.
            String personal_info = usersRepository.viewUserPersonalInfo(user_name);
            String dob_info = usersRepository.viewUserDOB(user_name);
            System.out.println();
            System.out.println(user_name + "'s address is " + personal_info);
            System.out.println("Their date of birth is " + dob_info);
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
            options();
        }

        if (choice == 3) {
            // account balance on account.
            float users_balance = usersRepository.viewUserAccountBalance(user_name);
            System.out.println();
            System.out.println(user_name + " has " + users_balance + " in their account.");
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
            options();
        }

        if (choice == 4) {
            // cancel an existing account.
            usersRepository.deleteUsers(user_name);

            System.out.println();
            System.out.println(user_name + " has been deleted from the system.");
            System.out.println();

            myLogger.info(signedUser + " deleted " + user_name + "'s account");

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
            options();
        }

        if (choice == 5) {
            AppStart start = new AppStart();
            start.start();
        }
    }
}