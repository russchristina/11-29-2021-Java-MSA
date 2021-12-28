package com.revature.project.util;

import com.revature.project.MainDisplay;
import com.revature.project.UserOptions;

import java.util.List;
import java.util.Scanner;

public class ModifyUsers {
    UserDB userDB = new UserDBImplementation();
    Scanner userInput = new Scanner(System.in);

    public void returnUserTable() {
        List<UserSpecs> userSpecsList;
        userSpecsList = userDB.findAll();
        System.out.println("-----------------------LIST START-------------------------\n");

        System.out.println(userSpecsList);

        System.out.println("-----------------------END OF LIST---------------------------------");

    }

    public void returnChildUserTable() {
        List<ChildUserSpecs> childUserSpecsList;
        childUserSpecsList = userDB.findAllChildren();
        System.out.println("-----------------------LIST START-------------------------\n");

        System.out.println(childUserSpecsList);

        System.out.println("-----------------------END OF LIST---------------------------------");

    }

    public void returnEmployeeTable() {
        List<EmployeeUserSpecs> employeeUserSpecsList;
        employeeUserSpecsList = userDB.findAllEmployees();
        System.out.println("-----------------------LIST START-------------------------\n");

        System.out.println(employeeUserSpecsList);

        System.out.println("-----------------------END OF LIST---------------------------------");

    }

    public void deleteMainUser() {
        userInput = new Scanner(System.in);
        int option = 0;
        boolean isAdmin = Boolean.parseBoolean(userDB.findEmployeeInfo(MainDisplay.getUsername()).get(3));

        System.out.println("What would you like to do?\n" +
                "1: Delete a user\n" +
                "2: Return Main Menu");
        option = userInput.nextInt();
        switch (option) {
            case 1:
                System.out.println("Enter the ID of a user listed above to delete: ");
                int user = userInput.nextInt();
                UserSpecs specs = new UserSpecs(user, "", "", 0);
                userDB.delete(specs);

                System.out.println("User deleted. Returning to Main Menu \n" +
                        "------------------------------------------------------------------------");
                if (isAdmin) {

                    new UserOptions().adminEmployeeLoggedIn();
                } else {
                    new UserOptions().employeeLoggedIn();
                }
                break;
            case 2:
                if (isAdmin) {
                    System.out.println("Returning to Main Menu \n" +
                            "------------------------------------------------------------------------");
                    new UserOptions().adminEmployeeLoggedIn();
                } else {
                    new UserOptions().employeeLoggedIn();
                }

                break;
            default:
                System.out.println("Returning to Main Menu \n" +
                        "------------------------------------------------------------------------");
        }

    }

    public void deleteChildUser() {
        userInput = new Scanner(System.in);
        int option = 0;
        boolean isAdmin = Boolean.parseBoolean(userDB.findEmployeeInfo(MainDisplay.getUsername()).get(3));

        System.out.println("What would you like to do?\n" +
                "1: Delete a user\n" +
                "2: Return Main Menu");
        option = userInput.nextInt();
        switch (option) {
            case 1:
                System.out.println("Enter the ID of a user listed above to delete: ");
                int user = userInput.nextInt();
                ChildUserSpecs specs = new ChildUserSpecs(user, "", "", "");
                userDB.deleteChild(specs);

                System.out.println("User deleted. Returning to Main Menu \n" +
                        "------------------------------------------------------------------------");
                if (isAdmin) {

                    new UserOptions().adminEmployeeLoggedIn();
                } else {
                    new UserOptions().employeeLoggedIn();
                }
                break;
            case 2:
                if (isAdmin) {
                    System.out.println("Returning to Main Menu \n" +
                            "------------------------------------------------------------------------");
                    new UserOptions().adminEmployeeLoggedIn();
                } else {
                    new UserOptions().employeeLoggedIn();
                }

                break;
            default:
                System.out.println("Returning to Main Menu \n" +
                        "------------------------------------------------------------------------");
        }

    }

    public void deleteEmployeeUser() {
        userInput = new Scanner(System.in);
        int option = 0;
        boolean isAdmin = Boolean.parseBoolean(userDB.findEmployeeInfo(MainDisplay.getUsername()).get(3));

        System.out.println("What would you like to do?\n" +
                "1: Delete a user\n" +
                "2: Return Main Menu");
        option = userInput.nextInt();
        switch (option) {
            case 1:
                System.out.println("Enter the ID of a user listed above to delete: ");
                int user = userInput.nextInt();
                EmployeeUserSpecs specs = new EmployeeUserSpecs(user, "", "", false);
                userDB.deleteEmployee(specs);

                System.out.println("User deleted. Returning to Main Menu \n" +
                        "------------------------------------------------------------------------");
                if (isAdmin) {

                    new UserOptions().adminEmployeeLoggedIn();
                } else {
                    new UserOptions().employeeLoggedIn();
                }
                break;
            case 2:
                if (isAdmin) {
                    System.out.println("Returning to Main Menu \n" +
                            "------------------------------------------------------------------------");
                    new UserOptions().adminEmployeeLoggedIn();
                } else {
                    new UserOptions().employeeLoggedIn();
                }

                break;
            default:
                System.out.println("Returning to Main Menu \n" +
                        "------------------------------------------------------------------------");
        }

    }

