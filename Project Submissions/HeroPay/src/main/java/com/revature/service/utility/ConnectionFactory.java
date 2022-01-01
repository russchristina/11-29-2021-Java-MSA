package com.revature.service.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    public static Connection getConnection() throws SQLException {

        Connection connection = DriverManager.getConnection(
                System.getenv("db_url"),
                System.getenv("db_username"),
                System.getenv("db_password"));

        connection.setSchema("project_1");
        return connection;
    }

}
