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
            System.out.println("\nPlanet: " + planet.getName());
            System.out.println("Goldilocks Zone? " + planet.isGoldilocksZone());
            System.out.println("Water: " + planet.getWaterPercent() + "%");
            System.out.println("Average Surface Temperature: " + planet.getAverageTemperature() +" celcius");
            System.out.println("Planet Atmosphere: ");
            planet.getAtmosphere().forEach((gas, amount) -> {
                System.out.println(gas + " - " + amount + "%");
            });
            if(planet.getLifeForm() != null){
                System.out.println("\nLIFE FORM\n");
                System.out.printf("Name: %s\nPopulation: %d\nTechnology Level: %d\n\n", planet.getLifeForm().getName(), planet.getLifeForm().getPopulation(), planet.getLifeForm().getTechnologyLevel());
            }

            System.out.println("Value: " + planet.getCost() + "\n");

        }
        for(int i = 0; i < 30; i++) System.out.print("=");
    }
}
