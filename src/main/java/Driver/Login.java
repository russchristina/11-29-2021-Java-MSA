package Driver;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.revature.model.User;
import com.revature.util.ConnectionClosers;
import com.revature.util.ConnectionFactory;

public class Login {
	Scanner sc = new Scanner(System.in);
	public  void login() {
		//String SQL = "select*From Customers where customer_username =? and customer_userpassword =?";
		User user = new User();
		String SQL = "select customer_username, customer_userpassword From Customers";
		String username;
		String password;
		System.out.println("Please Enter your username");
		username = sc.nextLine();
		System.out.println("Please Enter your password");
		password = sc.nextLine();
		System.out.println("Attempting to Login");
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet set = null;
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.prepareStatement(SQL);
			
			set= stmt.executeQuery();
		if(set.next()){
			user = new User(  set.getString(1), set.getString(2)
					);
					
			
		}
		if(username.equals(set.getString("customer_username"))) {
			if(password.equals(set.getString("customer_userpassword"))) {
				System.out.println("Logged in");
				
			}
		}else {
			System.out.println("Incorrect Login");
			
		}
	}catch(SQLException e) {
		e.printStackTrace();
	}finally {
		ConnectionClosers.closeConnection(conn);
		ConnectionClosers.closeStatement(stmt);
		ConnectionClosers.closeResultSet(set);}

	}
}