package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.Administrator;
import com.revature.util.ConnectionClosers;
import com.revature.util.ConnectionFactory;

public class AdministratorRepositoryImplement implements AdministratorRepository {

    @Override
    public void save(Administrator administrator) {
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
            stmt.setInt(1, administrator.getAdmin_id());
            stmt.setString(2, administrator.getAdmin_password());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionClosers.closeConnection(conn);
            ConnectionClosers.closeStatement(stmt);
        }
    }

    @Override
    public Administrator findById(int id) {
        //
        Administrator administrator = null;
        final String SQL = "select ingredient_name from ingredient where ingredient_id = " + id;
        Connection conn = null;
        Statement stmt = null;
        ResultSet set = null;

        try {
            conn = ConnectionFactory.getConnection();
            stmt = conn.createStatement();
            set = stmt.executeQuery(SQL);

            if (set.next()) {
                administrator = new Administrator(set.getInt(1),
                        set.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionClosers.closeConnection(conn);
            ConnectionClosers.closeResultSet(set);
            ConnectionClosers.closeStatement(stmt);
        }

        return administrator;

    }

    @Override
    public Administrator findByName(String name) {
        return null;
    }

    @Override
    public List<Administrator> findAll() {
        List<Administrator> administrator = new ArrayList<>();

        final String SQL = "select * from ingredient";
        Connection connection = null;
        Statement stmt = null;
        ResultSet set = null;

        try {

            connection = ConnectionFactory.getConnection();

            stmt = connection.createStatement();

            set = stmt.executeQuery(SQL);

            while (set.next()) {
                administrator.add(
                        new Administrator(
                                set.getInt(1),
                                set.getString(2)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionClosers.closeConnection(connection);
            ConnectionClosers.closeResultSet(set);
            ConnectionClosers.closeStatement(stmt);
        }

        return administrator;

    }

    @Override
    public void update(Administrator administrator) {

        final String SQL = "update ingredient set "
                + "ingredient_name = ? where ingredient_flavor = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        // no resultset is needed

        try {

            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement(SQL); // the prepare statment allows you to pass in values to ?
            stmt.setInt(1, administrator.getAdmin_id()); // this sets the first ? string
            stmt.setString(2, administrator.getAdmin_password()); // this sets the second ?
            stmt.execute();
        } catch (SQLException e) {

            e.printStackTrace();
        } finally {
            ConnectionClosers.closeConnection(conn);
            ConnectionClosers.closeStatement(stmt);
        }
    }

    @Override
    public void delete(Administrator administrator) {

    }

    @Override
    public String retrieveAdmin(String admin_name) {

        String user = null;
        final String SQL = "select admin_name from administrator where admin_name = '" + admin_name + "'";
        Connection conn = null;
        Statement stmt = null;
        ResultSet set = null;

        try {
            conn = ConnectionFactory.getConnection();
            stmt = conn.createStatement();
            set = stmt.executeQuery(SQL);

            if (set.next()) {
                user = set.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionClosers.closeConnection(conn);
            ConnectionClosers.closeResultSet(set);
            ConnectionClosers.closeStatement(stmt);
        }

        return user;
    }

    @Override
    public String retrievePassword(String admin_name) {

        String user = null;
        final String SQL = "select admin_password from administrator where admin_name = '" + admin_name + "'";
        Connection conn = null;
        Statement stmt = null;
        ResultSet set = null;

        try {
            conn = ConnectionFactory.getConnection();
            stmt = conn.createStatement();
            set = stmt.executeQuery(SQL);

            if (set.next()) {
                user = set.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionClosers.closeConnection(conn);
            ConnectionClosers.closeResultSet(set);
            ConnectionClosers.closeStatement(stmt);
        }

        return user;
    }

}
