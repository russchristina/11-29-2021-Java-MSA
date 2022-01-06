package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectUtil {

public static Connection getConnection() throws SQLException {
		
		return DriverManager.getConnection(
				"jdbc:postgresql://database-demo.chxg5v0vyn1y.us-east-2.rds.amazonaws.com:"
				+ "5432/postgres?currentSchema=greendale", "postgres", "$t4rw4r5"
				);
				
				
				
				

				
	}
}
//System.getenv("db_url"),
//System.getenv("db_username"),
//System.getenv("db_password")