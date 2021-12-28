package com.revature.repository;

import com.revature.models.accounts.CustomerAccount;
import com.revature.models.users.User;
import com.revature.repository.DAOInterface.CustomerUsersDAOInterface;
import com.revature.repository.Exception.InvalidCustomerAccountIdException;
import com.revature.repository.Exception.InvalidInventoryIdException;
import com.revature.repository.Exception.InvalidUserIdException;
import com.revature.utility.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class CustomerUserDAO implements CustomerUsersDAOInterface {
    private final Logger debugLogger = LoggerFactory.getLogger("debugLogger");

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
            debugLogger.debug(throwables.toString());
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
            debugLogger.debug(throwables.toString());
        }

        return customerAccountUsers;
    }

    @Override
    public User getUserById(int id) throws InvalidUserIdException {
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
            debugLogger.debug(throwables.toString());
        } finally {
            try {
                assert resultSet != null;
                resultSet.close();
            } catch (SQLException e) {
                debugLogger.debug(e.toString());
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
            debugLogger.debug(throwables.toString());
        }
    }


    @Override
    public void deleteUserById(int id) throws InvalidUserIdException {
        final String SQL = "delete from customer_users where user_id = ?";

        try(Connection connection = ConnectionFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL)) {

            statement.setInt(1, id);
            statement.execute();

        } catch (SQLException throwables) {
            debugLogger.debug(throwables.toString());
        }
    }

    public boolean readUserId(int id) {
        boolean success = false;
        User user = null;
        try{
            user = getUserById(id);
        }catch(Exception e){
            debugLogger.debug(e.toString());
        }
        if(user != null) success = true;
        return success;
    }

    @Override
    public void addUser(String name, int inventoryId, int customerAccountId) throws InvalidCustomerAccountIdException, InvalidInventoryIdException {
        final String SQL = "insert into customer_users values( default, ?, ?, ?)";
        InventoryDAO inventoryDAO = new InventoryDAO();
        CustomerAccountDAO customerAccountDAO = new CustomerAccountDAO();
        if (!inventoryDAO.checkInventoryId(inventoryId)) throw new InvalidInventoryIdException("Inventory does not have a valid ID");
        if(!customerAccountDAO.checkCustomerAccountId(customerAccountId)) throw new InvalidCustomerAccountIdException("Invalid Customer Account ID");

        try(
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL)
        ) {

            statement.setString(1, name);
            statement.setInt(2, inventoryId);
            statement.setInt(3, customerAccountId);
            statement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public boolean checkPrimaryUser(int id) {
        CustomerAccountDAO customerAccountDAO = new CustomerAccountDAO();
        List<CustomerAccount> customerAccounts = customerAccountDAO.getAllCustomerAccounts();
        boolean success = false;
        for (CustomerAccount customerAccount : customerAccounts) {
            if(customerAccount.getPrimaryUserId() == id) success = true;
        }
        if(id == 0) success = true;
        return success;
    }
}
