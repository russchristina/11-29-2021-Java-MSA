package com.revature.repository.DAOInterface;

import com.revature.models.shop.Planet;
import com.revature.models.shop.TemporaryPlanet;

import java.util.List;

public interface PlanetDAOInterface {

    List<Planet> getAllPlanets();
    List<Planet> getPlanetsByUserId(int userId);

    void updatePlanetGoldilockZone(int planetId, boolean isGoldilockZone);
    void updatePlanetWaterPercent(int planetId, int waterPercent);
    void updatePlanetAverageTemp(int planetId, int averageTemp);

    void deletePlanetById(int id);
    void addPlanet(TemporaryPlanet planet);

}
