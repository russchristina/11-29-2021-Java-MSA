package project0.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import project0.util.*;
import project0.Account;
import project0.models.User;
import project0.repos.UserRepo;

public class UserRepoImpl implements UserRepo {

	public void save(User user) {
		
		final String SQL = "insert into users values(?,?,?,?,?,?,?,?,?)";
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = ConnectUtil.getConnection();
			stmt = conn.prepareStatement(SQL);
			
			stmt.setString(1, user.getUserName()); 
			stmt.setString(2, user.getPassword());
			stmt.setString(3, user.getFullName());
			stmt.setInt(4, user.getBirthMonth());
			stmt.setInt(5, user.getBirthDay());
			stmt.setString(6, user.getAddress());
			stmt.setString(7, user.getCity());
			stmt.setString(8, user.getState());
			stmt.setString(9, user.getPhone());
			stmt.execute();
		} catch (SQLException e) {
			
		}	finally {
			ConnectCloser.closeConnection(conn);
			ConnectCloser.closeStatement(stmt);
		}
	}
	

	public User findByUsername(String userName) {
		User user= null;
		final String SQL = "select * from users where user_name = '" + userName + "'";
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
						set.getString(9));
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
						set.getString(9));
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
						set.getString(9)));
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

	public void updatePassword(User password) {
		User user= null;
		final String SQL = "update users set user_pass = ? where user_name = ?";
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = ConnectUtil.getConnection();
			stmt = conn.prepareStatement(SQL);
			stmt.setString(1, user.getPassword()); 
			stmt.setString(2, user.getUserName());
			stmt.execute();
	} catch (SQLException e) {
		System.out.println("No User found.  Please check the Username.");
	}	finally {
		ConnectCloser.closeConnection(conn);
		ConnectCloser.closeStatement(stmt);
	}
	}	


	public void updateAddress(User address) {
		User user= null;
		final String SQL = "update users set user_address = ? where user_name = ?";
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = ConnectUtil.getConnection();
			stmt = conn.prepareStatement(SQL);
			stmt.setString(1, user.getAddress()); 
			stmt.setString(2, user.getUserName());
			stmt.execute();
	} catch (SQLException e) {
		System.out.println("No User found.  Please check the Username.");
	}	finally {
		ConnectCloser.closeConnection(conn);
		ConnectCloser.closeStatement(stmt);
	}
	}


	public void updateCity(User city) {
		User user= null;
		final String SQL = "update users set user_city = ? where user_name = ?";
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = ConnectUtil.getConnection();
			stmt = conn.prepareStatement(SQL);
			stmt.setString(1, user.getCity()); 
			stmt.setString(2, user.getUserName());
			stmt.execute();
	} catch (SQLException e) {
		System.out.println("No User found.  Please check the Username.");
	}	finally {
		ConnectCloser.closeConnection(conn);
		ConnectCloser.closeStatement(stmt);
	}
	}


	public void updateState(User state) {
		User user= null;
		final String SQL = "update users set user_state = ? where user_name = ?";
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = ConnectUtil.getConnection();
			stmt = conn.prepareStatement(SQL);
			stmt.setString(1, user.getState()); 
			stmt.setString(2, user.getUserName());
			stmt.execute();
	} catch (SQLException e) {
		System.out.println("No User found.  Please check the Username.");
	}	finally {
		ConnectCloser.closeConnection(conn);
		ConnectCloser.closeStatement(stmt);
	}
	}


	public void updatePhone(User phone) {
		User user= null;
		final String SQL = "update users set user_phone = ? where user_name = ?";
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = ConnectUtil.getConnection();
			stmt = conn.prepareStatement(SQL);
			stmt.setString(1, user.getPhone()); 
			stmt.setString(2, user.getUserName());
			stmt.execute();
	} catch (SQLException e) {
		System.out.println("No User found.  Please check the Username.");
	}	finally {
		ConnectCloser.closeConnection(conn);
		ConnectCloser.closeStatement(stmt);
	}
	}

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
