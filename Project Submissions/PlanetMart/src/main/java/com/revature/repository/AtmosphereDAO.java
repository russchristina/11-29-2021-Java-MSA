package com.revature.repository;

import com.revature.repository.DAOInterface.AtomsphereDAOInterface;
import com.revature.utility.ConnectionFactory;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class AtmosphereDAO implements AtomsphereDAOInterface {
    @Override
    public Map<String, Integer> getAtmosphereMapById(int id) {

        Map<String, Integer> atmosphereComposition = new HashMap<>();

        final String SQL = "select * from atmosphere_content where atmosphere_id = ?";

        ResultSet resultSet = null;

        try(
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL)
                ){
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            if(resultSet.next()){
                atmosphereComposition.put("Water", resultSet.getInt(1));
                atmosphereComposition.put("Oxygen", resultSet.getInt(2));
                atmosphereComposition.put("Hydrogen", resultSet.getInt(3));
                atmosphereComposition.put("Nitrogen", resultSet.getInt(4));
                atmosphereComposition.put("Argon", resultSet.getInt(5));
                atmosphereComposition.put("Helium", resultSet.getInt(6));
                atmosphereComposition.put("Carbon Dioxide", resultSet.getInt(7));
                atmosphereComposition.put("Methane", resultSet.getInt(8));
                atmosphereComposition.put("Chlorine", resultSet.getInt(9));

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return atmosphereComposition;
    }

    public void addAtmosphereComposition(Map<String, Integer> atmosphereMap){
        final String SQL = "insert into atmosphere_content values (default, ?, ?, ?, ?, ?, ?, ?, ?, ?";

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

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
