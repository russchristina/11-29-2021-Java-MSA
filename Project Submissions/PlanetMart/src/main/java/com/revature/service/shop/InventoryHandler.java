package com.revature.service.shop;

import com.revature.models.accounts.Account;
import com.revature.models.accounts.CustomerAccount;
import com.revature.models.shop.Inventory;
import com.revature.models.shop.Planet;
import com.revature.models.users.User;
import com.revature.service.account.AccountInputHandler;
import com.revature.service.account.LifeInputHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InventoryHandler {

    private final Logger log = LoggerFactory.getLogger(InventoryHandler.class);
    public final List<Planet> usersPlanets = new ArrayList<>();
    protected final LifeInputHandler lifeInputHandler = new LifeInputHandler();
    protected final AccountInputHandler accountInputHandler = new AccountInputHandler();

    public Inventory generateUserInventory(CustomerAccount account, User user) {
        usersPlanets.clear();
        for (Planet planet : account.getPlanetsFromDao()) {
            try{
                if(planet.getOwner().getName().contentEquals(user.getName()))
                    usersPlanets.add(planet);
            }catch(NullPointerException e){
                System.out.println("Planet not found.\nPlease try again.\n");
                log.debug(e.toString());
            }
        }
        return new Inventory(usersPlanets);
    }

    public void addToBalance(CustomerAccount customerAccount, User user) {
        //ADD TO BALANCE
        //MAKE THIS
    }

    public void chooseOptions(CustomerAccount customerAccount, User user, Inventory inventory, Scanner sc) {
        System.out.println("\nChoose options");

        boolean choosingOptions = true;
        while(choosingOptions){

            System.out.println("\n1. Communicate with viable Planet");
            System.out.println("2. Terraform");
            System.out.println("3. Return");

            String chosenOption = sc.nextLine().trim();
            switch(chosenOption){
                case("1"):
                    System.out.println("\nOption 1: Communicate with viable Planet");
                    lifeInputHandler.communicate(sc, user, inventory);
                    choosingOptions = false;
                    break;
                case("2"):
                    System.out.println("\nOption 2: Terraform");
                    lifeInputHandler.terraform(sc, user, inventory, customerAccount);
                    choosingOptions = false;
                    break;
                case("3"):
                    System.out.println("\nOption 3: Return");
                    accountInputHandler.inputChooseCustomerOptions(customerAccount, user);
                    choosingOptions = false;
                    break;
                default:
                    System.out.println("\nInput invalid, try again.\n");
                    break;
            }

        }
    }
}
