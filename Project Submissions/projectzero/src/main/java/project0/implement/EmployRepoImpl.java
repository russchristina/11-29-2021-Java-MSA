package project0.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import project0.models.User;
import project0.repos.EmployRepo;
import project0.util.ConnectCloser;
import project0.util.ConnectUtil;

public class EmployRepoImpl implements EmployRepo {

	@Override
	public User findByUsername(String username) {
		User user= null;
		final String SQL = "select * from users where user_name = '" + username + "'";
		Connection conn = null;
		Statement stmt = null;
		ResultSet set = null;
		
		try {
			conn = ConnectUtil.getConnection();
			stmt = conn.createStatement();
			set = stmt.executeQuery(SQL);
			
			if(set.next()) {
				user = new User(set.getString(1),
						set.getString(2),
						set.getString(3),
						set.getInt(4),
						set.getInt(5),
						set.getString(6),
						set.getString(7),
						set.getString(8),
						set.getString(9),
						set.getInt(10)
						);
									}
		} catch (SQLException e) {
			System.out.println("No User found.  Please check the Username.");
			
		}	finally {
			ConnectCloser.closeConnection(conn);
			ConnectCloser.closeStatement(stmt);
			ConnectCloser.closeResultSet(set);
		}
		return user;		
	}

	@Override
	public User findByName(String fullName) {
		User user = null;
		final String SQL = "select * from users where user_fullname = '" + fullName + "'";
		Connection conn = null;
		Statement stmt = null;
		ResultSet set = null;
		
		try {
			conn = ConnectUtil.getConnection();
			stmt = conn.createStatement();
			set = stmt.executeQuery(SQL);
			
			if(set.next()) {
				user = new User(set.getString(1),
						set.getString(2),
						set.getString(3),
						set.getInt(4),
						set.getInt(5),
						set.getString(6),
						set.getString(7),
						set.getString(8),
						set.getString(9),
						set.getInt(10));
									}
	} catch (SQLException e) {
		System.out.println("No User found.  Please check the Name.");
	}	finally {
		ConnectCloser.closeConnection(conn);
		ConnectCloser.closeStatement(stmt);
		ConnectCloser.closeResultSet(set);
	}
	return user;
	}

	@Override
	public List<User> findAll() {
List<User> users = new ArrayList<>();
		
		final String SQL = "select * from users";
		Connection conn = null;
		Statement stmt = null;
		ResultSet set = null;
		
		try {
			conn = ConnectUtil.getConnection();
			stmt = conn.createStatement();
			set = stmt.executeQuery(SQL);
			while(set.next()) {
				users.add(new User(set.getString(1),
						set.getString(2),
						set.getString(3),
						set.getInt(4),
						set.getInt(5),
						set.getString(6),
						set.getString(7),
						set.getString(8),
						set.getString(9),
						set.getInt(10)
						));
			}
		} catch (SQLException e) {
			System.out.println("No records found.");
		}	finally {
			ConnectCloser.closeConnection(conn);
			ConnectCloser.closeStatement(stmt);
			ConnectCloser.closeResultSet(set);
		}
			return users;
	}

	@Override
	public void delete(User user) {
		final String SQL = "delete from users where user_name = ?";
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = ConnectUtil.getConnection();
			stmt = conn.prepareStatement(SQL);
			
			stmt.setString(1, user.getUserName()); 
			stmt.execute();
		} catch (SQLException e) {
			System.out.println("No User found.  Please check the Username.");
		}	finally {
			ConnectCloser.closeConnection(conn);
			ConnectCloser.closeStatement(stmt);
		}
		
	}

}
