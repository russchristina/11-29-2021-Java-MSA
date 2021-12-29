package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.Employee;
import com.revature.util.ConnectionClosers;
import com.revature.util.ConnectionFactory;

public class EmployeeRepositoryImplement implements EmployeeRepository {

    @Override
    public void save(Employee employee) {
        final String SQL = "insert into ingredient values(default, ?, ?)";
        Connection conn = null;
        PreparedStatement stmt = null; // use questions marks as placeholders since it's a prepared statment.

        try {
            conn = ConnectionFactory.getConnection();
            /*
             * The values of the parameters in a PreparedStatement are supplied later after
             * the statement has been compiled.
             */
            stmt = conn.prepareStatement(SQL);
            stmt.setInt(1, employee.getEmployee_id());
            stmt.setString(2, employee.getEmployee_user());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionClosers.closeConnection(conn);
            ConnectionClosers.closeStatement(stmt);
        }
    }

    @Override
    public Employee findById(int id) {
        Employee employee = null;
        final String SQL = "select ingredient_name from ingredient where ingredient_id = " + id;
        Connection conn = null;
        Statement stmt = null;
        ResultSet set = null;

        try {
            conn = ConnectionFactory.getConnection();
            stmt = conn.createStatement();
            set = stmt.executeQuery(SQL);

            if (set.next()) {
                employee = new Employee(set.getInt(1),
                        set.getString(2),
                        set.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionClosers.closeConnection(conn);
            ConnectionClosers.closeResultSet(set);
            ConnectionClosers.closeStatement(stmt);
        }

        return employee;

    }

    @Override
    public Employee findByName(String name) {
        return null;
    }

    @Override
    public List<Employee> findAll() {
        List<Employee> employee = new ArrayList<>();

        final String SQL = "select * from ingredient";
        Connection connection = null;
        Statement stmt = null;
        ResultSet set = null;

        try {

            connection = ConnectionFactory.getConnection();

            stmt = connection.createStatement();

            set = stmt.executeQuery(SQL);

            while (set.next()) {
                employee.add(
                        new Employee(
                                set.getInt(1),
                                set.getString(2),
                                set.getString(3)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionClosers.closeConnection(connection);
            ConnectionClosers.closeResultSet(set);
            ConnectionClosers.closeStatement(stmt);
        }

        return employee;

    }

    @Override
    public void update(Employee employee) {

    }

    @Override
    public void delete(Employee employee) {

    }

    @Override
    public String retrievePassword(String employee_name) {

        String user = null;
        final String SQL = "select employee_password from employee where employee_user = '" + employee_name + "'";
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
    public String retrieveEmployee(String employee_user) {

        String user = null;
        final String SQL = "select employee_user from employee where employee_user = '" + employee_user + "'";
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
