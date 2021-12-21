package com.revature.service.account;

import com.revature.database.DummyShopData;
import com.revature.display.account.AccountDisplay;
import com.revature.display.shop.PlanetShopDisplay;
import com.revature.display.user.InventoryDisplay;
import com.revature.models.accounts.CustomerAccount;
import com.revature.models.shop.Inventory;
import com.revature.models.users.PrimaryUser;
import com.revature.models.users.User;
import com.revature.models.exceptions.UserNotFoundException;
import com.revature.service.login.LoginInputHandler;
import com.revature.service.shop.InventoryHandler;
import com.revature.service.shop.ShopHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class AccountInputHandler {

    protected final Logger log = LoggerFactory.getLogger(AccountInputHandler.class);

    protected final Scanner sc = new Scanner(System.in);
    protected final StringBuilder input = new StringBuilder();
    protected final InventoryHandler inventoryHandler = new InventoryHandler();
    protected final InventoryDisplay inventoryDisplay = new InventoryDisplay();
    protected final AccountHandler accountHandler = new AccountHandler();
    protected final AccountDisplay accountDisplay = new AccountDisplay();
    protected final ShopHandler shopHandler = new ShopHandler();
    protected final LoginInputHandler loginInputHandler = new LoginInputHandler();

    public User inputChooseUser(CustomerAccount customerAccount) throws UserNotFoundException {
        input.setLength(0);
        input.append(sc.nextLine());

        if(customerAccount.getPrimaryUser().getName().contentEquals(input)) return customerAccount.getPrimaryUser();
        else if(customerAccount.getSecondaryUsers().get(input.toString()) != null) return customerAccount.getSecondaryUsers().get(input.toString());
        throw new UserNotFoundException();
    }

    public void inputChooseCustomerOptions(CustomerAccount customerAccount, User user) {
        boolean choosingOptions = true;
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
                    shopHandler.beginShopping(customerAccount, user);
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
            do {
                accountDisplay.displayCustomerOptions(customerAccount, user);
                System.out.println("Choose an option:");
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
                        shopHandler.beginShopping(customerAccount, user);
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
