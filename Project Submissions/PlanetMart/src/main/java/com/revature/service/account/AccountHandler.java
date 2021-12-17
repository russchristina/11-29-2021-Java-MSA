package com.revature.service.account;

import com.revature.display.account.AccountDisplay;
import com.revature.models.accounts.Account;
import com.revature.database.AccountDao;
import com.revature.models.accounts.CustomerAccount;
import com.revature.models.users.User;
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
            boolean getCorrectUser = true;
            User user = chooseUser(customerAccount);
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
        AccountDisplay accountDisplay = new AccountDisplay();
            accountDisplay.displayCustomerOptions(customerAccount, user);
            accountInputHandler.inputChooseCustomerOptions(customerAccount, user);

    }

    private User chooseUser(CustomerAccount customerAccount) {
        AccountInputHandler accountInputHandler = new AccountInputHandler();
        AccountDisplay accountDisplay = new AccountDisplay();
        try {
            User userChosen = accountInputHandler.inputChooseUser(customerAccount);
            accountDisplay.displayCurrentUser(customerAccount, userChosen);
            return userChosen;
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            accountDisplay.customerDisplay(customerAccount);
            chooseUser(customerAccount);
        }
        return null;
    }
}
