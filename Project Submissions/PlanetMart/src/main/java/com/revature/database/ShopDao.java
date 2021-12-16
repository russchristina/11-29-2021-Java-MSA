package com.revature.database;

import com.revature.models.shop.Planet;

import java.util.HashMap;
import java.util.Map;

import static com.revature.database.DummyShopData.*;

public class ShopDao {

    public ShopDao() {
    }

    public void removePlanetFromMap(String planetName) {
        planetCatalogueMap.remove(planetName);
    }

    public void addPlanetToUserOwnedMap(String planetChosen, Planet planet) {
        userOwnedPlanetsMap.put(planetChosen, planet);
    }
}
