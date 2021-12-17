package com.revature.display.shop;

import com.revature.models.shop.Planet;
import com.revature.models.shop.Shop;

public class PlanetShopDisplay {


    public void displayShop(Shop shop) {
        System.out.println("Welcome to the Planet Shop");
        System.out.println("These are the available Planets");
        shop.getPlanetCatalogueMap().forEach(
                (planetName, planet) -> {
                    for (int i = 0; i < 30; i++) System.out.print("=");
                    System.out.printf("\nPlanet: %s \nValue: %d\n", planetName, planet.getCost());
                    System.out.println(planet.toString());
                });
        for(int i = 0; i < 30; i++) System.out.print("=");

    }

}
