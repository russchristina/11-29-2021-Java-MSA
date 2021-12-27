package com.revature.repository.DAOInterface;

import com.revature.models.shop.Planet;
import com.revature.models.shop.TemporaryPlanet;

import java.sql.SQLException;
import java.util.List;

public interface PlanetDAOInterface {

    List<Planet> getAllPlanets() throws SQLException;
    List<Planet> getPlanetsByUserId(int userId) throws SQLException;

    void updatePlanetGoldilockZone(int planetId, boolean isGoldilockZone) throws SQLException;
    void updatePlanetWaterPercent(int planetId, int waterPercent) throws SQLException;
    void updatePlanetAverageTemp(int planetId, int averageTemp) throws SQLException;

    void deletePlanetById(int id) throws SQLException;
    void addPlanet(TemporaryPlanet planet) throws SQLException;

}
