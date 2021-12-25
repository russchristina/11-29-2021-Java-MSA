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
        accountDisplay.displayUsers(users);

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
        accountDisplay.displayUsers(users);

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
        accountDisplay.displayUsers(users);

        if(!users.isEmpty()){

            do{
                System.out.println("\nCHOOSE USER NUMBER");
                input.setLength(0);
                input.append(sc.nextLine().trim());
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
        System.out.println(users);

        if(!users.isEmpty()){
            do{
                System.out.println("\nCHOOSE USER NUMBER");
                input.setLength(0);
                input.append(sc.nextLine().trim());
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
                System.out.println("\nINPUT NEW USER NAME...");
                input.setLength(0);
                input.append(sc.nextLine().trim());
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
}
