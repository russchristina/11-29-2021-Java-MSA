package com.revature.models.shop;

import com.revature.database.AccountDao;
import com.revature.database.DummyShopData;
import com.revature.database.ShopDao;
import com.revature.models.accounts.CustomerAccount;
import com.revature.models.exceptions.InsufficientFundsException;
import com.revature.models.exceptions.NegativeAmountException;
import com.revature.models.users.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class Shop {

    private final Logger log = LoggerFactory.getLogger(Shop.class);

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
            System.out.println(e.getMessage());
            log.debug(e.toString());
            System.out.println("\nPlease try again.\n");
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

    public boolean sellPlanet(Planet planet, User user, CustomerAccount customerAccount) {
        ShopDao sDao = new ShopDao();
        AccountDao aDao = new AccountDao();

        planet.setOwner(null);
        planet.setUsername(null);

        if(sDao.addPlanetToCatalogue(planet) == null) {
            setPlanetCatalogueMap(DummyShopData.planetCatalogueMap);
            aDao.removePlanetFromOwnedList(planet);
            try {
                user.addFunds(planet.getCost());
                customerAccount.getPlanetsFromDao();
                return true;
            } catch (NegativeAmountException e) {
                System.out.println("Error: Negative amount for Planet\nPlease try again.");
                log.error(e.toString());
            }

        }
        return false;
    }
}
