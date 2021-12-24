package com.revature.repository;

import com.revature.models.accounts.CustomerAccount;
import com.revature.models.shop.Inventory;
import com.revature.repository.DAOInterface.CustomerAccountDAOInterface;
import com.revature.utility.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerAccountDAO implements CustomerAccountDAOInterface {
    @Override
    public List<CustomerAccount> getAllCustomerAccounts() {

        List<CustomerAccount> customerAccounts = new ArrayList<>();

        final String SQL = "select * from customer_account";

        try(
                Connection connection = ConnectionFactory.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(SQL)
                ){
            while(resultSet.next()){
                customerAccounts.add(new CustomerAccount(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getInt(3)
                ));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return customerAccounts;
    }

    @Override
    public List<CustomerAccount> getCustomerAccountsByUserCredentialId(int id) {
        List<CustomerAccount> customerAccounts = new ArrayList<>();

        final String SQL = "select * from customer_account where user_credential_id = ?";

        ResultSet resultSet = null;
        try(
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL)
        ){

            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            while(resultSet.next()){
                customerAccounts.add(new CustomerAccount(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getInt(3)
                ));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        return customerAccounts;
    }

    @Override
    public List<CustomerAccount> getCustomerAccountsByPrimaryUserId(int id) {
        List<CustomerAccount> customerAccounts = new ArrayList<>();

        final String SQL = "select * from customer_account where user_credential_id = ?";

        ResultSet resultSet = null;
        try(
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL)
        ){

            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            while(resultSet.next()){
                customerAccounts.add(new CustomerAccount(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getInt(3)
                ));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        return customerAccounts;
    }


    @Override
    public void deleteCustomerAccountById(int id) {
        final String SQL = "delete * from customer_account where customer_account = ?";

        try(
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL)
                ){
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void addCustomerAccount(int primaryUserId) {
        final String SQL = "insert into customer_account values(default, ?";

        try(
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL)
        ){
            statement.setInt(1, primaryUserId);
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}