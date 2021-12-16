package com.revature.models;


import com.revature.database.ShopDao;
import com.revature.models.shop.Inventory;
import com.revature.models.shop.Planet;
import com.revature.models.shop.Shop;
import com.revature.models.users.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.HashMap;
import java.util.Map;

import static com.revature.database.DummyShopData.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ShopTest {

    public User user;
    public Shop shop;
    public Planet testPlanet;

    @BeforeAll
    public void initiateVariables(){
        this.user = new User("testUser", 100, new Inventory());
        this.shop = new Shop(user);

        this.testPlanet = planetCatalogueMap.get("testPlanet");
    }

    @Test
    void buyPlanetTest(){
        Assertions.assertEquals(testPlanet, shop.buyPlanet("testPlanet"));
    }

    @Test
    void throwInsufficientFundsForBuyingPlanetExceptionTest(){
        Assertions.assertEquals(null, shop.buyPlanet("expensivePlanet"));
    }

    @Test
    void boughtPlanetRemovedFromCatalogueTest(){
        shop.buyPlanet("removePlanet");
        Assertions.assertFalse(planetCatalogueMap.containsKey("removePlanet"));
    }

    @Test
    void boughtPlanetAddedToUserOwnedMapTest(){
        ShopDao sDao = new ShopDao();
        sDao.addPlanetToUserOwnedMap("addPlanet", new Planet());
        Assertions.assertTrue(userOwnedPlanetsMap.containsKey("addPlanet"));
    }

    @Test
    void sellPlanetTest(){

    }


}