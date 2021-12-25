package com.revature.project;

import com.revature.project.util.Driver;
import com.revature.project.util.UserDB;
import com.revature.project.util.UserDBImplementation;
import com.revature.project.util.UserSpecs;
import sun.applet.Main;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserOptions {
    UserDB data = new UserDBImplementation();
    Scanner newUser = new Scanner(System.in);
    UserDB userDB = new UserDBImplementation();
    public void newAccount() {
        newUser = new Scanner(System.in);
        UserAccounts allAccounts = new UserAccounts();
        switch (MainDisplay.getUnregisteredDecision()) {
            case 1: //Create New Account and return to login screen
                System.out.println("Enter desired username.");
                String newUsername = newUser.nextLine();
                System.out.println("Enter desired password");
                String newPassword = newUser.nextLine();
                UserSpecs userSpecs = new UserSpecs(0, newUsername, newPassword);
                data.save(userSpecs);
                System.out.println("Thank you for signing up! Returning to login page.");
                MainDisplay mainDisplay = new MainDisplay();

                break;


            case 2: //Return to login page
                mainDisplay = new MainDisplay();

                break;
            case 3: //Return to login page
                System.out.println("Thank you for visiting. Goodbye! ");
                newUser.close();

                break;

        }
    }

    public void loggedIn() {
        System.out.println("Welcome, " + MainDisplay.getUsername() + ". Please select the number corresponding to your" +
                " desired action:\n" +
                "1: Link new account\n" +
                "2: Manage funds (add or remove)\n" +
                "3: Transfer funds to linked accounts\n" +
                "4: Log out");
        try {
            MainDisplay.setRegisterdDecision(newUser.nextInt());

            switch (MainDisplay.getRegisteredDecision()) {
                case 1: //Link new account
                    System.out.println("Enter desired username for new user.");
                    newUser.nextLine();
                    String newUsername = newUser.nextLine();
                    System.out.println("Enter desired password for new user " + newUsername);
                    String newPassword = newUser.nextLine();
                    String userID = userDB.findPass(MainDisplay.getUsername()).get(0);
                    int parsedInt = Integer.parseInt(userID);
                    UserSpecs userSpecs = new UserSpecs(0, newUsername, newPassword);
                    data.saveToChild(userSpecs);
                    System.out.println("New user " + newUsername + " has been successfully linked to your account!" +
                            " Returning to main menu.");
                    new UserOptions().loggedIn();
                    break;
                case 2: //manage funds (add or remove)
                    break;
                case 3: //Transfer funds to linked accounts
                    break;
                case 4: //Manage linked accounts

                    break;
                case 5: //Log Out
                    break;

                default:
                    System.out.println("Invalid input. Only the numbers listed on the main menu are valid inputs. " +
                            "Returning to main menu." +
                            "\n------------------------------------------------------------------------------------");
                    new UserOptions().loggedIn();
            }
            }catch(InputMismatchException e){
                System.out.println("Invalid input. Only the numbers listed on the main menu are valid inputs. " +
                        "Returning to main menu." +
                        "\n------------------------------------------------------------------------------------");
            new UserOptions().loggedIn();
            }


        }
    }

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
