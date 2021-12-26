package com.revature.repository;

import com.revature.models.shop.AtmosphereComposition;
import com.revature.repository.DAOInterface.AtomsphereDAOInterface;
import com.revature.utility.ConnectionFactory;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class AtmosphereDAO implements AtomsphereDAOInterface {
    @Override
    public AtmosphereComposition getAtmosphereCompositionByPlanetId(int id) {

        Map<String, Integer> atmosphereComposition = new HashMap<>();
        AtmosphereComposition aComp = null;

        final String SQL = "select * from atmosphere_content where planet_id = ?";

        ResultSet resultSet = null;

        try(
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL)
                ){
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            if(resultSet.next()){
                atmosphereComposition.put("Water", resultSet.getInt(2));
                atmosphereComposition.put("Oxygen", resultSet.getInt(3));
                atmosphereComposition.put("Hydrogen", resultSet.getInt(4));
                atmosphereComposition.put("Nitrogen", resultSet.getInt(5));
                atmosphereComposition.put("Argon", resultSet.getInt(6));
                atmosphereComposition.put("Helium", resultSet.getInt(7));
                atmosphereComposition.put("Carbon Dioxide", resultSet.getInt(8));
                atmosphereComposition.put("Methane", resultSet.getInt(9));
                atmosphereComposition.put("Chlorine", resultSet.getInt(10));
                atmosphereComposition.put("Unknown", resultSet.getInt(11));
                aComp = new AtmosphereComposition(resultSet.getInt(1), atmosphereComposition, resultSet.getInt(12));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return aComp;
    }

    public AtmosphereComposition getAtmosphereCompositionById(int atmoId){
        Map<String, Integer> atmosphereComposition = new HashMap<>();
        AtmosphereComposition aComp = null;
        final String SQL = "select * from user_id where atmosphere_id = ?";

        ResultSet resultSet = null;

        try(
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL)
        ){
            statement.setInt(1, atmoId);
            resultSet = statement.executeQuery();

            if(resultSet.next()){
                atmosphereComposition.put("Water", resultSet.getInt(2));
                atmosphereComposition.put("Oxygen", resultSet.getInt(3));
                atmosphereComposition.put("Hydrogen", resultSet.getInt(4));
                atmosphereComposition.put("Nitrogen", resultSet.getInt(5));
                atmosphereComposition.put("Argon", resultSet.getInt(6));
                atmosphereComposition.put("Helium", resultSet.getInt(7));
                atmosphereComposition.put("Carbon Dioxide", resultSet.getInt(8));
                atmosphereComposition.put("Methane", resultSet.getInt(9));
                atmosphereComposition.put("Chlorine", resultSet.getInt(10));
                atmosphereComposition.put("Unknown", resultSet.getInt(11));
                aComp = new AtmosphereComposition(resultSet.getInt(1), atmosphereComposition, resultSet.getInt(12));

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return aComp;
    }

    public void addAtmosphereComposition(Map<String, Integer> atmosphereMap, int planetId){
        final String SQL = "insert into atmosphere_content values (default, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try(
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL)
                ){

            statement.setInt(1, atmosphereMap.get("Water"));
            statement.setInt(2, atmosphereMap.get("Oxygen"));
            statement.setInt(3, atmosphereMap.get("Hydrogen"));
            statement.setInt(4, atmosphereMap.get("Nitrogen"));
            statement.setInt(5, atmosphereMap.get("Argon"));
            statement.setInt(6, atmosphereMap.get("Helium"));
            statement.setInt(7, atmosphereMap.get("Carbon Dioxide"));
            statement.setInt(8, atmosphereMap.get("Methane"));
            statement.setInt(9, atmosphereMap.get("Chlorine"));
            statement.setInt(10, atmosphereMap.get("Unknown"));
            statement.setInt(11, planetId);
            statement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deleteAtmosphereByPlanetId(int planetId) {
        final String SQL = "delete from atmosphere_content where planet_id = ?";

        try(Connection connection = ConnectionFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL)) {

            statement.setInt(1, planetId);
            statement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
