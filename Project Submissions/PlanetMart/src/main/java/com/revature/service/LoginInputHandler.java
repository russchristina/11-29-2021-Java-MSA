package com.revature.service;

import com.revature.database.exceptions.DuplicateUsernameException;
import com.revature.database.exceptions.EmptyUserCredentialDataException;
import com.revature.database.exceptions.IncorrectAccountCredentialsException;
import com.revature.display.LoginDisplay;
import com.revature.service.exceptions.EmptyInputException;

import java.util.Scanner;

public class LoginInputHandler {
    public static void main(String[] args) {
        LoginInputHandler us = new LoginInputHandler();
        Scanner sc = new Scanner(System.in);
        us.firstStage(sc);
    }

    public void firstStage(Scanner sc){
        LoginDisplay loginDisplay = new LoginDisplay();
        loginDisplay.printWelcomeDisplay();
        manageWelcomeOptions(loginDisplay, sc);
    }

    private void manageWelcomeOptions(LoginDisplay loginDisplay, Scanner sc) {
        loginDisplay.printWelcomeOptions();
        try{
            int input = Integer.parseInt(sc.nextLine());
            switch(input){
                case(1):
                    loginAccount(loginDisplay, sc);
                    break;
                case(2):
                    createAccount(loginDisplay, sc);
                    break;
                default:
                    System.out.println("Please choose a valid option");
                    manageWelcomeOptions(loginDisplay, sc);
                    break;
            }
        }catch(NumberFormatException | IncorrectAccountCredentialsException e){
            e.printStackTrace();
            manageWelcomeOptions(loginDisplay, sc);
        }

    }

    private void createAccount(LoginDisplay loginDisplay, Scanner sc) {
        UserLoginHandler userLoginHandler = new UserLoginHandler();
        loginDisplay.printCreateAccountDisplayUsername();
        try{
            try{
                userLoginHandler.setUsername(sc.nextLine());
                loginDisplay.printCreateAccountDisplayPassword();
                userLoginHandler.setPassword(sc.nextLine());
            } catch (NumberFormatException | EmptyInputException e){
                e.printStackTrace();
                createAccount(loginDisplay, sc);
            }
            if(userLoginHandler.createAccount(userLoginHandler.getUsername(), userLoginHandler.getPassword())) {
                System.out.println("new Account made ");
                firstStage(sc);
            }

        }catch (DuplicateUsernameException e){
            e.printStackTrace();
            createAccount(loginDisplay, sc);
        }
    }

    private void loginAccount(LoginDisplay loginDisplay, Scanner sc) throws IncorrectAccountCredentialsException {
        UserLoginHandler userLoginHandler = new UserLoginHandler();
        loginDisplay.printLoginDisplayUsername();
        try{
            userLoginHandler.setUsername(sc.nextLine());
            loginDisplay.printLoginDisplayPassword();
            userLoginHandler.setPassword(sc.nextLine());
            if(userLoginHandler.authenticateAccountCredentials()) {
                AccountHandler accountHandler = new AccountHandler();
            }
        }catch (EmptyUserCredentialDataException | EmptyInputException e){
            e.printStackTrace();
            loginAccount(loginDisplay, sc);
        }
    }
}
