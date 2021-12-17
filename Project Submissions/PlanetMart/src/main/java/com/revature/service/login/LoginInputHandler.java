package com.revature.service.login;

import com.revature.service.account.AccountHandler;
import com.revature.service.exceptions.EmptyInputException;
import com.revature.database.exceptions.DuplicateUsernameException;
import com.revature.database.exceptions.EmptyUserCredentialDataException;
import com.revature.database.exceptions.IncorrectAccountCredentialsException;
import com.revature.display.login.LoginDisplay;

import java.util.Locale;
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

    private void manageWelcomeOptions(LoginDisplay loginDisplay) {

        Scanner sc = new Scanner(System.in);
        loginDisplay.printWelcomeOptions();

        boolean choosingOptions = true;
        while (choosingOptions) {
            String input = sc.nextLine().toLowerCase().trim();
            switch (input) {
                case ("1"):
                    choosingOptions = false;
                    loginAccount(loginDisplay, sc);
                    break;
                case ("2"):
                    choosingOptions = false;
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
        loginDisplay.printCreateAccountDisplayUsername();
        try{
            try{
                userLoginHandler.setUsername(sc.nextLine().trim());
                loginDisplay.printCreateAccountDisplayPassword();
                userLoginHandler.setPassword(sc.nextLine().trim());
            } catch (NumberFormatException | EmptyInputException e){
                e.printStackTrace();
                sc.nextLine();
                createAccount(loginDisplay, sc);
            }
            if(userLoginHandler.createAccount(userLoginHandler.getUsername(), userLoginHandler.getPassword())) {
                System.out.println("new Account made ");
                firstStage();
            }

        }catch (DuplicateUsernameException e){
            e.printStackTrace();
            sc.nextLine();
            createAccount(loginDisplay, sc);
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
                e.printStackTrace();
                loginAccount(loginDisplay, sc);
            }
        }
    }
}
