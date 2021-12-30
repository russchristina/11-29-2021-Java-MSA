package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ContactProgram {

	public static void main(String[] args) {
		String jdbcURL = "";
		String username = "";
		String password = "";
		
		try {
			Connection connection = DriverManager.getConnection(jdbcURL, username, password);
			System.out.println("connected to PostgreSQL server");
			connection.close();
		} catch (SQLException e) {
			System.out.println("error in connecting");
			e.printStackTrace();
		}
		
		
		
		
		
	}

}
