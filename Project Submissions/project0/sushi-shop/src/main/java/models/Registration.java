package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Registration {

		private String firstName;
		private String lastName;
		private String userName;
		private String passWord;
		
		public void getUserDetails() {
	        Scanner input = new Scanner(System.in);
	        System.out.println("Enter your first Name");
	        firstName = input.nextLine();
	        System.out.println("Enter your last name");
	        lastName = input.nextLine();
	        System.out.println("Enter your username");
	       userName = input.nextLine();
	        System.out.println("Enter the password");
	        passWord = input.nextLine();
	    }
		
		public void sendUserDetails() {
		
			String jdbcURL = "";
			String username = "";
			String password = "";
			
			try {
				Connection connection = DriverManager.getConnection(jdbcURL, username, password);
				
				
				String sql = "INSERT INTO users VALUES (?,?,?,?)";
				PreparedStatement stmt = connection.prepareStatement(sql);
				stmt.setString(1, firstName);
				stmt.setString(2, lastName);
				stmt.setString(3, userName);
				stmt.setString(4, passWord);
				int x = stmt.executeUpdate();
				System.out.println("Thank you for joining");
				connection.close();
			} catch (SQLException e) {
				System.out.println("error in connecting");
				e.printStackTrace();
			}
			
			
			
			
			
		}	
			
			
			
			
			
		}
		
	
	


