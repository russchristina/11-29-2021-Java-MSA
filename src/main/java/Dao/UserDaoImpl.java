package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.User;
import com.revature.util.ConnectionClosers;
import com.revature.util.ConnectionFactory;

public class UserDaoImpl implements UserDao{

	@Override
	public List<User> findAll() {
		List<User> users = new ArrayList<>();
		final String SQL = "Select * From Customers";
		Connection conn = null;
		Statement stmt = null;
		ResultSet set = null;
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.createStatement();
			set = stmt.executeQuery(SQL);
			while(set.next()) {
				users.add(
						new User(
									set.getInt(1),
									set.getString(2),
									set.getString(3))
								);
			}
			}catch(SQLException e) {
				e.printStackTrace();
				
			}finally {
				ConnectionClosers.closeConnection(conn);
				ConnectionClosers.closeStatement(stmt);
				ConnectionClosers.closeStatement(stmt);
			}
		return users;
	}

	@Override
	public void save(User user) {
		final String SQL = "insert into Customers values(default, ?, ?)";
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = ConnectionFactory.getConnection();
			/*
			 * The values of the parameters in a PreparedStatement are supplied later after
			 * the statement has been compiled.
			 */
			stmt = conn.prepareStatement(SQL);
			stmt.setString(1, user.getUsername());
			stmt.setString(2, user.getUserpassword());
			stmt.execute();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionClosers.closeConnection(conn);
			ConnectionClosers.closeStatement(stmt);
		}
		
	}

		
	

	@Override
	public User findById(int id) {
		User user= null;
		final String SQL = "select * from Customers where customer_id = " + id;
		Connection conn = null;
		Statement stmt = null;
		ResultSet set = null;
		
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.createStatement();
			set = stmt.executeQuery(SQL);
			
			if(set.next()) {
				user = new User(set.getInt(1),
						set.getString(2),
						set.getString(3)
						);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionClosers.closeConnection(conn);
			ConnectionClosers.closeResultSet(set);
			ConnectionClosers.closeStatement(stmt);
		}
		
		return user;
	}

	@Override
	public User findByName(String username) {
		final String SQL = "select * from Customers where customer_username = '" + username + "'";
		User user = null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet set = null;
		
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.createStatement();
			set = stmt.executeQuery(SQL);
			
			if(set.next()) {
				user = new User(set.getInt(1),
						set.getString(2),
						set.getString(3));
						
									}
	} catch (SQLException e) {
		System.out.println("No User found.  Please check the Name.");
	}	finally {
		ConnectionClosers.closeConnection(conn);
		ConnectionClosers.closeStatement(stmt);
		ConnectionClosers.closeResultSet(set);
	}
	return user;
	}

	@Override
	public void update(User user) {
		final String SQL = "update Customer set "
				+ "customer_username = ? where customer_id = ?";
	
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.prepareStatement(SQL);
			stmt.setString(1, user.getUsername());
			stmt.setInt(2, user.getId());
			stmt.execute();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionClosers.closeConnection(conn);
			ConnectionClosers.closeStatement(stmt);
		}
	}
	

}

