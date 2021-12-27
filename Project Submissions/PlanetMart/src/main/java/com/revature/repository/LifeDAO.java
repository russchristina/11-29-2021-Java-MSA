package com.revature.repository;

import com.revature.models.exceptions.NegativeAmountException;
import com.revature.models.shop.Life;
import com.revature.repository.DAOInterface.LifeDAOInterface;
import com.revature.repository.Exception.InvalidPlanetIdException;
import com.revature.service.exceptions.EmptyInputException;
import com.revature.utility.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LifeDAO implements LifeDAOInterface {

    private final Logger debugLogger = LoggerFactory.getLogger("debugLogger");

    @Override
    public List<Life> getAllLife() {
        List<Life> lifeForms = new ArrayList<>();

        final String SQL = "select * from life_forms";

        try(Connection connection = ConnectionFactory.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL)
        ){
            while(resultSet.next()){
                lifeForms.add(
                        new Life(
                                resultSet.getInt(1),
                                resultSet.getString(2),
                                resultSet.getInt(3),
                                resultSet.getInt(4),
                                resultSet.getInt(5)));
            }
        } catch (SQLException throwables) {
            debugLogger.debug(throwables.toString());
        }

        return lifeForms;
    }

    @Override
    public Life getLifeById(int id) {
        Life life = null;

        final String SQL = "select * from life_forms where life_id = ?";

        ResultSet resultSet = null;
        try(
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL)

        ){
            statement.setInt(1, id);

            resultSet = statement.executeQuery();

            if(resultSet.next()) life =  new Life(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getInt(3),
                    resultSet.getInt(4),
                    resultSet.getInt(5));


        } catch (SQLException throwables) {
            debugLogger.debug(throwables.toString());
        } finally {
            try {
                assert resultSet != null;
                resultSet.close();
            } catch (SQLException e) {
                debugLogger.debug(e.toString());
            }
        }
        return life;
    }

    public Life getLifeByPlanetId(int planetId) throws SQLException {
        Life life = null;
        PlanetDAO planetDAO = new PlanetDAO();
        if(planetDAO.readPlanetId(planetId)) throw new InvalidPlanetIdException("Planet ID not found");

        final String SQL = "select * from life_forms where planet_id = ?";

        ResultSet resultSet = null;
        try(
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL)

        ){
            statement.setInt(1, planetId);

            resultSet = statement.executeQuery();

            if(resultSet.next()) life =  new Life(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getInt(3),
                    resultSet.getInt(4),
                    resultSet.getInt(5));


        } catch (SQLException throwables) {
            debugLogger.debug(throwables.toString());
        } finally {
            try {
                assert resultSet != null;
                resultSet.close();
            } catch (SQLException e) {
                debugLogger.debug(e.toString());
            }
        }
        return life;
    }

    @Override
    public void updateLifeName(int lifeId, String name) throws EmptyInputException {
        final String SQL = "update life_forms set life_name = ? where life_id = ?";
        if(name.trim().contentEquals("")) throw new EmptyInputException("Name given is empty");
        try(
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL)
        ){

            statement.setString(1, name);
            statement.setInt(2, lifeId);
            statement.execute();


        } catch (SQLException throwables) {
            debugLogger.debug(throwables.toString());
        }
    }

    @Override
    public void updateLifePopulation(int lifeId, int population) {

        if(population<0) throw new NegativeAmountException("Population is negative");

        final String SQL = "update life_forms set population = ? where life_id = ?";

        try(
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL)
        ){

            statement.setInt(1, population);
            statement.setInt(2, lifeId);
            statement.execute();


        } catch (SQLException throwables) {
            debugLogger.debug(throwables.toString());
        }
    }

    @Override
    public void updateLifeTechnologyLevel(int lifeId, int technologyLevel) {
        if(technologyLevel<0) throw new NegativeAmountException("Technology is negative");

        final String SQL = "update life_forms set technology_level = ? where life_id = ?";

        try(
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL)
        ){

            statement.setInt(1, technologyLevel);
            statement.setInt(2, lifeId);
            statement.execute();


        } catch (SQLException throwables) {
            debugLogger.debug(throwables.toString());
        }
    }

    @Override
    public void deleteLifeById(int id) {
        final String SQL = "delete from life_forms where life_id = ?";

        try(Connection connection = ConnectionFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL)) {

            statement.setInt(1, id);
            statement.execute();

        } catch (SQLException throwables) {
            debugLogger.debug(throwables.toString());
        }
    }

    @Override
    public void addLife(String name, int population, int technologyLevel, int planetId) throws EmptyInputException, SQLException {
        if(name.trim().contentEquals("")) throw new EmptyInputException("Name is empty");
        if(population<0 || technologyLevel <0) throw new NegativeAmountException("Negative integer passed");
        PlanetDAO planetDAO = new PlanetDAO();
        if(planetDAO.readPlanetId(planetId)) throw new InvalidPlanetIdException("Planet ID is not valid");

        final String SQL = "insert into life_forms values( default, ?, ?, ?, ?)";

        try(
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL)
        ) {

            statement.setString(1, name);
            statement.setInt(2, population);
            statement.setInt(3, technologyLevel);
            statement.setInt(4, planetId);
            statement.execute();

        } catch (SQLException throwables) {
            debugLogger.debug(throwables.toString());
        }
    }

    public void deleteLifeByPlanetId(int planetId) throws SQLException {
        PlanetDAO planetDAO = new PlanetDAO();
        if(planetDAO.readPlanetId(planetId)) throw new InvalidPlanetIdException("Planet ID not found");

        final String SQL = "delete * from life_forms where planet_id = ?";

        try(Connection connection = ConnectionFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL)) {

            statement.setInt(1, planetId);
            statement.execute();

        } catch (SQLException throwables) {
            debugLogger.debug(throwables.toString());
        }
    }
}
