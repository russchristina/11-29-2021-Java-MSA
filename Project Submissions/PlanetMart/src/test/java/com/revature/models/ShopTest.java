package com.revature.models;


import com.revature.models.shop.Inventory;
import com.revature.models.shop.Planet;
import com.revature.models.shop.Shop;
import com.revature.models.users.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static com.revature.database.DummyShopData.planetCatalogueMap;
import static com.revature.database.DummyShopData.userOwnedPlanetsList;

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
        Planet planet = planetCatalogueMap.get("addPlanet");
        shop.buyPlanet("addPlanet");
        Assertions.assertTrue(userOwnedPlanetsList.contains(planet));
    }

    @Test
    void boughtPlanetUpdatesOwnerTest(){
        Planet updateOwner = new Planet(1, "updateOwner", null, null);
        planetCatalogueMap.put("updateOwner", updateOwner);
        shop.buyPlanet("updateOwner");
        Assertions.assertTrue(userOwnedPlanetsList.contains(updateOwner));
    }

    @Test
    void boughtPlanetUpdatesUsernameTest(){
        Planet updateUsername = new Planet(1, "updateUsername", null, null);
        planetCatalogueMap.put("updateUsername", updateUsername);
        shop.buyPlanet("updateUsername");
        Assertions.assertTrue(userOwnedPlanetsList.contains(updateUsername));
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
        Assertions.assertNull(planetCatalogueMap.get("soldPlanet").getOwner());
    }

    @Test
    void sellPlanetRemovesUsernameTest(){
        Planet planet = new Planet(200, "soldPlanet", user, "primary");
        shop.sellPlanet(planet);
        Assertions.assertNull(planetCatalogueMap.get("soldPlanet").getUsername());
    }


}