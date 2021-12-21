package com.revature.service.login;

import com.revature.service.account.AccountHandler;
import com.revature.service.exceptions.EmptyInputException;
import com.revature.database.exceptions.DuplicateUsernameException;
import com.revature.database.exceptions.EmptyUserCredentialDataException;
import com.revature.database.exceptions.IncorrectAccountCredentialsException;
import com.revature.display.login.LoginDisplay;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class LoginInputHandler {
    private final Logger log = LoggerFactory.getLogger(LoginInputHandler.class);

    public static void main(String[] args) {
        LoginInputHandler login = new LoginInputHandler();
        login.firstStage();
    }

    public void firstStage(){
        LoginDisplay loginDisplay = new LoginDisplay();
        System.out.println("Welcome to PlanetMart!");
        try {
            manageWelcomeOptions(loginDisplay);
        }catch (Exception e){
            log.error(e.toString());
            System.out.println("Error.\n" +
                    "Restart the Application.\n");
        }

    }

    private void manageWelcomeOptions(LoginDisplay loginDisplay) {

        Scanner sc = new Scanner(System.in);

        boolean choosingOptions = true;
        while (choosingOptions) {
            loginDisplay.printWelcomeOptions();
            String input = sc.nextLine().toLowerCase().trim();
            switch (input) {
                case ("1"):
                    choosingOptions = false;
                    loginAccount(loginDisplay, sc);
                    break;
                case ("2"):
                    createAccount(loginDisplay, sc);
                    break;
                default:
                    System.out.println("Please choose a valid option");
                    break;
            }
        }
    }

    private void createAccount(LoginDisplay loginDisplay, Scanner sc) {
        UserLoginHandler userLoginHandler = new UserLoginHandler();
        String input = "";
        String user = "";
        boolean creatingAccount = true;
        while(creatingAccount){
            loginDisplay.printCreateAccountDisplay();
            input = sc.nextLine();

            switch(input.trim()){
                case("1"):
                    try{
                        try{
                            System.out.print("USERNAME:");
                            input = sc.nextLine();
                            userLoginHandler.setUsername(input.trim());
                            System.out.print("PASSWORD:");
                            userLoginHandler.setPassword(sc.nextLine().trim());
                        } catch (NumberFormatException | EmptyInputException e){
                            System.out.println("Invalid input.");
                            log.debug(e.toString());
                        }
                        if(userLoginHandler.createAccount(userLoginHandler.getUsername(), userLoginHandler.getPassword())) {
                            System.out.println("New Account made!");
                            System.out.println("Returning to Login.");
                            creatingAccount = false;
                        }

                    }catch (DuplicateUsernameException e){
                        System.out.println("Duplicate username, try again or type N to leave.");
                        log.debug(e.toString());
                    }
                    break;
                case("2"):
                    System.out.println("Returning to Login.");
                    creatingAccount = false;
                    break;
                default:
                    System.out.println("Type a valid input.");
                    break;
            }

        }

    }

    private void loginAccount(LoginDisplay loginDisplay, Scanner sc) {
        UserLoginHandler userLoginHandler = new UserLoginHandler();
        loginDisplay.printLoginDisplayUsername();
        boolean loggingIn = true;
        while (loggingIn) {
            try {
                String usernameInput = sc.nextLine().trim();
                userLoginHandler.setUsername(usernameInput);
                loginDisplay.printLoginDisplayPassword();
                userLoginHandler.setPassword(sc.nextLine().trim());
                if (userLoginHandler.authenticateAccountCredentials()) {
                    loggingIn = false;
                    AccountHandler accountHandler = new AccountHandler(usernameInput);
                    accountHandler.initiateAccount();
                }
            } catch (EmptyUserCredentialDataException | EmptyInputException | IncorrectAccountCredentialsException e) {
                System.out.println("Invalid input.\n" +
                        "Please try again.");
                log.debug(e.toString());
            }
        }
    }
}
