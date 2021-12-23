package com.revature.service.shop;

import com.revature.database.AccountDao;
import com.revature.database.DummyShopData;
import com.revature.database.ShopDao;
import com.revature.models.accounts.CustomerAccount;
import com.revature.models.exceptions.InsufficientFundsException;
import com.revature.models.exceptions.NegativeAmountException;
import com.revature.models.shop.Life;
import com.revature.models.shop.Planet;
import com.revature.models.shop.TemporaryPlanet;
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
            //DOA Interaction - update
            user.removeFunds(planet.getCost());
            planet.setOwner(user);
            planet.setUsername(customerAccount.getUsername());
        } catch (NegativeAmountException | InsufficientFundsException e) {
            System.out.println(e.getMessage());
            log.debug(e.toString());
            System.out.println("\nPlease try again.\n");
            return false;
        }

        if(sDao.removePlanetFromMap(planetChosen) != null
                && aDao.addPlanetToUserOwnedList(planet) != null) {
            //DOA Interaction - Create
            setPlanetCatalogueMap(DummyShopData.planetCatalogueMap);
            customerAccount.getPlanets();
            return true;
        }
        return false;
    }

    public void sellPlanet(Planet planet, User user, CustomerAccount customerAccount) {
        ShopDao sDao = new ShopDao();
        AccountDao aDao = new AccountDao();

        planet.setOwner(null);
        planet.setUsername(null);

        if(sDao.addPlanetToCatalogue(planet) == null) {
            setPlanetCatalogueMap(DummyShopData.planetCatalogueMap);
            aDao.removePlanetFromOwnedList(planet);
            try {
                //DOA Interaction - update
                user.addFunds(planet.getCost());
                customerAccount.getPlanets();
            } catch (NegativeAmountException e) {
                System.out.println("Error: Negative amount for Planet\nPlease try again.");
                log.error(e.toString());
            }

        }
    }

    public int calculateValueOfPlanet(TemporaryPlanet planet) {
        int baseValue = 100;
        int goldilocksMultiplier = 5;
        int waterPercentMultipllier = 10;
        int temperatureMultiplier = 10;
        int gasBonus = 0;
        int lifeFormMultiplier = 3;

        int averageTemperature = planet.getAverageTemperature();
        int waterPercent = planet.getWaterPercent();
        boolean goldilock = planet.isGoldilocksZone();
        Life lifeform = planet.getLifeform();


        if(waterPercent > 70) waterPercentMultipllier -= 3;
        if(waterPercent < 10) waterPercentMultipllier -= 8;

        if(averageTemperature <= 273) temperatureMultiplier = 1;
        if(averageTemperature <= 323) temperatureMultiplier = 2;
        if(averageTemperature > 373) temperatureMultiplier = 1;

        if(planet.getAtmosphere().containsKey("Oxygen")) gasBonus = 100;

        int suitableForLifePlanet = (baseValue * goldilocksMultiplier) +
                (waterPercent * waterPercentMultipllier)
                + (averageTemperature * temperatureMultiplier)
                + gasBonus;

        if(lifeform != null) return suitableForLifePlanet * lifeFormMultiplier;
        if(goldilock) return suitableForLifePlanet;
        return (baseValue) + (waterPercent * waterPercentMultipllier) + (averageTemperature * temperatureMultiplier) + gasBonus;
    }

}
