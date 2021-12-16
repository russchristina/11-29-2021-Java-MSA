package com.revature.service;

import com.revature.database.AccountDao;
import com.revature.display.AccountDisplay;
import com.revature.models.Account;
import com.revature.models.CustomerAccount;
import com.revature.models.User;
import com.revature.models.exceptions.UserNotFoundException;

public class AccountHandler {

    private String usernameInput;

    public AccountHandler(String usernameInput) {

        System.out.println("success!");
        this.usernameInput = usernameInput;
        //initiateAccount();
    }

    public void initiateAccount() {
        AccountDao aDao = new AccountDao();
        AccountDisplay accountDisplay = new AccountDisplay();
        Account account = aDao.getAccount(usernameInput);

        CustomerAccount customerAccount = account instanceof CustomerAccount ? ((CustomerAccount) account) : null;
        if(customerAccount != null) {
            accountDisplay.customerDisplay(customerAccount);
            ChooseUser(customerAccount, accountDisplay);
        }

    }

    private void ChooseUser(CustomerAccount customerAccount, AccountDisplay accountDisplay) {
        AccountInputHandler accountInputHandler = new AccountInputHandler();

        try {
            User userChosen = accountInputHandler.inputChooseUser(customerAccount);
            accountDisplay.displayCurrentUser(userChosen);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            accountDisplay.customerDisplay(customerAccount);
            ChooseUser(customerAccount, accountDisplay);
        }



    }
}
