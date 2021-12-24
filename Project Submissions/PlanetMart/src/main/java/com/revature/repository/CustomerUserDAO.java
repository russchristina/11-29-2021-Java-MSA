package com.revature.repository;

import com.revature.models.accounts.CustomerAccount;
import com.revature.models.shop.Inventory;
import com.revature.models.users.User;
import com.revature.repository.DAOInterface.CustomerUsersDAOInterface;
import com.revature.utility.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerUserDAO implements CustomerUsersDAOInterface {

    @Override
    public List<User> getAllCustomerUsers() {

        List<User> customerUsers = new ArrayList<>();

        final String SQL = "select * from customer_users";

        try(Connection connection = ConnectionFactory.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL)
        ){
            while(resultSet.next()){
                customerUsers.add(
                        new User(
                                resultSet.getInt(1),
                                resultSet.getString(2),
                                resultSet.getInt(3),
                                resultSet.getInt(4)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return customerUsers;
    }

    @Override
    public List<User> getAllUsersByCustomerId(int customerAccountId) {
        List<User> customerAccountUsers = new ArrayList<>();

        final String SQL = "select * from customer_users where customer_account_id = ?";

        ResultSet resultSet = null;
        try(Connection connection = ConnectionFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL)
        ){

            statement.setInt(1, customerAccountId);
            resultSet = statement.executeQuery();

            while(resultSet.next()){
                customerAccountUsers.add(
                        new User(
                                resultSet.getInt(1),
                                resultSet.getString(2),
                                resultSet.getInt(3),
                                resultSet.getInt(4)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return customerAccountUsers;
    }

    @Override
    public User getUserById(int id) {
       User user = null;

        final String SQL = "select * from customer_users where user_id = ?";

        ResultSet resultSet = null;
        try(
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL)

        ){
            statement.setInt(1, id);

            resultSet = statement.executeQuery();

            if(resultSet.next()) user = new User(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getInt(3),
                    resultSet.getInt(4)
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
        return user;
    }

    @Override
    public void updateUserName(int userId, String newName) {
        final String SQL = "update customer_users set user_name = ? where user_id = ?";

        try(
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL)
        ){

            statement.setString(1, newName);
            statement.setInt(2, userId);
            statement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }



    @Override
    public void deleteUserById(int id) {
        final String SQL = "delete * from customer_users where user_id = ?";

        try(Connection connection = ConnectionFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL)) {

            statement.setInt(1, id);
            statement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void addUser(String name, Inventory inventory, CustomerAccount customerAccount) {
        final String SQL = "insert into customer_users values( default, ?, ?, ?";

        try(
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL)
        ) {

            statement.setString(1, name);
            statement.setInt(2, inventory.getId());
            statement.setInt(3, customerAccount.getCustomerAccountId());
            statement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
