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

    private final Logger log = LoggerFactory.getLogger(AccountInputHandler.class);

    public User inputChooseUser(CustomerAccount customerAccount) throws UserNotFoundException {
        Scanner sc = new Scanner(System.in);
        boolean choosingUser = true;
        String userInput = "";

        userInput = sc.nextLine();
        if(customerAccount.getPrimaryUser().getName().contentEquals(userInput)) return customerAccount.getPrimaryUser();
        else if(customerAccount.getSecondaryUsers().get(userInput) != null) return customerAccount.getSecondaryUsers().get(userInput);

        throw new UserNotFoundException();

    }

    public void inputChooseCustomerOptions(CustomerAccount customerAccount, User user) {
        InventoryHandler inventoryHandler = new InventoryHandler();
        Scanner sc = new Scanner(System.in);
        boolean choosingOptions = true;
        AccountDisplay accountDisplay = new AccountDisplay();

        if(user instanceof PrimaryUser){
            inputChooseCustomerOptionsPrimary(customerAccount, user);
            choosingOptions = false;
        }

        while(choosingOptions){

            accountDisplay.displayCustomerOptions(customerAccount, user);
            System.out.println("Choose an option:");
            String chosenOption = sc.nextLine().trim();

            switch(chosenOption){
                    case("1"):
                        System.out.println("Option 1: Open Inventory");
                        InventoryDisplay inventoryDisplay = new InventoryDisplay();
                        Inventory inventory = inventoryHandler.generateUserInventory(customerAccount, user);
                        inventoryDisplay.displayInventory(inventory);
                        inventoryHandler.chooseOptions(customerAccount, user, inventory, sc);
//                        inputChooseCustomerOptions(customerAccount, user);
                        //choosingOptions = false;
                        break;
                    case("2"):
                        System.out.println("Option 2: Open Shop");
                        ShopHandler shopHandler = new ShopHandler();
                        shopHandler.beginShopping(customerAccount, user);
                        //inputChooseCustomerOptions(customerAccount, user);
                        //choosingOptions = false;
                        break;
                    case("3"):
                        System.out.println("Option 3: Change User");
                        AccountHandler accountHandler = new AccountHandler(customerAccount.getUsername());
                        accountHandler.changeUser(customerAccount);
                        choosingOptions = false;
                        break;
                    case("4"):
                        System.out.println("Option 4: Add to Balance");
                        inventoryHandler.addToBalance(customerAccount, user);
                        choosingOptions = false;
                        break;
                    case("5"):
                        System.out.println("Option 5: Logout");
                        LoginInputHandler loginInputHandler = new LoginInputHandler();
                        choosingOptions = false;
                        loginInputHandler.firstStage();
                        break;
                    default:
                        System.out.println("a valid option plz");
                        break;
                }
            }

        }

    private void inputChooseCustomerOptionsPrimary(CustomerAccount customerAccount, User user) {

        InventoryHandler inventoryHandler = new InventoryHandler();
        Scanner sc = new Scanner(System.in);
        boolean choosingOptions = true;
        AccountDisplay accountDisplay = new AccountDisplay();


        while(choosingOptions){

            accountDisplay.displayCustomerOptions(customerAccount, user);
            System.out.println("Choose an option:");
            String chosenOption = sc.nextLine().trim();

            switch(chosenOption){
                case("1"):
                    System.out.println("Option 1: Open Inventory");
                    InventoryDisplay inventoryDisplay = new InventoryDisplay();
                    Inventory inventory = inventoryHandler.generateUserInventory(customerAccount, user);
                    inventoryDisplay.displayInventory(inventory);
                    inventoryHandler.chooseOptions(customerAccount, user, inventory, sc);
//                        inputChooseCustomerOptions(customerAccount, user);
                    //choosingOptions = false;
                    break;
                case("2"):
                    System.out.println("Option 2: Open Shop");
                    ShopHandler shopHandler = new ShopHandler();
                    shopHandler.beginShopping(customerAccount, user);
                    //inputChooseCustomerOptions(customerAccount, user);
                    //choosingOptions = false;
                    break;
                case("3"):
                    System.out.println("Option 3: Change User");
                    AccountHandler accountHandler = new AccountHandler(customerAccount.getUsername());
                    accountHandler.changeUser(customerAccount);
                    choosingOptions = false;
                    break;
                case("4"):
                    System.out.println("Option 4: Add to Balance");
                    inventoryHandler.addToBalance(customerAccount, user);
//                    choosingOptions = false;
                    break;
                case("5"):
                    System.out.println("Option 5: Logout");
                    LoginInputHandler loginInputHandler = new LoginInputHandler();
                    choosingOptions = false;
                    loginInputHandler.firstStage();
                    break;
                case("6"):
                    System.out.println("Option 6: Request Account Deletion");
                    choosingOptions = false;
                    break;
                case("7"):
                    System.out.println("Option 7: Transfer Funds");
//                    choosingOptions = false;
                    break;
                case("8"):
                    System.out.println("Option 8: Change user names");
//                    choosingOptions = false;
                    break;
                case("9"):
                    System.out.println("Option 9: Remove User");
//                    choosingOptions = false;
                    break;
                default:
                    System.out.println("a valid option plz");
                    break;
            }
        }


    }

}
