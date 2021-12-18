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
        this.usernameInput = usernameInput;
    }

    public void changeUser(CustomerAccount customerAccount) {
        AccountDisplay accountDisplay = new AccountDisplay();
        boolean getCorrectUser = true;
        accountDisplay.customerDisplay(customerAccount);
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
                System.out.println("Ȉ̵̙͖͍͗Ǹ̶̹̺̎̓P̸̲͕̜̒͐̎Ú̷̢͍̫̀T̶̡̝͑ ̶̩̫̋̈͌I̷̡̳͔͊̐͝Ǹ̵͉͜V̸̪̺͚̇̇͝A̶̢̰̻̐̉L̵̼͆͛̔İ̴̱D̸̖̏̾ͅ");
                e.printStackTrace();
            }
        }
        return null;
    }
}
