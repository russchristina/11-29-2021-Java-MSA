package com.revature.service.account;

import com.revature.display.account.LifeDisplay;
import com.revature.display.utility.CreateShapes;
import com.revature.models.accounts.CustomerAccount;
import com.revature.models.shop.Inventory;
import com.revature.models.shop.Planet;
import com.revature.models.shop.TemporaryPlanet;
import com.revature.models.users.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.CacheRequest;
import java.util.List;
import java.util.Scanner;

public class LifeInputHandler {

    private final LifeDisplay lifeDisplay = new LifeDisplay();

    private final Logger transactionLogger = LoggerFactory.getLogger("transactionLogger");
    private final Logger debugLogger = LoggerFactory.getLogger("debugLogger");
    private final Logger errorLogger = LoggerFactory.getLogger("errorLogger");

    private final StringBuilder input = new StringBuilder();
    private final Scanner sc = new Scanner(System.in);
    CreateShapes createShapes = new CreateShapes();

    public void communicate(List<TemporaryPlanet> temporaryPlanetList, User user, CustomerAccount customerAccount) {


        boolean communicating = true;
        do {
            System.out.println();
            System.out.println(createShapes.border);
            System.out.println(createShapes.indent + "COMMUNICATION");
            input.setLength(0);
            System.out.println(createShapes.indent + "TYPE VALID PLANET NAME OR N TO LEAVE");
            System.out.print(createShapes.indent + "-> ");
            input.append(sc.nextLine().trim());

            if (input.toString().contentEquals("N")) {
                System.out.println();
                System.out.println(createShapes.indent + "...LEAVING...");
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
