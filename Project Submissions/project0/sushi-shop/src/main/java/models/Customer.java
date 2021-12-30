package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Customer {
	private String firstName;
	private String lastName;
	private String userName;
	private String passWord;
	private int bankMoney;
	private String sql = "";
	int choice = 0;
	Accounting accounting = new Accounting();
	
	public void loginCustomer() {
		
		
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
			
			ResultSet rs = stmt.executeQuery("SELECT * FROM CUSTOMER where username='" + s1 + "' and passwords='" + s2 + "'");
			if (rs.next())
				System.out.println("Welcome back user:" + s1); 
 
			else
				System.out.println("Invalid credentials");
	
			connection.close();
		} catch (SQLException e) {
			System.out.println("error in connecting");
			e.printStackTrace();
		}
    }
	
	
	public void customerReg() {
		
		  Scanner input = new Scanner(System.in);
	        System.out.println("Enter your first Name");
	        firstName = input.nextLine();
	        System.out.println("Enter your last name");
	        lastName = input.nextLine();
	        System.out.println("Enter your username");
	       userName = input.nextLine();
	        System.out.println("Enter the password");
	        passWord = input.nextLine();	
	        System.out.println("add starting balance");
	        bankMoney = input.nextInt();
	    	
	        String jdbcURL = "jdbc:postgresql://sushiinstance.cmhn9c7pusgl.us-east-2.rds.amazonaws.com:5432/postgres";
			String username = "postgres";
			String password = "Cricket!2";
			
			try {
				Connection connection = DriverManager.getConnection(jdbcURL, username, password);
				
				
				String sql = "INSERT INTO customer VALUES (?,?,?,?,?)";
				PreparedStatement stmt = connection.prepareStatement(sql);
				stmt.setString(1, firstName);
				stmt.setString(2, lastName);
				stmt.setString(3, userName);
				stmt.setString(4, passWord);
				stmt.setInt(5, bankMoney);
				int x = stmt.executeUpdate();
				System.out.println("Thank you for opening a customer account!");
				connection.close();
			} catch (SQLException e) {
				System.out.println("error in connecting");
				e.printStackTrace();
			}
		
		
		
	}
			
		public void customerMenu(){	
			do { 
				System.out.println("");
				System.out.println("");
				System.out.println("1. Add funds");
				System.out.println("2. remove funds");
				System.out.println("3. transfer funds");
				System.out.println("4. logout");

				Scanner sc = new Scanner(System.in);
				choice = sc.nextInt();	
				
				switch(choice)
				{
				case 1:
					accounting.addFunds();
					break;
				case 2:
					accounting.removeFunds();
					break;
				case 3:
					accounting.trasnferFunds();
					accounting.removeFunds();
				case 4:
					break;

default: System.out.println("pick a number between 1-4");
					
				}
				}	
			while(choice!=4);
			System.out.println("thanks for visiting space cowboy");
		}
			
			
		
		
	}
	
	
	
	
	
	

