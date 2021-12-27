package com.revature.service.account;

import com.revature.display.account.LifeDisplay;
import com.revature.models.accounts.CustomerAccount;
import com.revature.models.shop.Inventory;
import com.revature.models.shop.Planet;
import com.revature.models.shop.TemporaryPlanet;
import com.revature.models.users.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Scanner;

public class LifeInputHandler {

    private final LifeDisplay lifeDisplay = new LifeDisplay();

    private final Logger transactionLogger = LoggerFactory.getLogger("transactionLogger");
    private final Logger debugLogger = LoggerFactory.getLogger("debugLogger");
    private final Logger errorLogger = LoggerFactory.getLogger("errorLogger");

    private final StringBuilder input = new StringBuilder();
    private final Scanner sc = new Scanner(System.in);

    public void communicate(List<TemporaryPlanet> temporaryPlanetList, User user, CustomerAccount customerAccount) {

        boolean communicating = true;
        do {
            input.setLength(0);
            System.out.println("\nType a valid planet name or type n to leave");
            input.append(sc.nextLine().trim());

            if (input.toString().contentEquals("n")) {
                System.out.println("\n...LEAVING...\n");
                communicating = false;
            }
            for (TemporaryPlanet temporaryPlanet : temporaryPlanetList) {
                if(temporaryPlanet.getName().contentEquals(input.toString())){
                    if(temporaryPlanet.getLifeform() != null){
                        lifeDisplay.communicateWithLife(temporaryPlanet.getLifeform(), user);
                        communicating = false;
                        break;
                    }
                }
            }
        } while (communicating);
    }
}
