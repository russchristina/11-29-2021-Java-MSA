package dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OpenDB {

    public static Connection getConnection()throws SQLException {
    Connection connection = DriverManager.getConnection(
            System.getenv("db_url"),
            System.getenv("db_username"),
            System.getenv("db_password"));
            connection.setSchema("project_one");
            return connection;
}
}
