package com.revature.repository;

import com.revature.models.exceptions.NegativeAmountException;
import com.revature.models.shop.TemporaryPlanet;
import com.revature.repository.Exception.InvalidPlanetIdException;
import com.revature.repository.Exception.InvalidUserIdException;
import com.revature.service.exceptions.EmptyInputException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlanetDAOTest {

    PlanetDAO planetDAO = new PlanetDAO();

    @Test
    void getPlanetsByUserIdInvalidUserIdExceptionTest() {
        Assertions.assertThrows(InvalidUserIdException.class, () -> planetDAO.getPlanetsByUserId(-1));
    }

    @Test
    void updatePlanetWaterPercentNegativeAmountExceptionTest() {
        Assertions.assertThrows(NegativeAmountException.class, () -> planetDAO.updatePlanetWaterPercent(1, -1));
    }

    @Test
    void updatePlanetWaterPercentInvalidPlanetIdExceptionTest(){
        Assertions.assertThrows(InvalidPlanetIdException.class, () -> planetDAO.updatePlanetWaterPercent(-1, 1));
    }

    @Test
    void updatePlanetAverageTempInvalidPlanetIdExceptionTest() {
        Assertions.assertThrows(InvalidPlanetIdException.class, () -> planetDAO.updatePlanetAverageTemp(-1, 1));
    }

    @Test
    void updatePlanetAverageTempNegativeAmountExceptionTest(){
        Assertions.assertThrows(NegativeAmountException.class, () -> planetDAO.updatePlanetAverageTemp(1, -1));
    }

    @Test
    void addPlanetEmptyInputExceptionTest() {
        TemporaryPlanet emptyNamePlanet = new TemporaryPlanet("", null, 37, true, 1, 1, null);
        Assertions.assertThrows(EmptyInputException.class, () -> planetDAO.addPlanet(emptyNamePlanet));
    }

    @Test
    void addPlanetNegativeWaterPercentExceptionTest() {
        TemporaryPlanet negativeWaterPlanet = new TemporaryPlanet("asdf", null, 37, true, -1, 1, null);
        Assertions.assertThrows(NegativeAmountException.class, () -> planetDAO.addPlanet(negativeWaterPlanet));
    }

    @Test
    void addPlanetNegativeAverageTemperatureExceptionTest() {
        TemporaryPlanet negativeAverageTemperaturePlanet = new TemporaryPlanet("asdf", null, 37, true, 1, -1, null);
        Assertions.assertThrows(NegativeAmountException.class, () -> planetDAO.addPlanet(negativeAverageTemperaturePlanet));
    }

    @Test
    void addPlanetInvalidUserIdExceptionTest() {
        TemporaryPlanet invalidUserIdPlanet = new TemporaryPlanet("asdf", null, -1, true, 1, 1, null);
        Assertions.assertThrows(InvalidUserIdException.class, () -> planetDAO.addPlanet(invalidUserIdPlanet));
    }

    @Test
    void getPlanetIdByName() {
        Assertions.assertThrows(EmptyInputException.class, () -> planetDAO.getPlanetIdByName(""));
    }
}