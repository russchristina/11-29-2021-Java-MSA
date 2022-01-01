package com.revature.repository.DAOClasses;

import com.revature.presentation.model.Employee;
import com.revature.repository.DAOInteface.LoginInfoInterface;
import com.revature.repository.DTO.LoginInfoEntity;
import com.revature.service.utility.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginInfoDao implements LoginInfoInterface {

    private final Logger logger = LoggerFactory.getLogger(LoginInfoDao.class);

    @Override
    public LoginInfoEntity insertLoginInfo(String username, String password, int employeeId) throws SQLException {

        final String SQL = "INSERT INTO login_info values(default, ?, ?, ?) RETURNING *";

        LoginInfoEntity lie = null;
        ResultSet rs = null;

        try(
                Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(SQL)
        ){
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setInt(3, employeeId);
            rs = stmt.executeQuery();

            if(rs.next()) lie = new LoginInfoEntity(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getInt(4)
            );
        }
        return lie;
    }

    @Override
    public Employee getEmployeeWithLogin(String username, String password) throws SQLException {

        final String SQL = "SELECT * from employee_details_and_login WHERE username = ? AND password = ?";

        Employee employee = null;
        ResultSet rs = null;

        try(
                Connection con = ConnectionFactory.getConnection();
                PreparedStatement stmt = con.prepareStatement(SQL)
                ){
            stmt.setString(1, username);
            stmt.setString(2, password);
            rs = stmt.executeQuery();

            if(rs.next()) employee = new Employee(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4)
            );
        }

        return employee;
    }

    @Override
    public LoginInfoEntity getLoginInfo(String username, String password) throws SQLException {
        final String SQL = "SELECT * FROM login_info WHERE username = ? AND password = ?";
        LoginInfoEntity loginInfo = null;

        boolean success = false;
        ResultSet rs = null;

        try(
                Connection con = ConnectionFactory.getConnection();
                PreparedStatement stmt = con.prepareStatement(SQL)
        ){
            stmt.setString(1, username);
            stmt.setString(2, password);
            rs = stmt.executeQuery();

            if(rs.next()) loginInfo = new LoginInfoEntity(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getInt(4)
            );
        }

        return loginInfo;
    }

    @Override
    public LoginInfoEntity updateUsername(String newUsername, int employeeId) throws SQLException {
        final String SQL = "UPDATE login_info SET username = ? WHERE employee_id = ? RETURNING *";

        LoginInfoEntity lie = null;
        ResultSet rs = null;

        try(
                Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(SQL)
        ){
            stmt.setString(1, newUsername);
            stmt.setInt(2, employeeId);

            rs = stmt.executeQuery();

            if(rs.next()) lie = new LoginInfoEntity(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getInt(4));

        }

        return lie;
    }

    @Override
    public LoginInfoEntity updatePassword(String newPassword, int employeeId) throws SQLException {
        final String SQL = "UPDATE login_info SET password = ? WHERE employee_id = ? RETURNING *";

        LoginInfoEntity lie = null;
        ResultSet rs = null;

        try(
                Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(SQL)
        ){
            stmt.setString(1, newPassword);
            stmt.setInt(2, employeeId);

            rs = stmt.executeQuery();

            if(rs.next()) lie = new LoginInfoEntity(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getInt(4));

        }

        return lie;
    }

    @Override
    public LoginInfoEntity deleteLoginInfo(String username, int employeeId) throws SQLException {
        final String SQL = "DELETE FROM login_info WHERE username = ? AND employee_id = ? RETURNING *";

        LoginInfoEntity lie = null;
        ResultSet rs = null;

        try(
                Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(SQL)
        ){
            stmt.setString(1, username);
            stmt.setInt(2, employeeId);

            rs = stmt.executeQuery();

            if(rs.next()) lie = new LoginInfoEntity(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getInt(4));

        }

        return lie;
    }
}
