package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.revature.model.Users;
import com.revature.util.ConnectionClosers;
import com.revature.util.ConnectionFactory;

public class UsersRepositoryImplement implements UsersRepository {

    @Override
    public Users findByName(String name) {
        return null;
    }

    @Override
    public void updateBalance(Float newBalance, String user_name) {

        final String SQL = "update users set "
                + "user_balance = ? where user_name = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        // no resultset is needed

        try {

            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement(SQL);
            stmt.setFloat(1, newBalance);
            stmt.setString(2, user_name);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionClosers.closeConnection(conn);
            ConnectionClosers.closeStatement(stmt);
        }
    }

    @Override
    public void deleteUsers(String user_name) {
        // List<Users> usersDelete = new ArrayList<>();

        final String SQL = "delete from users where user_name = '" + user_name + "'";
        final String QUERY = "select user_id, user_name, user_password, secondary_user, user_balance, user_address, user_dob from users";
        Connection connection = null;
        Statement stmt = null;
        ResultSet set = null;

        try {

            connection = ConnectionFactory.getConnection();

            stmt = connection.createStatement();

            stmt.executeUpdate(SQL);

            set = stmt.executeQuery(QUERY);

            while (set.next()) {
                set.getInt(1);
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
    public void addUsers(Users users) {
        final String SQL = "insert into users values(default,?,?,?,?,?,?)";
        Connection conn = null;
        PreparedStatement stmt = null;
        // no resultset is needed

        try {

            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement(SQL);

            stmt.setString(1, users.getUsers_name());
            stmt.setString(2, users.getUsers_password());
            stmt.setString(3, users.getSecondary_users());
            stmt.setFloat(4, users.getUsers_balance());
            stmt.setString(5, users.getUsers_address());
            stmt.setString(6, users.getUsers_DOB());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionClosers.closeConnection(conn);
            ConnectionClosers.closeStatement(stmt);
        }
    }

    @Override
    public void updateUsersAddress(String address, String user_name) {

        final String SQL = "update users set "
                + "user_address = ? where user_name = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        // no resultset is needed

        try {

            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, address);
            stmt.setString(2, user_name);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionClosers.closeConnection(conn);
            ConnectionClosers.closeStatement(stmt);
        }
    }

    @Override
    public void updateUsersDOB(String newDOB, String user_name) {

        final String SQL = "update users set "
                + "user_DOB = ? where user_name = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        // no resultset is needed

        try {

            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, newDOB);
            stmt.setString(2, user_name);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionClosers.closeConnection(conn);
            ConnectionClosers.closeStatement(stmt);
        }
    }

    @Override
    public void changeOrAddSecondaryUser(String secondary, String user_name) {
        final String SQL = "update users set "
                + "secondary_user = ? where user_name = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        // no resultset is needed

        try {

            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, secondary);
            stmt.setString(2, user_name);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionClosers.closeConnection(conn);
            ConnectionClosers.closeStatement(stmt);
        }
    }

    @Override
    public String viewSecondaryUser(String user_name) {

        String user = null;
        final String SQL = "select secondary_user from users where user_name = '" + user_name + "'";
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
    public String viewUserPersonalInfo(String user_name) {

        String user = null;
        final String SQL = "select user_address from users where user_name = '" + user_name + "'";
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
    public String viewUserDOB(String user_name) {

        String user = null;
        final String SQL = "select user_dob from users where user_name = '" + user_name + "'";
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
    public Float viewUserAccountBalance(String user_name) {

        Float user = null;
        final String SQL = "select user_balance from users where user_name = '" + user_name + "'";
        Connection conn = null;
        Statement stmt = null;
        ResultSet set = null;

        try {
            conn = ConnectionFactory.getConnection();
            stmt = conn.createStatement();
            set = stmt.executeQuery(SQL);

            if (set.next()) {
                user = set.getFloat(1);
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
    public String retrievePassword(String user_name) {

        String user = null;
        final String SQL = "select user_password from users where user_name = '" + user_name + "'";
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
    public String retrieveUser(String user_name) {

        String user = null;
        final String SQL = "select user_name from users where user_name = '" + user_name + "'";
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
