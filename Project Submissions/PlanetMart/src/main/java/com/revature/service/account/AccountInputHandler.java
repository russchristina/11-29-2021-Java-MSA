package com.revature.service.account;

import com.revature.display.account.AccountDisplay;
import com.revature.models.accounts.CustomerAccount;
import com.revature.models.accounts.EmployeeAccount;
import com.revature.models.users.User;
import com.revature.models.users.UserCredential;
import com.revature.repository.*;
import com.revature.repository.Exception.DuplicateUsernameException;
import com.revature.repository.Exception.InvalidCustomerAccountIdException;
import com.revature.repository.Exception.InvalidUserCredentialException;
import com.revature.repository.Exception.InvalidUserIdException;
import com.revature.service.exceptions.EmptyInputException;
import com.revature.service.login.LoginInputHandler;
import com.revature.service.shop.InventoryHandler;
import com.revature.service.shop.ShopHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.auth.login.AccountNotFoundException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class AccountInputHandler {

    private final Logger transactionLogger = LoggerFactory.getLogger("transactionLogger");
    private final Logger debugLogger = LoggerFactory.getLogger("debugLogger");
    private final Logger errorLogger = LoggerFactory.getLogger("errorLogger");

    public final Scanner sc = new Scanner(System.in);
    public final StringBuilder input = new StringBuilder();

    public void inputChooseCustomerOptions(CustomerAccount customerAccount, User user, UserCredential username) {
        boolean choosingOptions = true;
        InventoryHandler inventoryHandler = new InventoryHandler();
        AccountHandler accountHandler = new AccountHandler();
        AccountDisplay accountDisplay = new AccountDisplay();
        if(user.getUserId() == customerAccount.getPrimaryUserId()){
            inputChooseCustomerOptionsPrimary(customerAccount, user, username);
            return;
        }

        do {

            accountDisplay.displayCustomerBasicOptions(customerAccount, user);
            System.out.println("Choose an option:");
            input.setLength(0);
            input.append(sc.nextLine().trim());

            switch (input.toString()) {
                case ("1"):
                    System.out.println("Option 1: Open Inventory");
                    inventoryHandler.openInventory(user, customerAccount);
                    break;
                case ("2"):
                    System.out.println("Option 2: Open Shop");
                    ShopHandler shopHandler = new ShopHandler();
                    shopHandler.beginShopping(customerAccount, user);
                    break;
                case ("3"):
                    System.out.println("Option 3: Change User");
                    choosingOptions = false;
                    accountHandler.changeUser(customerAccount, username);
                    break;
                case ("4"):
                    System.out.println("Option 4: Manage Money");
                    inventoryHandler.manageBalance(customerAccount, user);
                    break;
                case ("5"):
                    System.out.println("Option 5: Logout");
                    choosingOptions = false;
                    System.out.println("\nLOGGING OUT\n");
                    LoginInputHandler loginInputHandler = new LoginInputHandler();
                    loginInputHandler.firstStage();
                    break;
                default:
                    System.out.println("\nInput a valid choice.\n");
                    break;
            }
        } while (choosingOptions);
        }

    public void inputChooseCustomerOptionsPrimary(CustomerAccount customerAccount, User user, UserCredential username) {
        boolean choosingOptions = true;
        InventoryHandler inventoryHandler = new InventoryHandler();
        AccountHandler accountHandler = new AccountHandler();
        AccountDisplay accountDisplay = new AccountDisplay();

            do {
                accountDisplay.displayCustomerUpgradedOptions(customerAccount, user);
                System.out.println("Choose an option:");
                input.setLength(0);
                input.append(sc.nextLine().trim());
                switch (input.toString()) {
                    case ("1"):
                        System.out.println("Option 1: Open Inventory");
                        inventoryHandler.openInventory(user, customerAccount);
                        break;
                    case ("2"):
                        System.out.println("Option 2: Open Shop");
                        ShopHandler shopHandler = new ShopHandler();
                        shopHandler.beginShopping(customerAccount, user);
                        break;
                    case ("3"):
                        System.out.println("Option 3: Change User");
                        choosingOptions = false;
                        accountHandler.changeUser(customerAccount, username);
                        break;
                    case ("4"):
                        System.out.println("Option 4: Manage Money");
                        inventoryHandler.manageBalance(customerAccount, user);
                        break;
                    case ("5"):
                        System.out.println("Option 5: Logout");
                        choosingOptions = false;
                        System.out.println("\nLOGGING OUT\n");
                        LoginInputHandler loginInputHandler = new LoginInputHandler();
                        loginInputHandler.firstStage();
                        break;
                    case ("6"):
                        System.out.println("Option 6: Add User");
                        try {
                            accountHandler.addUser(customerAccount);
                        } catch (EmptyInputException e) {
                            debugLogger.debug(e.toString());
                            System.out.println("\nEMPTY INPUT, TRY AGAIN\n");
                        } catch (DuplicateUsernameException e) {
                            debugLogger.debug(e.toString());
                            System.out.println("\nDUPLICATE USERNAME, TRY AGAIN\n");
                        }
                        break;
                    case ("7"):
                        System.out.println("Option 7: Transfer Funds");
                        inventoryHandler.transferFunds(customerAccount, user);
                        break;
                    case ("8"):
                        System.out.println("Option 8: Change user names");
                        accountHandler.changeUserNames(customerAccount);
                        break;
                    case ("9"):
                        System.out.println("Option 9: Remove User");
                        accountHandler.removeUser(user, customerAccount);
                        break;
                    case ("10"):
                        System.out.println("Option 10: Add Account");
                        try {
                            accountHandler.addAccount(user, customerAccount, username);
                        } catch (SQLException e) {

                        } catch (InvalidUserCredentialException e) {
                            e.printStackTrace();
                        }
                        break;
                    case ("11"):
                        System.out.println("Option 11: Change Account");
                        try {
                            accountHandler.initiateAccount(username);
                        } catch (AccountNotFoundException e) {
                            e.printStackTrace();
                        }
                    default:
                        System.out.println("\nInput a valid choice.\n");
                        break;
                }
            } while (choosingOptions);
    }

    public void inputChooseEmployeeOption(EmployeeAccount employeeAccount, UserCredential username) {
        boolean choosingOptions = true;
        InventoryHandler inventoryHandler = new InventoryHandler();
        AccountHandler accountHandler = new AccountHandler();
        AccountDisplay accountDisplay = new AccountDisplay();
        CustomerAccountDAO customerAccountDAO = new CustomerAccountDAO();
        CustomerUserDAO customerUserDAO = new CustomerUserDAO();

        do {
            accountDisplay.displayEmployeeAccount(employeeAccount);

            System.out.println("Choose an option:");
            input.setLength(0);
            input.append(sc.nextLine().trim());

            switch (input.toString()) {
                case ("1"):
                    System.out.println("Option 1: View all Customer Accounts");
                    for (CustomerAccount customerAccount : customerAccountDAO.getAllCustomerAccounts()) {
                        accountDisplay.displayCustomerAccount(customerAccount);
                    }
                    break;
                case ("2"):
                    System.out.println("Option 2: View Customer Account Information");
                    viewCustomerAccountInformation();
                    break;
                case ("3"):
                    System.out.println("Option 3: View all Users");
                    accountDisplay.displayAllUsers(customerAccountDAO.getAllCustomerAccounts());
                    break;
                case ("4"):
                    System.out.println("Option 4: View User Information");
                    viewUserInformation(username);
                    break;
                case ("5"):
                    System.out.println("Option 5: Delete Account");
                    deleteAccount(employeeAccount, username);
                    break;
                case ("6"):
                    System.out.println("Option 6: Logout");
                    choosingOptions = false;
                    System.out.println("\nLOGGING OUT\n");
                    LoginInputHandler loginInputHandler = new LoginInputHandler();
                    loginInputHandler.firstStage();
                    break;
                default:
                    System.out.println("\nInput a valid choice.\n");
                    break;
            }
        } while (choosingOptions);

    }

    public void deleteAccount(EmployeeAccount employeeAccount, UserCredential username) {
        CustomerAccountDAO customerAccountDAO = new CustomerAccountDAO();
        CustomerUserDAO customerUserDAO = new CustomerUserDAO();
        InventoryDAO inventoryDAO = new InventoryDAO();
        PlanetDAO planetDAO = new PlanetDAO();
        AtmosphereDAO atmosphereDAO = new AtmosphereDAO();
        LifeDAO lifeDAO = new LifeDAO();
        int planetId = 0;
        boolean chooseAccount = true;

        do{
            System.out.println("\nINPUT ACCOUNT NUMBER");
            input.setLength(0);
            input.append(sc.nextLine().trim());

            try{
                int customerId = Integer.parseInt(input.toString());
                customerAccountDAO.deleteCustomerAccountById(customerId);

                chooseAccount = false;
            }catch (NumberFormatException e){
                debugLogger.debug(e.toString());
                System.out.println("\nTYPE A VALID NUMBER\n");
            } catch (InvalidCustomerAccountIdException e) {
                debugLogger.debug(e.toString());
                System.out.println("\nINVALID CUSTOMER ACCOUNT ID\n");
            }
        }while(chooseAccount);

    }

    public void viewUserInformation(UserCredential username) {

        CustomerUserDAO cUDao = new CustomerUserDAO();
        InventoryHandler inventoryHandler = new InventoryHandler();

        boolean chooseUser = true;

            do{
                System.out.println("\nCHOOSE USER NUMBER");
                input.setLength(0);
                input.append(sc.nextLine().trim());
                try{
                    int userId = Integer.parseInt(input.toString());
                    User user = cUDao.getUserById(userId);
                    inventoryHandler.openUserInventory(user);
                    chooseUser = false;
                }catch (NumberFormatException e){
                    debugLogger.debug(e.toString());
                    System.out.println("\nTYPE A VALID NUMBER\n");
                } catch (InvalidUserIdException e) {
                    debugLogger.debug(e.toString());
                    System.out.println("\nINVALID USER ID\n");
                }

            }while(chooseUser);
    }

    public void viewCustomerAccountInformation() {

        CustomerUserDAO customerUserDAO = new CustomerUserDAO();
        CustomerAccountDAO customerAccountDAO = new CustomerAccountDAO();
        AccountDisplay accountDisplay = new AccountDisplay();

        boolean chooseAccount = true;
        do{
            System.out.println("\nINPUT ACCOUNT NUMBER");
            input.setLength(0);
            input.append(sc.nextLine().trim());

            try{
                int customerId = Integer.parseInt(input.toString());
                CustomerAccount customerAccount = customerAccountDAO.getCustomerAccountById(customerId);
                List<User> users = customerUserDAO.getAllUsersByCustomerId(customerId);
                accountDisplay.displayUsers(users, customerAccount);
                chooseAccount = false;
            }catch (NumberFormatException e){
                debugLogger.debug(e.toString());
                System.out.println("\nTYPE A VALID NUMBER\n");
            }
        }while(chooseAccount);
    }

    public void inputChooseAdminOption(EmployeeAccount employeeAccount, UserCredential username) {

        boolean choosingOptions = true;
        EmployeeAccountDAO employeeAccountDAO = new EmployeeAccountDAO();
        AccountHandler accountHandler = new AccountHandler();
        AccountDisplay accountDisplay = new AccountDisplay();
        CustomerAccountDAO customerAccountDAO = new CustomerAccountDAO();
        CustomerUserDAO customerUserDAO = new CustomerUserDAO();

        do {
            accountDisplay.displayAdminAccount(employeeAccount);

            System.out.println("Choose an option:");
            input.setLength(0);
            input.append(sc.nextLine().trim());

            switch (input.toString()) {
                case ("1"):
                    System.out.println("Option 1: View all Customer Accounts");
                    for (CustomerAccount customerAccount : customerAccountDAO.getAllCustomerAccounts()) {
                        accountDisplay.displayCustomerAccount(customerAccount);
                    }
                    break;
                case ("2"):
                    System.out.println("Option 2: View Customer Account Information");
                    viewCustomerAccountInformation();
                    break;
                case ("3"):
                    System.out.println("Option 3: View all Users");
                    accountDisplay.displayAllUsers(customerAccountDAO.getAllCustomerAccounts());
                    break;
                case ("4"):
                    System.out.println("Option 4: View User Information");
                    viewUserInformation(username);
                    break;
                case ("5"):
                    System.out.println("Option 5: Delete Account");
                    deleteAccount(employeeAccount, username);
                    break;
                case ("6"):
                    System.out.println("Option 6: Logout");
                    choosingOptions = false;
                    System.out.println("\nLOGGING OUT\n");
                    LoginInputHandler loginInputHandler = new LoginInputHandler();
                    loginInputHandler.firstStage();
                    break;
                case ("7"):
                    System.out.println("Option 7: View All Employee Accounts");
                    List<EmployeeAccount> employeeAccounts = employeeAccountDAO.getAllEmployeeAccounts();
                    accountDisplay.displayEmployeeAccountInformation(employeeAccounts);
                    break;
                case ("8"):
                    System.out.println("Option 8: Change Account Data");
                    accountHandler.changeAccountData(employeeAccount, username);
                    break;
                case ("9"):
                    System.out.println("Option 9: Change User Data");
                    accountHandler.changeUserData(employeeAccount, username);
                    break;
                case ("10"):
                    System.out.println("Option 10: Add Employee Account");
                    try {
                        accountHandler.addEmployeeAccount(employeeAccount, username);
                    } catch (EmptyInputException e) {
                        debugLogger.debug(String.valueOf(e));
                        System.out.println("\nEMPTY INPUT REGISTERED, TRY AGAIN\n");
                    }
                    break;
                case ("11"):
                    System.out.println("Option 11: Add Admin Account");
                    try {
                        accountHandler.addAdminAccount(employeeAccount, username);
                    } catch (EmptyInputException e) {
                        debugLogger.debug(String.valueOf(e));
                        System.out.println("\nEMPTY INPUT REGISTERED, TRY AGAIN\n");
                    }
                    break;
                default:
                    System.out.println("\nInput a valid choice.\n");
                    break;
            }
        } while (choosingOptions);

    }
}
