package com.revature.repository;

import com.revature.models.shop.Inventory;
import com.revature.models.shop.Life;
import com.revature.repository.DAOInterface.LifeDAOInterface;
import com.revature.utility.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LifeDAO implements LifeDAOInterface {
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
            throwables.printStackTrace();
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
            throwables.printStackTrace();
        } finally {
            try {
                assert resultSet != null;
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return life;
    }

    public Life getLifeByName(String name){
        Life life = null;

        final String SQL = "select * from life_forms where life_name = ?";

        ResultSet resultSet = null;
        try(
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL)

        ){
            statement.setString(1, name);

            resultSet = statement.executeQuery();

            if(resultSet.next()) life =  new Life(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getInt(3),
                    resultSet.getInt(4),
                    resultSet.getInt(5));


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                assert resultSet != null;
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return life;
    }

    public Life getLifeByPlanetId(int planetId){
        Life life = null;

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
            throwables.printStackTrace();
        } finally {
            try {
                assert resultSet != null;
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return life;
    }

    @Override
    public void updateLifeName(int lifeId, String name) {
        final String SQL = "update life_forms set life_name = ? where life_id = ?";

        try(
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL)
        ){

            statement.setString(1, name);
            statement.setInt(2, lifeId);
            statement.execute();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void updateLifePopulation(int lifeId, int population) {

        final String SQL = "update life_forms set population = ? where life_id = ?";

        try(
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL)
        ){

            statement.setInt(1, population);
            statement.setInt(2, lifeId);
            statement.execute();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    @Override
    public void updateLifeTechnologyLevel(int lifeId, int technologyLevel) {
        final String SQL = "update life_forms set technology_level = ? where life_id = ?";

        try(
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL)
        ){

            statement.setInt(1, technologyLevel);
            statement.setInt(2, lifeId);
            statement.execute();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateLifeUserId(int lifeId, int planetId){
        final String SQL = "update life_forms set planet_id = ? where life_id = ?";

        try(
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL)
        ){

            statement.setInt(1, planetId);
            statement.setInt(2, lifeId);
            statement.execute();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    @Override
    public void deleteLifeById(int id) {
        final String SQL = "delete * from life_forms where life_id = ?";

        try(Connection connection = ConnectionFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL)) {

            statement.setInt(1, id);
            statement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void addLife(String name, int population, int technologyLevel, int planetId) {
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
            throwables.printStackTrace();
        }
    }

    public void deleteLifeByPlanetId(int planetId) {
        final String SQL = "delete * from life_forms where planet_id = ?";

        try(Connection connection = ConnectionFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL)) {

            statement.setInt(1, planetId);
            statement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
