package com.revature.service.shop;

import com.revature.models.exceptions.InsufficientFundsException;
import com.revature.models.shop.Inventory;
import com.revature.models.shop.Life;
import com.revature.models.shop.Planet;
import com.revature.models.shop.TemporaryPlanet;
import com.revature.models.shop.generator.PlanetGenerator;
import com.revature.models.users.User;
import com.revature.repository.AtmosphereDAO;
import com.revature.repository.InventoryDAO;
import com.revature.repository.LifeDAO;
import com.revature.repository.PlanetDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Shop {

    private final Logger log = LoggerFactory.getLogger(Shop.class);


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

    public List<TemporaryPlanet> planetsForSale() {
        List<TemporaryPlanet> temporaryPlanetList = new ArrayList<>();
        PlanetGenerator planetGenerator = new PlanetGenerator();

        for(int i = 0; i < 5; i++){
            temporaryPlanetList.add(planetGenerator.generateRandomPlanet());
        }

        return temporaryPlanetList;
    }

    public void buyPlanet(TemporaryPlanet planet, User user, List<TemporaryPlanet> planetsForSale, Inventory inventory) throws InsufficientFundsException {
        int value = calculateValueOfPlanet(planet);
        if(inventory.getBalance()<value) throw new InsufficientFundsException();

        PlanetDAO planetDAO = new PlanetDAO();
        LifeDAO lifeDAO;
        AtmosphereDAO atmosphereDAO = new AtmosphereDAO();
        InventoryDAO iDao = new InventoryDAO();

        planet.setUserId(user.getUserId());
        planetDAO.addPlanet(planet);
        int planetId = planetDAO.getPlanetIdByName(planet.getName());

        if(planet.getLifeform() != null){
            Life life = planet.getLifeform();
            lifeDAO = new LifeDAO();
            lifeDAO.addLife(life.getName(), life.getPopulation(), life.getTechnologyLevel(), planetId);
        }

        Map<String, Integer> atmosphereComposition = new HashMap<>();
        Map<String, Integer> atmFromPlanet = planet.getAtmosphere();

        atmosphereDAO.addAtmosphereComposition(planet.getAtmosphere(), planetId);

        planetsForSale.remove(planet);
        inventory.setBalance(inventory.getBalance() - value);
        iDao.updateInventoryBalance(inventory.getId(), inventory.getBalance());
    }

    public void sellPlanet(TemporaryPlanet planet, User user, List<TemporaryPlanet> temporaryPlanetList, List<TemporaryPlanet> planetsForSale, Inventory inventory) {
        int value = calculateValueOfPlanet(planet);
        PlanetDAO planetDAO = new PlanetDAO();
        LifeDAO lifeDAO;
        AtmosphereDAO atmosphereDAO = new AtmosphereDAO();
        InventoryDAO iDao = new InventoryDAO();

        int planetId = planetDAO.getPlanetIdByName(planet.getName());
        Planet planetDB = planetDAO.getPlanetsById(planetId);

        if(planet.getLifeform() != null){
            lifeDAO = new LifeDAO();
            lifeDAO.deleteLifeByPlanetId(planetId);
        }

        atmosphereDAO.deleteAtmosphereByPlanetId(planetId);
        planetDAO.deletePlanetById(planetId);

        inventory.setBalance(inventory.getBalance()+value);
        iDao.updateInventoryBalance(inventory.getId(), inventory.getBalance());

        temporaryPlanetList.add(planet);

    }
}
