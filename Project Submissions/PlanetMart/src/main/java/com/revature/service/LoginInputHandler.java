package com.revature.service;

import com.revature.service.exceptions.EmptyInputException;
import com.revature.database.exceptions.DuplicateUsernameException;
import com.revature.database.exceptions.EmptyUserCredentialDataException;
import com.revature.database.exceptions.IncorrectAccountCredentialsException;
import com.revature.display.LoginDisplay;

import java.util.Scanner;

public class LoginInputHandler {

    public static void main(String[] args) {
        LoginInputHandler login = new LoginInputHandler();
        login.firstStage();
    }

    public void firstStage(){
        LoginDisplay loginDisplay = new LoginDisplay();
        loginDisplay.printWelcomeDisplay();
        try {
            manageWelcomeOptions(loginDisplay);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private void manageWelcomeOptions(LoginDisplay loginDisplay) throws Exception {

        Scanner sc = new Scanner(System.in);
        loginDisplay.printWelcomeOptions();

            try {
                boolean choosingOptions = true;
                while (choosingOptions) {
                    int input = Integer.parseInt(sc.nextLine());
                    switch (input) {
                        case (1):
                            choosingOptions = false;
                            loginAccount(loginDisplay);
                            break;
                        case (2):
                            choosingOptions = false;
                            createAccount(loginDisplay);
                            break;
                        default:
                            System.out.println("Please choose a valid option");
                            break;
                    }
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
                manageWelcomeOptions(loginDisplay);
            }

    }

    private void createAccount(LoginDisplay loginDisplay) {
        Scanner sc = new Scanner(System.in);
        UserLoginHandler userLoginHandler = new UserLoginHandler();
        loginDisplay.printCreateAccountDisplayUsername();
        try{
            try{
                userLoginHandler.setUsername(sc.nextLine());
                loginDisplay.printCreateAccountDisplayPassword();
                userLoginHandler.setPassword(sc.nextLine());
            } catch (NumberFormatException | EmptyInputException e){
                e.printStackTrace();
                createAccount(loginDisplay);
            }
            if(userLoginHandler.createAccount(userLoginHandler.getUsername(), userLoginHandler.getPassword())) {
                System.out.println("new Account made ");
                firstStage();
            }

        }catch (DuplicateUsernameException e){
            e.printStackTrace();
            createAccount(loginDisplay);
        }
    }

    private void loginAccount(LoginDisplay loginDisplay) {
        Scanner sc = new Scanner(System.in);
        UserLoginHandler userLoginHandler = new UserLoginHandler();
        loginDisplay.printLoginDisplayUsername();
        boolean loggingIn = true;
        while (loggingIn) {
            try {
                String usernameInput = sc.nextLine();
                userLoginHandler.setUsername(usernameInput);
                loginDisplay.printLoginDisplayPassword();
                userLoginHandler.setPassword(sc.nextLine());
                if (userLoginHandler.authenticateAccountCredentials()) {
                    loggingIn = false;
                    AccountHandler accountHandler = new AccountHandler(usernameInput);
                    accountHandler.initiateAccount();
                    ;
                }
            } catch (EmptyUserCredentialDataException | EmptyInputException | IncorrectAccountCredentialsException e) {
                e.printStackTrace();
                loginAccount(loginDisplay);
            }
        }
    }
}
