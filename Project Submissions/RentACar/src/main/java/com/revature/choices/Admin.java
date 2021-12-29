package com.revature.choices;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.revature.logging.Logging;
import com.revature.repository.AdministratorRepository;
import com.revature.repository.AdministratorRepositoryImplement;
import com.revature.repository.UsersRepository;
import com.revature.repository.UsersRepositoryImplement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Admin {

    Scanner scan = new Scanner(System.in);
    AdministratorRepository adminRepository = new AdministratorRepositoryImplement();
    UsersRepository usersRepository = new UsersRepositoryImplement();
    Logger myLogger = LoggerFactory.getLogger(Logging.class);

    String signedUser;
    String user_name;

    int oneOrTwo;

    public void admin() {

        System.out.print("Please enter your administrator name (case sensitive): ");
        System.out.println();

        signedUser = scan.nextLine();

        String userInDB = adminRepository.retrieveAdmin(signedUser);

        while (!signedUser.equals(userInDB)) {
            System.out.println("Oops. We don't have that administrator name, try again.");
            signedUser = scan.nextLine();
            userInDB = adminRepository.retrieveAdmin(signedUser);

        }

        System.out.print("Password: ");
        System.out.println();

        String password = scan.nextLine();

        String passwordInDB = adminRepository.retrievePassword(signedUser);

        System.out.println();

        while (!password.equals(passwordInDB)) {
            System.out.println("Oops. Wrong password, please try again.");
            System.out.println();
            password = scan.nextLine();

        }

        // MAIN MENU
        System.out.println();
        System.out.println("========================");
        System.out.println("Welcome " + signedUser + ".");
        System.out.println("========================");

        options();
    }

    public void options() {

        int choice = 0;
        do {
            try {
                System.out.println();
                System.out.println("--------------------------------");
                System.out.println("What would you like to do?");
                System.out.println("Type '1' to modify account balances");
                System.out.println("Type '2' to modify personal information");
                System.out.println("Type '3' to cancel an existing account");
                System.out.println("Type '4' to sign out");
                System.out.println();

                choice = scan.nextInt();
            } catch (InputMismatchException e) {

                System.out.println("Oops. Please type an option.");
                System.out.println();

            }
            // scan.nextLine();
        } while (choice < 0 || choice > 5);

        if (choice != 4) {

            scan.nextLine();

            System.out.println("Please type in a user's account name (case sensitive): ");
            System.out.println();

            user_name = scan.nextLine();

            String user_name_InDB = usersRepository.retrieveUser(user_name);

            while (!user_name.equals(user_name_InDB)) {
                System.out.println("Oops. We don't have that username, try again.");
                System.out.println();
                user_name = scan.nextLine();
                user_name_InDB = usersRepository.retrieveUser(user_name);

            }
        }

        if (choice == 1) {
            // modify account balances
            float acctBalance = usersRepository.viewUserAccountBalance(user_name);

            System.out.println();
            System.out.println("Users balance is " + acctBalance);
            System.out.println();

            float newBalance = -1;

            do {
                try {

                    System.out.println("What would you like the balance to be? ");
                    System.out.println();

                    newBalance = scan.nextFloat();

                    if (newBalance < 0) {
                        System.out.println();
                        System.out.println("Customer cannot have a negative balance.");
                        System.out.println("Please try again");
                        System.out.println();
                    }

                } catch (InputMismatchException e) {
                    System.out.println("Please input a number");
                    System.out.println();
                }
                scan.nextLine();
            } while (newBalance < 0);

            usersRepository.updateBalance(newBalance, user_name);
            float newestBalance = usersRepository.viewUserAccountBalance(user_name);

            System.out.println();
            System.out.println(user_name + " has $" + newestBalance + " in their account.");
            myLogger.info("Admin has changed " + user_name + "'s balance to " + newestBalance);

            oneOrTwo = 0;
            do {
                try {
                    System.out.println("Press '1' to go back");

                    oneOrTwo = scan.nextInt();

                } catch (InputMismatchException e) {
                    System.out.println("Oops.  Please type '1'");
                }
                scan.nextLine();
            } while (oneOrTwo != 1);

            options();

        }

        if (choice == 2) {

            // modify personal information
            String address = usersRepository.viewUserPersonalInfo(user_name);
            String dob_info = usersRepository.viewUserDOB(user_name);

            System.out.println();
            System.out.println(user_name + "'s address is " + address);
            System.out.println("Their date of birth is " + dob_info);
            System.out.println();

            oneOrTwo = 0;

            do {

                try {
                    System.out.println();
                    System.out.println("Would you like to: ");
                    System.out.println("Type '1' to update user's address");
                    System.out.println("Type '2' to update user's date of birth");
                    System.out.println();
                    oneOrTwo = scan.nextInt();

                } catch (InputMismatchException e) {
                    System.out.println("Oops.  Please type a number.");
                    System.out.println();
                }
                scan.nextLine();
            } while (oneOrTwo < 0 || oneOrTwo > 3);

            if (oneOrTwo == 1) {
                System.out.println("Please type user's new address: ");
                System.out.println();

                String newAddress = scan.nextLine();

                usersRepository.updateUsersAddress(newAddress, user_name);

                System.out.println(user_name + "'s new address is " + newAddress);
                System.out.println();
                myLogger.info("Admin changed " + user_name + "'s address to " + newAddress);

            } else if (oneOrTwo == 2) {
                System.out.println("Please type user's new date of birth (MM/DD/YYYY)");
                System.out.println();

                String newDOB = scan.nextLine();

                usersRepository.updateUsersDOB(newDOB, user_name);

                System.out.println(user_name + "'s new date of birth is " + newDOB);
                System.out.println();
                myLogger.info("Admin changed " + user_name + "'s birthday to " + newDOB);
            }

            oneOrTwo = 0;
            do {

                try {
                    System.out.println("Press '1' to go back");
                    System.out.println();

                    oneOrTwo = scan.nextInt();

                } catch (InputMismatchException e) {
                    System.out.println("Oops.  Please type '1'");
                    System.out.println();
                }
                scan.nextLine();
            } while (oneOrTwo != 1);

            options();

        }

        if (choice == 3) {
            // cancel an existing account.
            usersRepository.deleteUsers(user_name);
            System.out.println();
            System.out.println(user_name + " has been deleted from the system.");
            myLogger.info("Admin deleted " + user_name + "'s account");

            oneOrTwo = 0;
            do {

                try {
                    System.out.println();
                    System.out.println("Press '1' to go back");
                    System.out.println();
                    oneOrTwo = scan.nextInt();

                } catch (

                InputMismatchException e) {
                    System.out.println("Oops.  Please type '1'");
                    System.out.println();
                }
                scan.nextLine();
            } while (oneOrTwo != 1);
            options();

        }

        if (choice == 4) {
            AppStart start = new AppStart();
            start.start();
        }
    }
}
