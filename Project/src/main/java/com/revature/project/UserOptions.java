package com.revature.project;

import com.revature.project.util.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserOptions {
    UserDB data = new UserDBImplementation();
    Scanner newUser = new Scanner(System.in);
    UserDB userDB = new UserDBImplementation();
    CustomExceptions exceptions = new CustomExceptions();
    int funds;

    public void newAccount() {
        newUser = new Scanner(System.in);
        UserAccounts allAccounts = new UserAccounts();
        switch (MainDisplay.getUnregisteredDecision()) {
            case 1: //Create New Account and return to login screen
                System.out.println("Enter desired username.");
                String newUsername = newUser.nextLine();
                System.out.println("Enter desired password");
                String newPassword = newUser.nextLine();
                UserSpecs userSpecs = new UserSpecs(0, newUsername, newPassword, 0);
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
//                    String userID = userDB.findInfo(MainDisplay.getUsername()).get(0);
//                    int parsedInt = Integer.parseInt(userID);
                    UserSpecs userSpecs = new UserSpecs(0, newUsername, newPassword, 0);
                    data.saveToChild(userSpecs);
                    System.out.println("New user " + newUsername + " has been successfully linked to your account!" +
                            "Returning to main menu." +
                            "\n------------------------------------------------------------------------------------");
                    new UserOptions().loggedIn();
                    break;
                //---------------------------------------------------------------------------------------------------

                case 2: //manage funds (add or remove)
                    System.out.printf("Current balance: $" + "%,d", returnFunds(MainDisplay.getUsername()));
                    System.out.println("\n" +
                            " 1: Add Funds\n " +
                            "2: Subtract funds ");
                    if (newUser.hasNextInt()) {
                        int choice = newUser.nextInt();
                        manageFunds(choice);
                        new UserOptions().loggedIn();
                        break;
                    } else {
                        System.out.println("INVAF input. Only the numbers listed on the main menu are valid inputs. " +
                                "Returning to main menu." +
                                "\n------------------------------------------------------------------------------------");
                        new UserOptions().loggedIn();
                    }
                case 3: //Transfer funds to linked accounts
                    break;
                case 4: //Manage linked accounts
                    System.out.println("\nReturning to login screen." +
                            "\n------------------------------------------------------------------------------------");
                    new MainDisplay();
                    break;
                case 5: //Log Out
                    break;

                default:
                    System.out.println("INVALIDDD input. Only the numbers listed on the main menu are valid inputs. " +
                            "Returning to main menu." +
                            "\n------------------------------------------------------------------------------------");
                    new UserOptions().loggedIn();
            }
        } catch (InputMismatchException e) {
            System.out.println("INVALID input. Only the numbers listed on the main menu are valid inputs. " +
                    "Returning to main menu." +
                    "\n------------------------------------------------------------------------------------");
            new UserOptions().loggedIn();
        }


    }

    private int returnFunds(String name) {
        name = MainDisplay.getUsername();
        funds = Integer.parseInt(userDB.findInfo(name).get(3));
        return funds;
    }

    private int addQuickMaffs(int funds) {

        int result = funds + this.funds;
        System.out.printf("Your new balance is " + "%,d", result);
        UserSpecs specs = new UserSpecs(0, MainDisplay.getUsername(), "null", result);
        userDB.updateFunds(specs);
        System.out.println("\nReturning to main menu." +
                "\n------------------------------------------------------------------------------------");
        return result;

    }

    private int removeQuickMaffs(int funds) {
       int result = 0;
        if (this.funds + funds < 0) {
            System.out.println("Insufficient funds BOZO");
            new UserOptions().loggedIn();
        } else {
                result = this.funds + funds;
                System.out.printf("Your new balance is " + "%,d", result);
                UserSpecs specs = new UserSpecs(0, MainDisplay.getUsername(), "null", result);
                userDB.updateFunds(specs);
                System.out.println("\nReturning to main menu." +
                        "\n------------------------------------------------------------------------------------");
            }
        return result;

    }




    private void manageFunds(int choice) {
        int result = 0;
        int amount = 0;
        switch (choice) {
            case 1:
                System.out.println("Enter desired value. To withdraw, input a minus before the number");
                if (newUser.hasNextInt()) {
                    amount = newUser.nextInt();
                    if (amount > 0) {
                        addQuickMaffs(amount);
                        break;
                    } else {
                        System.out.println("Invalid Input. If you would like to withdraw, please use the withdraw option. ");
                        new UserOptions().loggedIn();
                    }
                    break;
                } else {
                    System.out.println("Only numbers BOZO");
                    new UserOptions().loggedIn();

                }

            case 2:
                System.out.println("Enter desired value. To withdraw, input a minus before the number");
                if (newUser.hasNextInt()) {
                    amount = newUser.nextInt();
                        removeQuickMaffs(amount);
                        break;



                }
            default:
                System.out.println("Invalid input");
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
