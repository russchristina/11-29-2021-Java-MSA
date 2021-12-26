package com.revature.service.account;

import com.revature.database.exceptions.DuplicateUsernameException;
import com.revature.display.account.AccountDisplay;
import com.revature.models.accounts.CustomerAccount;
import com.revature.models.accounts.EmployeeAccount;
import com.revature.models.shop.Inventory;
import com.revature.models.users.User;
import com.revature.models.users.UserCredential;
import com.revature.repository.CustomerAccountDAO;
import com.revature.repository.CustomerUserDAO;
import com.revature.repository.EmployeeAccountDAO;
import com.revature.repository.InventoryDAO;
import com.revature.service.exceptions.EmptyInputException;
import com.revature.service.shop.InventoryHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;
import java.util.Scanner;

public class AccountHandler {
    private final Logger log = LoggerFactory.getLogger(AccountHandler.class);
    private final StringBuilder input = new StringBuilder();
    private final Scanner sc = new Scanner(System.in);
    AccountDisplay accountDisplay = new AccountDisplay();

    public void changeUser(CustomerAccount customerAccount, UserCredential username) {
        CustomerUserDAO cUDao = new CustomerUserDAO();
        AccountInputHandler accountInputHandler = new AccountInputHandler();


        List<User> users = cUDao.getAllUsersByCustomerId(customerAccount.getCustomerAccountId());
        boolean chooseUser = true;
        accountDisplay.displayUsers(users, customerAccount);

        if(!users.isEmpty()){

            do{
                System.out.println("\nCHOOSE USER NUMBER");
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
        List<CustomerAccount> customerAccountList = cDao.getCustomerAccountsByPrimaryUserId(username.getId());
        EmployeeAccount employeeAccount = eDao.getEmployeeAccountsById(username.getId());
        boolean chooseAccount = true;


        if(!customerAccountList.isEmpty()){
            for (CustomerAccount customerAccount : customerAccountList) {
                accountDisplay.displayCustomerAccount(customerAccount);
            }

            do {
                System.out.println("\nTYPE ACCOUNT NUMBER");
                input.setLength(0);
                input.append(sc.nextLine().trim());
                for (CustomerAccount customerAccount : customerAccountList) {
                    if(String.valueOf(customerAccount.getCustomerAccountId()).contentEquals(input)){
                        chooseUser(customerAccount, username);
                        chooseAccount = false;
                        break;
                    }
                }
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
    }

    private void chooseUser(CustomerAccount customerAccount, UserCredential username) {
        CustomerUserDAO cUDao = new CustomerUserDAO();
        AccountInputHandler accountInputHandler = new AccountInputHandler();

        List<User> users = cUDao.getAllUsersByCustomerId(customerAccount.getCustomerAccountId());
        boolean chooseUser = true;
        accountDisplay.displayUsers(users, customerAccount);

        if(!users.isEmpty()){
            do{
                System.out.println("\nCHOOSE USER NUMBER");
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

    public void changeUserNames(User pUser, CustomerAccount customerAccount) {

        CustomerUserDAO cUDao = new CustomerUserDAO();
        List<User> users = cUDao.getAllUsersByCustomerId(customerAccount.getCustomerAccountId());
        boolean chooseUser = true;
        accountDisplay.displayUsers(users, customerAccount);

        if(!users.isEmpty()){

            do{
                System.out.println("\nCHOOSE USER NUMBER OR N TO RETURN");
                input.setLength(0);
                input.append(sc.nextLine().trim());
                if(input.toString().contentEquals("N")){
                    System.out.println("RETURNING");
                    return;
                }
                for (User user : users) {
                    if (String.valueOf(user.getUserId()).contentEquals(input)) {

                        System.out.println("\nCHOOSE NEW NAME:");
                        input.setLength(0);
                        input.append(sc.nextLine().trim());
                        cUDao.updateUserName(user.getUserId(), input.toString());
                        System.out.println("USER NAME CHANGED TO: " + cUDao.getUserById(user.getUserId()).getName());

                        chooseUser = false;
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
                System.out.println("\nCHOOSE USER NUMBER OR TYPE N TO LEAVE");
                input.setLength(0);
                input.append(sc.nextLine().trim());
                if(input.toString().contentEquals("N")){
                    System.out.println("RETURNING");
                    return;
                }
                for (User user : users) {
                    if (String.valueOf(user.getUserId()).contentEquals(input)) {
                        if(user.getUserId() == pUser.getUserId()) {
                            System.out.println("\nCANNOT DELETE CURRENT USER");
                            break;
                        }
                        cUDao.deleteUserById(user.getUserId());
                        System.out.println("\nUSER HAS BEEN DELETED");
                        chooseUser = false;
                        break;
                    }
                }
            }while(chooseUser);
        }
    }

    public void addUser(User user, CustomerAccount customerAccount) throws EmptyInputException, DuplicateUsernameException {
        CustomerUserDAO cUDao = new CustomerUserDAO();
        InventoryDAO inventoryDAO = new InventoryDAO();

        boolean addingUser = true;
        boolean inputtingName = true;

        do{
            do{
                System.out.println("\nINPUT NEW USER NAME OR N TO LEAVE...");
                input.setLength(0);
                input.append(sc.nextLine().trim());
                if(input.toString().contentEquals("N")){
                    System.out.println("RETURNING");
                    return;
                }
                if(input.toString().contentEquals("")) throw new EmptyInputException("Name cannot be empty.");
                for (User user1 : cUDao.getAllUsersByCustomerId(customerAccount.getCustomerAccountId())) {
                    if(user1.getName().contentEquals(input.toString())) throw new DuplicateUsernameException("User with that name already exists.");
                }
                inputtingName = false;
            }while(inputtingName);

            inventoryDAO.addInventory(new Inventory(0, 0));

            List<Inventory> inventories = inventoryDAO.getAllInventories();
            int inventoryId = inventories.get(inventories.size()-1).getId();

            cUDao.addUser(input.toString(), inventoryId, customerAccount.getCustomerAccountId());
            addingUser = false;
        }while(addingUser);

    }

    public void addAccount(User user, CustomerAccount customerAccount, UserCredential username) {
        CustomerAccountDAO customerAccountDAO = new CustomerAccountDAO();
        CustomerUserDAO customerUserDAO = new CustomerUserDAO();
        customerAccountDAO.addCustomerAccount(username.getId(), customerAccount.getPrimaryUserId());
        List<CustomerAccount> customerAccountList = customerAccountDAO.getCustomerAccountsByUserCredentialId(username.getId());
        int customerAccountId = customerAccountList.get(customerAccountList.size()-1).getCustomerAccountId();
        customerUserDAO.addUser(user.getName(), user.getInventoryId(), customerAccountId);

        int newUserId = customerUserDAO.getAllUsersByCustomerId(customerAccountId).get(0).getUserId();

        customerAccountDAO.updateCustomerAccountPrimaryId(customerAccountId, newUserId);

        System.out.println("ADDED CUSTOMER ACCOUNT");
    }

    public void changeAccountData(EmployeeAccount employeeAccount, UserCredential username) {

        AccountDisplay accountDisplay = new AccountDisplay();
        InventoryHandler inventoryHandler = new InventoryHandler();
        CustomerAccountDAO customerAccountDAO = new CustomerAccountDAO();
        CustomerUserDAO customerUserDAO = new CustomerUserDAO();

        boolean chooseAccount = true;

        do{
            System.out.println("\nINPUT ACCOUNT NUMBER OR N TO RETURN");
            input.setLength(0);
            input.append(sc.nextLine().trim());
            if(input.toString().contentEquals("N")){
                System.out.println("RETURNING");
                return;
            }
            try{
                int customerId = Integer.parseInt(input.toString());
                CustomerAccount customerAccount = customerAccountDAO.getCustomerAccountById(Integer.parseInt(input.toString()));
                List<User> users = customerUserDAO.getAllUsersByCustomerId(customerAccount.getCustomerAccountId());
                accountDisplay.displayCustomerAccount(customerAccount);

                boolean changeData = true;

                do{
                    System.out.println("\nCHOOSE OPTION");
                    System.out.println("1. Change Primary User Id");
                    System.out.println("2. Return\n");

                    try{
                        input.setLength(0);
                        input.append(sc.nextLine().trim());
                        switch(input.toString()){
                            case ("1"):
                                accountDisplay.displayUsers(users, customerAccount);
                                changeAccountPrimaryUser(users, customerAccount);
                                break;
                            case("2"):
                                changeData = false;
                                System.out.println("RETURNING");
                                break;
                            default:
                                System.out.println("\nINVALID INPUT\n");
                                break;
                        }

                    }catch (NumberFormatException e){
                        log.debug(e.toString());
                        System.out.println("\nTYPE A VALID NUMBER\n");
                    }
                }while(changeData);
                chooseAccount = false;
            }catch (NumberFormatException e){
                log.debug(e.toString());
                System.out.println("\nTYPE A VALID NUMBER\n");
            }
        }while(chooseAccount);
    }

    private void changeAccountPrimaryUser(List<User> users, CustomerAccount customerAccount) {
        boolean changeAccount = true;
        CustomerAccountDAO customerAccountDAO = new CustomerAccountDAO();

        do{
            input.setLength(0);
            input.append(sc.nextLine().trim());
            System.out.println("INPUT NEW PRIMARY USER ID OR N TO LEAVE");
            try{
                if(input.toString().contentEquals("N")) return;
                int newPrimaryUserId = Integer.parseInt(input.toString());
                if(newPrimaryUserId == customerAccount.getPrimaryUserId()) {
                    System.out.println("REPEATED PRIMARY USER");
                    break;
                }
                customerAccountDAO.updateCustomerAccountPrimaryId(customerAccount.getCustomerAccountId(), newPrimaryUserId);
                changeAccount = false;
            } catch (NumberFormatException e){
                log.debug(e.toString());
                System.out.println("\nINVALID INPUT");
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
            System.out.println("\nINPUT USER NUMBER");
            input.setLength(0);
            input.append(sc.nextLine().trim());

            try{
                int userId = Integer.parseInt(input.toString());
                User user = customerUserDAO.getUserById(userId);

                boolean changeData = true;

                do{
                    System.out.println("\nCHOOSE OPTION");
                    System.out.println("1. Change User Name");
                    System.out.println("2. Change User Balance");
                    System.out.println("3. Delete User");
                    System.out.println("4. Return\n");

                    try{
                        input.setLength(0);
                        input.append(sc.nextLine().trim());
                        switch(input.toString()){
                            case ("1"):
                                changeUserNames(user, customerAccountDAO.getCustomerAccountById(user.getCustomerAccountId()));
                                break;
                            case("2"):
                                inventoryHandler.manageBalance(customerAccountDAO.getCustomerAccountById(user.getCustomerAccountId()), user);
                                break;
                            case("3"):
                                removeUser(user, customerAccountDAO.getCustomerAccountById(user.getCustomerAccountId()));
                                break;
                            case("4"):
                                changeData = false;
                                System.out.println("RETURNING");
                                break;
                            default:
                                System.out.println("\nINVALID INPUT\n");
                                break;
                        }

                    }catch (NumberFormatException e){
                        log.debug(e.toString());
                        System.out.println("\nTYPE A VALID NUMBER\n");
                    }
                }while(changeData);
                chooseUser = false;
            }catch (NumberFormatException e){
                log.debug(e.toString());
                System.out.println("\nTYPE A VALID NUMBER\n");
            }
        }while(chooseUser);
    }
}
