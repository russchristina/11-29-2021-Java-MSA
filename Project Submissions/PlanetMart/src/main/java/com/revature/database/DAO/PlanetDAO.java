package com.revature.database.DAO;

import com.revature.models.shop.Planet;

import java.util.List;

public interface PlanetDAO {

    public List<Planet> getAllPlanets();
    public List<Planet> getPlanetsByLocation(String location);
    public void updatePlanetById(int id);
    public void deletePlanetById(int id);
    public void addPlanet(Planet planet);

}
