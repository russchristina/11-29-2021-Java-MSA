package com.revature.repository;

import com.revature.models.accounts.EmployeeAccount;
import com.revature.repository.DAOInterface.EmployeeAccountDAOInterface;
import com.revature.repository.Exception.InvalidAdminIdException;
import com.revature.repository.Exception.InvalidEmployeeAccountIdException;
import com.revature.repository.Exception.InvalidUserCredentialException;
import com.revature.repository.Exception.InvalidUserIdException;
import com.revature.utility.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeAccountDAO implements EmployeeAccountDAOInterface {

    private final Logger debugLogger = LoggerFactory.getLogger("debugLogger");

    @Override
    public List<EmployeeAccount> getAllEmployeeAccounts() {
        List<EmployeeAccount> employeeAccounts = new ArrayList<>();

        final String SQL = "select * from employee_account";

        try(Connection connection = ConnectionFactory.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL)
        ){
            while(resultSet.next()){
                employeeAccounts.add(
                        new EmployeeAccount(
                                resultSet.getInt(1),
                                resultSet.getInt(2),
                                resultSet.getInt(3)));
            }
        } catch (SQLException throwables) {
            debugLogger.debug(throwables.toString());
        }

        return employeeAccounts;

    }

    @Override
    public EmployeeAccount getEmployeeAccountByEmployeeId(int id) {
        EmployeeAccount employeeAccount = null;

        final String SQL = "select * from employee_account where employee_id = ?";

        ResultSet resultSet = null;
        try(
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL)

        ){
            statement.setInt(1, id);

            resultSet = statement.executeQuery();

            if(resultSet.next()) employeeAccount = new EmployeeAccount(
                    resultSet.getInt(1),
                    resultSet.getInt(2),
                    resultSet.getInt(3)
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
        return employeeAccount;
    }

    @Override
    public EmployeeAccount getEmployeeAccountsByUserId(int id) throws SQLException, InvalidUserCredentialException {
        UserCredentialsDAO userCredentialsDAO = new UserCredentialsDAO();
        if(!userCredentialsDAO.readUserCredentials(id)) throw new InvalidUserCredentialException("Invalid User Credential ID");

        EmployeeAccount employeeAccount = null;

        final String SQL = "select * from employee_account where user_id = ?";

        ResultSet resultSet = null;
        try(
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL)

        ){
            statement.setInt(1, id);

            resultSet = statement.executeQuery();

            if(resultSet.next()) employeeAccount = new EmployeeAccount(
                    resultSet.getInt(1),
                    resultSet.getInt(2),
                    resultSet.getInt(3)
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
        return employeeAccount;
    }


    @Override
    public void updateEmployeeAdmin(int employeeId, int adminId) throws InvalidEmployeeAccountIdException, InvalidAdminIdException {
        final String SQL = "update employee_account set admin_id = ? where employee_id = ?";

        if(!checkEmployeeId(employeeId)) throw new InvalidEmployeeAccountIdException("Invalid Employee Account Id");

        try(
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL)
        ){

            statement.setInt(1, adminId);
            statement.setInt(2, employeeId);
            statement.execute();


        } catch (SQLException throwables) {
            debugLogger.debug(throwables.toString());
        }
    }

    private boolean checkAdminId(int adminId) {
        final String SQL = "select * from employee_account where admin_id = ?";
        boolean success = false;
        ResultSet resultSet = null;
        try(Connection connection = ConnectionFactory.getConnection();
        PreparedStatement statement = connection.prepareStatement(SQL)){
            statement.setInt(1, adminId);
            resultSet = statement.executeQuery();

            if(resultSet.next()) success = true;
        } catch (SQLException throwables) {
            debugLogger.debug(throwables.toString());
        }

        return success;
    }

    @Override
    public void updateEmployeeAccountUser(int employeeId, int userId) throws InvalidEmployeeAccountIdException, SQLException, InvalidUserCredentialException {
        UserCredentialsDAO userCredentialsDAO = new UserCredentialsDAO();
        if(!userCredentialsDAO.readUserCredentials(userId)) throw new InvalidUserCredentialException("Invalid User Credential Id");
        if(!checkEmployeeId(employeeId)) throw new InvalidEmployeeAccountIdException("Invalid Employee Account Id");

        final String SQL = "update employee_account set user_id = ? where employee_id = ?";

        try(
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL)
        ){

            statement.setInt(1, userId);
            statement.setInt(2, employeeId);
            statement.execute();


        } catch (SQLException throwables) {
            debugLogger.debug(throwables.toString());
        }
    }

    @Override
    public void deleteEmployeeAccountById(int id) throws InvalidEmployeeAccountIdException {
        if(!checkEmployeeId(id)) throw new InvalidEmployeeAccountIdException("Invalid Employee Account Id");
        final String SQL = "delete * employee_account where employee_id = ?";

        try(
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL)
        ){

            statement.setInt(1, id);
            statement.execute();


        } catch (SQLException throwables) {
            debugLogger.debug(throwables.toString());
        }
    }

    @Override
    public void addEmployeeAccount(int userId, int adminId) throws SQLException, InvalidAdminIdException, InvalidUserCredentialException {
        UserCredentialsDAO userCredentialsDAO = new UserCredentialsDAO();
        if(!userCredentialsDAO.readUserCredentials(userId)) throw new InvalidUserCredentialException("Invalid User Credential ID");
        if(!checkAdminId(adminId)) throw new InvalidAdminIdException("Invalid Admin ID");

        final String SQL = "insert into employee_account values( default, ?, ?)";

        try(
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL)
        ) {
            statement.setInt(1, userId);
            statement.setInt(2, adminId);
            statement.execute();

        } catch (SQLException throwables) {
            debugLogger.debug(throwables.toString());
        }
    }

    public boolean checkEmployeeId(int employeeId){
        boolean success = false;

        EmployeeAccount employeeAccount = null;
        try{
            employeeAccount = getEmployeeAccountByEmployeeId(employeeId);
        }catch(Exception e){
            debugLogger.debug(e.toString());
        }
        if(employeeAccount != null) success = true;
        return success;
    }
}
