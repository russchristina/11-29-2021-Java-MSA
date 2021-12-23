package com.revature.service.shop;

import com.revature.display.shop.PlanetShopDisplay;
import com.revature.display.user.InventoryDisplay;
import com.revature.models.accounts.CustomerAccount;
import com.revature.models.shop.Inventory;
import com.revature.models.shop.Planet;
import com.revature.models.shop.generator.PlanetGenerator;
import com.revature.models.users.User;
import com.revature.service.account.AccountInputHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ShopHandler {

    private final Logger log = LoggerFactory.getLogger(ShopHandler.class);
    protected final AccountInputHandler accountInputHandler = new AccountInputHandler();
    protected final StringBuilder input = new StringBuilder();
    protected final Scanner sc = new Scanner(System.in);
    protected final InventoryHandler inventoryHandler = new InventoryHandler();
    protected final InventoryDisplay inventoryDisplay = new InventoryDisplay();
    protected final PlanetGenerator planetGenerator = new PlanetGenerator();

    protected final PlanetShopDisplay planetShopDisplay = new PlanetShopDisplay();

    public void beginShopping(CustomerAccount customerAccount, User user, Shop shop) {
        boolean choosingOptions = true;

        do {
            planetShopDisplay.displayShop(shop);
            System.out.println("\n1. Buy a planet");
            System.out.println("2. Sell a planet");
            System.out.println("3. Get new Options");
            System.out.println("4. Return");
            input.setLength(0);
            input.append(sc.nextLine().trim());
            switch (input.toString()) {
                case ("1"):
                    System.out.println("\nOption 1: Buy a Planet");
                    buyAPlanet(customerAccount, user, sc, shop);
                    choosingOptions = false;
                    break;
                case ("2"):
                    System.out.println("\nOption 2: Sell a Planet");
                    sellAPlanet(customerAccount, user, sc, shop);
                    choosingOptions = false;
                    break;
                case ("3"):
                    System.out.println("\nOption 3: Get new Options");
                    refreshShop(shop);
                    break;
                case ("4"):
                    System.out.println("\nOption 3: Return");
                    accountInputHandler.inputChooseCustomerOptions(customerAccount, user);
                    choosingOptions = false;
                    break;
                default:
                    System.out.println("\nInput invalid, please try again.\n");
                    break;
            }
        } while (choosingOptions);
    }

    public void buyAPlanet(CustomerAccount customerAccount, User user, Scanner sc, Shop shop) {
        boolean buyingPlanet = true;
        do {
            input.setLength(0);
            System.out.println("Type a valid planet name or type n to leave");
            input.append(sc.nextLine());
            if (shop.getPlanetCatalogueMap().containsKey(input.toString())) {
                shop.buyPlanet(customerAccount, input.toString(), user);
                buyingPlanet = false;
                System.out.println("Successful purchase of Planet: " + input + "\n");
            }
            if (input.toString().trim().toLowerCase().contentEquals("n")) {
                buyingPlanet = false;
            }
        } while (buyingPlanet);
    }

    public void sellAPlanet(CustomerAccount customerAccount, User user, Scanner sc, Shop shop) {
        System.out.println("\nYour Inventory\n");
        Inventory inventory = inventoryHandler.generateUserInventory(customerAccount, user);
        inventoryDisplay.displayInventory(inventory);
        boolean sellingPlanet = true;
        do {
            System.out.println("\nType a valid planet name or type n to leave");
            input.setLength(0);
            input.append(sc.nextLine());
            for (Planet planet : inventory.getPlanetOwnedList()) {
                if (planet.getName().contentEquals(input.toString())) {
                    shop.sellPlanet(planet, user, customerAccount);
                    sellingPlanet = false;
                    System.out.println("Successful sale of Planet: " + input);
                    break;
                }
            }
            if (input.toString().trim().contentEquals("n")) {
                break;
            }
        } while (sellingPlanet);
    }

    public void refreshShop(Shop shop){
        Map<String, Planet> planetCatalogueMap = new HashMap();
        for(int i = 0; i < 5; i++){
            Planet planet = planetGenerator.generateRandomPlanet();
            planetCatalogueMap.put(planet.getName(), planet);
        }
        shop.setPlanetCatalogueMap(planetCatalogueMap);
    }


}

