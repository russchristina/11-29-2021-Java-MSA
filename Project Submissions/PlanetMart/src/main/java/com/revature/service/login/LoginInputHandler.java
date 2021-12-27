package com.revature.service.login;

import com.revature.database.exceptions.DuplicateUsernameException;
import com.revature.database.exceptions.EmptyUserCredentialDataException;
import com.revature.display.account.AccountDisplay;
import com.revature.display.login.LoginDisplay;
import com.revature.models.accounts.CustomerAccount;
import com.revature.models.shop.Inventory;
import com.revature.models.users.UserCredential;
import com.revature.repository.*;
import com.revature.repository.Exception.InvalidCustomerAccountIdException;
import com.revature.repository.Exception.InvalidInventoryIdException;
import com.revature.repository.Exception.InvalidPrimaryUserException;
import com.revature.repository.Exception.InvalidUserCredentialException;
import com.revature.service.account.AccountHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.auth.login.AccountNotFoundException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class LoginInputHandler {
    private final Logger transactionLogger = LoggerFactory.getLogger("transactionLogger");
    private final Logger debugLogger = LoggerFactory.getLogger("debugLogger");
    private final Logger errorLogger = LoggerFactory.getLogger("errorLogger");

    public final Scanner sc;
    public final StringBuilder input;

    public final AccountDisplay accountDisplay;
    public final StringBuilder username = new StringBuilder();
    public final StringBuilder password = new StringBuilder();
    public final LoginDisplay loginDisplay;

    public LoginInputHandler(){
        sc = new Scanner(System.in);
        input = new StringBuilder();
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
                errorLogger.error(e.toString());
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
                        debugLogger.debug(e.toString());
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
                UserCredential userCredential = authenticateAccountCredentials(username.toString(), password.toString());
                if (userCredential != null) {
                    loggingIn = false;
                    AccountHandler accountHandler = new AccountHandler();
                    accountHandler.initiateAccount(userCredential);
                }
            } catch (EmptyUserCredentialDataException e) {
                System.out.println("Invalid input.\n" +
                        "Please try again.");
                debugLogger.debug(e.toString());
            } catch (AccountNotFoundException e) {
                errorLogger.error(e.toString());
                System.out.println("\nAccount not found...\n");
            }
        } while (loggingIn);
    }

    public UserCredential authenticateAccountCredentials(String username, String password) throws EmptyUserCredentialDataException {
        UserCredentialsDAO userCredentialsDAO = new UserCredentialsDAO();
        UserCredential userCredential = userCredentialsDAO.getUserCredentialByUsername(username);
        if(userCredential.getPassword().contentEquals(password)) return userCredential;
        return null;
    }

    public boolean createAccount(StringBuilder username, StringBuilder password) throws DuplicateUsernameException {
        UserCredentialsDAO userCredentialsDAO = new UserCredentialsDAO();
        CustomerAccountDAO customerAccountDAO = new CustomerAccountDAO();
        CustomerUserDAO customerUserDAO = new CustomerUserDAO();
        InventoryDAO inventoryDAO = new InventoryDAO();
        if(userCredentialsDAO.getUserCredentialByUsername(username.toString()) == null){
            System.out.print("FIRST NAME:");
            input.setLength(0);
            String firstName = input.append(sc.nextLine().trim()).toString();
            System.out.print("LAST NAME:");
            input.setLength(0);
            String lastName = input.append(sc.nextLine().trim()).toString();
            userCredentialsDAO.addUserCredential(new UserCredential(0,
                    username.toString(),
                    password.toString(),
                    firstName,
                    lastName));
            int userCredentialId = userCredentialsDAO.getUserCredentialByUsername(username.toString()).getId();
            inventoryDAO.addInventory(new Inventory(0, 0));
            List<Inventory> inventoryList = inventoryDAO.getAllInventories();
            try {
                customerAccountDAO.addCustomerAccount(userCredentialId, 0);
            } catch (InvalidPrimaryUserException e) {
                debugLogger.debug(e.toString());
                System.out.println("\nINVALID PRIMARY USER ID");
            } catch (SQLException throwables) {
                debugLogger.debug(throwables.toString());
            } catch (InvalidUserCredentialException e) {
                debugLogger.debug(e.toString());
            }
            List<CustomerAccount> customerAccounts = null;
            try {
                customerAccounts = customerAccountDAO.getCustomerAccountsByUserCredentialId(userCredentialId);
            } catch (InvalidUserCredentialException e) {
                debugLogger.debug(e.toString());
                System.out.println("\nINVALID USER CREDENTIAL\n");
            } catch (SQLException e) {
                debugLogger.debug(e.toString());
                System.out.println("\nDATABASE ERROR\n");
            } catch (Exception e){
                errorLogger.error(e.toString());
                System.out.println("\nERROR IN APPLICATON, IF ISSUES PERSIST, PLEASE RESTART\n");
            }
            int customerAccountId = customerAccounts.get(customerAccounts.size()-1).getCustomerAccountId();
            try {
                customerUserDAO.addUser(firstName, inventoryList.get(inventoryList.size()-1).getId(), customerAccountId);
            } catch (InvalidCustomerAccountIdException e) {
                debugLogger.debug(e.toString());
                System.out.println("\nINVALID CUSTOMER ACCOUNT ID\n");
            } catch (InvalidInventoryIdException e) {
                debugLogger.debug(e.toString());
                System.out.println("\nINVALID INVENTORY ID\n");
            }
            customerAccountDAO.updateCustomerAccountPrimaryId(customerAccountId, customerUserDAO.getAllUsersByCustomerId(customerAccountId).get(customerUserDAO.getAllUsersByCustomerId(customerAccountId).size()-1).getUserId());
            System.out.println("CREATED ACCOUNT");
            System.out.println("CUSTOMER ACCOUNT ID: " + customerAccountId);
            transactionLogger.info("CUSTOMER ACCOUNT CREATED ID: " + customerAccountId);
        }
        return false;
    }

}