    public void adminUsernameSelection(int choice) {
        switch (choice) {
            case 1:
                System.out.println("Enter the current username of the user who's name you would like to change");
                String desiredUser = userInput.nextLine();
                try {
                    int userID = Integer.parseInt(userDB.findInfo(desiredUser).get(0));
                    updateUsername(userID);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Username not recognized. Consult the master list under the 'Modify Accounts' section" +
                            "for a list of all users across all tiers\n" +
                            "--------------------------------------------------------------------------------------------");
                    new UserOptions().adminEmployeeLoggedIn();
                }
            case 2:
                System.out.println("Enter the current username of the user who's name you would like to change");
                desiredUser = userInput.nextLine();
                try {
                    int userID = Integer.parseInt(userDB.findChildInfo(desiredUser).get(0));
                    updateChildUsername(userID);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Username not recognized. Consult the master list under the 'Modify Accounts' section" +
                            "for a list of all users across all tiers\n" +
                            "--------------------------------------------------------------------------------------------");
                    new UserOptions().adminEmployeeLoggedIn();
                    break;
                }
            case 3:
                System.out.println("Enter the current username of the user who's name you would like to change");
                desiredUser = userInput.nextLine();
                try {
                    int userID = Integer.parseInt(userDB.findEmployeeInfo(desiredUser).get(0));
                    updateEmployeeUsername(userID);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Username not recognized. Consult the master list under the 'Modify Accounts' section" +
                            "for a list of all users across all tiers\n" +
                            "--------------------------------------------------------------------------------------------");
                    new UserOptions().adminEmployeeLoggedIn();
                    break;

                }
            default:
                System.out.println("Invalid input. Only the numbers listed above are valid. Returning to Main Menu");
                new UserOptions().adminEmployeeLoggedIn();
        }
    }

    public void adminPasswordSelection(int choice) {
        switch (choice) {
            case 4:
                System.out.println("Enter the current username of the user who's password you would like to change");
                String desiredUser = userInput.nextLine();
                try {
                    String userPass = String.valueOf(userDB.findInfo(desiredUser).get(2));
                    updatePass(desiredUser);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Username not recognized. Consult the master list under the 'Modify Accounts' section" +
                            "for a list of all users across all tiers\n" +
                            "--------------------------------------------------------------------------------------------");
                    new UserOptions().adminEmployeeLoggedIn();
                }
            case 5:
                System.out.println("Enter the current username of the user who's password you would like to change");
                desiredUser = userInput.nextLine();
                try {
                    String userPass = String.valueOf(userDB.findChildInfo(desiredUser).get(2));
                    updateChildPass(desiredUser);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Username not recognized. Consult the master list under the 'Modify Accounts' section" +
                            "for a list of all users across all tiers\n" +
                            "--------------------------------------------------------------------------------------------");
                    new UserOptions().adminEmployeeLoggedIn();
                    break;
                }
            case 6:
                System.out.println("Enter the current username of the user who's name you would like to change");
                desiredUser = userInput.nextLine();
                try {
                    String userPass = String.valueOf(userDB.findEmployeeInfo(desiredUser).get(2));
                    updateEmployeePass(desiredUser);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Username not recognized. Consult the master list under the 'Modify Accounts' section" +
                            "for a list of all users across all tiers\n" +
                            "--------------------------------------------------------------------------------------------");
                    new UserOptions().adminEmployeeLoggedIn();
                    break;

                }
            default:
                System.out.println("Invalid input. Only the numbers listed above are valid. Returning to Main Menu");
                new UserOptions().adminEmployeeLoggedIn();
        }
    }

    public void updateUsername(int ID) {
        System.out.println("What would you like the new username to be?");
        String newUsername = userInput.nextLine();
        UserSpecs specs = new UserSpecs(ID, newUsername, "", 0);
        userDB.updateUserName(specs);
        System.out.println("Task completed successfully. Returning to Main Menu\n" +
                "--------------------------------------------------------------------");
        new UserOptions().adminEmployeeLoggedIn();
    }

    public void updateChildUsername(int ID) {
        System.out.println("What would you like the new username to be?");
        String newUsername = userInput.nextLine();
        ChildUserSpecs specs = new ChildUserSpecs(ID, newUsername, "", "");
        userDB.updateChildUserName(specs);
        System.out.println("Task completed successfully. Returning to Main Menu\n" +
                "--------------------------------------------------------------------");
        new UserOptions().adminEmployeeLoggedIn();
    }

    public void updateEmployeeUsername(int ID) {
        System.out.println("What would you like the new username to be?");
        String newUsername = userInput.nextLine();
        EmployeeUserSpecs specs = new EmployeeUserSpecs(ID, newUsername, "", false);
        userDB.updateEmployeeUserName(specs);
        System.out.println("Task completed successfully. Returning to Main Menu\n" +
                "--------------------------------------------------------------------");
        new UserOptions().adminEmployeeLoggedIn();
    }

    public void updatePass(String name) {
        System.out.println("What would you like the new password to be?");
        String pass = userInput.nextLine();
        UserSpecs specs = new UserSpecs(0, name, pass, 0);
        userDB.updateUserPass(specs);
        System.out.println("Task completed successfully. Returning to Main Menu\n" +
                "--------------------------------------------------------------------");
        new UserOptions().adminEmployeeLoggedIn();
    }

    public void updateChildPass(String name) {
        System.out.println("What would you like the new password to be?");
        String pass = userInput.nextLine();
        ChildUserSpecs specs = new ChildUserSpecs(0, name, pass, "");
        userDB.updateChildUserPass(specs);
        System.out.println("Task completed successfully. Returning to Main Menu\n" +
                "--------------------------------------------------------------------");
        new UserOptions().adminEmployeeLoggedIn();
    }

    public void updateEmployeePass(String name) {
        System.out.println("What would you like the new password to be?");
        String pass = userInput.nextLine();
        EmployeeUserSpecs specs = new EmployeeUserSpecs(0, name, pass, false);
        userDB.updateEmployeeUserPass(specs);
        System.out.println("Task completed successfully. Returning to Main Menu\n" +
                "--------------------------------------------------------------------");
        new UserOptions().adminEmployeeLoggedIn();
    }

    public void adminFundUpdate() {
        int decision = 0;
        int funds = 0;
        int result = 0;
        System.out.println("What would you like to? \n" +
                "1: Add funds to a user's account\n" +
                "2: Withdraw funds from a user's account");
        decision = userInput.nextInt();
        switch (decision) {
            case 1:
                userInput.nextLine();
                System.out.println("Who's account would you like to add funds to?");
                String user = userInput.nextLine();

                if (!userDB.findInfo(user).isEmpty()) {
                    System.out.println("Enter amount to add:");
                    funds = userInput.nextInt();
                    if (funds < 0) {
                        System.out.println("Cannot add a negative number. ");
                        new UserOptions().loggedIn();
                    } else {
                        int currentBalance = Integer.parseInt(userDB.findInfo(user).get(3));
                        result = funds + currentBalance;
                        System.out.printf("Your new balance is " + "%,d", result);
                        UserSpecs specs = new UserSpecs(0, user, "null", result);
                        userDB.updateFunds(specs);
                     Logs.adminLogDeposits(funds, currentBalance, result, user);
                        System.out.println("\nReturning to main menu." +
                                "\n------------------------------------------------------------------------------------");
                        new UserOptions().adminEmployeeLoggedIn();
                        break;
                    }
                } else System.out.println("Username not recognized");
            case 2:
                userInput.nextLine();
                System.out.println("Who's account would you like to remove funds from?");
                 user = userInput.nextLine();

                if (!userDB.findInfo(user).isEmpty()) {
                    System.out.println("Enter amount to withdraw:");
                    funds = userInput.nextInt();
                    if (funds < 0) {
                        System.out.println("Cannot withdraw a negative number. ");
                        new UserOptions().adminEmployeeLoggedIn();
                    } else {
                        int currentBalance = Integer.parseInt(userDB.findInfo(user).get(3));
                        result = currentBalance - funds;

                        System.out.printf("Your new balance is " + "%,d", result);
                        UserSpecs specs = new UserSpecs(0, user, "null", result);
                        userDB.updateFunds(specs);
                        Logs.adminLogWithdraws(funds, currentBalance, result, user);
                        System.out.println("\nReturning to main menu." +
                                "\n------------------------------------------------------------------------------------");
                        new UserOptions().adminEmployeeLoggedIn();
                        break;
                    }
                } else System.out.println("Username not recognized");
        }

    }

}

