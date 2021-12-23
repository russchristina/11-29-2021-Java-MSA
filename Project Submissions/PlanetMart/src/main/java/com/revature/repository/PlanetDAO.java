package com.revature.repository;

import com.revature.models.shop.Planet;
import com.revature.repository.DAOInterface.PlanetDAOInterface;
import com.revature.utility.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlanetDAO implements PlanetDAOInterface {
    @Override
    public List<Planet> getAllPlanets() {
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
                                resultSet.getInt(2),
                                resultSet.getString(3),
                                resultSet.getInt(4),
                                resultSet.getBoolean(5),
                                resultSet.getInt(6),
                                resultSet.getInt(7),
                                resultSet.getInt(8)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return planets;

    }

    @Override
    public List<Planet> getPlanetsByUserId(int userId) {

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
                                resultSet.getInt(2),
                                resultSet.getString(3),
                                resultSet.getInt(4),
                                resultSet.getBoolean(5),
                                resultSet.getInt(6),
                                resultSet.getInt(7),
                                resultSet.getInt(8)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return planets;
    }

    @Override
    public void updatePlanetGoldilockZone(int planetId, boolean isGoldilockZone) {
        final String SQL = "update planets set goldilocks_zone = ? where planet_id = ?";

        try(
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL)
                ){
            statement.setBoolean(1, isGoldilockZone);
            statement.setInt(2, planetId);
            statement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public void updatePlanetWaterPercent(int planetId, int waterPercent) {
        final String SQL = "update planets set water_percent = ? where planet_id = ?";

        try(
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL)
        ){
            statement.setInt(1, waterPercent);
            statement.setInt(2, planetId);
            statement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void updatePlanetAverageTemp(int planetId, int averageTemp) {
        final String SQL = "update planets set average_temp = ? where planet_id = ?";

        try(
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL)
        ){
            statement.setInt(1, averageTemp);
            statement.setInt(2, planetId);
            statement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void deletePlanetById(int id) {
        final String SQL = "delete * from planets where planet_id = ?";

        try(Connection connection = ConnectionFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL)) {

            statement.setInt(1, id);
            statement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void addPlanet(Planet planet) {
        final String SQL = "insert into planets values( default, ?, ?, ?, ? ,? ,? ,?";

        try(
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL)
        ) {

            statement.setInt(1, planet.getLifeId());
            statement.setString(2, planet.getName());
            statement.setInt(3, planet.getUserId());
            statement.setBoolean(4, planet.isGoldilocksZone());
            statement.setInt(5, planet.getWaterPercent());
            statement.setInt(6, planet.getAverageTemperature());
            statement.setInt(7, planet.getAtmosphereId());
            statement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
