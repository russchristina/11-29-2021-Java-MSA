package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	
	/*
	 * Because we don't want to write the same code several times thoughout our application,
	 * we will create a utility class for easily grabbing connections to our database.
	 * 
	 * That said, let's define a method that returns a new connection on each call.
	 */
	
	public static Connection getConnection() throws SQLException {
		
		return DriverManager.getConnection(
				System.getenv("db_url"), 
				System.getenv("db_username"),
				System.getenv("db_password")
				);
	}
}
