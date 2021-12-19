package com.revature.service.account;

import com.revature.display.account.LifeDisplay;
import com.revature.display.user.InventoryDisplay;
import com.revature.models.accounts.CustomerAccount;
import com.revature.models.shop.Inventory;
import com.revature.models.shop.Planet;
import com.revature.models.users.User;
import com.revature.service.shop.InventoryHandler;

import java.util.Scanner;

public class LifeInputHandler {

    public void communicate(Scanner sc, User user, Inventory inventory) {
        String userInput = "";
        LifeDisplay lifeDisplay = new LifeDisplay();
        boolean communicating = true;

        while(communicating){
            System.out.println("\nType a valid planet name or type n to leave");
            userInput = sc.nextLine();

            for (Planet planet : inventory.getPlanetOwnedList()) {
                if(planet.getLifeForm() != null){
                    lifeDisplay.communicateWithLife(planet.getLifeForm(), user);
                    communicating = false;
                    break;
                }

            }if(userInput.trim().contentEquals("n")) {
                communicating = false;
                break;
            }
            if(communicating)System.out.println("\nLife not found...\n");

        }
    }

    public void terraform(Scanner sc, User user, Inventory inventory, CustomerAccount customerAccount) {
        System.out.println("\nBUILDING\n");
    }
}
