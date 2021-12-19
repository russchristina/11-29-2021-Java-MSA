package com.revature.service.shop;

import com.revature.models.accounts.Account;
import com.revature.models.accounts.CustomerAccount;
import com.revature.models.shop.Inventory;
import com.revature.models.shop.Planet;
import com.revature.models.users.User;
import com.revature.service.account.AccountInputHandler;
import com.revature.service.account.LifeInputHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InventoryHandler {

    public Inventory generateUserInventory(CustomerAccount account, User user) {
        List<Planet> usersPlanets = new ArrayList<>();
        for (Planet planet : account.getPlanetsFromDao()) {
            try{
                if(planet.getOwner().getName().contentEquals(user.getName()))
                    usersPlanets.add(planet);
            }catch(NullPointerException e){
                e.printStackTrace();
            }
        }
        return new Inventory(usersPlanets);
    }

    public void addToBalance(CustomerAccount customerAccount, User user) {
    }

    public void chooseOptions(CustomerAccount customerAccount, User user, Inventory inventory, Scanner sc) {
        System.out.println("\nChoose options");
        LifeInputHandler lifeInputHandler = new LifeInputHandler();
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
