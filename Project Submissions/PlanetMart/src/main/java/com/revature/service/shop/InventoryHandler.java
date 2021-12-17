package com.revature.service.shop;

import com.revature.models.accounts.Account;
import com.revature.models.accounts.CustomerAccount;
import com.revature.models.shop.Inventory;
import com.revature.models.shop.Planet;
import com.revature.models.users.User;

import java.util.ArrayList;
import java.util.List;

public class InventoryHandler {

    public Inventory generateUserInventory(CustomerAccount account, User user) {
        List<Planet> usersPlanets = new ArrayList<>();
        for (Planet planet : account.getPlanetsFromDao()) {
            if(planet.getOwner().getName().contentEquals(user.getName()))
                usersPlanets.add(planet);
        }
        return new Inventory(usersPlanets);
    }

    public void addToBalance(CustomerAccount customerAccount, User user) {
    }
}
