package com.revature.repository;

import com.revature.repository.Exception.InvalidMapException;
import com.revature.repository.Exception.NoPlanetFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class AtmosphereDAOTest {

 AtmosphereDAO atmosphereDAO = new AtmosphereDAO();
 PlanetDAO planetDAO = new PlanetDAO();



    @Test
    void getAtmosphereCompositionNoPlanetFoundException() {
     Assertions.assertThrows(NoPlanetFoundException.class, () -> atmosphereDAO.getAtmosphereCompositionByPlanetId(-1));
    }

    @Test
    void getAtmosphereCompositionByIdInvalidIdExceptionTest() {
        Assertions.assertThrows(NullPointerException.class, () -> atmosphereDAO.getAtmosphereCompositionById(9999));
    }

    @Test
    void addAtmosphereCompositionNullException() {
        Assertions.assertThrows(NullPointerException.class, () -> atmosphereDAO.addAtmosphereComposition(null, 0));
    }

    @Test
    void addAtmosphereCompositionNoPlanetFoundException() {
        Map<String, Integer> atmo = new HashMap<>();
        atmo.put("Water", 0);
        atmo.put("Oxygen", 0);
        atmo.put("Hydrogen", 0);
        atmo.put("Nitrogen", 0);
        atmo.put("Argon", 0);
        atmo.put("Helium", 0);
        atmo.put("Carbon Dioxide", 0);
        atmo.put("Methane", 0);
        atmo.put("Chlorine", 0);
        atmo.put("Unknown", 0);
        Assertions.assertThrows(NoPlanetFoundException.class, () -> atmosphereDAO.addAtmosphereComposition(atmo, -1));
    }

    @Test
    void addAtmosphereCompositionInvalidMapExceptionTest() {
        Map<String, Integer> atmo = new HashMap<>();
        Assertions.assertThrows(InvalidMapException.class, () -> atmosphereDAO.addAtmosphereComposition(atmo, 0));
    }

    @Test
    void deleteAtmosphereByPlanetIdNoPlanetFoundException() {
        Assertions.assertThrows(NoPlanetFoundException.class, () -> atmosphereDAO.deleteAtmosphereByPlanetId(-1));
    }
}