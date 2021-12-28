package com.revature.service.shop;

import com.revature.models.exceptions.InsufficientFundsException;
import com.revature.models.shop.Inventory;
import com.revature.models.shop.Life;
import com.revature.models.shop.Planet;
import com.revature.models.shop.TemporaryPlanet;
import com.revature.models.shop.generator.PlanetGenerator;
import com.revature.models.users.User;
import com.revature.repository.*;
import com.revature.repository.Exception.InvalidInventoryIdException;
import com.revature.repository.Exception.InvalidUserIdException;
import com.revature.repository.Exception.NoPlanetFoundException;
import com.revature.service.exceptions.EmptyInputException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Shop {

    private final Logger debugLogger = LoggerFactory.getLogger("debugLogger");
    private final Logger errorLogger = LoggerFactory.getLogger("errorLogger");
    private final Logger transactionLogger = LoggerFactory.getLogger("transactionLogger");

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
        if(inventory.getBalance()<value) {
            throw new InsufficientFundsException("User does not have enough money");
        }

        PlanetDAO planetDAO = new PlanetDAO();
        LifeDAO lifeDAO = new LifeDAO();
        AtmosphereDAO atmosphereDAO = new AtmosphereDAO();
        InventoryDAO iDao = new InventoryDAO();

        planet.setUserId(user.getUserId());
        try {
            planetDAO.addPlanet(planet);
            int planetId = planetDAO.getPlanetIdByName(planet.getName());
            if(planet.getLifeform() != null){
                Life life = planet.getLifeform();
                lifeDAO.addLife(life.getName(), life.getPopulation(), life.getTechnologyLevel(), planetId);
                atmosphereDAO.addAtmosphereComposition(planet.getAtmosphere(), planetId);
                planetsForSale.remove(planet);
                inventory.setBalance(inventory.getBalance() - value);
                iDao.updateInventoryBalance(inventory.getId(), inventory.getBalance());
                transactionLogger.info("PLANET Purchased ID: " + planetId +
                        "\nUSER ID: " + user.getUserId() + "BALANCE: " + inventory.getBalance());
            }
        } catch (SQLException e) {
                debugLogger.debug(String.valueOf(e));
                System.out.println("\nDATABASE ERROR\nTRY AGAIN.\n");
        }catch (EmptyInputException e) {
            debugLogger.debug(String.valueOf(e));
            System.out.println("\nEMPTY INPUT\n");
        } catch (InvalidUserIdException e) {
            debugLogger.debug(String.valueOf(e));
            System.out.println("\nINVALID USER ID\n");
        } catch (Exception e){
            errorLogger.error(String.valueOf(e));
            System.out.println("\nERROR\nIF ISSUES ARISE AGAIN PLEASE RESTART APPLICATION\n");
        }
    }

    public void sellPlanet(TemporaryPlanet planet, User user, List<TemporaryPlanet> temporaryPlanetList, List<TemporaryPlanet> planetsForSale, Inventory inventory) {
        int value = calculateValueOfPlanet(planet);
        PlanetDAO planetDAO = new PlanetDAO();
        LifeDAO lifeDAO;
        AtmosphereDAO atmosphereDAO = new AtmosphereDAO();
        InventoryDAO iDao = new InventoryDAO();

        int planetId;
        try {
            planetId = planetDAO.getPlanetIdByName(planet.getName());
            Planet planetDB = planetDAO.getPlanetsById(planetId);
            if(planet.getLifeform() != null) {
                lifeDAO = new LifeDAO();
                lifeDAO.deleteLifeByPlanetId(planetId);
            }
            atmosphereDAO.deleteAtmosphereByPlanetId(planetId);
            planetDAO.deletePlanetById(planetId);
            inventory.setBalance(inventory.getBalance()+value);
            iDao.updateInventoryBalance(inventory.getId(), inventory.getBalance());
            temporaryPlanetList.add(planet);
            planetsForSale.add(planet);
            transactionLogger.info("PLANET SOLD ID: " + planetId +
                    "\nUSER ID: " + user.getUserId() + "BALANCE: " + inventory.getBalance());
        } catch (SQLException e) {
            debugLogger.debug(String.valueOf(e));
            System.out.println("\nDATABASE ERROR, TRY AGAIN.\n");
        } catch (EmptyInputException e) {
            debugLogger.debug(e.toString());
            System.out.println("\nEMPTY INPUT EXCEPTION\n");
        } catch (NoPlanetFoundException e) {
            debugLogger.debug(String.valueOf(e));
            System.out.println("\nNO PLANET FOUND ERROR\n");
        } catch (InvalidInventoryIdException e) {
            debugLogger.debug(String.valueOf(e));
            System.out.println("\nINVALID INVENTORY ID\n");
        }
    }
}
