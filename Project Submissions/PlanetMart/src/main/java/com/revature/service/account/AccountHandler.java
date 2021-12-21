package com.revature.service.account;

import com.revature.display.account.AccountDisplay;
import com.revature.models.accounts.Account;
import com.revature.database.AccountDao;
import com.revature.models.accounts.CustomerAccount;
import com.revature.models.shop.Inventory;
import com.revature.models.users.PrimaryUser;
import com.revature.models.users.User;
import com.revature.models.exceptions.UserNotFoundException;
import com.revature.service.shop.InventoryHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AccountHandler {

    private final Logger log = LoggerFactory.getLogger(AccountHandler.class);

    private String usernameInput;

    public AccountHandler(String usernameInput) {
        this.usernameInput = usernameInput;
    }

    public void changeUser(CustomerAccount customerAccount) {
        AccountDisplay accountDisplay = new AccountDisplay();
        accountDisplay.customerDisplay(customerAccount);
        User user = chooseUser(customerAccount);
        InventoryHandler inventoryHandler = new InventoryHandler();
        Inventory inventory = inventoryHandler.generateUserInventory(customerAccount, user);
        accountDisplay.displayCurrentUser(customerAccount, user, inventory);

        customerUserOptions(customerAccount, user);
    }


    public void initiateAccount() {
        AccountDao aDao = new AccountDao();
        AccountDisplay accountDisplay = new AccountDisplay();
        Account account = aDao.getAccount(usernameInput);


        CustomerAccount customerAccount = account instanceof CustomerAccount ? ((CustomerAccount) account) : null;
        if(customerAccount != null) {
            accountDisplay.customerDisplay(customerAccount);

                User user = chooseUser(customerAccount);
                InventoryHandler inventoryHandler = new InventoryHandler();
                Inventory inventory = inventoryHandler.generateUserInventory(customerAccount, user);
                accountDisplay.displayCurrentUser(customerAccount, user, inventory);
                customerUserOptions(customerAccount, user);



        }

    }

    private void customerUserOptions(CustomerAccount customerAccount, User user) {
        AccountInputHandler accountInputHandler = new AccountInputHandler();
        accountInputHandler.inputChooseCustomerOptions(customerAccount, user);

    }

    private User chooseUser(CustomerAccount customerAccount) {
        AccountInputHandler accountInputHandler = new AccountInputHandler();
        AccountDisplay accountDisplay = new AccountDisplay();
        boolean getCorrectUser = true;
        while(getCorrectUser) {
            try {
                User userChosen = accountInputHandler.inputChooseUser(customerAccount);
                getCorrectUser = false;
                return userChosen;
            } catch (UserNotFoundException e) {
                System.out.println("User not found.\n" +
                        "Please try again.\n");
                log.debug(e.toString());
            }
        }
        return null;
    }
}
