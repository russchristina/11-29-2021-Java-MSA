package com.revature.utility;

import com.revature.models.shop.Planet;
import com.revature.models.shop.TemporaryPlanet;
import com.revature.models.users.User;
import com.revature.repository.AtmosphereDAO;
import com.revature.repository.Exception.InvalidUserIdException;
import com.revature.repository.Exception.NoPlanetFoundException;
import com.revature.repository.LifeDAO;
import com.revature.repository.PlanetDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlanetToTempPlanet {
    private final Logger errorLogger = LoggerFactory.getLogger("errorLogger");

    public List<TemporaryPlanet> getUsersTemporaryPlanets(User user) throws SQLException, NoPlanetFoundException {
        PlanetDAO planetDAO = new PlanetDAO();
        LifeDAO lifeDAO = new LifeDAO();
        AtmosphereDAO atmosphereDAO = new AtmosphereDAO();

        List<Planet> planetList = null;
        try {
            planetList = planetDAO.getPlanetsByUserId(user.getUserId());
        } catch (InvalidUserIdException e) {
            errorLogger.error(String.valueOf(e));
            System.out.println("\nERROR\nTRY AGAIN OR RESTART IF ISSUE PERSISTS\n");
        }
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
