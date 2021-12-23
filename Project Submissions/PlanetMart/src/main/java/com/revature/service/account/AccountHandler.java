package com.revature.service.account;

import com.revature.models.accounts.CustomerAccount;
import com.revature.models.exceptions.UserNotFoundException;
import com.revature.models.shop.Inventory;
import com.revature.models.users.User;
import com.revature.models.users.UserCredential;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.auth.login.AccountNotFoundException;

public class AccountHandler {
    private final Logger log = LoggerFactory.getLogger(AccountHandler.class);



    public AccountHandler(){

    }

    public void changeUser(CustomerAccount customerAccount) {

    }

    public void initiateAccount(UserCredential username) {
        Account account = null;
        try {
            account = aDao.getAccount(username);
        } catch (AccountNotFoundException e) {
            log.error(e.toString());
            System.out.println("\nAccount not found, try again.\n");
        }
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
