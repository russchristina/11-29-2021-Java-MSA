package com.revature.database;

import com.revature.models.shop.Inventory;
import com.revature.models.shop.Planet;
import com.revature.models.users.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DummyShopData {

    public static Map<String, Planet> planetCatalogueMap = new HashMap();
    public static List<Planet> userOwnedPlanetsList = new ArrayList<>();


    static {
        Planet testPlanet = new Planet(1, "testPlanet", null, null);
        Planet removePlanet = new Planet(1, "removeMe", null, null);
        Planet expensivePlanet = new Planet(99999, "gucciPlanet", null, null);
        Planet addPlanet = new Planet(1, "addPlanet", null, null);
        planetCatalogueMap.put("testPlanet", testPlanet);
        planetCatalogueMap.put("removePlanet", removePlanet);
        planetCatalogueMap.put("expensivePlanet", expensivePlanet);
        planetCatalogueMap.put("addPlanet", addPlanet);

        userOwnedPlanetsList.add(new Planet(10, "myOwnPlanet", new User("Joleyne", 0, new Inventory(), "user1"), "user1"));
    }


}
