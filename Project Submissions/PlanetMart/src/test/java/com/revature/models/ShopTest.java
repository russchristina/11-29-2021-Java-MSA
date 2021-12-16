package com.revature.models;


import com.revature.database.ShopDao;
import com.revature.models.accounts.CustomerAccount;
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
        this.user = new User("testUser", 100, new Inventory(), "primary");
        this.shop = new Shop(user);

        this.testPlanet = planetCatalogueMap.get("testPlanet");
    }

    @Test
    void buyPlanetTest(){
        Assertions.assertTrue(shop.buyPlanet("testPlanet"));
    }

    @Test
    void throwInsufficientFundsForBuyingPlanetExceptionTest(){
        Assertions.assertFalse(shop.buyPlanet("expensivePlanet"));
    }

    @Test
    void boughtPlanetRemovedFromCatalogueTest(){
        shop.buyPlanet("removePlanet");
        Assertions.assertFalse(planetCatalogueMap.containsKey("removePlanet"));
    }

    @Test
    void boughtPlanetAddedToUserOwnedMapTest(){
        shop.buyPlanet("addPlanet");
        Assertions.assertTrue(userOwnedPlanetsMap.containsKey("addPlanet"));
    }

    @Test
    void boughtPlanetUpdatesOwnerTest(){
        planetCatalogueMap.put("updateOwner", new Planet(1, "updateOwner", null, null));
        shop.buyPlanet("updateOwner");
        Assertions.assertEquals(user, userOwnedPlanetsMap.get("updateOwner").getOwner());
    }

    @Test
    void boughtPlanetUpdatesUsernameTest(){
        planetCatalogueMap.put("updateUsername", new Planet(1, "updateUsername", null, null));
        shop.buyPlanet("updateUsername");
        Assertions.assertEquals(user.getPrimaryUsername(), userOwnedPlanetsMap.get("updateUsername").getUsername());
    }

    @Test
    void sellPlanetTest(){
        Planet planet = new Planet(200, "soldPlanet", user, "primary");
        shop.sellPlanet(planet);
        Assertions.assertTrue(planetCatalogueMap.containsKey("soldPlanet"));
    }

    @Test
    void sellPlanetRemovesOwnerTest(){
        Planet planet = new Planet(200, "soldPlanet", user, "primary");
        shop.sellPlanet(planet);
        Assertions.assertEquals(null, planetCatalogueMap.get("soldPlanet").getOwner());
    }

    @Test
    void sellPlanetRemovesUsernameTest(){
        Planet planet = new Planet(200, "soldPlanet", user, "primary");
        shop.sellPlanet(planet);
        Assertions.assertEquals(null, planetCatalogueMap.get("soldPlanet").getUsername());
    }


}