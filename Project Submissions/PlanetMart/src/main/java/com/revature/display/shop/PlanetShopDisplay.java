package com.revature.display.shop;

import com.revature.service.shop.Shop;

public class PlanetShopDisplay {


    public void displayShop(Shop shop) {
        System.out.println("Welcome to the Planet Shop");
        System.out.println("These are the available Planets");
        shop.getPlanetCatalogueMap().forEach(
                (planetName, planet) -> {
                    for (int i = 0; i < 30; i++) System.out.print("=");
                        System.out.println("\nPlanet: " + planet.getName());
                        System.out.println("Goldilocks Zone? " + planet.isGoldilocksZone());
                        System.out.println("Water: " + planet.getWaterPercent() + "%");
                        System.out.println("Average Surface Temperature: " + planet.getAverageTemperature() +" Kelvin : " + (planet.getAverageTemperature()-273) + " Celcius");
                        System.out.println("Planet Atmosphere: ");
                        planet.getAtmosphere().forEach((gas, amount) -> {
                            System.out.println(gas + " - " + amount + "%");
                        });
                        if(planet.getLifeForm() != null){
                            System.out.println("\nLIFE FORM\n");
                            System.out.printf("Name: %s\nPopulation: %d\nTechnology Level: %d\n\n", planet.getLifeForm().getName(), planet.getLifeForm().getPopulation(), planet.getLifeForm().getTechnologyLevel());
                        }

                        System.out.println("Value: " + planet.getCost() + "\n");

                });
        for(int i = 0; i < 30; i++) System.out.print("=");

    }

}
