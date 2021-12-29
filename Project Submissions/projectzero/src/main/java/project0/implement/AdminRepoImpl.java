package project0.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import project0.models.User;
import project0.repos.AdminRepo;
import project0.repos.UserRepo;
import project0.util.ConnectCloser;
import project0.util.ConnectUtil;

public class AdminRepoImpl implements AdminRepo{

	@Override
	public void save(User user) {
		final String SQL = "insert into users values(?,?,?,?,?,?,?,?,?,?)";
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
			stmt.setInt(10, user.getBalance());
			stmt.execute();
		} catch (SQLException e) {
			
		}	finally {
			ConnectCloser.closeConnection(conn);
			ConnectCloser.closeStatement(stmt);
		}		
	}

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
	public void updatePassword(User username) {
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

	@Override
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

	@Override
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

	@Override
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

	@Override
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

	@Override
	public void addToBalance(User balance) {
		User user = null;
		final String SQL = "update users set user_balance = ? where user_name = ?";
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = ConnectUtil.getConnection();
			stmt = conn.prepareStatement(SQL);
			
			stmt.setInt(1, user.getBalance());
			stmt.setString(2, user.getUserName());
			stmt.execute();
		} catch (SQLException e) {
			System.out.println("No User found.  Please check the Username.");
		}finally {
			ConnectCloser.closeConnection(conn);
			ConnectCloser.closeStatement(stmt);
		}
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
