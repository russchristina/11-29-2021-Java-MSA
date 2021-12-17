package com.revature.models.shop;

import com.revature.database.AccountDao;
import com.revature.database.DummyShopData;
import com.revature.database.ShopDao;
import com.revature.models.accounts.CustomerAccount;
import com.revature.models.exceptions.InsufficientFundsException;
import com.revature.models.exceptions.NegativeAmountException;
import com.revature.models.users.User;
import com.revature.service.shop.InventoryHandler;

import java.util.Map;

public class Shop {

    private Map<String, Planet> planetCatalogueMap;

    public Shop(Map<String, Planet> planetCatalogueMap) {
        this.planetCatalogueMap = planetCatalogueMap;
    }

    public Shop() {

    }

    public Map<String, Planet> getPlanetCatalogueMap() {
        return planetCatalogueMap;
    }

    public void setPlanetCatalogueMap(Map<String, Planet> planetCatalogueMap) {
        this.planetCatalogueMap = planetCatalogueMap;
    }

    public boolean buyPlanet(CustomerAccount customerAccount, String planetChosen, User user) {

        Planet planet = planetCatalogueMap.get(planetChosen);
        ShopDao sDao = new ShopDao();
        AccountDao aDao = new AccountDao();


        try {
            user.removeFunds(planet.getCost());
            planet.setOwner(user);
            planet.setUsername(user.getPrimaryUsername());
        } catch (NegativeAmountException | InsufficientFundsException e) {
            e.printStackTrace();
            return false;
        }

        if(sDao.removePlanetFromMap(planetChosen) != null
                && aDao.addPlanetToUserOwnedList(planet) != null) {
            setPlanetCatalogueMap(DummyShopData.planetCatalogueMap);
            customerAccount.getPlanetsFromDao();
            return true;
        }
        return false;
    }

    public boolean sellPlanet(Planet planet, User user) {
        ShopDao sDao = new ShopDao();
        planet.setOwner(null);
        planet.setUsername(null);
        if(sDao.addPlanetToCatalogue(planet) != null) {
            setPlanetCatalogueMap(DummyShopData.planetCatalogueMap);
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
