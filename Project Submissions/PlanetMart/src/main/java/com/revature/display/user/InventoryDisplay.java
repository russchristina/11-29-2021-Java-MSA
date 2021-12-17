package com.revature.display.user;

import com.revature.models.shop.Inventory;
import com.revature.models.shop.Planet;

public class InventoryDisplay {

    public void displayInventory(Inventory inventory) {
        if(inventory.getPlanetOwnedList().isEmpty()) {
            System.out.println("empty bro");
            return;
        }

        for (Planet planet : inventory.getPlanetOwnedList()) {
            for(int i = 0; i < 30; i++) System.out.print("=");
            System.out.printf("\nPlanet: %s \nValue: %d\nUserOwner: %s\nAttached to Account: %s\n",
                    planet.getName(), planet.getCost(), planet.getOwner().getName(), planet.getUsername());
        }
        for(int i = 0; i < 30; i++) System.out.print("=");
    }
}
