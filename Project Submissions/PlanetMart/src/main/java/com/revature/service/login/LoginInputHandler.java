package com.revature.service.login;


import com.revature.display.account.AccountDisplay;
import com.revature.display.login.LoginDisplay;
import com.revature.models.accounts.CustomerAccount;
import com.revature.models.shop.Inventory;
import com.revature.models.users.UserCredential;
import com.revature.repository.*;
import com.revature.repository.Exception.*;
import com.revature.service.account.AccountHandler;
import com.revature.service.exceptions.EmptyInputException;
import com.revature.service.exceptions.InvalidPasswordException;
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
        System.out.println("WELCOME TO PLANETMART!\n");
        while (choosingOptions) {
            input.setLength(0);
            try {
                loginDisplay.printWelcomeOptions();
                input.append(sc.nextLine().toLowerCase().trim());
                switch (input.toString()) {
                    case ("1"):
                        loginAccount(loginDisplay);
                        break;
                    case ("2"):
                        createAccountInput(loginDisplay);
                        break;
                    case ("3"):
                        choosingOptions = false;
                        System.out.println("\nGOODBYE!\n");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("\nCHOOSE A VALID OPTION\n");
                        break;
                }
            }catch (Exception e){
                errorLogger.error(String.valueOf(e));
                System.out.println("\nERROR\nRESTART APPLICATION\n");
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
                        System.out.println("\nUSERNAME IS NOT VALID\n");
                        debugLogger.debug(String.valueOf(e));
                    }catch (EmptyUserCredentialDataException e){
                        debugLogger.debug(String.valueOf(e));
                        System.out.println("\nEMPTY INPUT\n");
                    } catch (InvalidUserCredentialException e) {
                        debugLogger.debug(String.valueOf(e));
                        System.out.println("\nUSER CREDENTIALS TOO SHORT, MUST BE A MINIMUM 4 CHARACTERS\n");
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
                if(username.toString().trim().contentEquals("N")) return;
                loginDisplay.printLoginDisplayPassword();
                password.append(sc.nextLine()).trimToSize();
                UserCredential userCredential = authenticateAccountCredentials(username.toString(), password.toString());
                if (userCredential != null) {
                    loggingIn = false;
                    AccountHandler accountHandler = new AccountHandler();
                    accountHandler.initiateAccount(userCredential);
                }
            } catch (EmptyUserCredentialDataException e) {
                System.out.println("\nEMPTY INPUT\nTRY AGAIN\n");
                debugLogger.debug(String.valueOf(e));
            } catch (AccountNotFoundException e) {
                debugLogger.debug(String.valueOf(e));
                System.out.println("\n\nINVALID CREDENTIALS\n\n");
            } catch (NullPointerException e){
                debugLogger.debug(String.valueOf(e));
                System.out.println("\nINVALID CREDENTIALS\n");
            } catch (InvalidPasswordException e){
                debugLogger.debug(String.valueOf(e));
                System.out.println("\nINVALID CREDENTIALS\n");
            }
            catch (Exception e){
                errorLogger.error(String.valueOf(e));
                System.out.println("\nERROR\nTRY AGAIN\n");
            }
        } while (loggingIn);
    }

    public UserCredential authenticateAccountCredentials(String username, String password) throws EmptyUserCredentialDataException {

        if(username.trim().contentEquals("")) throw new EmptyUserCredentialDataException("Empty username input");
        if(password.trim().contentEquals("")) throw new EmptyUserCredentialDataException("Empty password input");


        UserCredentialsDAO userCredentialsDAO = new UserCredentialsDAO();
        UserCredential userCredential = null;
        try {
            userCredential = userCredentialsDAO.getUserCredentialByUsername(username);
        } catch (EmptyInputException e) {
            debugLogger.debug(String.valueOf(e));
            System.out.println("\nEMPTY INPUT\n");
        }
        if(userCredential.getPassword().contentEquals(password)) {
            return userCredential;
        }
        throw new InvalidPasswordException();
    }

    public boolean createAccount(StringBuilder username, StringBuilder password) throws DuplicateUsernameException, EmptyUserCredentialDataException, InvalidUserCredentialException {
        UserCredentialsDAO userCredentialsDAO = new UserCredentialsDAO();
        CustomerAccountDAO customerAccountDAO = new CustomerAccountDAO();
        CustomerUserDAO customerUserDAO = new CustomerUserDAO();
        InventoryDAO inventoryDAO = new InventoryDAO();
        if(username.toString().trim().contentEquals("")) throw new EmptyUserCredentialDataException("Empty Username Input");
        if(password.toString().trim().contentEquals("")) throw new EmptyUserCredentialDataException("Empty Password Input");

        if(username.toString().trim().length() < 4 || password.toString().trim().length() < 4) throw new InvalidUserCredentialException("Inputted Credentials is too short");

        try {
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
                int userCredentialId = 0;
                try {
                    userCredentialId = userCredentialsDAO.getUserCredentialByUsername(username.toString()).getId();
                } catch (EmptyInputException e) {
                    debugLogger.debug(String.valueOf(e));
                    System.out.println("\nEMPTY INPUT\n");
                }
                inventoryDAO.addInventory(new Inventory(0, 0));
                List<Inventory> inventoryList = inventoryDAO.getAllInventories();
                try {
                    customerAccountDAO.addCustomerAccount(userCredentialId, 0);
                } catch (InvalidPrimaryUserException e) {
                    debugLogger.debug(String.valueOf(e));
                    System.out.println("\nINVALID PRIMARY USER ID");
                } catch (SQLException | InvalidUserCredentialException throwables) {
                    debugLogger.debug(String.valueOf(throwables));
                }
                List<CustomerAccount> customerAccounts = null;
                try {
                    customerAccounts = customerAccountDAO.getCustomerAccountsByUserCredentialId(userCredentialId);
                } catch (InvalidUserCredentialException e) {
                    debugLogger.debug(String.valueOf(e));
                    System.out.println("\nINVALID USER CREDENTIAL\n");
                } catch (SQLException e) {
                    debugLogger.debug(String.valueOf(e));
                    System.out.println("\nDATABASE ERROR\n");
                } catch (Exception e){
                    errorLogger.error(String.valueOf(e));
                    System.out.println("\nERROR IN APPLICATON, IF ISSUES PERSIST, PLEASE RESTART\n");
                }
                int customerAccountId = customerAccounts.get(customerAccounts.size()-1).getCustomerAccountId();
                try {
                    customerUserDAO.addUser(firstName, inventoryList.get(inventoryList.size()-1).getId(), customerAccountId);
                } catch (InvalidCustomerAccountIdException e) {
                    debugLogger.debug(String.valueOf(e));
                    System.out.println("\nINVALID CUSTOMER ACCOUNT ID\n");
                } catch (InvalidInventoryIdException e) {
                    debugLogger.debug(String.valueOf(e));
                    System.out.println("\nINVALID INVENTORY ID\n");
                }
                customerAccountDAO.updateCustomerAccountPrimaryId(customerAccountId, customerUserDAO.getAllUsersByCustomerId(customerAccountId).get(customerUserDAO.getAllUsersByCustomerId(customerAccountId).size()-1).getUserId());
                System.out.println("CREATED ACCOUNT");
                System.out.println("CUSTOMER ACCOUNT ID: " + customerAccountId);
                transactionLogger.info("CUSTOMER ACCOUNT CREATED ID: " + customerAccountId);
            }else{
                throw new DuplicateUsernameException("Username already exists");
            }
        } catch (EmptyInputException e) {
            debugLogger.debug(String.valueOf(e));
            System.out.println("\nEMPTY INPUT\n");
        }
        return false;
    }

}
