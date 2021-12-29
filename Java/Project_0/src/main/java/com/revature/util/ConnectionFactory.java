package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	public static Connection getConnection() throws SQLException {
		
		return DriverManager.getConnection(
				System.getenv("db_url"), 
				System.getenv("db_username"),
				System.getenv("db_password")
				);
	}
}