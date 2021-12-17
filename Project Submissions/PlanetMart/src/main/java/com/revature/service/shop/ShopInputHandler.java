package com.revature.service.shop;

import com.revature.models.accounts.CustomerAccount;
import com.revature.models.shop.Inventory;
import com.revature.models.shop.Planet;
import com.revature.models.shop.Shop;
import com.revature.models.users.User;
import com.revature.service.account.AccountInputHandler;

import java.util.Scanner;

public class ShopInputHandler {


    public void buyAPlanet(CustomerAccount customerAccount, User user, Scanner sc, Shop shop) {

        String userInput = "";
        boolean buyingPlanet = true;

        while(buyingPlanet){
            System.out.println("Type a valid planet name or type n to leave");
            userInput = sc.nextLine();
            if(shop.getPlanetCatalogueMap().containsKey(userInput)) {
                shop.buyPlanet(customerAccount, userInput, user);
                buyingPlanet = false;
                System.out.println("Successful purchase of Planet: " + userInput);
            }if(userInput.trim().contentEquals("n")) buyingPlanet = false;

        }
        AccountInputHandler accountInputHandler = new AccountInputHandler();
        accountInputHandler.inputChooseCustomerOptions(customerAccount, user);

    }
}
