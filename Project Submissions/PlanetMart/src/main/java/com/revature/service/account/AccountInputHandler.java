package com.revature.service.account;

import com.revature.database.DummyShopData;
import com.revature.display.account.AccountDisplay;
import com.revature.display.login.LoginDisplay;
import com.revature.display.user.InventoryDisplay;
import com.revature.models.accounts.CustomerAccount;
import com.revature.models.exceptions.UserNotFoundException;
import com.revature.models.shop.Inventory;
import com.revature.service.shop.Shop;
import com.revature.models.users.PrimaryUser;
import com.revature.models.users.User;
import com.revature.service.login.LoginInputHandler;
import com.revature.service.shop.InventoryHandler;
import com.revature.service.shop.ShopHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class AccountInputHandler {

    protected final Logger log = LoggerFactory.getLogger(AccountInputHandler.class);
    public final Scanner sc;
    public final StringBuilder input;
    public final InventoryDisplay inventoryDisplay;
    public final AccountDisplay accountDisplay;
    public final LoginDisplay loginDisplay;

    public AccountInputHandler() {
        sc = new Scanner(System.in);
        input = new StringBuilder();
        inventoryDisplay = new InventoryDisplay();
        accountDisplay = new AccountDisplay();
        loginDisplay = new LoginDisplay();
    }


    public User inputChooseUser(CustomerAccount customerAccount) throws UserNotFoundException {
        input.setLength(0);
        input.append(sc.nextLine());

        for (User user : customerAccount.getUsers()) {
            if(user.getName().contentEquals(input)) return user;
        }
        throw new UserNotFoundException();
    }

    public void inputChooseCustomerOptions(CustomerAccount customerAccount, User user) {
        boolean choosingOptions = true;
        InventoryHandler inventoryHandler = new InventoryHandler();
        ShopHandler shopHandler = new ShopHandler();
        AccountHandler accountHandler = new AccountHandler();
        LoginInputHandler loginInputHandler = new LoginInputHandler();
        Shop shop = new Shop(DummyShopData.planetCatalogueMap);
        if(user instanceof PrimaryUser){
            inputChooseCustomerOptionsPrimary(customerAccount, user);
            return;
        }

        do {
            accountDisplay.displayCustomerOptions(customerAccount, user);
            System.out.println("Choose an option:");
            input.setLength(0);
            input.append(sc.nextLine().trim());

            switch (input.toString()) {
                case ("1"):
                    System.out.println("Option 1: Open Inventory");

                    Inventory inventory = inventoryHandler.generateUserInventory(customerAccount, user);
                    inventoryDisplay.displayInventory(inventory);
                    inventoryHandler.chooseOptions(customerAccount, user, inventory, sc);
                    //inputChooseCustomerOptions(customerAccount, user);
                    //choosingOptions = false;
                    break;
                case ("2"):
                    System.out.println("Option 2: Open Shop");
                    shopHandler.beginShopping(customerAccount, user, shop);
                    //inputChooseCustomerOptions(customerAccount, user);
                    //choosingOptions = false;
                    break;
                case ("3"):
                    System.out.println("Option 3: Change User");
                    accountHandler.changeUser(customerAccount);
                    choosingOptions = false;
                    break;
                case ("4"):
                    System.out.println("Option 4: Add to Balance");
                    inventoryHandler.addToBalance(customerAccount, user);
                    choosingOptions = false;
                    break;
                case ("5"):
                    System.out.println("Option 5: Logout");
                    choosingOptions = false;
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
        ShopHandler shopHandler = new ShopHandler();
        AccountHandler accountHandler = new AccountHandler();
        LoginInputHandler loginInputHandler = new LoginInputHandler();
        Shop shop = new Shop(DummyShopData.planetCatalogueMap);
            do {
                accountDisplay.displayCustomerOptions(customerAccount, user);
                System.out.println("Choose an option:");
                input.setLength(0);
                input.append(sc.nextLine().trim());
                switch (input.toString()) {
                    case ("1"):
                        System.out.println("Option 1: Open Inventory");
                        Inventory inventory = inventoryHandler.generateUserInventory(customerAccount, user);
                        inventoryDisplay.displayInventory(inventory);
                        inventoryHandler.chooseOptions(customerAccount, user, inventory, sc);
//                        inputChooseCustomerOptions(customerAccount, user);
                        //choosingOptions = false;
                        break;
                    case ("2"):
                        System.out.println("Option 2: Open Shop");
                        shopHandler.beginShopping(customerAccount, user, shop);
                        //inputChooseCustomerOptions(customerAccount, user);
                        //choosingOptions = false;
                        break;
                    case ("3"):
                        System.out.println("Option 3: Change User");
                        accountHandler.changeUser(customerAccount);
                        choosingOptions = false;
                        break;
                    case ("4"):
                        System.out.println("Option 4: Add to Balance");
                        inventoryHandler.addToBalance(customerAccount, user);
//                    choosingOptions = false;
                        break;
                    case ("5"):
                        System.out.println("Option 5: Logout");
                        choosingOptions = false;
                        loginInputHandler.firstStage();
                        break;
                    case ("6"):
                        System.out.println("Option 6: Request Account Deletion");
                        choosingOptions = false;
                        break;
                    case ("7"):
                        System.out.println("Option 7: Transfer Funds");
//                    choosingOptions = false;
                        break;
                    case ("8"):
                        System.out.println("Option 8: Change user names");
//                    choosingOptions = false;
                        break;
                    case ("9"):
                        System.out.println("Option 9: Remove User");
//                    choosingOptions = false;
                        break;
                    default:
                        System.out.println("\nInput a valid choice.\n");
                        break;
                }
            } while (choosingOptions);
    }
}
