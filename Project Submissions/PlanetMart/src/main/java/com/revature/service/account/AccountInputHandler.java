package com.revature.service.account;

import com.revature.database.exceptions.DuplicateUsernameException;
import com.revature.display.account.AccountDisplay;
import com.revature.models.accounts.CustomerAccount;
import com.revature.models.accounts.EmployeeAccount;
import com.revature.models.users.User;
import com.revature.models.users.UserCredential;
import com.revature.repository.CustomerAccountDAO;
import com.revature.repository.CustomerUserDAO;
import com.revature.service.exceptions.EmptyInputException;
import com.revature.service.login.LoginInputHandler;
import com.revature.service.shop.InventoryHandler;
import com.revature.service.shop.ShopHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;
import java.util.Scanner;

public class AccountInputHandler {

    protected final Logger log = LoggerFactory.getLogger(AccountInputHandler.class);
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
                    inventoryHandler.openInventory(user);
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
                    System.out.println("Option 4: Add to Balance");
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

    private void inputChooseCustomerOptionsPrimary(CustomerAccount customerAccount, User user, UserCredential username) {
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
                        inventoryHandler.openInventory(user);
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
                        System.out.println("Option 4: Add to Balance");
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
                            accountHandler.addUser(user, customerAccount);
                        } catch (EmptyInputException e) {
                            log.debug(e.toString());
                        } catch (DuplicateUsernameException e) {
                            log.debug(e.toString());
                        }
                        break;
                    case ("7"):
                        System.out.println("Option 7: Transfer Funds");
                        inventoryHandler.transferFunds(customerAccount, user);
                        break;
                    case ("8"):
                        System.out.println("Option 8: Change user names");
                        accountHandler.changeUserNames(user, customerAccount);
                        break;
                    case ("9"):
                        System.out.println("Option 9: Remove User");
                        accountHandler.removeUser(user, customerAccount);
                        break;
                    case ("10"):
                        System.out.println("Option 10: Add Account");
                        accountHandler.addAccount(user, customerAccount, username);
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
                    accountDisplay.displayUsers(customerUserDAO.getAllCustomerUsers());
                    break;
                case ("4"):
                    System.out.println("Option 4: View User Information");
                    viewUserInformation(username);
                    break;
                case ("5"):
                    System.out.println("Option 5: Delete Account");

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

    private void viewUserInformation(UserCredential username) {

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
                    inventoryHandler.openInventory(user);
                    chooseUser = false;
                }catch (NumberFormatException e){
                    log.debug(e.toString());
                    System.out.println("\nTYPE A VALID NUMBER\n");
                }

            }while(chooseUser);
    }

    private void viewCustomerAccountInformation() {

        CustomerUserDAO customerUserDAO = new CustomerUserDAO();
        AccountDisplay accountDisplay = new AccountDisplay();

        boolean chooseAccount = true;

        do{
            System.out.println("\nINPUT ACCOUNT NUMBER");
            input.setLength(0);
            input.append(sc.nextLine().trim());

            try{
                int customerId = Integer.parseInt(input.toString());
                List<User> users = customerUserDAO.getAllUsersByCustomerId(customerId);
                accountDisplay.displayUsers(users);
                chooseAccount = false;
            }catch (NumberFormatException e){
                log.debug(e.toString());
                System.out.println("\nTYPE A VALID NUMBER\n");
            }
        }while(chooseAccount);

    }

    public void inputChooseAdminOption(EmployeeAccount employeeAccount, UserCredential username) {
        AccountDisplay accountDisplay = new AccountDisplay();
        accountDisplay.displayAdminAccount(employeeAccount);
    }
}
