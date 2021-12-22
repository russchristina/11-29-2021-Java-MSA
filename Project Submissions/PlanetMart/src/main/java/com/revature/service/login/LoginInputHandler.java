package com.revature.service.login;

import com.revature.database.UserCredentialsDao;
import com.revature.database.exceptions.DuplicateUsernameException;
import com.revature.database.exceptions.EmptyUserCredentialDataException;
import com.revature.database.exceptions.IncorrectAccountCredentialsException;
import com.revature.display.account.AccountDisplay;
import com.revature.display.login.LoginDisplay;
import com.revature.display.user.InventoryDisplay;
import com.revature.models.exceptions.UserNotFoundException;
import com.revature.service.account.AccountHandler;
import com.revature.service.shop.InventoryHandler;
import com.revature.service.shop.ShopHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class LoginInputHandler {
    private final Logger log = LoggerFactory.getLogger(LoginInputHandler.class);

    public final Scanner sc;
    public final StringBuilder input;
    public final InventoryDisplay inventoryDisplay;
    public final AccountDisplay accountDisplay;
    public final StringBuilder username = new StringBuilder();
    public final StringBuilder password = new StringBuilder();
    public final LoginDisplay loginDisplay;


    public final UserCredentialsDao userCredentialsDao = new UserCredentialsDao();

    public LoginInputHandler(){
        sc = new Scanner(System.in);
        input = new StringBuilder();
        inventoryDisplay = new InventoryDisplay();
        accountDisplay = new AccountDisplay();
        loginDisplay = new LoginDisplay();
    }


    public static void main(String[] args) {
        LoginInputHandler login = new LoginInputHandler();
        login.firstStage();
    }

    public void firstStage(){
        boolean choosingOptions = true;
        System.out.println("Welcome to PlanetMart!");
        while (choosingOptions) {
            input.setLength(0);
            try {
                loginDisplay.printWelcomeOptions();
                input.append(sc.nextLine().toLowerCase().trim());
                switch (input.toString()) {
                    case ("1"):
                        choosingOptions = false;
                        loginAccount(loginDisplay);
                        break;
                    case ("2"):
                        createAccountInput(loginDisplay);
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

    private void createAccountInput(LoginDisplay loginDisplay) {
        boolean creatingAccount = true;
        do {
            username.setLength(0);
            password.setLength(0);
            loginDisplay.printCreateAccountDisplay();
            input.setLength(0);
            input.append(sc.nextLine().trim());
            switch (input.toString()) {
                case ("1"):
                    try {
                        System.out.print("USERNAME:");
                        username.append(sc.nextLine()).trimToSize();
                        System.out.print("PASSWORD:");
                        password.append(sc.nextLine()).trimToSize();
                        if (createAccount(username, password)) {
                            System.out.println("New Account made!");
                            System.out.println("Returning to Login.");
                            creatingAccount = false;
                        }
                    } catch (DuplicateUsernameException e) {
                        System.out.println("Duplicate username, try again or type N to leave.");
                        log.debug(e.toString());
                    }
                    break;
                case ("2"):
                    System.out.println("Returning to Login.");
                    creatingAccount = false;
                    break;
                default:
                    System.out.println("Type a valid input.");
                    break;
            }
        } while (creatingAccount);
    }


    private void loginAccount(LoginDisplay loginDisplay) {

        boolean loggingIn = true;
        do {
            loginDisplay.printLoginDisplayUsername();
            username.setLength(0);
            password.setLength(0);
            try {
                username.append(sc.nextLine()).trimToSize();
                loginDisplay.printLoginDisplayPassword();
                password.append(sc.nextLine()).trimToSize();
                if (authenticateAccountCredentials(username.toString(), password.toString())) {
                    loggingIn = false;
                    AccountHandler accountHandler = new AccountHandler();
                    accountHandler.initiateAccount(username.toString());
                }
            } catch (EmptyUserCredentialDataException e) {
                System.out.println("Invalid input.\n" +
                        "Please try again.");
                log.debug(e.toString());
            }
        } while (loggingIn);
    }


    public boolean authenticateAccountCredentials(String username, String password) throws EmptyUserCredentialDataException {
        return userCredentialsDao.userCredentialCheck(username, password);
    }

    public boolean createAccount(StringBuilder username, StringBuilder password) throws DuplicateUsernameException {
        if(userCredentialsDao.usernameDuplicateCheck(username.toString(), password.toString())) return true;
        else throw new DuplicateUsernameException("Duplicate Username");
    }

}
