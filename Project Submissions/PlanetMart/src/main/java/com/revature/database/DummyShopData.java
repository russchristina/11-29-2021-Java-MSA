package com.revature.database;

import com.revature.models.shop.Planet;
import com.revature.models.users.User;

import java.util.HashMap;
import java.util.Map;

public class DummyShopData {

    public static Map<String, Planet> planetCatalogueMap = new HashMap();
    public static Map<String, Planet> userOwnedPlanetsMap = new HashMap<>();

    static {
        planetCatalogueMap.put("testPlanet", new Planet(1, "testPlanet", null, null));
        planetCatalogueMap.put("removePlanet", new Planet(1, "removeMe", null, null));
        planetCatalogueMap.put("expensivePlanet", new Planet(99999, "gucciPlanet", null, null));
        planetCatalogueMap.put("addPlanet", new Planet(1, "addPlanet", null, null));

    }


}
