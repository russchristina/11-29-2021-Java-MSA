package Driver;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import com.revature.util.ConnectionClosers;
import com.revature.util.ConnectionFactory;

public class Register {
	public void RegisterAcct() {
		Scanner sc = new Scanner(System.in);
		final String SQL = "insert into Customers(customer_username,customer_userpassword)" +"values(?,?)";
		Connection conn = null;
		PreparedStatement  stmt = null;
		System.out.println("Enter a username");
		String username = sc.nextLine();
		System.out.println("Enter a password");
		String password = sc.nextLine();
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.prepareStatement(SQL);
			stmt.setString(1, username);
			stmt.setString(2, password);
			stmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionClosers.closeConnection(conn);
			ConnectionClosers.closeStatement(stmt);
			}

		}
}
