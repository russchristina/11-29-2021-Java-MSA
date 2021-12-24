package com.revature.utility;

import com.revature.models.shop.Planet;
import com.revature.models.shop.TemporaryPlanet;
import com.revature.models.users.User;
import com.revature.repository.AtmosphereDAO;
import com.revature.repository.LifeDAO;
import com.revature.repository.PlanetDAO;

import java.util.ArrayList;
import java.util.List;

public class PlanetToTempPlanet {

    public static List<TemporaryPlanet> getUsersTemporaryPlanets(User user){
        PlanetDAO planetDAO = new PlanetDAO();
        LifeDAO lifeDAO = new LifeDAO();
        AtmosphereDAO atmosphereDAO = new AtmosphereDAO();

        List<Planet> planetList = planetDAO.getPlanetsByUserId(user.getUserId());
        List<TemporaryPlanet> temporaryPlanetList = new ArrayList<>();

        if(!planetList.isEmpty()){
            for (Planet planet : planetList) {
                temporaryPlanetList.add(new TemporaryPlanet(
                        planet.getName(),
                        lifeDAO.getLifeByPlanetId(planet.getId()),
                        planet.getUserId(),
                        planet.isGoldilocksZone(),
                        planet.getWaterPercent(),
                        planet.getAverageTemperature(),
                        atmosphereDAO.getAtmosphereCompositionByPlanetId(planet.getId()).getAtmosphereMap()
                ));
            }
        }
        return temporaryPlanetList;
    }

}
