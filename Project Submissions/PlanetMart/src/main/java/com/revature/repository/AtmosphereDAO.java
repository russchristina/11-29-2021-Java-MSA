package com.revature.repository;

import com.revature.models.shop.AtmosphereComposition;
import com.revature.repository.DAOInterface.AtomsphereDAOInterface;
import com.revature.repository.Exception.InvalidMapException;
import com.revature.repository.Exception.NoPlanetFoundException;
import com.revature.utility.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class AtmosphereDAO implements AtomsphereDAOInterface {
    private final Logger debugLogger = LoggerFactory.getLogger("debugLogger");

    @Override
    public AtmosphereComposition getAtmosphereCompositionByPlanetId(int id) throws SQLException, NullPointerException, NoPlanetFoundException {

        Map<String, Integer> atmosphereComposition = new HashMap<>();
        AtmosphereComposition aComp = null;

        PlanetDAO planetDAO = new PlanetDAO();
        if(planetDAO.readPlanetId(id)) throw new NoPlanetFoundException("Planet with ID: " + id + " not in database.");

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
        }finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                debugLogger.debug(e.toString());
            }
        }
        return aComp;
    }

    public AtmosphereComposition getAtmosphereCompositionById(int atmoId) throws SQLException, NullPointerException {
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
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                debugLogger.debug(e.toString());
            }
        }
        return aComp;
    }

    public void addAtmosphereComposition(Map<String, Integer> atmosphereMap, int planetId) throws SQLException, NoPlanetFoundException, InvalidMapException {
        final String SQL = "insert into atmosphere_content values (default, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        if(atmosphereMap.size() != 10) throw new InvalidMapException("Atmosphere Map provided is of size " + atmosphereMap.size());

        PlanetDAO planetDAO = new PlanetDAO();
        if(planetDAO.readPlanetId(planetId)) throw new NoPlanetFoundException("Planet with ID: " + planetId + " not in database.");


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
        }
    }

    public void deleteAtmosphereByPlanetId(int planetId) throws SQLException, NoPlanetFoundException {
        final String SQL = "delete from atmosphere_content where planet_id = ?";
        PlanetDAO planetDAO = new PlanetDAO();

        if(planetDAO.readPlanetId(planetId)) throw new NoPlanetFoundException("Planet with ID: " + planetId + " not in database.");

        try(Connection connection = ConnectionFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL)) {

            statement.setInt(1, planetId);
            statement.execute();

        }
    }
}
