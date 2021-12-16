package com.revature.models.shop;

import com.revature.database.DummyShopData;
import com.revature.database.ShopDao;
import com.revature.models.exceptions.InsufficientFundsException;
import com.revature.models.exceptions.NegativeAmountException;
import com.revature.models.users.User;

import java.util.Map;

import static com.revature.database.DummyShopData.planetCatalogueMap;

public class Shop {

    private User user;


    public Shop() {
    }

    public Shop(User user) {
        this.user = user;
    }

    public Planet buyPlanet(String planetChosen) {
        Planet planet = planetCatalogueMap.get(planetChosen);
        try {
            user.removeFunds(planet.getCost());
        } catch (NegativeAmountException e) {
            e.printStackTrace();
        } catch (InsufficientFundsException e) {
            e.printStackTrace();
            return null;
        }
        ShopDao sDao = new ShopDao();
        sDao.removePlanetFromMap(planetChosen);
        sDao.addPlanetToUserOwnedMap(planetChosen, planet);

        return planet;
    }
}
