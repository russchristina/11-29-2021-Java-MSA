package com.revature.repository;

import com.revature.models.shop.Inventory;
import com.revature.models.shop.Planet;
import com.revature.models.shop.TemporaryPlanet;
import com.revature.repository.DAOInterface.PlanetDAOInterface;
import com.revature.utility.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlanetDAO implements PlanetDAOInterface {

    private final Logger debugLogger = LoggerFactory.getLogger("debugLogger");
    @Override
    public List<Planet> getAllPlanets() throws SQLException {
        List<Planet> planets = new ArrayList<>();

        final String SQL = "select * from planets";

        try(Connection connection = ConnectionFactory.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL)
        ){
            while(resultSet.next()){
                planets.add(
                        new Planet(
                                resultSet.getInt(1),
                                resultSet.getString(2),
                                resultSet.getInt(3),
                                resultSet.getBoolean(4),
                                resultSet.getInt(5),
                                resultSet.getInt(6)));
            }
        }

        return planets;

    }

    @Override
    public List<Planet> getPlanetsByUserId(int userId) throws SQLException {

        List<Planet> planets = new ArrayList<>();

        final String SQL = "select * from planets where planet_user_id = ?";

        ResultSet resultSet = null;

        try(
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL)
                ){

            statement.setInt(1, userId);
            resultSet = statement.executeQuery();

            while (resultSet.next()){
                planets.add(
                        new Planet(
                                resultSet.getInt(1),
                                resultSet.getString(2),
                                resultSet.getInt(3),
                                resultSet.getBoolean(4),
                                resultSet.getInt(5),
                                resultSet.getInt(6)));
            }
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                debugLogger.debug(e.toString());
            }
        }
        return planets;
    }

    @Override
    public void updatePlanetGoldilockZone(int planetId, boolean isGoldilockZone) throws SQLException {
        final String SQL = "update planets set goldilocks_zone = ? where planet_id = ?";

        try(
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL)
                ){
            statement.setBoolean(1, isGoldilockZone);
            statement.setInt(2, planetId);
            statement.execute();

        }

    }

    @Override
    public void updatePlanetWaterPercent(int planetId, int waterPercent) throws SQLException {
        final String SQL = "update planets set water_percent = ? where planet_id = ?";

        try(
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL)
        ){
            statement.setInt(1, waterPercent);
            statement.setInt(2, planetId);
            statement.execute();

        }
    }

    @Override
    public void updatePlanetAverageTemp(int planetId, int averageTemp) throws SQLException {
        final String SQL = "update planets set average_temp = ? where planet_id = ?";

        try(
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL)
        ){
            statement.setInt(1, averageTemp);
            statement.setInt(2, planetId);
            statement.execute();

        }
    }

    @Override
    public void deletePlanetById(int id) throws SQLException {
        final String SQL = "delete from planets where planet_id = ?";

        try(Connection connection = ConnectionFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL)) {

            statement.setInt(1, id);
            statement.execute();

        }
    }

    @Override
    public void addPlanet(TemporaryPlanet planet) throws SQLException {
        final String SQL = "insert into planets values( default, ?, ?, ?, ? ,?)";

        try(
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL)
        ) {

            statement.setString(1, planet.getName());
            statement.setInt(2, planet.getUserId());
            statement.setBoolean(3, planet.isGoldilocksZone());
            statement.setInt(4, planet.getWaterPercent());
            statement.setInt(5, planet.getAverageTemperature());
            statement.execute();

        }
    }


    public int getPlanetIdByName(String name) throws SQLException {
        int planetId = 0;

        final String SQL = "select * from planets where planet_name = ?";

        ResultSet resultSet = null;
        try(
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL)

        ){
            statement.setString(1, name);

            resultSet = statement.executeQuery();

            if(resultSet.next()) planetId = resultSet.getInt(1);


        }  finally {
            try {
                assert resultSet != null;
                resultSet.close();
            } catch (SQLException e) {
                debugLogger.debug(e.toString());
            }
        }
        return planetId;
    }

    public Planet getPlanetsById(int planetId) throws SQLException {
        Planet planet = null;

        final String SQL = "select * from planets where planet_id = ?";

        ResultSet resultSet = null;
        try(
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL)

        ){
            statement.setInt(1, planetId);

            resultSet = statement.executeQuery();

            if(resultSet.next()) {
                planet = new Planet(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getInt(3),
                        resultSet.getBoolean(4),
                        resultSet.getInt(5),
                        resultSet.getInt(6)
                );
            }
        } finally {
            try {
                assert resultSet != null;
                resultSet.close();
            } catch (SQLException e) {
                debugLogger.debug(e.toString());
            }
        }
        return planet;
    }

    public boolean readPlanetId(int id) throws SQLException {
        boolean success = false;

        final String SQL = "select * from planets where planet_id = ?";

        ResultSet resultSet = null;
        try(Connection connection = ConnectionFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL)
        ){
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if(resultSet.next()) success = true;
        }

        return success;
    }
}
