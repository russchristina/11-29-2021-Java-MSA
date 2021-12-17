package com.revature.database;

import com.revature.models.shop.Planet;

import static com.revature.database.DummyShopData.*;

public class ShopDao {

    public ShopDao() {
    }

    public Planet removePlanetFromMap(String planetName) {
        return planetCatalogueMap.remove(planetName);
    }

    public Planet addPlanetToUserOwnedMap(Planet planet) {
        if(!userOwnedPlanetsList.contains(planet)){
            userOwnedPlanetsList.add(planet);
            return planet;
        }
        return null;
    }

    public Planet addPlanetToCatalogue(Planet planet) {
        return planetCatalogueMap.put(planet.getName(), planet);
    }
}
