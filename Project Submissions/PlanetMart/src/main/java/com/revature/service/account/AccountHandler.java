package com.revature.service.account;

import com.revature.display.account.AccountDisplay;
import com.revature.display.login.LoginDisplay;
import com.revature.display.utility.CreateShapes;
import com.revature.models.accounts.CustomerAccount;
import com.revature.models.accounts.EmployeeAccount;
import com.revature.models.shop.Inventory;
import com.revature.models.users.User;
import com.revature.models.users.UserCredential;
import com.revature.repository.*;
import com.revature.repository.Exception.*;
import com.revature.service.exceptions.EmptyInputException;
import com.revature.service.shop.InventoryHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.auth.login.AccountNotFoundException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class AccountHandler {
    private final Logger transactionLogger = LoggerFactory.getLogger("transactionLogger");
    private final Logger debugLogger = LoggerFactory.getLogger("debugLogger");
    private final Logger errorLogger = LoggerFactory.getLogger("errorLogger");

    private final StringBuilder input = new StringBuilder();
    private final Scanner sc = new Scanner(System.in);
    AccountDisplay accountDisplay = new AccountDisplay();
    CreateShapes createShapes = new CreateShapes();

    public void changeUser(CustomerAccount customerAccount, UserCredential username) {
        CustomerUserDAO cUDao = new CustomerUserDAO();
        AccountInputHandler accountInputHandler = new AccountInputHandler();
        List<User> users = cUDao.getAllUsersByCustomerId(customerAccount.getCustomerAccountId());
        boolean chooseUser = true;

        accountDisplay.displayUsers(users, customerAccount);

        if(!users.isEmpty()){
            do{
                System.out.println(createShapes.indent + "CHOOSE USER NUMBER: ");
                System.out.print(createShapes.indent + "-> ");
                input.setLength(0);
                input.append(sc.nextLine().trim());
                for (User user : users) {
                    if (String.valueOf(user.getUserId()).contentEquals(input)) {
                        accountInputHandler.inputChooseCustomerOptions(customerAccount, user, username);
                        chooseUser = false;
                        break;
                    }
                }
            }while(chooseUser);
        }
    }

    public void initiateAccount(UserCredential username) throws AccountNotFoundException {
        CustomerAccountDAO cDao = new CustomerAccountDAO();
        EmployeeAccountDAO eDao = new EmployeeAccountDAO();
        AccountInputHandler accountInputHandler = new AccountInputHandler();
        List<CustomerAccount> customerAccountList;
        boolean chooseAccount = true;
        EmployeeAccount employeeAccount;

        try {
            customerAccountList = cDao.getCustomerAccountsByUserCredentialId(username.getId());
            try {
                employeeAccount = eDao.getEmployeeAccountsByUserId(username.getId());
                if(!customerAccountList.isEmpty()){
                    System.out.println(createShapes.border);
                    for (CustomerAccount customerAccount : customerAccountList) {
                        accountDisplay.displayCustomerAccount(customerAccount);
                    }
                    do {
                        System.out.println();
                        System.out.println(createShapes.indent + "TYPE ACCOUNT NUMBER: ");
                        System.out.print(createShapes.indent + "-> ");
                        input.setLength(0);
                        input.append(sc.nextLine().trim());
                        for (CustomerAccount customerAccount : customerAccountList) {
                            if(String.valueOf(customerAccount.getCustomerAccountId()).contentEquals(input)){
                                System.out.println(createShapes.border);
                                chooseUser(customerAccount, username);
                                chooseAccount = false;
                                break;
                            }
                        }
                        System.out.print(createShapes.indent + "INPUT A VALID NUMBER");
                    }while(chooseAccount);
                }
                if(employeeAccount != null){
                    if(employeeAccount.getEmployeeId() == employeeAccount.getAdminId()) {
                        accountInputHandler.inputChooseAdminOption(employeeAccount, username);
                    }else{
                        accountInputHandler.inputChooseEmployeeOption(employeeAccount, username);
                    }
                    return;
                }
                throw new AccountNotFoundException("username is not attached to any account");
            } catch (SQLException e) {
                debugLogger.debug(String.valueOf(e));
                System.out.println(createShapes.indent + "DATABASE ERROR, RESTART APPLICATION IF ERROR PERSISTS");
            } catch (InvalidUserCredentialException e) {
                debugLogger.debug(String.valueOf(e));
                System.out.println(createShapes.indent + "INVALID USER CREDENTIAL");
            }
        } catch (InvalidUserCredentialException | SQLException e){
            errorLogger.error(String.valueOf(e));
            System.out.println(createShapes.indent + "DATABASE ERROR TRY AGAIN");
        } catch (Exception e){
            errorLogger.error(String.valueOf(e));
            System.out.println(createShapes.indent + "ERROR TRY AGAIN OR RESTART IF ERROR PERSISTS");
        }
    }

    private void chooseUser(CustomerAccount customerAccount, UserCredential username) {
        CustomerUserDAO cUDao = new CustomerUserDAO();
        AccountInputHandler accountInputHandler = new AccountInputHandler();
        List<User> users = cUDao.getAllUsersByCustomerId(customerAccount.getCustomerAccountId());
        boolean chooseUser = true;

        accountDisplay.displayUsers(users, customerAccount);
        if(!users.isEmpty()){
            do{
                System.out.println();
                System.out.println(createShapes.indent + "CHOOSE USER NUMBER: ");
                System.out.print(createShapes.indent + "-> ");
                input.setLength(0);
                input.append(sc.nextLine().trim());
                for (User user : users) {
                    if (String.valueOf(user.getUserId()).contentEquals(input)) {
                        accountInputHandler.inputChooseCustomerOptions(customerAccount, user, username);
                        chooseUser = false;
                        break;
                    }
                }
                System.out.println();
                System.out.println(createShapes.indent + "INVALID INPUT");
            }while(chooseUser);
        }
    }

    public void changeUserNames(CustomerAccount customerAccount) {
        CustomerUserDAO cUDao = new CustomerUserDAO();
        List<User> users = cUDao.getAllUsersByCustomerId(customerAccount.getCustomerAccountId());
        boolean chooseUser = true;

        accountDisplay.displayUsers(users, customerAccount);
        if(!users.isEmpty()){
            do{
                System.out.println();
                System.out.println(createShapes.indent + "CHOOSE USER NUMBER OR N TO RETURN");
                System.out.print(createShapes.indent + "-> ");
                input.setLength(0);
                input.append(sc.nextLine().trim());
                if(input.toString().contentEquals("N")){
                    System.out.println(createShapes.indent + "RETURNING");
                    return;
                }
                for (User user : users) {
                    if (String.valueOf(user.getUserId()).contentEquals(input)) {
                        System.out.println(createShapes.indent + "CHOOSE NEW NAME:");
                        System.out.print(createShapes.indent + "-> ");
                        input.setLength(0);
                        input.append(sc.nextLine().trim());
                        cUDao.updateUserName(user.getUserId(), input.toString());
                        try {
                            System.out.println(createShapes.indent + "USER NAME CHANGED TO: " + cUDao.getUserById(user.getUserId()).getName());
                            transactionLogger.info("USER NAME CHANGED: " + "USER ID: " + user.getUserId() + " USER NEW NAME: " + user.getName());
                            chooseUser = false;
                        } catch (InvalidUserIdException e) {
                            debugLogger.debug(String.valueOf(e));
                            System.out.println(createShapes.indent + "USER ID ERROR");
                        }
                        break;
                    }
                }
            }while(chooseUser);
        }
    }

    public void removeUser(User pUser, CustomerAccount customerAccount) {
        CustomerUserDAO cUDao = new CustomerUserDAO();
        List<User> users = cUDao.getAllUsersByCustomerId(customerAccount.getCustomerAccountId());
        boolean chooseUser = true;
        accountDisplay.displayUsers(users, customerAccount);

        if(!users.isEmpty()){
            do{
                System.out.println(createShapes.indent + "CHOOSE USER NUMBER OR TYPE N TO LEAVE");
                System.out.print(createShapes.indent + "-> ");
                input.setLength(0);
                input.append(sc.nextLine().trim());
                if(input.toString().contentEquals("N")){
                    System.out.println(createShapes.indent + "RETURNING");
                    return;
                }
                for (User user : users) {
                    if (String.valueOf(user.getUserId()).contentEquals(input)) {
                        if(user.getUserId() == pUser.getUserId()) {
                            System.out.println(createShapes.indent + "CANNOT DELETE CURRENT USER");
                            break;
                        }
                        try {
                            cUDao.deleteUserById(user.getUserId());
                            transactionLogger.info("USER DELETED: " + " ACCOUNT ID: " + customerAccount.getCustomerAccountId() + " FORMER USER ID: " + user.getUserId());
                            chooseUser = false;
                            break;
                        } catch (InvalidUserIdException e) {
                            debugLogger.debug(String.valueOf(e));
                            System.out.println(createShapes.indent + "USER NOT VALID");
                        }
                        System.out.println(createShapes.indent + "USER HAS BEEN DELETED");
                    }
                }
            }while(chooseUser);
        }
    }

    public void addUser(CustomerAccount customerAccount) throws EmptyInputException, DuplicateUsernameException {
        CustomerUserDAO cUDao = new CustomerUserDAO();
        InventoryDAO inventoryDAO = new InventoryDAO();
        List<Inventory> inventories = inventoryDAO.getAllInventories();
        boolean addingUser = true;

        do{
            System.out.println(createShapes.indent + "INPUT NEW USER NAME OR N TO LEAVE");
            System.out.print(createShapes.indent + "-> ");
            input.setLength(0);
            input.append(sc.nextLine().trim());
            if(input.toString().contentEquals("N")){
                System.out.println(createShapes.indent + "RETURNING");
                return;
            }else if(input.toString().contentEquals("")) throw new EmptyInputException("User name input empty.");
            else {
                for (User user1 : cUDao.getAllUsersByCustomerId(customerAccount.getCustomerAccountId())) {
                    if (user1.getName().contentEquals(input.toString()))
                        throw new DuplicateUsernameException("User with inputted name already exists.");
                }
                inventoryDAO.addInventory(new Inventory(0, 0));
                int inventoryId = inventories.get(inventories.size() - 1).getId();
                try {
                    cUDao.addUser(input.toString(), inventoryId, customerAccount.getCustomerAccountId());
                    addingUser = false;
                    transactionLogger.info("ADDED USER: " + "ACCOUNT ID: " + customerAccount.getCustomerAccountId());
                } catch (InvalidCustomerAccountIdException e) {
                    errorLogger.error(String.valueOf(e));
                    System.out.println(createShapes.indent + "INVALID CUSTOMER ACCOUNT ID");
                } catch (InvalidInventoryIdException e) {
                    errorLogger.error(String.valueOf(e));
                    System.out.println(createShapes.indent + "INVALID INVENTORY ID");
                }
            }}while(addingUser);
    }

    public void addAccount(User user, CustomerAccount customerAccount, UserCredential username) throws SQLException, InvalidUserCredentialException {
        CustomerAccountDAO customerAccountDAO = new CustomerAccountDAO();
        CustomerUserDAO customerUserDAO = new CustomerUserDAO();
        try {
            customerAccountDAO.addCustomerAccount(username.getId(), customerAccount.getPrimaryUserId());
            List<CustomerAccount> customerAccountList = customerAccountDAO.getCustomerAccountsByUserCredentialId(username.getId());
            int customerAccountId = customerAccountList.get(customerAccountList.size()-1).getCustomerAccountId();
            try {
                customerUserDAO.addUser(user.getName(), user.getInventoryId(), customerAccountId);
                int newUserId = customerUserDAO.getAllUsersByCustomerId(customerAccountId).get(0).getUserId();
                customerAccountDAO.updateCustomerAccountPrimaryId(customerAccountId, newUserId);
                transactionLogger.info("ADDED ACCOUNT: " + "NEW ACCOUNT ID: " + customerAccountId + " PRIMARY USER ID: " + user.getUserId());
                System.out.println(createShapes.indent + "ADDED CUSTOMER ACCOUNT");
            } catch (InvalidCustomerAccountIdException e) {
                debugLogger.debug(String.valueOf(e));
                System.out.println(createShapes.indent + "INVALID CUSTOMER ACCOUNT ID");
            } catch (InvalidInventoryIdException e) {
                debugLogger.debug(String.valueOf(e));
                System.out.println(createShapes.indent + "INVALID INVENTORY ID");
            } catch (Exception e){
                errorLogger.error(String.valueOf(e));
                System.out.println(createShapes.indent + "ERROR TRY AGAIN OR RESTART APPLICATION");
            }
        } catch (InvalidPrimaryUserException e) {
            debugLogger.debug(String.valueOf(e));
            System.out.println(createShapes.indent + "INVALID PRIMARY USER ID");
        } catch (Exception e){
            errorLogger.error(String.valueOf(e));
            System.out.println(createShapes.indent + "ERROR TRY AGAIN OR RESTART APPLICATION");
        }


    }

    public void changeAccountData(EmployeeAccount employeeAccount, UserCredential username) {
        AccountDisplay accountDisplay = new AccountDisplay();
        CustomerAccountDAO customerAccountDAO = new CustomerAccountDAO();
        CustomerUserDAO customerUserDAO = new CustomerUserDAO();
        boolean chooseAccount = true;

        do{
            System.out.println(createShapes.indent + "INPUT ACCOUNT NUMBER OR N TO RETURN");
            System.out.print(createShapes.indent + "-> ");
            input.setLength(0);
            input.append(sc.nextLine().trim());
            if(input.toString().contentEquals("N")){
                System.out.println(createShapes.indent + "RETURNING");
                return;
            }
            try{
                int customerId = Integer.parseInt(input.toString());
                CustomerAccount customerAccount = customerAccountDAO.getCustomerAccountById(customerId);
                List<User> users = customerUserDAO.getAllUsersByCustomerId(customerAccount.getCustomerAccountId());
                boolean changeData = true;
                accountDisplay.displayCustomerAccount(customerAccount);
                do{
                    System.out.println(createShapes.indent + "CHOOSE OPTION");
                    System.out.println(createShapes.indent + "1. CHANGE PRIMARY USER ID");
                    System.out.println(createShapes.indent + "2. RETURN");
                    try{
                        System.out.print(createShapes.indent + "-> ");
                        input.setLength(0);
                        input.append(sc.nextLine().trim());
                        switch(input.toString()){
                            case ("1"):
                                accountDisplay.displayUsers(users, customerAccount);
                                changeAccountPrimaryUser(users, customerAccount);
                                transactionLogger.info("EMPLOYEE ACTIVITY: " + "CUSTOMER ACCOUNT PRIMARY ID CHANGED - " + "EMPLOYEE ID: " +employeeAccount.getEmployeeId() + "CUSTOMER ACCOUNT ID: " + customerAccount.getCustomerAccountId());
                                break;
                            case("2"):
                                changeData = false;
                                System.out.println(createShapes.indent + "RETURNING");
                                break;
                            default:
                                System.out.println(createShapes.indent + "INVALID INPUT");
                                break;
                        }

                    }catch (NumberFormatException e){
                        debugLogger.debug(String.valueOf(e));
                        System.out.println(createShapes.indent + "TYPE A VALID NUMBER");
                    } catch (Exception e){
                        errorLogger.error(String.valueOf(e));
                        System.out.println(createShapes.indent + "ERROR TRY AGAIN OR RESTART APPLICATION");
                    }
                }while(changeData);
                chooseAccount = false;
            }catch (NumberFormatException e){
                debugLogger.debug(String.valueOf(e));
                System.out.println(createShapes.indent + "TYPE A VALID NUMBER");
            } catch (Exception e){
                errorLogger.error(String.valueOf(e));
                System.out.println(createShapes.indent + "ERROR TRY AGAIN OR RESTART APPLICATION");
            }
        }while(chooseAccount);
    }

    private void changeAccountPrimaryUser(List<User> users, CustomerAccount customerAccount) {
        boolean changeAccount = true;
        CustomerAccountDAO customerAccountDAO = new CustomerAccountDAO();

        do{

            System.out.println(createShapes.indent + "INPUT NEW PRIMARY USER ID OR N TO LEAVE");
            try{
                System.out.print(createShapes.indent + "-> ");
                input.setLength(0);
                input.append(sc.nextLine().trim());
                if(input.toString().contentEquals("N")) return;
                int newPrimaryUserId = Integer.parseInt(input.toString());
                if(newPrimaryUserId == customerAccount.getPrimaryUserId()) {
                    System.out.println(createShapes.indent + "REPEATED PRIMARY USER");
                    break;
                }
                customerAccountDAO.updateCustomerAccountPrimaryId(customerAccount.getCustomerAccountId(), newPrimaryUserId);
                transactionLogger.info("EMPLOYEE ALTERATION:" + " - CUSTOMER ACCOUNT PRIMARY USER CHANGED - " + "ACCOUNT ID: " + customerAccount.getCustomerAccountId()
                + "NEW PRIMARY USER ID: " + newPrimaryUserId);
                changeAccount = false;
            } catch (NumberFormatException e){
                debugLogger.debug(String.valueOf(e));
                System.out.println(createShapes.indent + "INVALID INPUT");
            }
        }while (changeAccount);
    }

    public void changeUserData(EmployeeAccount employeeAccount, UserCredential username) {
        AccountDisplay accountDisplay = new AccountDisplay();
        InventoryHandler inventoryHandler = new InventoryHandler();
        CustomerAccountDAO customerAccountDAO = new CustomerAccountDAO();
        CustomerUserDAO customerUserDAO = new CustomerUserDAO();
        boolean chooseUser = true;

        do{
            accountDisplay.displayAllUsers(customerAccountDAO.getAllCustomerAccounts());
            System.out.println(createShapes.indent + "INPUT USER NUMBER ");
            System.out.print(createShapes.indent + "-> ");

            input.setLength(0);
            input.append(sc.nextLine().trim());

            try{
                int userId = Integer.parseInt(input.toString());
                User user = customerUserDAO.getUserById(userId);
                transactionLogger.info("EMPLOYEE ACTIVITY: " + " - CHANGING USER DATA - " + "EMPLOYEE ID: " + employeeAccount.getEmployeeId() + " USER ID: " + userId);
                boolean changeData = true;

                do{
                    System.out.println(createShapes.indent + "CHOOSE OPTION");
                    System.out.println(createShapes.indent + "1. CHANGE USER NAME");
                    System.out.println(createShapes.indent + "2. CHANGE USER BALANCE");
                    System.out.println(createShapes.indent + "3. DELETE USER");
                    System.out.println(createShapes.indent + "4. RETURN");

                    try{
                        System.out.print(createShapes.indent + "-> ");
                        input.setLength(0);
                        input.append(sc.nextLine().trim());
                        switch(input.toString()){
                            case ("1"):
                                changeUserNames(customerAccountDAO.getCustomerAccountById(user.getCustomerAccountId()));
                                break;
                            case("2"):
                                inventoryHandler.manageBalance(customerAccountDAO.getCustomerAccountById(user.getCustomerAccountId()), user);
                                break;
                            case("3"):
                                removeUser(user, customerAccountDAO.getCustomerAccountById(user.getCustomerAccountId()));
                                break;
                            case("4"):
                                changeData = false;
                                System.out.println(createShapes.indent + "RETURNING");
                                break;
                            default:
                                System.out.println(createShapes.indent + "INVALID INPUT");
                                break;
                        }
                    }catch (NumberFormatException e){
                        debugLogger.debug(String.valueOf(e));
                        System.out.println(createShapes.indent + "TYPE A VALID NUMBER");
                    } catch (Exception e){
                        errorLogger.error(String.valueOf(e));
                        System.out.println(createShapes.indent + "ERROR TRY AGAIN OR RESTART APPLICATION");
                    }
                }while(changeData);
                chooseUser = false;
            }catch (NumberFormatException e){
                debugLogger.debug(String.valueOf(e));
                System.out.println(createShapes.indent + "TYPE A VALID NUMBER");
            } catch (InvalidUserIdException e) {
                debugLogger.debug(String.valueOf(e));
                System.out.println(createShapes.indent + "INVALID USER ID");
            } catch (Exception e){
                errorLogger.error(String.valueOf(e));
                System.out.println(createShapes.indent + "ERROR TRY AGAIN OR RESTART APPLICATION");
            }
        }while(chooseUser);
    }

    public void addEmployeeAccount(EmployeeAccount employeeAccount, UserCredential username) throws EmptyInputException {
        boolean creatingAccount = true;
        UserCredentialsDAO userCredentialsDAO = new UserCredentialsDAO();
        EmployeeAccountDAO employeeAccountDAO = new EmployeeAccountDAO();
        StringBuilder employeeUsername = new StringBuilder();
        StringBuilder employeePassword = new StringBuilder();
        LoginDisplay loginDisplay = new LoginDisplay();
        do {
            employeeUsername.setLength(0);
            employeePassword.setLength(0);
            loginDisplay.printCreateEmployeeAccountDisplay();
            input.setLength(0);
            input.append(sc.nextLine().trim());
            switch (input.toString()) {
                case ("1"):
                    System.out.println(createShapes.indent + "USERNAME: ");
                    System.out.print(createShapes.indent + "-> ");
                    employeeUsername.append(sc.nextLine()).trimToSize();
                    System.out.println(createShapes.indent + "PASSWORD: ");
                    System.out.print(createShapes.indent + "-> ");
                    employeePassword.append(sc.nextLine()).trimToSize();

                    if(employeeUsername.toString().contentEquals("")) throw new EmptyInputException("Empty employee username");
                    if(employeePassword.toString().contentEquals("")) throw new EmptyInputException("Empty employee password");

                    if(userCredentialsDAO.getUserCredentialByUsername(employeeUsername.toString()) == null){
                        System.out.print(createShapes.indent + "FIRST NAME: ");
                        input.setLength(0);
                        String firstName = input.append(sc.nextLine().trim()).toString();
                        System.out.print(createShapes.indent + "LAST NAME: ");
                        input.setLength(0);
                        String lastName = input.append(sc.nextLine().trim()).toString();

                        if(firstName.contentEquals("")) throw new EmptyInputException("Empty First Name");
                        if(lastName.contentEquals("")) throw new EmptyInputException("Empty Last Name");

                        userCredentialsDAO.addUserCredential(new UserCredential(0,
                                employeeUsername.toString(),
                                employeePassword.toString(),
                                firstName,
                                lastName));

                        int userCredentialId = userCredentialsDAO.getUserCredentialByUsername(employeeUsername.toString()).getId();
                        try {
                            employeeAccountDAO.addEmployeeAccount(userCredentialId, employeeAccount.getAdminId());
                            System.out.println(createShapes.indent + "SUCCESSFUL CREATION OF EMPLOYEE ACCOUNT");
                            creatingAccount = false;
                            transactionLogger.info("ADMIN ACTIVITY: " + " - CREATING NEW EMPLOYEE ACCOUNT - " + "ADMIN ID: " + employeeAccount.getEmployeeId() + " NEW EMPLOYEE USER CREDENTIAL ID: " + userCredentialId);
                            break;
                        } catch (SQLException e) {
                            debugLogger.debug(String.valueOf(e));
                        } catch (InvalidAdminIdException e) {
                            debugLogger.debug(String.valueOf(e));
                        } catch (InvalidUserCredentialException e) {
                            debugLogger.debug(String.valueOf(e));
                        }catch (Exception e){
                            errorLogger.error(String.valueOf(e));
                            System.out.println(createShapes.indent + "ERROR TRY AGAIN OR RESTART APPLICATION");
                        }
                    }
                    break;
                case ("2"):
                    System.out.println(createShapes.indent + "Returning to Login.");
                    creatingAccount = false;
                    break;
                default:
                    System.out.println(createShapes.indent + "Type a valid input.");
                    break;
            }
        } while (creatingAccount);
    }

    public void addAdminAccount(EmployeeAccount employeeAccount, UserCredential username) throws EmptyInputException {
        boolean creatingAccount = true;
        UserCredentialsDAO userCredentialsDAO = new UserCredentialsDAO();
        EmployeeAccountDAO employeeAccountDAO = new EmployeeAccountDAO();
        StringBuilder employeeUsername = new StringBuilder();
        StringBuilder employeePassword = new StringBuilder();
        LoginDisplay loginDisplay = new LoginDisplay();
        do {
            employeeUsername.setLength(0);
            employeePassword.setLength(0);
            loginDisplay.printCreateAdminAccountDisplay();
            input.setLength(0);
            input.append(sc.nextLine().trim());
            switch (input.toString()) {
                case ("1"):
                    System.out.println(createShapes.indent + "USERNAME: ");
                    System.out.print(createShapes.indent + "-> ");
                    employeeUsername.append(sc.nextLine()).trimToSize();
                    System.out.println(createShapes.indent + "PASSWORD: ");
                    System.out.print(createShapes.indent + "-> ");
                    employeePassword.append(sc.nextLine()).trimToSize();
                    if(employeeUsername.toString().contentEquals("")) throw new EmptyInputException("Empty employee username");
                    if(employeePassword.toString().contentEquals("")) throw new EmptyInputException("Empty employee password");


                    if(userCredentialsDAO.getUserCredentialByUsername(employeeUsername.toString()) == null){
                        System.out.print(createShapes.indent + "FIRST NAME: ");
                        input.setLength(0);
                        String firstName = input.append(sc.nextLine().trim()).toString();
                        System.out.print(createShapes.indent + "LAST NAME: ");
                        input.setLength(0);
                        String lastName = input.append(sc.nextLine().trim()).toString();

                        if(firstName.contentEquals("")) throw new EmptyInputException("Empty First Name");
                        if(lastName.contentEquals("")) throw new EmptyInputException("Empty Last Name");

                        userCredentialsDAO.addUserCredential(new UserCredential(0,
                                employeeUsername.toString(),
                                employeePassword.toString(),
                                firstName,
                                lastName));

                        int userCredentialId = userCredentialsDAO.getUserCredentialByUsername(employeeUsername.toString()).getId();
                        try {
                            employeeAccountDAO.addEmployeeAccount(userCredentialId, employeeAccount.getAdminId());
                            int employeeId = employeeAccountDAO.getEmployeeAccountsByUserId(userCredentialId).getEmployeeId();
                            employeeAccountDAO.updateEmployeeAdmin(employeeId, employeeId);
                            System.out.println(createShapes.indent + "SUCCESSFUL CREATION OF ADMIN ACCOUNT");
                            creatingAccount = false;
                            transactionLogger.info("ADMIN ACTIVITY: " + "- CREATED NEW ADMIN ACCOUNT - " + "ADMIN RESPONSIBLE ID: " + employeeAccount.getEmployeeId() + " ADMIN CREATED ID: " + employeeId);
                            break;
                        } catch (SQLException e) {
                            debugLogger.debug(String.valueOf(e));
                            System.out.println(createShapes.indent + "DATABASE ERROR");
                        } catch (InvalidAdminIdException e) {
                            debugLogger.debug(String.valueOf(e));
                            System.out.println(createShapes.indent + "INVALID ADMIN ID");
                        } catch (InvalidUserCredentialException e) {
                            debugLogger.debug(String.valueOf(e));
                            System.out.println(createShapes.indent + "INVALID USER CREDENTIALS");
                        } catch (InvalidEmployeeAccountIdException e) {
                            debugLogger.debug(String.valueOf(e));
                            System.out.println(createShapes.indent + "INVALID EMPLOYEE ACCOUNT");
                        } catch (Exception e){
                            errorLogger.error(String.valueOf(e));
                            System.out.println(createShapes.indent + "ERROR TRY AGAIN OR RESTART APPLICATION");
                        }
                    }
                    break;
                case ("2"):
                    System.out.println(createShapes.indent + "Returning to Login.");
                    creatingAccount = false;
                    break;
                default:
                    System.out.println(createShapes.indent + "Type a valid input.");
                    break;
            }
        } while (creatingAccount);
    }
}
