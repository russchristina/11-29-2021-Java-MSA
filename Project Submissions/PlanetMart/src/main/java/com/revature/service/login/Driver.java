package com.revature.service.login;


import com.revature.display.account.AccountDisplay;
import com.revature.display.login.LoginDisplay;
import com.revature.display.utility.CreateShapes;
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

public class Driver {
    CreateShapes createShapes = new CreateShapes();
    private final Logger transactionLogger = LoggerFactory.getLogger("transactionLogger");
    private final Logger debugLogger = LoggerFactory.getLogger("debugLogger");
    private final Logger errorLogger = LoggerFactory.getLogger("errorLogger");

    public final Scanner sc;
    public final StringBuilder input;

    public final AccountDisplay accountDisplay;
    public final StringBuilder username = new StringBuilder();
    public final StringBuilder password = new StringBuilder();
    public final LoginDisplay loginDisplay;

    public Driver(){
        sc = new Scanner(System.in);
        input = new StringBuilder();
        accountDisplay = new AccountDisplay();
        loginDisplay = new LoginDisplay();
        firstStage();
    }

    public static void main(String[] args) {
        Driver login = new Driver();
    }

    public void firstStage(){
        loginDisplay.opening();
        boolean choosingOptions = true;
        while (choosingOptions) {
            input.setLength(0);
            try {
                loginDisplay.printWelcomeOptions();
                System.out.print(createShapes.indent + "-> ");
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
                        System.out.println(createShapes.indent + "GOODBYE!");
                        System.exit(0);
                        break;
                    default:
                        System.out.println(createShapes.indent + "CHOOSE A VALID OPTION");
                        break;
                }
            }catch (Exception e){
                errorLogger.error(String.valueOf(e) + "\n" + e.getMessage());
                System.out.println(createShapes.indent + "ERROR RESTART APPLICATION");
                input.setLength(0);
            }
        }
    }

    private void createAccountInput(LoginDisplay loginDisplay) {
        boolean creatingAccount = true;
        do {
            username.setLength(0);
            password.setLength(0);
            try {
                System.out.println(createShapes.border);
                System.out.println(createShapes.indent + "INPUT NEW LOGIN CREDENTIALS OR TYPE N TO LEAVE");
                System.out.println(createShapes.border);
                System.out.print(createShapes.indent+"USERNAME:");
                username.append(sc.nextLine()).trimToSize();
                if(username.toString().contentEquals("N")) return;
                System.out.print(createShapes.indent+"PASSWORD:");
                password.append(sc.nextLine()).trimToSize();
                if(password.toString().contentEquals("N")) return;
                if (createAccount(username, password)) {
                    System.out.println(createShapes.border);
                    System.out.println(createShapes.indent + "NEW ACCOUNT MADE");
                    System.out.println(createShapes.indent + "RETURNING TO LOGIN");
                    System.out.println(createShapes.border);
                    creatingAccount = false;
                }
            } catch (DuplicateUsernameException e) {
                System.out.println(createShapes.indent + "USERNAME IS NOT VALID");
                debugLogger.debug(String.valueOf(e) + "\n" + e.getMessage());
            }catch (EmptyUserCredentialDataException e){
                debugLogger.debug(String.valueOf(e) + "\n" + e.getMessage());
                System.out.println(createShapes.indent + "EMPTY INPUT");
            } catch (InvalidUserCredentialException e) {
                debugLogger.debug(String.valueOf(e) + "\n" + e.getMessage());
                System.out.println(createShapes.indent + "USER CREDENTIALS TOO SHORT, MUST BE A MINIMUM 4 CHARACTERS");
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
                if(password.toString().trim().contentEquals("N")) return;
                UserCredential userCredential = authenticateAccountCredentials(username.toString(), password.toString());
                if (userCredential != null) {
                    loggingIn = false;
                    AccountHandler accountHandler = new AccountHandler();
                    accountHandler.initiateAccount(userCredential);
                }
            } catch (EmptyUserCredentialDataException e) {
                System.out.println(createShapes.indent + "EMPTY INPUT TRY AGAIN");
                debugLogger.debug(String.valueOf(e) + "\n" + e.getMessage());
            } catch (AccountNotFoundException | NullPointerException | InvalidPasswordException e) {
                debugLogger.debug(String.valueOf(e) + "\n" + e.getMessage());
                System.out.println(createShapes.indent + "INVALID CREDENTIALS");
            } catch (Exception e){
                errorLogger.error(String.valueOf(e) + "\n" + e.getMessage());
                System.out.println(createShapes.indent + "ERROR TRY AGAIN");
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
            debugLogger.debug(String.valueOf(e) + "\n" + e.getMessage());
            System.out.println(createShapes.indent + "EMPTY INPUT");
        }
        if(userCredential != null && userCredential.getPassword().contentEquals(password)) {
            return userCredential;
        }
        throw new InvalidPasswordException();
    }

    public boolean createAccount(StringBuilder username, StringBuilder password) throws DuplicateUsernameException, EmptyUserCredentialDataException, InvalidUserCredentialException {
        UserCredentialsDAO userCredentialsDAO = new UserCredentialsDAO();
        CustomerAccountDAO customerAccountDAO = new CustomerAccountDAO();
        CustomerUserDAO customerUserDAO = new CustomerUserDAO();
        InventoryDAO inventoryDAO = new InventoryDAO();
        if (username.toString().trim().contentEquals(""))
            throw new EmptyUserCredentialDataException("Empty Username Input");
        if (password.toString().trim().contentEquals(""))
            throw new EmptyUserCredentialDataException("Empty Password Input");

        if (username.toString().trim().length() < 4 || password.toString().trim().length() < 4)
            throw new InvalidUserCredentialException("Inputted Credentials is too short");

        try {
            if (userCredentialsDAO.getUserCredentialByUsername(username.toString()) == null) {
                System.out.println(createShapes.border);
                System.out.print(createShapes.indent + "FIRST NAME:");
                input.setLength(0);
                String firstName = input.append(sc.nextLine().trim()).toString();
                System.out.print(createShapes.indent + "LAST NAME:");
                input.setLength(0);
                String lastName = input.append(sc.nextLine().trim()).toString();
                System.out.println(createShapes.border);
                userCredentialsDAO.addUserCredential(new UserCredential(0,
                        username.toString(),
                        password.toString(),
                        firstName,
                        lastName));

                int userCredentialId = userCredentialsDAO.getUserCredentialByUsername(username.toString()).getId();
                inventoryDAO.addInventory(new Inventory(0, 0));
                List<Inventory> inventoryList = inventoryDAO.getAllInventories();
                customerAccountDAO.addCustomerAccount(userCredentialId, 0);
                List<CustomerAccount> customerAccounts = customerAccountDAO.getCustomerAccountsByUserCredentialId(userCredentialId);
                int customerAccountId = customerAccounts.get(customerAccounts.size() - 1).getCustomerAccountId();
                customerUserDAO.addUser(firstName, inventoryList.get(inventoryList.size() - 1).getId(), customerAccountId);
                customerAccountDAO.updateCustomerAccountPrimaryId(customerAccountId, customerUserDAO.getAllUsersByCustomerId(customerAccountId).get(customerUserDAO.getAllUsersByCustomerId(customerAccountId).size() - 1).getUserId());

                System.out.println(createShapes.indent + "CREATED ACCOUNT");
                System.out.println(createShapes.indent + "CUSTOMER ACCOUNT ID: " + customerAccountId);
                System.out.println(createShapes.border);
                transactionLogger.info("CUSTOMER ACCOUNT CREATED ID: " + customerAccountId);
            } else {
                throw new DuplicateUsernameException("Username already exists");
            }
        }catch(DuplicateUsernameException e){
            throw new DuplicateUsernameException("Username already exists");
        }catch (EmptyInputException e) {
            debugLogger.debug(String.valueOf(e) + "\n" + e.getMessage());
            System.out.println(createShapes.indent + "EMPTY INPUT");
        } catch (InvalidPrimaryUserException e) {
            System.out.println(createShapes.border);
            debugLogger.debug(String.valueOf(e) + "\n" + e.getMessage());
            System.out.println(createShapes.indent + "INVALID PRIMARY USER ID");
            System.out.println(createShapes.border);
        } catch (SQLException | InvalidUserCredentialException throwables) {
            debugLogger.debug(String.valueOf(throwables));
        }catch (InvalidCustomerAccountIdException e) {
            System.out.println(createShapes.border);
            debugLogger.debug(String.valueOf(e) + "\n" + e.getMessage());
            System.out.println(createShapes.indent + "INVALID CUSTOMER ACCOUNT ID");
            System.out.println(createShapes.border);
        } catch (InvalidInventoryIdException e) {
            System.out.println(createShapes.border);
            debugLogger.debug(String.valueOf(e) + "\n" + e.getMessage());
            System.out.println(createShapes.indent + "INVALID INVENTORY ID");
            System.out.println(createShapes.border);
        } catch (Exception e){
            System.out.println(createShapes.border);
            errorLogger.error(String.valueOf(e) + "\n" + e.getMessage());
            System.out.println(createShapes.indent + "ERROR IN APPLICATON, IF ISSUES PERSIST, PLEASE RESTART");
            System.out.println(createShapes.border);

        }
        return false;
    }
}

