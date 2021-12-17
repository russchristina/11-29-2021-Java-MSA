package com.revature.service.account;

import com.revature.display.shop.PlanetShopDisplay;
import com.revature.display.user.InventoryDisplay;
import com.revature.models.accounts.CustomerAccount;
import com.revature.models.users.User;
import com.revature.models.exceptions.UserNotFoundException;
import com.revature.service.login.LoginInputHandler;

import java.util.Scanner;

public class AccountInputHandler {

    public User inputChooseUser(CustomerAccount customerAccount) throws UserNotFoundException {
        Scanner sc = new Scanner(System.in);


        String userInput = sc.nextLine();
        if(customerAccount.getPrimaryUser().getName().contentEquals(userInput)) return customerAccount.getPrimaryUser();
        else if(customerAccount.getSecondaryUsers().get(userInput) != null) return customerAccount.getSecondaryUsers().get(userInput);

        throw new UserNotFoundException();


    }

    public void inputChooseCustomerOptions(CustomerAccount customerAccount, User user) {
        Scanner sc = new Scanner(System.in);
        boolean choosingOptions = true;
        System.out.println("Choose an option:");
        while(choosingOptions){

            int chosenOption = 0;
            try{
                chosenOption = sc.nextInt();
            }catch (Exception e){
                e.printStackTrace();
                sc.nextLine();
            }
                switch(chosenOption){
                    case(1):
                        System.out.println("Option 1: Open Inventory");
                        //InventoryDisplay inventoryDisplay = new InventoryDisplay(customerAccount, user);
                        //inventoryDisplay.displayInventory();
                        choosingOptions = false;
                        break;
                    case(2):
                        System.out.println("Option 2: Open Shop");
                        //PlanetShopDisplay planetShopDisplay = new PlanetShopDisplay(customerAccount, user);
                        //planetShopDisplay.displayPlanetShop();
                        choosingOptions = false;
                        break;
                    case(3):
                        System.out.println("Option 3: Change User");
                        //AccountHandler.changeUser(customerAccount, user);
                        choosingOptions = false;
                        break;
                    case(4):
                        System.out.println("Option 4: Add to Balance");
                        //addToBalance(customerAccount, user);
                        choosingOptions = false;
                        break;
                    case(5):
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

}
