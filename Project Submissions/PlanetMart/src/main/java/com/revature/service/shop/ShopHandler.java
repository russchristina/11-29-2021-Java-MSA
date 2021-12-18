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

import java.util.Map;
import java.util.Scanner;

public class ShopHandler {


    public ShopHandler() {

    }

    public void beginShopping(CustomerAccount customerAccount, User user) {
        Shop shop = new Shop(DummyShopData.planetCatalogueMap);
        Scanner sc = new Scanner(System.in);
        ShopInputHandler shopInputHandler = new ShopInputHandler();
        boolean choosingOptions = true;
        PlanetShopDisplay planetShopDisplay = new PlanetShopDisplay();
        planetShopDisplay.displayShop(shop);

        while(choosingOptions){

            System.out.println("\n1. Buy a planet");
            System.out.println("2. Sell a planet");
            System.out.println("3. Return");

            String chosenOption = sc.nextLine().trim();
            switch(chosenOption){
                case("1"):
                    System.out.println("\nOption 1: Buy a Planet");
                    shopInputHandler.buyAPlanet(customerAccount, user, sc, shop);
                    choosingOptions = false;
                    break;
                case("2"):
                    System.out.println("\nOption 2: Sell a Planet");
                    shopInputHandler.sellAPlanet(customerAccount, user, sc, shop);
                    choosingOptions = false;
                    break;
                case("3"):
                    System.out.println("\nOption 3: Return");
                    AccountInputHandler accountInputHandler = new AccountInputHandler();
                    accountInputHandler.inputChooseCustomerOptions(customerAccount, user);
                    choosingOptions = false;
                    break;
                default:
                    System.out.println("\nȈ̵̙͖͍͗Ǹ̶̹̺̎̓P̸̲͕̜̒͐̎Ú̷̢͍̫̀T̶̡̝͑ ̶̩̫̋̈͌I̷̡̳͔͊̐͝Ǹ̵͉͜V̸̪̺͚̇̇͝A̶̢̰̻̐̉L̵̼͆͛̔İ̴̱D̸̖̏̾ͅ\n");
                    break;
            }

        }
    }
}
