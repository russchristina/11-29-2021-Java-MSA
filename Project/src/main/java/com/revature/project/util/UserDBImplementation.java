package com.revature.project.util;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDBImplementation implements UserDB {
    @Override
    public void save(UserSpecs specs) {
        final String SQL = "insert into users values (default, ?, ?)";
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = OpenConnection.getConnection();
            statement = connection.prepareStatement(SQL);
            statement.setString(1, specs.getUsername());
            statement.setString(2, specs.getUserPass());
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CloseDB.closeConnection(connection);
            CloseDB.closeStatement(statement);
        }
    }

    @Override
    public UserSpecs findById(int id) {
        final String SQL = "select * from users where user_id = "
                + id;
        UserSpecs userSpecs = null;
        Connection connection = null;
        ResultSet set = null;
        Statement statement = null;

        try {
            connection = OpenConnection.getConnection();
            statement = connection.createStatement();
            set = statement.executeQuery(SQL);
            if (set.next()) {
                userSpecs = new UserSpecs(set.getInt(1),
                        set.getString(2),
                        set.getString(3)
                );

            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CloseDB.closeConnection(connection);
            CloseDB.closeStatement(statement);
            CloseDB.closeResultSet(set);
        }
        return userSpecs;
    }

    @Override
    public Map<UserSpecs, UserSpecs> findByName(String name) {
        Connection connection = null;
        Statement statement = null;
        ResultSet set = null;
        UserSpecs specs = new UserSpecs();
        Map<UserSpecs,UserSpecs> specsMap = new HashMap<>();
       final String SQL = "select * from users where user_name = '" + name + "'";
       try{
           connection = OpenConnection.getConnection();
           statement = connection.createStatement();
           set = statement.executeQuery(SQL);
//            while (set.next()){
//                specsMap.put(new UserSpecs(set.getString(2), set.getString(3)));
//            }
       }catch(SQLException e){
            e.printStackTrace();
       }finally {
           CloseDB.closeConnection(connection);
       }

        return specsMap;
    }

    @Override
    public List<UserSpecs> findAll() {
        //Setup all the stuff you will need here
        final String SQL = "select * from users";
        Connection connection = null;
        Statement stmt = null;
        ResultSet set = null;
        List<UserSpecs> userInfo = new ArrayList<>();
        {
            try {
                connection = OpenConnection.getConnection();
                stmt = connection.createStatement();
                set = stmt.executeQuery(SQL);
                while (set.next()) {
                    userInfo.add(new UserSpecs(
                            set.getInt(1),
                            set.getString(2),
                            set.getString(3)));
                }

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                CloseDB.closeConnection(connection);
                CloseDB.closeResultSet(set);
                CloseDB.closeStatement(stmt);
            }

            return userInfo;
        }

    }

    @Override
    public void update(UserSpecs specs) {

    }

    @Override
    public UserSpecs delete(UserSpecs specs) {
        final String SQL = "delete from users where user_id = ?" ;
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = OpenConnection.getConnection();
          statement = connection.prepareStatement(SQL);
            statement.setInt(1, specs.getId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CloseDB.closeConnection(connection);
            CloseDB.closeStatement(statement);
        }
        return specs;
    }
}