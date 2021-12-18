package com.revature.models;


import com.revature.database.DummyCustomerData;
import com.revature.models.accounts.CustomerAccount;
import com.revature.models.shop.Inventory;
import com.revature.models.shop.Planet;
import com.revature.models.shop.Shop;
import com.revature.models.users.User;
import com.revature.service.shop.InventoryHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static com.revature.database.DummyShopData.planetCatalogueMap;
import static com.revature.database.DummyCustomerData.userOwnedPlanetsList;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ShopTest {

    public User user;
    public Shop shop;
    public Planet testPlanet;
    public CustomerAccount customerAccount;
    public Inventory inventory;

    @BeforeAll
    public void initiateVariables(){
        this.user = DummyCustomerData.secondaryUsers1.get("Joseph");
        this.shop = new Shop(planetCatalogueMap);
        this.customerAccount = (CustomerAccount) DummyCustomerData.accountMap.get("user1");

        InventoryHandler inventoryHandler = new InventoryHandler();
        this.inventory = inventoryHandler.generateUserInventory(customerAccount,user);
        this.testPlanet = planetCatalogueMap.get("testPlanet");
    }

    @Test
    void buyPlanetTest(){

        Assertions.assertTrue(shop.buyPlanet(customerAccount, "testPlanet", user));
    }

    @Test
    void throwInsufficientFundsForBuyingPlanetExceptionTest(){
        Assertions.assertFalse(shop.buyPlanet(customerAccount, "expensivePlanet", user));
    }

    @Test
    void boughtPlanetRemovedFromCatalogueTest(){
        shop.buyPlanet(customerAccount, "removePlanet", user);
        Assertions.assertFalse(planetCatalogueMap.containsKey("removePlanet"));
    }

    @Test
    void boughtPlanetAddedToUserOwnedMapTest(){
        Planet planet = planetCatalogueMap.get("addPlanet");
        shop.buyPlanet(customerAccount, "addPlanet", user);
        Assertions.assertTrue(userOwnedPlanetsList.contains(planet));
    }

    @Test
    void boughtPlanetUpdatesOwnerTest(){
        Planet updateOwner = new Planet(1, "updateOwner", null, null);
        planetCatalogueMap.put("updateOwner", updateOwner);
        shop.buyPlanet(customerAccount, "updateOwner", user);
        Assertions.assertTrue(userOwnedPlanetsList.contains(updateOwner));
    }

    @Test
    void boughtPlanetUpdatesUsernameTest(){
        Planet updateUsername = new Planet(1, "updateUsername", null, null);
        planetCatalogueMap.put("updateUsername", updateUsername);
        shop.buyPlanet(customerAccount, "updateUsername", user);
        Assertions.assertTrue(userOwnedPlanetsList.contains(updateUsername));
    }

    @Test
    void sellPlanetTest(){
        Planet planet = new Planet(200, "soldPlanet", user, "primary");
        shop.sellPlanet(planet, user, customerAccount);
        Assertions.assertTrue(planetCatalogueMap.containsKey("soldPlanet"));
    }

    @Test
    void sellPlanetRemovesOwnerTest(){
        Planet planet = new Planet(200, "soldPlanet", user, "primary");
        shop.sellPlanet(planet, user, customerAccount);
        Assertions.assertNull(planetCatalogueMap.get("soldPlanet").getOwner());
    }

    @Test
    void sellPlanetRemovesUsernameTest(){
        Planet planet = new Planet(200, "soldPlanet", user, "primary");
        shop.sellPlanet(planet, user, customerAccount);
        Assertions.assertNull(planetCatalogueMap.get("soldPlanet").getUsername());
    }


}