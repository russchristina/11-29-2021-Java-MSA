package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.revature.model.Vehicle;
import com.revature.util.ConnectionClosers;
import com.revature.util.ConnectionFactory;

public class VehicleRepositoryImplement implements VehicleRepository {

    @Override
    public void save(Vehicle vehicle) {
        final String SQL = "insert into ingredient values(default, ?, ?)";
        Connection conn = null;
        PreparedStatement stmt = null; // use questions marks as placeholders since it's a prepared statment.

        try {
            conn = ConnectionFactory.getConnection();
            /*
             * The values of the parameters in a PreparedStatement are supplied later after
             * the statement has been compiled.
             */
            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, vehicle.getVehicle_style());
            stmt.setString(2, vehicle.getVehicle_maker());
            stmt.setString(3, vehicle.getVehicle_info());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionClosers.closeConnection(conn);
            ConnectionClosers.closeStatement(stmt);
        }
    }

    @Override
    public Vehicle findById(int id) {
        Vehicle vehicle = null;
        final String SQL = "select ingredient_name from ingredient where ingredient_id = " + id;
        Connection conn = null;
        Statement stmt = null;
        ResultSet set = null;

        try {
            conn = ConnectionFactory.getConnection();
            stmt = conn.createStatement();
            set = stmt.executeQuery(SQL);

            if (set.next()) {
                vehicle = new Vehicle(set.getInt(1),
                        set.getString(2),
                        set.getString(3),
                        set.getString(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionClosers.closeConnection(conn);
            ConnectionClosers.closeResultSet(set);
            ConnectionClosers.closeStatement(stmt);
        }

        return vehicle;

    }

    @Override
    public Vehicle findByName(String name) {
        return null;
    }

    @Override
    public void findAll() {

        final String SQL = "select vehicle_style, vehicle_maker, vehicle_info from vehicle";
        Connection connection = null;
        Statement stmt = null;
        ResultSet set = null;

        try {

            connection = ConnectionFactory.getConnection();

            stmt = connection.createStatement();

            set = stmt.executeQuery(SQL);

            while (set.next()) {

                System.out.println("Style: " + set.getString("vehicle_style"));
                System.out.println("Brand: " + set.getString("vehicle_maker"));
                System.out.println("Details: " + set.getString("vehicle_info"));
                System.out.println("RENTED");
                System.out.println(" ============================== ");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionClosers.closeConnection(connection);
            ConnectionClosers.closeResultSet(set);
            ConnectionClosers.closeStatement(stmt);
        }

    }

    @Override
    public void update(Vehicle vehicle) {
    }

    @Override
    public void delete(Vehicle vehicle) {

    }
}
