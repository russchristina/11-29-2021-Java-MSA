package com.revature.database;

import com.revature.models.shop.Planet;

import static com.revature.database.DummyShopData.*;
import static com.revature.database.DummyCustomerData.*;

public class ShopDao {

    public ShopDao() {
    }

    public Planet removePlanetFromMap(String planetName) {
        return planetCatalogueMap.remove(planetName);
    }



    public Planet addPlanetToCatalogue(Planet planet) {
        return planetCatalogueMap.put(planet.getName(), planet);
    }
}
