package com.revature.service.account;

import com.revature.display.account.AccountDisplay;
import com.revature.models.accounts.CustomerAccount;
import com.revature.models.users.User;
import com.revature.service.login.LoginInputHandler;
import com.revature.service.shop.InventoryHandler;
import com.revature.service.shop.ShopHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class AccountInputHandler {

    protected final Logger log = LoggerFactory.getLogger(AccountInputHandler.class);
    public final Scanner sc = new Scanner(System.in);
    public final StringBuilder input = new StringBuilder();

    public void inputChooseCustomerOptions(CustomerAccount customerAccount, User user) {
        boolean choosingOptions = true;
        InventoryHandler inventoryHandler = new InventoryHandler();
        AccountHandler accountHandler = new AccountHandler();
        AccountDisplay accountDisplay = new AccountDisplay();
        if(user.getUserId() == customerAccount.getPrimaryUserId()){
            inputChooseCustomerOptionsPrimary(customerAccount, user);
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
                    inventoryHandler.openInventory(customerAccount, user);
                    break;
                case ("2"):
                    System.out.println("Option 2: Open Shop");
                    ShopHandler shopHandler = new ShopHandler();
                    shopHandler.beginShopping(customerAccount, user);
                    break;
                case ("3"):
                    System.out.println("Option 3: Change User");
                    choosingOptions = false;
                    accountHandler.changeUser(customerAccount);
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

    private void inputChooseCustomerOptionsPrimary(CustomerAccount customerAccount, User user) {
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
                        inventoryHandler.openInventory(customerAccount, user);
                        break;
                    case ("2"):
                        System.out.println("Option 2: Open Shop");
                        ShopHandler shopHandler = new ShopHandler();
                        shopHandler.beginShopping(customerAccount, user);
                        break;
                    case ("3"):
                        System.out.println("Option 3: Change User");
                        choosingOptions = false;
                        accountHandler.changeUser(customerAccount);
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
                        accountHandler.addUser(user, customerAccount);
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
                        System.out.println("Option 9: Add Account");
                        accountHandler.addAccount(user, customerAccount);
                        break;
                    default:
                        System.out.println("\nInput a valid choice.\n");
                        break;
                }
            } while (choosingOptions);
    }
}
