package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Accounting {
	private String firstName;
	private String lastName;
	private String userName;
	private String passWord;
	private String bankMoney;
	private String sql = "";
	int choice = 0;
	private int updateBank;
	

public void addFunds() {	
String jdbcURL = "";
String username = "";
String password = "";
			
			try {
				Connection connection = DriverManager.getConnection(jdbcURL, username, password);
				System.out.println("RE-Enter your username:");
				Scanner input = new Scanner(System.in);
				userName = input.nextLine();
				System.out.println("add new amount:");
				bankMoney = input.nextLine();
			
				sql ="update customer set bank=?where username=?";
				PreparedStatement stmt = connection.prepareStatement(sql);
				stmt.setString(1,bankMoney );
				stmt.setString(2, userName);
				int x = stmt.executeUpdate();
				if(x>0)
		        {
					System.out.println("updated balance.");
		        }else
		        {
		            System.out.println("failed to update");
		        }
				connection.close();
			} catch (SQLException e) {
				System.out.println("error in connecting");
				e.printStackTrace();
			}
    
  
    
}
public void removeFunds() {	
	String jdbcURL = "";
	String username = "";
	String password = "";
				
				try {
					Connection connection = DriverManager.getConnection(jdbcURL, username, password);
					System.out.println("RE-Enter your username:");
					Scanner input = new Scanner(System.in);
					userName = input.nextLine();
					bankMoney = "0";
				
					sql ="update customer set bank =? where username=?";
					PreparedStatement stmt = connection.prepareStatement(sql);
					stmt.setString(1,bankMoney );
					stmt.setString(2, userName);
					int x = stmt.executeUpdate();
					if(x>0)
			        {
						System.out.println("removed funds.");
			        }else
			        {
			            System.out.println("failed to update");
			        }
					connection.close();
				} catch (SQLException e) {
					System.out.println("error in connecting");
					e.printStackTrace();
				}
	    
}
public void trasnferFunds() {	
	String jdbcURL = "";
	String username = "";
	String password = "";
				
				try {
					Connection connection = DriverManager.getConnection(jdbcURL, username, password);
					System.out.println("What is the username you would like to trasnsfer funds to?");
					Scanner input = new Scanner(System.in);
					userName = input.nextLine();
					System.out.println("amount you would like to transfer:");
					bankMoney = input.nextLine();
				
					sql ="update customer set bank =? where username=?";
					PreparedStatement stmt = connection.prepareStatement(sql);
					stmt.setString(1,bankMoney );
					stmt.setString(2, userName);
					int x = stmt.executeUpdate();
					if(x>0)
			        {
						System.out.println("money trasnfer was passed.");
			        }else
			        {
			            System.out.println("failed to update");
			        }
					connection.close();
				} catch (SQLException e) {
					System.out.println("error in connecting");
					e.printStackTrace();
				}
	    
}
	
}
	
	

