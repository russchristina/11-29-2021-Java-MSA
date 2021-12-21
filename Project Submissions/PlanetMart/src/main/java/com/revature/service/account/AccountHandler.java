package com.revature.service.account;

import com.revature.database.AccountDao;
import com.revature.display.account.AccountDisplay;
import com.revature.models.accounts.Account;
import com.revature.models.accounts.CustomerAccount;
import com.revature.models.exceptions.UserNotFoundException;
import com.revature.models.shop.Inventory;
import com.revature.models.users.User;
import com.revature.service.shop.InventoryHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AccountHandler {
    private final Logger log = LoggerFactory.getLogger(AccountHandler.class);

    private final AccountDao aDao = new AccountDao();
    private final AccountDisplay accountDisplay = new AccountDisplay();
    private final AccountInputHandler accountInputHandler = new AccountInputHandler();
    private final InventoryHandler inventoryHandler = new InventoryHandler();

    public AccountHandler(){};

    public void changeUser(CustomerAccount customerAccount) {
        User user = chooseUser(customerAccount);
        Inventory inventory = inventoryHandler.generateUserInventory(customerAccount, user);
        accountDisplay.customerDisplay(customerAccount);
        accountDisplay.displayCurrentUser(customerAccount, user, inventory);
        accountInputHandler.inputChooseCustomerOptions(customerAccount, user);
    }

    public void initiateAccount(String username) {
        Account account = aDao.getAccount(username);
        CustomerAccount customerAccount = account instanceof CustomerAccount ? ((CustomerAccount) account) : null;
        if(customerAccount != null) {
            accountDisplay.customerDisplay(customerAccount);
            User user = chooseUser(customerAccount);
            Inventory inventory = inventoryHandler.generateUserInventory(customerAccount, user);
            accountDisplay.displayCurrentUser(customerAccount, user, inventory);
            accountInputHandler.inputChooseCustomerOptions(customerAccount, user);
        }
    }

    private User chooseUser(CustomerAccount customerAccount) {
        do {
            accountDisplay.chooseUserDisplay(customerAccount);
            try {
                return accountInputHandler.inputChooseUser(customerAccount);
            } catch (UserNotFoundException e) {
                System.out.println("\nUser not found.\n" +
                        "Please try again.\n");
                log.debug(e.toString());
            }
        } while (true);
    }
}
