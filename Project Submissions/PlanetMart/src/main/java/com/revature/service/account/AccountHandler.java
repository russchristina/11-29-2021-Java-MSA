package com.revature.service.account;

import com.revature.display.account.AccountDisplay;
import com.revature.models.accounts.Account;
import com.revature.database.AccountDao;
import com.revature.models.accounts.CustomerAccount;
import com.revature.models.shop.Inventory;
import com.revature.models.users.User;
import com.revature.models.exceptions.UserNotFoundException;
import com.revature.service.shop.InventoryHandler;

public class AccountHandler {

    private String usernameInput;

    public AccountHandler(String usernameInput) {

        System.out.println("success!");
        this.usernameInput = usernameInput;
        //initiateAccount();
    }

    public void changeUser(CustomerAccount customerAccount) {
        AccountDisplay accountDisplay = new AccountDisplay();
        boolean getCorrectUser = true;

        User user = chooseUser(customerAccount);
        InventoryHandler inventoryHandler = new InventoryHandler();
        Inventory inventory = inventoryHandler.generateUserInventory(customerAccount, user);
        accountDisplay.displayCurrentUser(customerAccount, user, inventory);

        while(getCorrectUser){
            if(user == null) {
                user = chooseUser(customerAccount);
            }else if(user != null) getCorrectUser = false;

        }

        customerUserOptions(customerAccount, user);
    }


    public void initiateAccount() {
        AccountDao aDao = new AccountDao();
        AccountDisplay accountDisplay = new AccountDisplay();
        Account account = aDao.getAccount(usernameInput);


        CustomerAccount customerAccount = account instanceof CustomerAccount ? ((CustomerAccount) account) : null;
        if(customerAccount != null) {
            accountDisplay.customerDisplay(customerAccount);
            boolean getCorrectUser = true;

            User user = chooseUser(customerAccount);
            InventoryHandler inventoryHandler = new InventoryHandler();
            Inventory inventory = inventoryHandler.generateUserInventory(customerAccount, user);
            accountDisplay.displayCurrentUser(customerAccount, user, inventory);

            while(getCorrectUser){
                if(user == null) {
                    user = chooseUser(customerAccount);
                }else if(user != null) getCorrectUser = false;

            }

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
        try {
            User userChosen = accountInputHandler.inputChooseUser(customerAccount);

            return userChosen;
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            accountDisplay.customerDisplay(customerAccount);
            chooseUser(customerAccount);
        }
        return null;
    }
}
