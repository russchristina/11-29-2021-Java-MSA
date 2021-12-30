package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Employee {
	private String userName;
	private String passWord;
	private boolean  isLoggedIn = false;
	
	public void getEmployeeLoginDetails() {
		
        Scanner input = new Scanner(System.in);
        System.out.println("<<Welcome Back>>");
       
        String jdbcURL = "";
		String username = "";
		String password = "";
		
		try {
			Connection connection = DriverManager.getConnection(jdbcURL, username, password);
			Statement stmt = connection.createStatement();
			Scanner scan = new Scanner(System.in);
			System.out.println("Enter your username:");
			String s1 = scan.next();
			System.out.println("Enter your password:");
			String s2 = scan.next();
			
			ResultSet rs = stmt.executeQuery("SELECT * FROM employee where username='" + s1 + "' and passwords='" + s2 + "'");
			if (rs.next())
				System.out.println("Welcome back employee:" + s1); 
			else
				System.out.println("Invalid credentials");
	
			connection.close();
		} catch (SQLException e) {
			System.out.println("error in connecting");
			e.printStackTrace();
		}
    }
	

}
