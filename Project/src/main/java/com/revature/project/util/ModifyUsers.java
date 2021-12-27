package com.revature.project.util;

import com.revature.project.MainDisplay;
import com.revature.project.UserOptions;

import java.util.List;
import java.util.Scanner;

public class ModifyUsers   {
    static UserDB userDB = new UserDBImplementation();
    public static void returnUserTable(){
        List<UserSpecs>userSpecsList;
        userSpecsList = userDB.findAll();
        System.out.println("-----------------------LIST START-------------------------\n");

        System.out.println(userSpecsList);

        System.out.println("-----------------------END OF LIST---------------------------------");

    }
public static void returnChildUserTable(){
    List<ChildUserSpecs>childUserSpecsList;
    childUserSpecsList = userDB.findAllChildren();
    System.out.println("-----------------------LIST START-------------------------\n");

    System.out.println(childUserSpecsList);

    System.out.println("-----------------------END OF LIST---------------------------------");

}
    public static void returnEmployeeTable(){
        List<EmployeeUserSpecs>employeeUserSpecsList;
        employeeUserSpecsList = userDB.findAllEmployees();
        System.out.println("-----------------------LIST START-------------------------\n");

        System.out.println(employeeUserSpecsList);

        System.out.println("-----------------------END OF LIST---------------------------------");

    }
    public static void deleteMainUser() {
        Scanner userInput = new Scanner(System.in);
        int option = 0;
        boolean isAdmin = Boolean.parseBoolean(userDB.findEmployeeInfo(MainDisplay.getUsername()).get(3));

        System.out.println("What would you like to do?\n" +
                "1: Delete a user\n" +
                "2: Return Main Menu");
        option = userInput.nextInt();
        switch (option){
            case 1:
                System.out.println("Enter the ID of a user listed above to delete: ");
                int user = userInput.nextInt();
                UserSpecs specs = new UserSpecs(user,"","",0);
                userDB.delete(specs);

                System.out.println("User deleted. Returning to Main Menu \n" +
                        "------------------------------------------------------------------------");
                if (isAdmin){

                    new UserOptions().adminEmployeeLoggedIn();
                }else {
                    new UserOptions().employeeLoggedIn();
                }
                break;
            case 2:
                if (isAdmin){
                    System.out.println("Returning to Main Menu \n" +
                            "------------------------------------------------------------------------");
                    new UserOptions().adminEmployeeLoggedIn();
                }else {
                    new UserOptions().employeeLoggedIn();
                }

                break;
            default:
                System.out.println("Returning to Main Menu \n" +
                        "------------------------------------------------------------------------");
        }

        }
    public static void deleteChildUser() {
        Scanner userInput = new Scanner(System.in);
        int option = 0;
        boolean isAdmin = Boolean.parseBoolean(userDB.findEmployeeInfo(MainDisplay.getUsername()).get(3));

        System.out.println("What would you like to do?\n" +
                "1: Delete a user\n" +
                "2: Return Main Menu");
        option = userInput.nextInt();
        switch (option){
            case 1:
                System.out.println("Enter the ID of a user listed above to delete: ");
                int user = userInput.nextInt();
                ChildUserSpecs specs = new ChildUserSpecs(user,"","","");
                userDB.deleteChild(specs);

                System.out.println("User deleted. Returning to Main Menu \n" +
                        "------------------------------------------------------------------------");
                if (isAdmin){

                    new UserOptions().adminEmployeeLoggedIn();
                }else {
                    new UserOptions().employeeLoggedIn();
                }
                break;
            case 2:
                if (isAdmin){
                    System.out.println("Returning to Main Menu \n" +
                            "------------------------------------------------------------------------");
                    new UserOptions().adminEmployeeLoggedIn();
                }else {
                    new UserOptions().employeeLoggedIn();
                }

                break;
            default:
                System.out.println("Returning to Main Menu \n" +
                        "------------------------------------------------------------------------");
        }

    }
    public static void deleteEmployeeUser() {
        Scanner userInput = new Scanner(System.in);
        int option = 0;
        boolean isAdmin = Boolean.parseBoolean(userDB.findEmployeeInfo(MainDisplay.getUsername()).get(3));

        System.out.println("What would you like to do?\n" +
                "1: Delete a user\n" +
                "2: Return Main Menu");
        option = userInput.nextInt();
        switch (option){
            case 1:
                System.out.println("Enter the ID of a user listed above to delete: ");
                int user = userInput.nextInt();
                EmployeeUserSpecs specs = new EmployeeUserSpecs(user,"","",false);
                userDB.deleteEmployee(specs);

                System.out.println("User deleted. Returning to Main Menu \n" +
                        "------------------------------------------------------------------------");
                if (isAdmin){

                    new UserOptions().adminEmployeeLoggedIn();
                }else {
                    new UserOptions().employeeLoggedIn();
                }
                break;
            case 2:
                if (isAdmin){
                    System.out.println("Returning to Main Menu \n" +
                            "------------------------------------------------------------------------");
                    new UserOptions().adminEmployeeLoggedIn();
                }else {
                    new UserOptions().employeeLoggedIn();
                }

                break;
            default:
                System.out.println("Returning to Main Menu \n" +
                        "------------------------------------------------------------------------");
        }

    }
    }

