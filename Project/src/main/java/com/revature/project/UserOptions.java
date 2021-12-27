package com.revature.project;

import com.revature.project.util.*;
import com.sun.org.apache.xpath.internal.operations.Mod;

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
            default:
                System.out.println("That number was not recongnized as an option. Please try again. Error code: FW662B" +
                        " \n");
                new MainDisplay();

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
                    System.out.println("New user " + newUsername + " has been successfully linked to your account!\n" +
                            "Returning to main menu." +
                            "\n------------------------------------------------------------------------------------");
                    new UserOptions().loggedIn();
                    break;
                //---------------------------------------------------------------------------------------------------

                case 2: //manage funds (add or remove)
                    System.out.printf("Current balance: " + "%,d", returnFunds());
                    System.out.println(" Glizzies");
                    System.out.println("\n" +
                            " 1: Add Funds\n " +
                            "2: Subtract funds ");
                    if (newUser.hasNextInt()) {
                        int choice = newUser.nextInt();
                        manageFunds(choice);
                        new UserOptions().loggedIn();
                        break;
                    } else {
                        System.out.println("Invalid input. Only the numbers listed on the main menu are valid inputs. " +
                                "Returning to main menu. Error code: ED1JKK " +
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
                    System.out.println("Invalid input. Only the numbers listed on the main menu are valid inputs. " +
                            "Returning to main menu. Error code: FL09YN" +
                            "\n------------------------------------------------------------------------------------");
                    new UserOptions().loggedIn();
            }
        } catch (InputMismatchException e) {
            System.out.println("INVALID input. Only the numbers listed on the main menu are valid inputs. " +
                    "Returning to main menu. Error code: G7VL0E"  +
                    "\n------------------------------------------------------------------------------------");
            new UserOptions().loggedIn();
        }


    }


    public void employeeLoggedIn() {
       boolean isAdmin = Boolean.parseBoolean(userDB.findEmployeeInfo(MainDisplay.getUsername()).get(3));
        if (isAdmin){
            adminEmployeeLoggedIn();
        }else {
            System.out.println("Welcome, Glizzard Wizzard " + MainDisplay.getUsername() + ". Please select the number corresponding to your" +
                    " desired action:\n" +
                    "1: View or delete main user accounts\n" +
                    "2: View or delete child user accounts\n" +
                    "3: Log out");
            try {
                MainDisplay.setRegisterdDecision(newUser.nextInt());

                switch (MainDisplay.getRegisteredDecision()) {
                    case 1:
                        ModifyUsers.returnUserTable();
                        ModifyUsers.deleteMainUser();
                        break;
                    case 2:
                        ModifyUsers.returnChildUserTable();
                        ModifyUsers.deleteChildUser();
                        break;
                    case 3:
                        new MainDisplay();
                        break;
                    //--------------------------------------------------------------------------------------------------
                    default:
                        System.out.println("Invalid input. Only the numbers listed on the main menu are valid inputs. " +
                                "Returning to main menu. Error code: FL09YN" +
                                "\n------------------------------------------------------------------------------------");
                        new UserOptions().employeeLoggedIn();
                }
            } catch (InputMismatchException e) {
                System.out.println("INVALID input. Only the numbers listed on the main menu are valid inputs. " +
                        "Returning to main menu. Error code: G7VL0E"  +
                        "\n------------------------------------------------------------------------------------");
                new UserOptions().employeeLoggedIn();
            }
        }



    }
    public void adminEmployeeLoggedIn() {
        System.out.println("Welcome to the Glizzy Gladiator Admin Console, " + MainDisplay.getUsername() +
                " What would you like to do?\n1: View or delete any account +\n" +
                "2: Modify Accounts\n" +
                "3: Log Out");
        try {
            MainDisplay.setRegisterdDecision(newUser.nextInt());

            switch (MainDisplay.getRegisteredDecision()) {
                case 1:
                  deleteUser();

                    //--------------------------------------------------------------------------------------------------
                default:
                    System.out.println("Invalid input. Only the numbers listed on the main menu are valid inputs. " +
                            "Returning to main menu. Error code: FL09YN" +
                            "\n------------------------------------------------------------------------------------");
                    new UserOptions().employeeLoggedIn();
            }
        } catch (InputMismatchException e) {
            System.out.println("INVALID input. Only the numbers listed on the main menu are valid inputs. " +
                    "Returning to main menu. Error code: G7VL0E"  +
                    "\n------------------------------------------------------------------------------------");
            new UserOptions().employeeLoggedIn();
        }


    }

    private int returnFunds() {
        String name = MainDisplay.getUsername();
        funds = Integer.parseInt(userDB.findInfo(name).get(3));
        return funds;
    }

    private int returnChildFunds() {
        String name = String.valueOf(userDB.findChildInfo("John").get(3));
        funds = Integer.parseInt(userDB.findInfo(name).get(3));
        return funds;
    }

    private void addQuickMaffs(int funds) {
        if (funds < 0){
            System.out.println("Cannot withdraw a negative number. ");
            new UserOptions().loggedIn();
        }
        else {


            int result = funds + this.funds;
            System.out.printf("Your new balance is " + "%,d", result);
            UserSpecs specs = new UserSpecs(0, MainDisplay.getUsername(), "null", result);
            userDB.updateFunds(specs);
            System.out.println("\nReturning to main menu." +
                    "\n------------------------------------------------------------------------------------");
        }
    }

    private void removeQuickMaffs(int funds) {
        int result = 0;
        if (funds < 0){
            System.out.println("Cannot withdraw a negative number");
            new UserOptions().loggedIn();
        }
        else if (this.funds - funds < 0) {
            System.out.println("Insufficient funds" +
                    "\n------------------------------------------------------------------------------------");
            new UserOptions().loggedIn();
        } else {
            result = this.funds - funds;
            System.out.printf("Your new balance is " + "%,d", result);
            UserSpecs specs = new UserSpecs(0, MainDisplay.getUsername(), "null", result);
            userDB.updateFunds(specs);
            System.out.println("\nReturning to main menu." +
                    "\n------------------------------------------------------------------------------------");
        }

    }

    private void quickChildMaffs(int funds) {
        int result = 0;
        String childAdmin = String.valueOf(userDB.findChildInfo(MainDisplay.getUsername()).get(3));
        if (funds < 0){
            System.out.println("Cannot withdraw a negative number" +
                    "\n------------------------------------------------------------------------------------");
            new UserOptions().childLoggedIn();
        }
        else if (this.funds - funds < 0) {
            System.out.println("Insufficient funds. You'll have to ask " + childAdmin + " to" +
                    " add more funds." +
                    "\n------------------------------------------------------------------------------------");
            new UserOptions().childLoggedIn();
        } else {
            childAdmin = String.valueOf(userDB.findChildInfo(MainDisplay.getUsername()).get(3));
            result = this.funds - funds;
            System.out.printf("Your new balance is " + "%,d", result);
            UserSpecs specs = new UserSpecs(0, childAdmin, "null", result);
            userDB.updateFunds(specs);
            System.out.println("\nReturning to main menu." +
                    "\n------------------------------------------------------------------------------------");
        }
    }


    private void manageFunds(int choice) {
        int result = 0;
        int amount = 0;
        switch (choice) {
            case 1:
                System.out.println("Enter desired value.");
                if (newUser.hasNextInt()) {
                    amount = newUser.nextInt();
                    if (amount > 0) {
                        addQuickMaffs(amount);
                        break;
                    } else {
                        System.out.println("Invalid Input. If you would like to withdraw," +
                                " please use the withdraw option (2). Error code: NVI00P  " +
                                "\n------------------------------------------------------------------------------------");
                        new UserOptions().loggedIn();
                    }
                    break;
                } else {
                    System.out.println("Invalid input. Error code: VV3WED" +
                            "\n------------------------------------------------------------------------------------");
                    new UserOptions().loggedIn();

                }

            case 2:
                System.out.println("Enter desired value. ");
                if (newUser.hasNextInt()) {
                    amount = newUser.nextInt();
                    removeQuickMaffs(amount);
                    break;


                }
            default:
                System.out.println("Invalid input");
        }
    }


    private void manageChildFunds(int amount) {
        switch (amount) {
            case 1:
                System.out.println("Enter desired value. ");
                if (newUser.hasNextInt()) {
                    amount = newUser.nextInt();
                    quickChildMaffs(amount);
                    break;
                }
            case 2:
                System.out.println("\nReturning to main menu." +
                        "\n------------------------------------------------------------------------------------");
                new UserOptions().childLoggedIn();

            default:
                System.out.println("Invalid input. Error code : DK7GWD " +
                        "\n------------------------------------------------------------------------------------");
                new UserOptions().childLoggedIn();
        }
    }


    public void childLoggedIn() {
        System.out.println("Welcome, " + MainDisplay.getUsername() + ". Please select the number corresponding to your" +
                " desired action:\n" +
                "1: View account funds\n" +
                "2: Log out");
        try {
            MainDisplay.setRegisterdDecision(newUser.nextInt());

            switch (MainDisplay.getRegisteredDecision()) {

                //---------------------------------------------------------------------------------------------------

                case 1: //View balance (read only)
                    String childAdmin = String.valueOf(userDB.findChildInfo(MainDisplay.getUsername()).get(3));
                    System.out.printf("Current balance: " + "%,d", returnChildFunds());
                    System.out.println(" Glizzies");
                    System.out.println("\n" +
                            "1: Subtract funds \n" +
                            "2: Main Menu");
                    if (newUser.hasNextInt()) {
                        int choice = newUser.nextInt();
                        manageChildFunds(choice);
                        new UserOptions().loggedIn();
                        break;
                    } else {
                        System.out.println("Invalid input. Only the numbers listed on the main menu are valid inputs. " +
                                "Returning to main menu. Error code: BYVO0U" +
                                "\n------------------------------------------------------------------------------------");
                        new UserOptions().loggedIn();
                    }

                case 2: //Logout
                    System.out.println("\nReturning to login screen." +
                            "\n------------------------------------------------------------------------------------");
                    new MainDisplay();
                    break;

                default:
                    System.out.println("Invalid input. Only the numbers listed on the main menu are valid inputs. " +
                            "Returning to main menu. Error code: C3HQAH" +
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
    private void deleteUser(){
        boolean isAdmin = Boolean.parseBoolean(userDB.findEmployeeInfo(MainDisplay.getUsername()).get(3));
        int userType = 0;
        if (isAdmin){
            System.out.println("What type of user would you like to view? Enter (0) to return to the Main Menu\n" +
                    " 1: Main User\n" +
                    "2: Child User\n" +
                    "3: Employee");
            userType = newUser.nextInt();
            switch (userType){
                case 1: // Main User
                    ModifyUsers.returnUserTable();
                    System.out.println("Select the user ID of the user you would like to delete");
                    ModifyUsers.deleteMainUser();
                    break;
                default:
                    System.out.println("Invalid input");
                case 2: //Child User
                    ModifyUsers.returnChildUserTable();
                    System.out.println("Select the user ID of the user you would like to delete");

                break;
                case 3:
                    ModifyUsers.returnEmployeeTable();
                    ModifyUsers.deleteEmployeeUser();
                    break;
                case 0:
                    new UserOptions().adminEmployeeLoggedIn();

            }

        }
        else{
            ModifyUsers.returnChildUserTable();
            ModifyUsers.deleteChildUser();
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
