package com.revature.service.shop;

import com.revature.display.user.InventoryDisplay;
import com.revature.models.accounts.CustomerAccount;
import com.revature.models.shop.Inventory;
import com.revature.models.shop.Planet;
import com.revature.models.shop.Shop;
import com.revature.models.users.User;
import com.revature.service.account.AccountInputHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class ShopInputHandler {

    private final Logger log = LoggerFactory.getLogger(ShopInputHandler.class);
    protected final AccountInputHandler accountInputHandler = new AccountInputHandler();
    protected final StringBuilder input = new StringBuilder();
    protected final InventoryHandler inventoryHandler = new InventoryHandler();
    protected final InventoryDisplay inventoryDisplay = new InventoryDisplay();

    public void buyAPlanet(CustomerAccount customerAccount, User user, Scanner sc, Shop shop) {
        boolean buyingPlanet = true;
        do {
            System.out.println("Type a valid planet name or type n to leave");
            input.append(sc.nextLine());
            if (shop.getPlanetCatalogueMap().containsKey(input)) {
                shop.buyPlanet(customerAccount, input.toString(), user);
                buyingPlanet = false;
                System.out.println("Successful purchase of Planet: " + input + "\n");
                accountInputHandler.inputChooseCustomerOptions(customerAccount, user);
            }
            if (input.toString().trim().contentEquals("n")) {
                buyingPlanet = false;
                accountInputHandler.inputChooseCustomerOptions(customerAccount, user);
            }
        } while (buyingPlanet);
    }

    public void sellAPlanet(CustomerAccount customerAccount, User user, Scanner sc, Shop shop) {
        Inventory inventory = inventoryHandler.generateUserInventory(customerAccount, user);
        inventoryDisplay.displayInventory(inventory);
        boolean sellingPlanet = true;
        do {
            input.setLength(0);
            input.append(sc.nextLine());
            for (Planet planet : inventory.getPlanetOwnedList()) {
                if (planet.getName().contentEquals(input)) {
                    shop.sellPlanet(planet, user, customerAccount);
                    sellingPlanet = false;
                    System.out.println("Successful sale of Planet: " + input);
                    accountInputHandler.inputChooseCustomerOptions(customerAccount, user);
                    break;
                }
            }
            if (input.toString().trim().contentEquals("n")) {
                sellingPlanet = false;
                accountInputHandler.inputChooseCustomerOptions(customerAccount, user);
                break;
            }else{
                System.out.println("\nType a valid planet name or type n to leave");
            }
        } while (sellingPlanet);
    }


}

