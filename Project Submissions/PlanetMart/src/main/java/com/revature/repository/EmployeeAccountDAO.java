package com.revature.repository;

import com.revature.models.accounts.EmployeeAccount;
import com.revature.repository.DAOInterface.EmployeeAccountDAOInterface;
import com.revature.utility.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeAccountDAO implements EmployeeAccountDAOInterface {

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
            throwables.printStackTrace();
        }

        return employeeAccounts;

    }

    @Override
    public EmployeeAccount getEmployeeAccountsById(int id) {
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
            throwables.printStackTrace();
        } finally {
            try {
                assert resultSet != null;
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return employeeAccount;
    }


    @Override
    public void updateEmployeeAdmin(int employeeId, int adminId) {
        final String SQL = "update employee_account set admin_id = ? where employee_id = ?";

        try(
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL)
        ){

            statement.setInt(1, adminId);
            statement.setInt(2, employeeId);
            statement.execute();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void updateEmployeeAccountUser(int employeeId, int userId) {
        final String SQL = "update employee_account set user_id = ? where employee_id = ?";

        try(
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL)
        ){

            statement.setInt(1, userId);
            statement.setInt(2, employeeId);
            statement.execute();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void deleteEmployeeAccountById(int id) {
        final String SQL = "delete * employee_account where employee_id = ?";

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
    public void addEmployeeAccount(int userId, int adminId) {
        final String SQL = "insert into employee_account values( default, ?, ?";

        try(
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL)
        ) {
            statement.setInt(1, userId);
            statement.setInt(2, adminId);
            statement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
