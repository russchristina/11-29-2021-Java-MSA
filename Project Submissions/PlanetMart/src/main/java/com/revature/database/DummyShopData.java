package com.revature.database;

import com.revature.models.shop.Planet;
import com.revature.models.shop.generator.PlanetGenerator;

import java.util.HashMap;
import java.util.Map;

public class DummyShopData {

    public static Map<String, Planet> planetCatalogueMap = new HashMap();

    static {
        PlanetGenerator planetGenerator = new PlanetGenerator();
        Planet testPlanet = planetGenerator.generateRandomPlanet();
        Planet removePlanet = planetGenerator.generateRandomPlanet();
        Planet expensivePlanet = planetGenerator.generateRandomPlanet();
        Planet addPlanet = planetGenerator.generateRandomPlanet();

        planetCatalogueMap.put(testPlanet.getName(), testPlanet);
        planetCatalogueMap.put(removePlanet.getName(), removePlanet);
        planetCatalogueMap.put(expensivePlanet.getName(), expensivePlanet);
        planetCatalogueMap.put(addPlanet.getName(), addPlanet);

    }


}
