package com.revature.service.shop;

import com.revature.display.user.InventoryDisplay;
import com.revature.models.accounts.CustomerAccount;
import com.revature.models.shop.Inventory;
import com.revature.models.shop.Planet;
import com.revature.models.shop.Shop;
import com.revature.models.users.User;
import com.revature.service.account.AccountInputHandler;

import java.util.Scanner;

public class ShopInputHandler {


    public void buyAPlanet(CustomerAccount customerAccount, User user, Scanner sc, Shop shop) {
        AccountInputHandler accountInputHandler = new AccountInputHandler();
        String userInput = "";
        boolean buyingPlanet = true;

        while(buyingPlanet){
            System.out.println("Type a valid planet name or type n to leave");
            userInput = sc.nextLine();
            if(shop.getPlanetCatalogueMap().containsKey(userInput)) {
                shop.buyPlanet(customerAccount, userInput, user);
                buyingPlanet = false;
                System.out.println("Successful purchase of Planet: " + userInput);
                accountInputHandler.inputChooseCustomerOptions(customerAccount, user);
                break;
            }if(userInput.trim().contentEquals("n")){
                buyingPlanet = false;
                accountInputHandler.inputChooseCustomerOptions(customerAccount, user);
                break;
            }

        }



    }

    public void sellAPlanet(CustomerAccount customerAccount, User user, Scanner sc, Shop shop) {
        InventoryHandler inventoryHandler = new InventoryHandler();
        InventoryDisplay inventoryDisplay = new InventoryDisplay();
        AccountInputHandler accountInputHandler = new AccountInputHandler();
        Inventory inventory = inventoryHandler.generateUserInventory(customerAccount, user);
        inventoryDisplay.displayInventory(inventory);

        String userInput = "";
        boolean sellingPlanet = true;

        while(sellingPlanet){
            if(sellingPlanet) System.out.println("\nType a valid planet name or type n to leave");

            userInput = sc.nextLine();

            for (Planet planet : inventory.getPlanetOwnedList()) {
                if(planet.getName().contentEquals(userInput)){
                    shop.sellPlanet(planet, user, customerAccount);
                    sellingPlanet = false;
                    System.out.println("Successful sale of Planet: " + userInput);
                    accountInputHandler.inputChooseCustomerOptions(customerAccount, user);
                    break;
                }

            }
            if(userInput.trim().contentEquals("n")) {
                sellingPlanet = false;
                accountInputHandler.inputChooseCustomerOptions(customerAccount, user);
                break;
            }

        }


    }
}
