package com.revature.repository.DAOClasses;

import com.revature.repository.DAOInteface.EmployeeAccountInterface;
import com.revature.repository.DTO.EmployeeAccountEntity;
import com.revature.repository.DTO.EmployeeRoleEntity;
import com.revature.service.utility.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeAccountDao implements EmployeeAccountInterface {

    private final Logger logger = LoggerFactory.getLogger(LoginInfoDao.class);

    @Override
    public EmployeeAccountEntity insertEmployeeAccount(String firstName, String lastName, int roleId) throws SQLException {
        final String SQL = "INSERT INTO employee_account values(default, ? , ?, ?) returning * " ;

        EmployeeAccountEntity employeeAccountEntity = null;
        ResultSet result;

        try(
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL)
                ){

            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setInt(3, roleId);
            result = statement.executeQuery();

            if(result.next()) employeeAccountEntity = new EmployeeAccountEntity(
                    result.getInt(1),
                    result.getString(2),
                    result.getString(3),
                    result.getInt(4)
            );
        }
        return employeeAccountEntity;
    }

    @Override
    public EmployeeRoleEntity insertEmployeeRole(String roleName) throws SQLException {
        final String SQL = "INSERT INTO employee_role values(default, ?) returning *" ;

        EmployeeRoleEntity employeeRoleEntity = null;
        ResultSet result;

        try(
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL)
        ){

            statement.setString(1, roleName);
            result = statement.executeQuery();

            if(result.next()) employeeRoleEntity = new EmployeeRoleEntity(
                    result.getInt(1),
                    result.getString(2)
            );
        }
        return employeeRoleEntity;
    }

    @Override
    public EmployeeAccountEntity getEmployeeAccount(int employeeId) throws SQLException {
        final String SQL = "SELECT * FROM employee_account WHERE id = ?";

        EmployeeAccountEntity employeeAccountEntity = null;
        ResultSet result;

        try(
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL)
                ){
            statement.setInt(1, employeeId);
            result = statement.executeQuery();
            if(result.next()) employeeAccountEntity = new EmployeeAccountEntity(
                    result.getInt(1),
                    result.getString(2),
                    result.getString(3),
                    result.getInt(4)
            );
        }
        return employeeAccountEntity;
    }

    @Override
    public List<EmployeeAccountEntity> getAllEmployeeAccountList() throws SQLException {
        final String SQL = "SELECT * FROM employee_account";

        List<EmployeeAccountEntity> employeeAccountEntityList = new ArrayList<>();
        ResultSet result;

        try(
                Connection con = ConnectionFactory.getConnection();
                PreparedStatement statement = con.prepareStatement(SQL)
        ){
            result = statement.executeQuery();
            while(result.next()) employeeAccountEntityList.add(
                    new EmployeeAccountEntity(
                            result.getInt(1),
                            result.getString(2),
                            result.getString(3),
                            result.getInt(4)));
        }
        return employeeAccountEntityList;
    }

    @Override
    public List<EmployeeAccountEntity> getEmployeeAccountsByRoleList(int roleId) throws SQLException {
        final String SQL = "SELECT * FROM employee_account WHERE role = ?";
        List<EmployeeAccountEntity> employeeAccountEntityList = new ArrayList<>();
        ResultSet result;

        try(
                Connection con = ConnectionFactory.getConnection();
                PreparedStatement statement = con.prepareStatement(SQL)
        ){
            statement.setInt(1, roleId);
            result = statement.executeQuery();
            while(result.next()) employeeAccountEntityList.add(
                    new EmployeeAccountEntity(
                            result.getInt(1),
                            result.getString(2),
                            result.getString(3),
                            result.getInt(4)));
        }
        return employeeAccountEntityList;
    }

    @Override
    public EmployeeRoleEntity getEmployeeRoleById(int id) throws SQLException {
        final String SQL = "SELECT * FROM employee_role WHERE id = ?" ;

        EmployeeRoleEntity employeeRoleEntity = null;
        ResultSet result;

        try(
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL)
        ){

            statement.setInt(1, id);
            result = statement.executeQuery();

            if(result.next()) employeeRoleEntity = new EmployeeRoleEntity(
                    result.getInt(1),
                    result.getString(2)
            );
        }
        return employeeRoleEntity;
    }

    @Override
    public EmployeeRoleEntity getEmployeeRoleByName(String roleName) throws SQLException {
        final String SQL = "SELECT * FROM employee_role WHERE role_name = ?" ;

        EmployeeRoleEntity employeeRoleEntity = null;
        ResultSet result;

        try(
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL)
        ){

            statement.setString(1, roleName);
            result = statement.executeQuery();

            if(result.next()) employeeRoleEntity = new EmployeeRoleEntity(
                    result.getInt(1),
                    result.getString(2)
            );
        }
        return employeeRoleEntity;
    }

    @Override
    public Map<Integer, String> getEmployeeRoleMap() throws SQLException {
        final String SQL = "SELECT * FROM employee_role";

        Map<Integer, String> employeeRoleMap = new HashMap<>();

        ResultSet result;

        try(
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL)
                ){
            result = statement.executeQuery();

            while(result.next()) employeeRoleMap.put(result.getInt(1), result.getString(2));

        }

        return employeeRoleMap;
    }

    @Override
    public EmployeeAccountEntity updateEmployeeRole(int employeeId, int roleId) throws SQLException {

        final String SQL = "UPDATE employee_account SET role = ? WHERE id = ? RETURNING *";

        EmployeeAccountEntity entity = null;
        ResultSet result;

        try(
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL)
                ){
            statement.setInt(1, roleId);
            statement.setInt(2, employeeId);
            result = statement.executeQuery();

            if(result.next()) entity = new EmployeeAccountEntity(
                    result.getInt(1),
                    result.getString(2),
                    result.getString(3),
                    result.getInt(4)
            );

        }
        return entity;
    }

    @Override
    public EmployeeRoleEntity updateRole(int roleId, String roleName) throws SQLException {
        final String SQL = "UPDATE employee_role SET role_name = ? WHERE id = ? RETURNING *";

        EmployeeRoleEntity entity = null;
        ResultSet result;

        try(
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL)
        ){
            statement.setString(1, roleName);
            statement.setInt(2, roleId);
            result = statement.executeQuery();

            if(result.next()) entity = new EmployeeRoleEntity(
                    result.getInt(1),
                    result.getString(2)
            );

        }
        return entity;
    }
}
