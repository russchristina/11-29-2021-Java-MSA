package com.revature.choices;

import java.util.Scanner;

import com.revature.logging.Logging;
import com.revature.model.Users;
import com.revature.repository.UsersRepository;
import com.revature.repository.UsersRepositoryImplement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Register {

    Scanner scan = new Scanner(System.in);
    Logger myLogger = LoggerFactory.getLogger(Logging.class);
    UsersRepository usersRepository = new UsersRepositoryImplement();

    public void registration() {

        int signRegister = 1;
        String registerUserName = "";
        String registerPassword = "";
        String registerSecondaryYesNo;
        String registeredSecondUser = "none";
        String registerAddress = "";
        String registerDOB = "";

        if (signRegister == 1) {
            System.out.println(" ---------------------------- ");
            System.out.println("Welcome to registration at RentACar");
            System.out.println(" ----------------------------- ");

            System.out.println();
            System.out.print("Type your username: ");
            System.out.println();

            registerUserName = scan.nextLine();

            String user_name_InDB = usersRepository.retrieveUser(registerUserName);

            System.out.println();

            while (registerUserName.equals(user_name_InDB)) {
                System.out.println();
                System.out.println("Oops. That name's been taken, please try another.");
                System.out.println();

                registerUserName = scan.nextLine();

            }

            System.out.print("Type your password: ");
            System.out.println();

            registerPassword = scan.nextLine();

            System.out.println("Would you like to make a second user (y/n): ");
            System.out.println();

            registerSecondaryYesNo = scan.nextLine();

            if (registerSecondaryYesNo.equals("y")) {
                System.out.println("What is your second user's name?");
                System.out.println();
                registeredSecondUser = scan.nextLine();
                usersRepository.changeOrAddSecondaryUser(registeredSecondUser, registerUserName);
            }

            System.out.println("Type your address (Street, City, State, Zip): ");
            System.out.println();

            registerAddress = scan.nextLine();

            System.out.println("Your date of birth MM-DD-YYYY: ");
            System.out.println();

            registerDOB = scan.nextLine();

            // REGISTER a complete new user

            Users newUser = new Users(4, registerUserName, registerPassword, registeredSecondUser, (float) 0.0,
                    registerAddress,
                    registerDOB);
            myLogger.info("A new user has been registered with the following information. " + registerUserName + ","
                    + registerPassword + "," + registeredSecondUser + "," + (float) 0.0 + "," +
                    registerAddress + "," +
                    registerDOB);

            usersRepository.addUsers(newUser);

        }
        System.out.println();
        System.out.println();
        System.out.println();
        AppStart start = new AppStart();
        start.start();
    }
}