package com.revature.service.account;

import com.revature.display.account.LifeDisplay;
import com.revature.models.accounts.CustomerAccount;
import com.revature.models.shop.Inventory;
import com.revature.models.shop.Planet;
import com.revature.models.users.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class LifeInputHandler {

    protected final StringBuilder input = new StringBuilder();
    protected final LifeDisplay lifeDisplay = new LifeDisplay();

    private final Logger log = LoggerFactory.getLogger(LifeInputHandler.class);

//    public void communicate(Scanner sc, User user, Inventory inventory) {
//        boolean communicating = true;
//        do {
//            input.setLength(0);
//            System.out.println("\nType a valid planet name or type n to leave");
//            input.append(sc.nextLine().trim());
//            for (Planet planet : inventory.getPlanetOwnedList()) {
//                if (planet.getLifeForm() != null) {
//                    lifeDisplay.communicateWithLife(planet.getLifeForm(), user);
//                    communicating = false;
//                    break;
//                }
//            }
//            if (input.toString().contentEquals("n")) {
//                System.out.println("\n...LEAVING...\n");
//                communicating = false;
//            }
//            if (communicating) System.out.println("\n...Life not found...\n");
//
//        } while (communicating);
//    }

    public void terraform(Scanner sc, User user, Inventory inventory, CustomerAccount customerAccount) {
        System.out.println("\nBUILDING\n");
    }
}
