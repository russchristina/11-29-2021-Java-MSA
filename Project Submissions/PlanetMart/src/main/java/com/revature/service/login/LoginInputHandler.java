package com.revature.service.login;

import com.revature.database.UserCredentialsDao;
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

    private StringBuilder username = new StringBuilder();
    private StringBuilder password = new StringBuilder();
    private UserCredentialsDao userCredentialsDao = new UserCredentialsDao();

    public static void main(String[] args) {
        LoginInputHandler login = new LoginInputHandler();
        login.firstStage();
    }

    public void firstStage(){
        boolean choosingOptions = true;
        StringBuilder input = new StringBuilder();
        LoginDisplay loginDisplay = new LoginDisplay();
        System.out.println("Welcome to PlanetMart!");
        while (choosingOptions) {
            input.setLength(0);
            Scanner sc = new Scanner(System.in);
            try {
                loginDisplay.printWelcomeOptions();
                input.append(sc.nextLine().toLowerCase().trim());
                switch (input.toString()) {
                    case ("1"):
                        choosingOptions = false;
                        loginAccount(loginDisplay, sc);
                        break;
                    case ("2"):
                        createAccount(loginDisplay, sc);
                        break;
                    default:
                        System.out.println("\nPlease choose a valid option\n");
                        break;
                }
            }catch (Exception e){
                log.error(e.toString());
                System.out.println("Error.\n" + "Restart the Application.\n");
                input.setLength(0);
            }
        }
    }

    private void createAccount(LoginDisplay loginDisplay, Scanner sc) {
        StringBuilder input = new StringBuilder();
        boolean creatingAccount = true;
        while(creatingAccount){
            username.setLength(0);
            password.setLength(0);
            loginDisplay.printCreateAccountDisplay();
            input.setLength(0);
            input.append(sc.nextLine().trim());
            switch(input.toString()){
                case("1"):
                    try{
                        try{
                            input.setLength(0);
                            System.out.print("USERNAME:");
                            username.append(sc.nextLine()).trimToSize();
                            System.out.print("PASSWORD:");
                            password.append(sc.nextLine()).trimToSize();
                        } catch (NumberFormatException e){
                            System.out.println("Invalid input.");
                            username.setLength(0);
                            password.setLength(0);
                            log.debug(e.toString());
                        }
                        if(createAccount(username, password)) {
                            System.out.println("New Account made!");
                            System.out.println("Returning to Login.");
                            creatingAccount = false;
                        }

                    }catch (DuplicateUsernameException e){
                        System.out.println("Duplicate username, try again or type N to leave.");
                        log.debug(e.toString());
                        username.setLength(0);
                        password.setLength(0);
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
        loginDisplay.printLoginDisplayUsername();
        boolean loggingIn = true;
        while (loggingIn) {
            username.setLength(0);
            password.setLength(0);
            try {
                username.append(sc.nextLine()).trimToSize();;
                loginDisplay.printLoginDisplayPassword();
                password.append(sc.nextLine()).trimToSize();;
                if (authenticateAccountCredentials(username, password)) {
                    loggingIn = false;
                    AccountHandler accountHandler = new AccountHandler(username.toString());
                    accountHandler.initiateAccount();
                }
            } catch (EmptyUserCredentialDataException | IncorrectAccountCredentialsException e) {
                System.out.println("Invalid input.\n" +
                        "Please try again.");
                log.debug(e.toString());
                username.setLength(0);
                password.setLength(0);
            }
        }
    }

    public boolean authenticateAccountCredentials(StringBuilder username, StringBuilder password) throws IncorrectAccountCredentialsException, EmptyUserCredentialDataException {
        if(!userCredentialsDao.userCredentialCheck(username.toString(), password.toString())) throw new IncorrectAccountCredentialsException();
        return true;
    }

    public boolean createAccount(StringBuilder username, StringBuilder password) throws DuplicateUsernameException {
        if(userCredentialsDao.usernameDuplicateCheck(username.toString(), password.toString())) return true;
        else throw new DuplicateUsernameException("Duplicate Username");
    }

}
