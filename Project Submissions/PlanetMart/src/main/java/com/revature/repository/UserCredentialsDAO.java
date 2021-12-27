package com.revature.repository;

import com.revature.models.users.UserCredential;
import com.revature.repository.DAOInterface.UserCredentialDAO;
import com.revature.utility.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserCredentialsDAO implements UserCredentialDAO {
    @Override
    public List<UserCredential> getAllUserCredentials() {

        List<UserCredential> userCredentials = new ArrayList<>();

        final String SQL = "select * from user_credentials";

        try(Connection connection = ConnectionFactory.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL)
        ){
            while(resultSet.next()){
                userCredentials.add(
                        new UserCredential(
                                resultSet.getInt(1),
                                resultSet.getString(2),
                                resultSet.getString(3),
                                resultSet.getString(4),
                                resultSet.getString(5)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return userCredentials;
    }

    @Override
    public UserCredential getUserCredentialByUsername(String username) {

        UserCredential userCredential = null;

        final String SQL = "select * from user_credentials where username = ?";

        ResultSet resultSet = null;
        try(
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL)

                ){
            statement.setString(1, username);

            resultSet = statement.executeQuery();

            if(resultSet.next()) userCredential = new UserCredential(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            );


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
        return userCredential;
    }

    @Override
    public void updateUserCredentialById(int id) {

        System.out.println("Not implemented");
    }

    public void updateUserCredentialUsername(int id, String username){
        final String SQL = "update user_credentials set username = ? where id = ?";

        try(
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL)
        ){

            statement.setString(1, username);
            statement.setInt(2, id);
            statement.execute();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateUserCredentialFirstname(int id, String firstname){
        final String SQL = "update user_credentials set first_name = ? where id = ?";

        try(
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL)
        ){

            statement.setString(1, firstname);
            statement.setInt(2, id);
            statement.execute();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateUserCredentialLastname(int id, String lastname){
        final String SQL = "update user_credentials set last_name = ? where id = ?";

        try(
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL)
        ){

            statement.setString(1, lastname);
            statement.setInt(2, id);
            statement.execute();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void updateUserCredentialPassword(int id, String password){
        final String SQL = "update user_credentials set password = ? where id = ?";

        try(
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL)
        ){

            statement.setString(1, password);
            statement.setInt(2, id);
            statement.execute();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public void deleteUserCredentialById(int id) {

        final String SQL = "delete * from user_credentials where id = ?";

        try(Connection connection = ConnectionFactory.getConnection();
        PreparedStatement statement = connection.prepareStatement(SQL)) {

            statement.setInt(1, id);
            statement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public void addUserCredential(UserCredential user) {

        final String SQL = "insert into user_credentials values( default, ?, ?, ?, ?)";

        try(
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL);
                ) {

            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFirstName());
            statement.setString(4, user.getLastName());

            statement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    @Override
    public boolean readUserCredentials(int id) throws SQLException {
        boolean success = false;

        final String SQL = "select * from user_credentials where id = ?";

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

    public UserCredential getUserCredentialById(int userId) {
        UserCredential userCredential = null;

        final String SQL = "select * from user_credentials where id = ?";

        ResultSet resultSet = null;
        try(
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL)

        ){
            statement.setInt(1, userId);

            resultSet = statement.executeQuery();

            if(resultSet.next()) userCredential = new UserCredential(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            );


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
        return userCredential;
    }
}
