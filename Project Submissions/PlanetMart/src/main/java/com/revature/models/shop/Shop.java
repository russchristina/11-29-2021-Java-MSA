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

    public boolean buyPlanet(String planetChosen) {
        Planet planet = planetCatalogueMap.get(planetChosen);
        try {
            user.removeFunds(planet.getCost());
        } catch (NegativeAmountException e) {
            e.printStackTrace();
        } catch (InsufficientFundsException e) {
            e.printStackTrace();
            return false;
        }
        planet.setOwner(user);
        planet.setUsername(user.getPrimaryUsername());
        ShopDao sDao = new ShopDao();
        if(sDao.removePlanetFromMap(planetChosen) != null)
                if(sDao.addPlanetToUserOwnedMap(planetChosen, planet) != null)
                    return true;

        return false;
    }

    public boolean sellPlanet(Planet planet) {
        ShopDao sDao = new ShopDao();
        planet.setOwner(null);
        planet.setUsername(null);
        if(sDao.addPlanetToCatalogue(planet) != null) {
            try {
                user.addFunds(planet.getCost());
            } catch (NegativeAmountException e) {
                e.printStackTrace();
            }
            return true;
        }
        return false;
    }
}
