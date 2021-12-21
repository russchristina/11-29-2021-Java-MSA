package com.revature.service.shop;

import com.revature.database.DummyShopData;
import com.revature.display.shop.PlanetShopDisplay;
import com.revature.display.user.InventoryDisplay;
import com.revature.models.accounts.CustomerAccount;
import com.revature.models.shop.Inventory;
import com.revature.models.shop.Planet;
import com.revature.models.shop.Shop;
import com.revature.models.users.User;
import com.revature.service.account.AccountHandler;
import com.revature.service.account.AccountInputHandler;
import com.revature.service.login.LoginInputHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Scanner;

public class ShopHandler {

    private final Logger log = LoggerFactory.getLogger(ShopHandler.class);
    protected final Scanner sc = new Scanner(System.in);
    protected final ShopInputHandler shopInputHandler = new ShopInputHandler();
    protected final PlanetShopDisplay planetShopDisplay = new PlanetShopDisplay();
    protected final AccountInputHandler accountInputHandler = new AccountInputHandler();
    protected final StringBuilder input = new StringBuilder();

    public ShopHandler() {}

    public void beginShopping(CustomerAccount customerAccount, User user) {
        Shop shop = new Shop(DummyShopData.planetCatalogueMap);
        boolean choosingOptions = true;
        planetShopDisplay.displayShop(shop);
        do {
            System.out.println("\n1. Buy a planet");
            System.out.println("2. Sell a planet");
            System.out.println("3. Return");
            input.setLength(0);
            input.append(sc.nextLine().trim());
            switch (input.toString()) {
                case ("1"):
                    System.out.println("\nOption 1: Buy a Planet");
                    shopInputHandler.buyAPlanet(customerAccount, user, sc, shop);
                    choosingOptions = false;
                    break;
                case ("2"):
                    System.out.println("\nOption 2: Sell a Planet");
                    shopInputHandler.sellAPlanet(customerAccount, user, sc, shop);
                    choosingOptions = false;
                    break;
                case ("3"):
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
}

