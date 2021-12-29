package com.revature.service.account;

import com.revature.display.account.AccountDisplay;
import com.revature.display.utility.CreateShapes;
import com.revature.models.accounts.CustomerAccount;
import com.revature.models.accounts.EmployeeAccount;
import com.revature.models.shop.Planet;
import com.revature.models.users.User;
import com.revature.models.users.UserCredential;
import com.revature.repository.*;
import com.revature.repository.Exception.*;
import com.revature.service.exceptions.EmptyInputException;
import com.revature.service.login.Driver;
import com.revature.service.shop.InventoryHandler;
import com.revature.service.shop.ShopHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.auth.login.AccountNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AccountInputHandler {

    private final Logger transactionLogger = LoggerFactory.getLogger("transactionLogger");
    private final Logger debugLogger = LoggerFactory.getLogger("debugLogger");
    private final Logger errorLogger = LoggerFactory.getLogger("errorLogger");

    public final Scanner sc = new Scanner(System.in);
    public final StringBuilder input = new StringBuilder();
    CreateShapes createShapes = new CreateShapes();

    public void inputChooseCustomerOptions(CustomerAccount customerAccount, User user, UserCredential username) {
        boolean choosingOPTIONs = true;
        InventoryHandler inventoryHandler = new InventoryHandler();
        AccountHandler accountHandler = new AccountHandler();
        AccountDisplay accountDisplay = new AccountDisplay();
        if(user.getUserId() == customerAccount.getPrimaryUserId()){
            inputChooseCustomerOPTIONsPrimary(customerAccount, user, username);
            return;
        }

        do {

            accountDisplay.displayCustomerBasicOptions(customerAccount, user);
            System.out.println(createShapes.indent + "CHOOSE AN OPTION:");
            System.out.print(createShapes.indent + "-> ");
            input.setLength(0);
            input.append(sc.nextLine().trim());

            switch (input.toString()) {
                case ("1"):
                    System.out.println(createShapes.indent + "OPTION 1: INVENTORY");
                    inventoryHandler.openInventory(user, customerAccount);
                    break;
                case ("2"):
                    System.out.println(createShapes.indent + "OPTION 2: SHOP");
                    ShopHandler shopHandler = new ShopHandler();
                    shopHandler.beginShopping(customerAccount, user);
                    break;
                case ("3"):
                    System.out.println(createShapes.indent + "OPTION 3: CHANGE USER");
                    choosingOPTIONs = false;
                    accountHandler.changeUser(customerAccount, username);
                    break;
                case ("4"):
                    System.out.println(createShapes.indent + "OPTION 4: MANAGE MONEY");
                    inventoryHandler.manageBalance(customerAccount, user);
                    break;
                case ("5"):
                    System.out.println(createShapes.indent + "OPTION 5: LOGOUT");
                    choosingOPTIONs = false;
                    System.out.println();
                    System.out.println(createShapes.indent + "LOGGING OUT");
                    Driver driver = new Driver();
                    driver.firstStage();
                    break;
                default:
                    System.out.println(createShapes.indent + "INPUT VALID CHOICE");
                    break;
            }
        } while (choosingOPTIONs);
        }

    public void inputChooseCustomerOPTIONsPrimary(CustomerAccount customerAccount, User user, UserCredential username) {
        boolean choosingOPTIONs = true;
        InventoryHandler inventoryHandler = new InventoryHandler();
        AccountHandler accountHandler = new AccountHandler();
        AccountDisplay accountDisplay = new AccountDisplay();

            do {
                accountDisplay.displayCustomerUpgradedOptions(customerAccount, user);
                System.out.println(createShapes.indent + "CHOOSE AN OPTION: ");
                System.out.print(createShapes.indent + "-> ");
                input.setLength(0);
                input.append(sc.nextLine().trim());
                switch (input.toString()) {
                    case ("1"):
                        System.out.println(createShapes.indent + "OPTION 1: INVENTORY");
                        inventoryHandler.openInventory(user, customerAccount);
                        break;
                    case ("2"):
                        System.out.println(createShapes.indent + "OPTION 2: SHOP");
                        ShopHandler shopHandler = new ShopHandler();
                        shopHandler.beginShopping(customerAccount, user);
                        break;
                    case ("3"):
                        System.out.println(createShapes.indent + "OPTION 3: CHANGE USER");
                        choosingOPTIONs = false;
                        accountHandler.changeUser(customerAccount, username);
                        break;
                    case ("4"):
                        System.out.println(createShapes.indent + "OPTION 4: MANAGE MONEY");
                        inventoryHandler.manageBalance(customerAccount, user);
                        break;
                    case ("5"):
                        System.out.println(createShapes.indent + "OPTION 5: LOGOUT");
                        choosingOPTIONs = false;
                        System.out.println();
                        System.out.println(createShapes.indent + "LOGGING OUT");
                        Driver driver = new Driver();
                        driver.firstStage();
                        break;
                    case ("6"):
                        System.out.println(createShapes.indent + "OPTION 6: ADD USER");
                        try {
                            accountHandler.addUser(customerAccount);
                        } catch (EmptyInputException e) {
                            debugLogger.debug(e.toString());
                            System.out.println(createShapes.indent + "EMPTY INPUT, TRY AGAIN");
                        } catch (DuplicateUsernameException e) {
                            debugLogger.debug(e.toString());
                            System.out.println(createShapes.indent + "DUPLICATE USERNAME, TRY AGAIN");
                        }
                        break;
                    case ("7"):
                        System.out.println(createShapes.indent + "OPTION 7: TRANSFER FUNDS BETWEEN USERS");
                        inventoryHandler.transferFunds(customerAccount, user);
                        break;
                    case ("8"):
                        System.out.println(createShapes.indent + "OPTION 8: CHANGE NAME OF USER");
                        accountHandler.changeUserNames(customerAccount);
                        break;
                    case ("9"):
                        System.out.println(createShapes.indent + "OPTION 9: REMOVE USER");
                        accountHandler.removeUser(user, customerAccount);
                        break;
                    case ("10"):
                        System.out.println(createShapes.indent + "OPTION 10: ADD ACCOUNT");
                        try {
                            accountHandler.addAccount(user, customerAccount, username);
                        } catch (SQLException e) {

                        } catch (InvalidUserCredentialException e) {
                            e.printStackTrace();
                        }
                        break;
                    case ("11"):
                        System.out.println(createShapes.indent + "OPTION 11: CHANGE CURRENT ACCOUNT");
                        try {
                            accountHandler.initiateAccount(username);
                        } catch (AccountNotFoundException e) {
                            e.printStackTrace();
                        }
                    default:
                        System.out.println(createShapes.indent + "INPUT A VALID CHOICE");
                        break;
                }
            } while (choosingOPTIONs);
    }

    public void inputChooseEmployeeOption(EmployeeAccount employeeAccount, UserCredential username) {
        boolean choosingOPTIONs = true;
        InventoryHandler inventoryHandler = new InventoryHandler();
        AccountHandler accountHandler = new AccountHandler();
        AccountDisplay accountDisplay = new AccountDisplay();
        CustomerAccountDAO customerAccountDAO = new CustomerAccountDAO();
        CustomerUserDAO customerUserDAO = new CustomerUserDAO();

        do {
            accountDisplay.displayEmployeeAccount(employeeAccount);

            System.out.println(createShapes.indent + "CHOOSE AN OPTION: ");
            System.out.print(createShapes.indent + "-> ");
            input.setLength(0);
            input.append(sc.nextLine().trim());

            switch (input.toString()) {
                case ("1"):
                    System.out.println(createShapes.indent + "OPTION 1: VIEW ALL CUSTOMER ACCOUNTS");
                    for (CustomerAccount customerAccount : customerAccountDAO.getAllCustomerAccounts()) {
                        accountDisplay.displayCustomerAccount(customerAccount);
                    }
                    break;
                case ("2"):
                    System.out.println(createShapes.indent + "OPTION 2: VIEW SPECIFIC CUSTOMER ACCOUNT INFORMATION");
                    viewCustomerAccountInformation();
                    break;
                case ("3"):
                    System.out.println(createShapes.indent + "OPTION 3: VIEW ALL USERS");
                    accountDisplay.displayAllUsers(customerAccountDAO.getAllCustomerAccounts());
                    break;
                case ("4"):
                    System.out.println(createShapes.indent + "OPTION 4: VIEW SPECIFIC USER INFORMATION");
                    viewUserInformation(username);
                    break;
                case ("5"):
                    System.out.println(createShapes.indent + "OPTION 5: WIPE ACCOUNT FROM HISTORY");
                    deleteAccountTotally();
                    break;
                case ("6"):
                    System.out.println(createShapes.indent + "OPTION 6: DELETE SPECIFIC ACCOUNT");
                    deleteSpecificAccount();
                    break;
                case ("7"):
                    System.out.println(createShapes.indent + "OPTION 7: LOGOUT");
                    choosingOPTIONs = false;
                    System.out.println(createShapes.indent + "LOGGING OUT");
                    Driver driver = new Driver();
                    driver.firstStage();
                    break;
                default:
                    System.out.println(createShapes.indent + "Input a valid choice.");
                    break;
            }
        } while (choosingOPTIONs);
    }

    private void deleteSpecificAccount() {
        CustomerAccountDAO customerAccountDAO = new CustomerAccountDAO();
        boolean chooseAccount = true;
        do{
            System.out.println(createShapes.indent + "INPUT ACCOUNT NUMBER OR N TO RETURN");
            System.out.print(createShapes.indent + "-> ");
            input.setLength(0);
            input.append(sc.nextLine().trim());

            if(input.toString().contentEquals("N")){
                return;
            }

            int customerId = Integer.parseInt(input.toString());
            if(!customerAccountDAO.checkCustomerAccountId(customerId)){
                System.out.println(createShapes.indent + "INVALID CUSTOMER ACCOUNT ID");
                return;
            }
            CustomerAccount customerAccount = customerAccountDAO.getCustomerAccountById(customerId);
            try{
                int userCredentialId = customerAccount.getUserCredentialId();
                List<CustomerAccount> customerAccountList = customerAccountDAO.getCustomerAccountsByUserCredentialId(userCredentialId);
                if(customerAccountList.size() == 1){
                    System.out.println(createShapes.indent + "FINAL ACCOUNT OF CUSTOMER");
                    System.out.println(createShapes.indent + "REPEAT ID TO DELETE ACCOUNT");
                    deleteAccountTotally();
                    System.out.println(createShapes.indent + "DONE");
                    return;
                }
                customerAccountDAO.deleteCustomerAccountById(customerId);
                chooseAccount = false;
                transactionLogger.info("ADMIN ACTIVIY: ACCOUNT WIPE ID: " + customerAccount.getCustomerAccountId());
                System.out.println(createShapes.indent + "DONE");
            }catch (NumberFormatException e){
                debugLogger.debug(e.toString());
                System.out.println(createShapes.indent + "TYPE A VALID NUMBER");
            } catch (InvalidCustomerAccountIdException e) {
                debugLogger.debug(e.toString());
                System.out.println(createShapes.indent + "INVALID CUSTOMER ACCOUNT ID");
            } catch (SQLException throwables) {
                debugLogger.debug(String.valueOf(throwables));
            } catch (InvalidUserCredentialException e) {
                e.printStackTrace();
            }
        }while(chooseAccount);
    }

    public void deleteAccountTotally() {
        CustomerAccountDAO customerAccountDAO = new CustomerAccountDAO();
        CustomerUserDAO customerUserDAO = new CustomerUserDAO();
        InventoryDAO inventoryDAO = new InventoryDAO();
        PlanetDAO planetDAO = new PlanetDAO();
        AtmosphereDAO atmosphereDAO = new AtmosphereDAO();
        LifeDAO lifeDAO = new LifeDAO();
        boolean chooseAccount = true;
        boolean allowTermination = true;

        do{
            System.out.println(createShapes.indent + "INPUT ACCOUNT NUMBER OR N TO RETURN");
            System.out.print(createShapes.indent + "-> ");
            input.setLength(0);
            input.append(sc.nextLine().trim());

            if(input.toString().contentEquals("N")){
                return;
            }

            try{
                int customerId = Integer.parseInt(input.toString());
                if(!customerAccountDAO.checkCustomerAccountId(customerId)){
                    System.out.println(createShapes.indent + "INVALID CUSTOMER ACCOUNT ID");
                    return;
                }
                CustomerAccount customerAccount = customerAccountDAO.getCustomerAccountById(customerId);
                List<CustomerAccount> customerAccounts = customerAccountDAO.getCustomerAccountsByUserCredentialId(customerAccount.getUserCredentialId());

                List<User> users = customerUserDAO.getAllUsersByCustomerId(customerId);

                do{
                    System.out.println(createShapes.indent + "THIS WILL DELETE ALL INFORMATION RELATED TO ACCOUNT AND USER");
                    System.out.println(createShapes.shortIndent + "PROCEEDING WILL DELETE THE USER CREDENTIAL AND THEY WILL BE UNABLE TO LOGIN EXCEPT BY MAKING A NEW ACCOUNT");
                    System.out.println(createShapes.indent + "TYPE Y TO PROCEED TYPE N TO CANCEL AND RETURN");
                    System.out.print(createShapes.indent + "-> ");
                    input.setLength(0);
                    input.append(sc.nextLine());
                    switch (input.toString().trim()){
                        case ("Y"):
                            UserCredentialsDAO userCredentialsDAO = new UserCredentialsDAO();
                            userCredentialsDAO.deleteUserCredentialByUserCredentialId(customerAccount.getUserCredentialId());
                            allowTermination = false;
                            break;
                        case ("N"):
                            System.out.println(createShapes.indent + "CANCELLING AND RETURNING");
                            return;
                        default:
                            System.out.println(createShapes.indent + "INPUT A VALID CHOICE");
                            break;
                    }
                }while(allowTermination);

                    for (User user : users) {
                        int userId = user.getUserId();
                        int userInventoryId = user.getInventoryId();
                        inventoryDAO.deleteInventoryById(userInventoryId);
                        customerUserDAO.deleteUserById(userId);

                        List<Planet> planets = new ArrayList<>();
                        try{
                            planets = planetDAO.getPlanetsByUserId(user.getUserId());
                        }catch (NullPointerException e){
                            debugLogger.debug(e.toString());
                        }
                        if(planets.size() < 1){
                            transactionLogger.info("ADMIN ACTIVITY: TOTAL ACCOUNT WIPE PRIMARY ACCOUNT HOLDER USER CREDENTIAL ID: " + customerAccount.getUserCredentialId());
                            System.out.println(createShapes.indent + "COMPLETE");
                            return;
                        }
                        for (Planet planet : planets) {
                            planetDAO.deletePlanetById(planet.getId());
                            atmosphereDAO.deleteAtmosphereByPlanetId(planet.getId());
                            lifeDAO.deleteLifeByPlanetId(planet.getId());
                        }
                        for (CustomerAccount account : customerAccounts) {
                            customerAccountDAO.deleteCustomerAccountById(account.getCustomerAccountId());
                        }

                    }

                    chooseAccount = false;

            }catch (NumberFormatException e){
                debugLogger.debug(e.toString());
                System.out.println(createShapes.indent + "TYPE A VALID NUMBER");
            } catch (InvalidCustomerAccountIdException e) {
                debugLogger.debug(e.toString());
                System.out.println(createShapes.indent + "INVALID CUSTOMER ACCOUNT ID");
            } catch (InvalidUserIdException | InvalidInventoryIdException | InvalidUserCredentialException | SQLException | NoPlanetFoundException e) {
                debugLogger.debug(String.valueOf(e));
            }
        }while(chooseAccount);

    }

    public void viewUserInformation(UserCredential username) {

        CustomerUserDAO cUDao = new CustomerUserDAO();
        InventoryHandler inventoryHandler = new InventoryHandler();

        boolean chooseUser = true;

            do{
                System.out.println(createShapes.indent + "CHOOSE USER NUMBER");
                System.out.print(createShapes.indent + "-> ");
                input.setLength(0);
                input.append(sc.nextLine().trim());
                try{
                    int userId = Integer.parseInt(input.toString());
                    User user = cUDao.getUserById(userId);
                    inventoryHandler.openUserInventory(user);
                    chooseUser = false;
                }catch (NumberFormatException e){
                    debugLogger.debug(e.toString());
                    System.out.println(createShapes.indent + "TYPE A VALID NUMBER");
                } catch (InvalidUserIdException e) {
                    debugLogger.debug(e.toString());
                    System.out.println(createShapes.indent + "INVALID USER ID");
                }

            }while(chooseUser);
    }

    public void viewCustomerAccountInformation() {

        CustomerUserDAO customerUserDAO = new CustomerUserDAO();
        CustomerAccountDAO customerAccountDAO = new CustomerAccountDAO();
        AccountDisplay accountDisplay = new AccountDisplay();

        boolean chooseAccount = true;
        do{
            System.out.println(createShapes.indent + "INPUT ACCOUNT NUMBER");
            System.out.print(createShapes.indent + "-> ");
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
                System.out.println(createShapes.indent + "TYPE A VALID NUMBER");
            }
        }while(chooseAccount);
    }

    public void inputChooseAdminOption(EmployeeAccount employeeAccount, UserCredential username) {

        boolean choosingOPTIONs = true;
        EmployeeAccountDAO employeeAccountDAO = new EmployeeAccountDAO();
        AccountHandler accountHandler = new AccountHandler();
        AccountDisplay accountDisplay = new AccountDisplay();
        CustomerAccountDAO customerAccountDAO = new CustomerAccountDAO();
        CustomerUserDAO customerUserDAO = new CustomerUserDAO();

        do {
            accountDisplay.displayAdminAccount(employeeAccount);

            System.out.println(createShapes.indent + "CHOOSE AN OPTION:");
            System.out.print(createShapes.indent + "-> ");
            input.setLength(0);
            input.append(sc.nextLine().trim());

            switch (input.toString()) {
                case ("1"):
                    System.out.println(createShapes.indent + "OPTION 1: VIEW ALL CUSTOMER ACCOUNTS");
                    for (CustomerAccount customerAccount : customerAccountDAO.getAllCustomerAccounts()) {
                        accountDisplay.displayCustomerAccount(customerAccount);
                    }
                    break;
                case ("2"):
                    System.out.println(createShapes.indent + "OPTION 2: VIEW SPECIFIC CUSTOMER ACCOUNT INFORMATION");
                    viewCustomerAccountInformation();
                    break;
                case ("3"):
                    System.out.println(createShapes.indent + "OPTION 3: VIEW ALL USERS");
                    accountDisplay.displayAllUsers(customerAccountDAO.getAllCustomerAccounts());
                    break;
                case ("4"):
                    System.out.println(createShapes.indent + "OPTION 4: VIEW SPECIFIC USER INFORMATION");
                    viewUserInformation(username);
                    break;
                case ("5"):
                    System.out.println(createShapes.indent + "OPTION 5: WIPE ACCOUNT FROM HISTORY");
                    deleteAccountTotally();
                    break;
                case ("6"):
                    System.out.println(createShapes.indent + "OPTION 6: DELETE SPECIFIC ACCOUNT");
                    deleteSpecificAccount();
                    break;
                case ("7"):
                    System.out.println(createShapes.indent + "OPTION 7: LOGOUT");
                    choosingOPTIONs = false;
                    System.out.println();
                    System.out.println(createShapes.indent + "LOGGING OUT");
                    Driver driver = new Driver();
                    driver.firstStage();
                    break;
                case ("8"):
                    System.out.println(createShapes.indent + "OPTION 8: VIEW ALL EMPLOYEE ACCOUNTS");
                    List<EmployeeAccount> employeeAccounts = employeeAccountDAO.getAllEmployeeAccounts();
                    accountDisplay.displayEmployeeAccountInformation(employeeAccounts);
                    break;
                case ("9"):
                    System.out.println(createShapes.indent + "OPTION 9: ALTER ACCOUNT DATA");
                    accountHandler.changeAccountData(employeeAccount, username);
                    break;
                case ("10"):
                    System.out.println(createShapes.indent + "OPTION 10: ALTER USER DATA");
                    accountHandler.changeUserData(employeeAccount, username);
                    break;
                case ("11"):
                    System.out.println(createShapes.indent + "OPTION 11: ADD EMPLOYEE ACCOUNT");
                    try {
                        accountHandler.addEmployeeAccount(employeeAccount, username);
                    } catch (EmptyInputException e) {
                        debugLogger.debug(String.valueOf(e));
                        System.out.println(createShapes.indent + "EMPTY INPUT REGISTERED, TRY AGAIN");
                    }
                    break;
                case ("12"):
                    System.out.println(createShapes.indent + "OPTION 12: ADD ADMIN ACCOUNT");
                    try {
                        accountHandler.addAdminAccount(employeeAccount, username);
                    } catch (EmptyInputException e) {
                        debugLogger.debug(String.valueOf(e));
                        System.out.println(createShapes.indent + "EMPTY INPUT REGISTERED, TRY AGAIN");
                    }
                    break;
                default:
                    System.out.println(createShapes.indent + "Input a valid choice.");
                    break;
            }
        } while (choosingOPTIONs);

    }
}
