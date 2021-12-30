package models;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;



public class Employeefunction {
	private String firstName;
	private String lastName;
	private String userName;
	private String passWord;
	private String bankMoney;
	private String sql = "";
	int choice = 0;
	private int updateBank;
	
		
	
public void viewAccount() {
	String jdbcURL = "";
	String username = "";
	String password = "";
				
				try {
					Connection connection = DriverManager.getConnection(jdbcURL, username, password);
					System.out.println("Enter customer username you would like view info about");
					Scanner input = new Scanner(System.in);
					userName = input.nextLine();
				
					sql ="select*from customer where username=?";
					PreparedStatement stmt = connection.prepareStatement(sql);
					stmt.setString(1,userName );
					ResultSet rs = stmt.executeQuery();
					if(rs.next()==false)
			        {
						System.out.println("no user exists");
			        }else
			        {
			        	System.out.println("first name:" + rs.getString(1));
			        	System.out.println("last name:" + rs.getString(2));
			        	System.out.println("username:" + rs.getString(3));
			        	System.out.println("password:" + rs.getString(4));
			        }
					connection.close();
				} catch (SQLException e) {
					System.out.println("error in connecting");
					e.printStackTrace();
				}
}
	
	
public void viewfunds() {
	
	String jdbcURL = "";
	String username = "";
	String password = "";
				
				try {
					Connection connection = DriverManager.getConnection(jdbcURL, username, password);
					System.out.println("Enter customer username you would like view info about");
					Scanner input = new Scanner(System.in);
					userName = input.nextLine();
				
					sql ="select*from customer where username=?";
					PreparedStatement stmt = connection.prepareStatement(sql);
					stmt.setString(1,userName );
					ResultSet rs = stmt.executeQuery();
					if(rs.next()==false)
			        {
						System.out.println("no user exists");
			        }else
			        {
			        	System.out.println("balance:$" + rs.getString(5));
			        }
					connection.close();
				} catch (SQLException e) {
					System.out.println("error in connecting");
					e.printStackTrace();
				}
		
	}
public void viewInfo() {
	
	
	String jdbcURL = "";
	String username = "";
	String password = "";
				
				try {
					Connection connection = DriverManager.getConnection(jdbcURL, username, password);
					System.out.println("Enter username you would like view info about");
					Scanner input = new Scanner(System.in);
					userName = input.nextLine();
				
					sql ="select*from users where username=?";
					PreparedStatement stmt = connection.prepareStatement(sql);
					stmt.setString(1,userName );
					ResultSet rs = stmt.executeQuery();
					if(rs.next()==false)
			        {
						System.out.println("no user exists");
			        }else
			        {
			        	System.out.println("first name:" + rs.getString(1));
			        	System.out.println("last name:" + rs.getString(2));
			        	System.out.println("username:" + rs.getString(3));
			        	System.out.println("password:" + rs.getString(4));
			        }
					connection.close();
				} catch (SQLException e) {
					System.out.println("error in connecting");
					e.printStackTrace();
				}
	
	
	
	
}
public void deleteUser() {
	String jdbcURL = "";
	String username = "";
	String password = "";
				
				try {
					Connection connection = DriverManager.getConnection(jdbcURL, username, password);
					System.out.println("Enter user you would like to delete");
					Scanner input = new Scanner(System.in);
					userName = input.nextLine();
				
					sql = "delete from users where username=?";
					PreparedStatement stmt = connection.prepareStatement(sql);
					stmt.setString(1,userName );
					int x = stmt.executeUpdate();
					if(x>0)
			        {
						System.out.println("user deleted.");
			        }else
			        {
			            System.out.println("failed to delete");
			        }
					connection.close();
				} catch (SQLException e) {
					System.out.println("error in connecting");
					e.printStackTrace();
				}
}
	
	
	
}
