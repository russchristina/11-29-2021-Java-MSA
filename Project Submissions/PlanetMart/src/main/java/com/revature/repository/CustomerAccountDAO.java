package com.revature.repository;

import com.revature.models.accounts.CustomerAccount;
import com.revature.models.shop.Inventory;
import com.revature.repository.DAOInterface.CustomerAccountDAOInterface;
import com.revature.repository.Exception.InvalidCustomerAccountIdException;
import com.revature.repository.Exception.InvalidPrimaryUserException;
import com.revature.repository.Exception.InvalidUserCredentialException;
import com.revature.utility.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerAccountDAO implements CustomerAccountDAOInterface {

    private final Logger debugLogger = LoggerFactory.getLogger("debugLogger");

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
    public List<CustomerAccount> getCustomerAccountsByUserCredentialId(int id) throws InvalidUserCredentialException, SQLException {
        List<CustomerAccount> customerAccounts = new ArrayList<>();
        UserCredentialsDAO userCredentialDAO = new UserCredentialsDAO();
        if(!userCredentialDAO.readUserCredentials(id)) throw new InvalidUserCredentialException("User Credential invalid.");

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
    public List<CustomerAccount> getCustomerAccountsByPrimaryUserId(int id) throws InvalidPrimaryUserException {
        List<CustomerAccount> customerAccounts = new ArrayList<>();
        CustomerUserDAO customerUserDAO = new CustomerUserDAO();

        if(!customerUserDAO.checkPrimaryUser(id)) throw new InvalidPrimaryUserException("Invalid Primary User ID");
        final String SQL = "select * from customer_account where primary_user_id = ?";

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
    public void deleteCustomerAccountById(int id) throws InvalidCustomerAccountIdException {
        final String SQL = "delete from customer_account where customer_account_id = ?";

        if(!readCustomerAccountId(id)) throw new InvalidCustomerAccountIdException("Invalid Customer Account Id");

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

    private boolean readCustomerAccountId(int id) {
        boolean success = false;
        List<CustomerAccount> customerAccounts = getAllCustomerAccounts();
        for (CustomerAccount customerAccount : customerAccounts) {
            if(customerAccount.getCustomerAccountId() == id) success = true;
        }
        return success;
    }

    @Override
    public void addCustomerAccount(int userCredentialId, int primaryUserId) throws InvalidPrimaryUserException, SQLException, InvalidUserCredentialException {
        final String SQL = "insert into customer_account values(default, ?, ?)";
        CustomerUserDAO customerUserDAO = new CustomerUserDAO();
        UserCredentialsDAO userCredentialsDAO = new UserCredentialsDAO();

        if(!customerUserDAO.checkPrimaryUser(primaryUserId)) throw new InvalidPrimaryUserException("Invalid Primary User ID");
        if(!userCredentialsDAO.readUserCredentials(userCredentialId)) throw new InvalidUserCredentialException("User Credential invalid.");


        try(
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL)
        ){
            statement.setInt(1, userCredentialId);
            statement.setInt(2, primaryUserId);
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateCustomerAccountPrimaryId(int customerAccountId, int newUserId) {
        final String SQL = "update customer_account set primary_user_id = ? where customer_account_id = ?";

        try(
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL)
                ){
            statement.setInt(1, newUserId);
            statement.setInt(2, customerAccountId);
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateCustomerAccountUserCredentialId(int customerAccountId, int newUserCredentialId){
        final String SQL = "update customer_account set user_credential_id = ? where customer_account_id = ?";

        try(
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL)
        ){
            statement.setInt(1, newUserCredentialId);
            statement.setInt(2, customerAccountId);
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public CustomerAccount getCustomerAccountById(int parseInt) {
        CustomerAccount customerAccount = null;
        final String SQL = "select * from customer_account where customer_account_id = ?";

        ResultSet resultSet = null;
        try(
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL)
        ){

            statement.setInt(1, parseInt);
            resultSet = statement.executeQuery();

            if(resultSet.next()){
                customerAccount = new CustomerAccount(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getInt(3)
                );
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

        return customerAccount;

    }

    public boolean checkCustomerAccountId(int customerAccountId) {
        boolean success = false;
        CustomerAccount customerAccount = null;
        try{
            customerAccount = getCustomerAccountById(customerAccountId);

        }catch (Exception e){
            debugLogger.debug(e.toString());
        }
        if(customerAccount != null) success = true;
        return success;
    }
}
