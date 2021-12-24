package com.revature.display.account;

import com.revature.models.accounts.CustomerAccount;
import com.revature.models.users.User;

import java.util.List;

public class AccountDisplay {

    public void displayCustomerBasicOptions(CustomerAccount customerAccount, User user) {
        System.out.println("\nSECONDARY USER OPTIONS");
        System.out.println("1. Open Inventory");
        System.out.println("2. Open Shop");
        System.out.println("3. Change User");
        System.out.println("4. Add to Balance");
        System.out.println("5. Logout\n");
    }

    public void displayCustomerUpgradedOptions(CustomerAccount customerAccount, User user) {
        System.out.println("\nPRIMARY USER OPTIONS");
        System.out.println("1. Open Inventory");
        System.out.println("2. Open Shop");
        System.out.println("3. Change User");
        System.out.println("4. Add to Balance");
        System.out.println("5. Logout");
        System.out.println("6. Add User");
        System.out.println("7. Transfer Funds");
        System.out.println("8. Change User names");
        System.out.println("9. Remove User");
        System.out.println("10. Add Account");
    }

    public void displayCustomerAccount(CustomerAccount customerAccount) {
        System.out.println("\nAccount ID: " + customerAccount.getCustomerAccountId());
    }

    public void displayUsers(List<User> users) {
        for (User user : users) {

            System.out.println("\nUSER ID: " + user.getUserId());
            System.out.println("USER NAME: " + user.getName());

        }

    }
}
