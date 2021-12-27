package com.revature.repository;

import com.revature.models.exceptions.NegativeAmountException;
import com.revature.repository.Exception.InvalidPlanetIdException;
import com.revature.service.exceptions.EmptyInputException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LifeDAOTest {

    LifeDAO lifeDAO = new LifeDAO();

    @Test
    void getLifeByPlanetIdInvalidPlanetIdExceptionTest() {
        Assertions.assertThrows(InvalidPlanetIdException.class, () -> lifeDAO.getLifeByPlanetId(-1));
    }

    @Test
    void updateLifeNameEmptyInputExceptionTest(){
        Assertions.assertThrows(EmptyInputException.class, () -> lifeDAO.updateLifeName(2, " "));
    }

    @Test
    void updateLifePopulationNegativePopulationExceptionTest(){
        Assertions.assertThrows(NegativeAmountException.class, () -> lifeDAO.updateLifePopulation(2, -1));
    }

    @Test
    void updateLifeTechnologyLevelNegativeTechnology(){
        Assertions.assertThrows(NegativeAmountException.class, () -> lifeDAO.updateLifeTechnologyLevel(2, -1));
    }

    @Test
    void addLifeEmptyNameExceptionTest() {
        Assertions.assertThrows(EmptyInputException.class, () -> lifeDAO.addLife("", 1, 1, 1));
    }

    @Test
    void addLifeNegativePopulationExceptionTest() {
        Assertions.assertThrows(NegativeAmountException.class, () -> lifeDAO.addLife("asdf", -1, 1, 1));
    }

    @Test
    void addLifeNegativeTechnologyExceptionTest() {
        Assertions.assertThrows(NegativeAmountException.class, () -> lifeDAO.addLife("asdf", 1, -1, 1));
    }

    @Test
    void addLifeInvalidPlanetIdExceptionTest() {
        Assertions.assertThrows(InvalidPlanetIdException.class, () -> lifeDAO.addLife("asdf", 1, 1, -1));
    }

    @Test
    void deleteLifeByPlanetIdInvalidPlanetIdExceptionTest() {
        Assertions.assertThrows(InvalidPlanetIdException.class, () -> lifeDAO.deleteLifeByPlanetId(-1));
    }
}