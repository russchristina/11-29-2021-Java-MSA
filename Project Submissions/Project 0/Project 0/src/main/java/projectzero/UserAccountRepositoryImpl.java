package projectzero;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserAccountRepositoryImpl implements UserAccountRepository {
	
	public void createAccount() {
		final String SQL = "insert into accounts values(default, ?)";
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = ConnectionHandler.getConnection();
			stmt = conn.prepareStatement(SQL);
			stmt.setInt(1, 0);
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionClosers.closeConnection(conn);
			ConnectionClosers.closeStatement(stmt);
		} // End finally block
	} // End method

	public void createUser(VirtualDollarsUser user) {
		final String SQL = "insert into users values(?, ?, ?, ?, ?, ?, ?, ?)";
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = ConnectionHandler.getConnection();
			stmt = conn.prepareStatement(SQL);
			stmt.setString(1, user.getUsername());
			stmt.setString(2, user.getFirstName());
			stmt.setString(3, user.getLastName());
			stmt.setString(4, user.getPassword());
			stmt.setString(5, user.getDateOfBirth());
			stmt.setString(6, user.getEmail());
			stmt.setString(7, user.getAddress());
			stmt.setInt(8, user.getAccount().getAccountId());
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionClosers.closeConnection(conn);
			ConnectionClosers.closeStatement(stmt);
		} // End finally block
	} // End method

	public VirtualDollarsAccount getLastAccount() {
		VirtualDollarsAccount account = null;
		final String SQL = "select * from accounts order by account_id desc limit 1";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet set = null;
		
		try {
			conn = ConnectionHandler.getConnection();
			stmt = conn.prepareStatement(SQL);
			set = stmt.executeQuery();
			
			if(set.next()) {
				account = new VirtualDollarsAccount(
						set.getInt(1),
						set.getDouble(2)
						);
			} // End if statement
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionClosers.closeConnection(conn);
			ConnectionClosers.closeResultSet(set);
			ConnectionClosers.closeStatement(stmt);
		} // End finally block
		
		return account;
	} // End method
	
	public VirtualDollarsAccount findByAccountId(int id) {
		VirtualDollarsAccount account = null;
		final String SQL = "select * from accounts where account_id = ?";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet set = null;
		
		try {
			conn = ConnectionHandler.getConnection();
			stmt = conn.prepareStatement(SQL);
			stmt.setInt(1, id);
			set = stmt.executeQuery();
			
			if(set.next()) {
				account = new VirtualDollarsAccount(
						set.getInt(1),
						set.getDouble(2)
						);
			} // End if statement
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionClosers.closeConnection(conn);
			ConnectionClosers.closeResultSet(set);
			ConnectionClosers.closeStatement(stmt);
		} // End finally block
		
		return account;
	} // End method

	public VirtualDollarsUser findByUsername(String username) {
		VirtualDollarsUser user = null;
		final String SQL = "select * from users where user_name = ?";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet set = null;
		
		try {
			conn = ConnectionHandler.getConnection();
			stmt = conn.prepareStatement(SQL);
			stmt.setString(1, username);
			set = stmt.executeQuery();
			
			if(set.next()) {
				user = new VirtualDollarsUser(
						set.getString(1),
						set.getString(2),
						set.getString(3),
						set.getString(4),
						set.getString(5),
						set.getString(6),
						set.getString(7),
						findByAccountId(set.getInt(8))
						);
			} // End if statement
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionClosers.closeConnection(conn);
			ConnectionClosers.closeResultSet(set);
			ConnectionClosers.closeStatement(stmt);
		} // End finally block
		
		return user;
	} // End method

	public VirtualDollarsEmployee findByEmployeeName(String name) {
		VirtualDollarsEmployee employee = null;
		final String SQL = "select * from employees where employee_name = ?";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet set = null;
		
		try {
			conn = ConnectionHandler.getConnection();
			stmt = conn.prepareStatement(SQL);
			stmt.setString(1, name);
			set = stmt.executeQuery();
			
			if(set.next()) {
				employee = new VirtualDollarsEmployee(
						set.getString(1),
						set.getString(2),
						set.getBoolean(3)
						);
			} // End if statement
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionClosers.closeConnection(conn);
			ConnectionClosers.closeResultSet(set);
			ConnectionClosers.closeStatement(stmt);
		} // End finally block
		
		return employee;
	} // End method
	
	public List<VirtualDollarsUser> findUsersByAccountId(int id) {
		List<VirtualDollarsUser> userList = new ArrayList<>();
		final String SQL = "select * from users where account_id = ?";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet set = null;
		
		try {
			conn = ConnectionHandler.getConnection();
			stmt = conn.prepareStatement(SQL);
			stmt.setInt(1, id);
			set = stmt.executeQuery();
			
			while(set.next()) {
				userList.add(new VirtualDollarsUser(
						set.getString(1),
						set.getString(2),
						set.getString(3),
						set.getString(4),
						set.getString(5),
						set.getString(6),
						set.getString(7),
						findByAccountId(set.getInt(8))
						));
			} // End if statement
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionClosers.closeConnection(conn);
			ConnectionClosers.closeResultSet(set);
			ConnectionClosers.closeStatement(stmt);
		} // End finally block
		
		return userList;
	} // End method

	public List<VirtualDollarsAccount> findAllAccounts() {
		List<VirtualDollarsAccount> accountList = new ArrayList<>();
		final String SQL = "select * from accounts";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet set = null;
		
		try {
			conn = ConnectionHandler.getConnection();
			stmt = conn.prepareStatement(SQL);
			set = stmt.executeQuery();
			
			while(set.next()) {
				accountList.add(new VirtualDollarsAccount(
						set.getInt(1),
						set.getDouble(2)
						));
			} // End if statement
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionClosers.closeConnection(conn);
			ConnectionClosers.closeResultSet(set);
			ConnectionClosers.closeStatement(stmt);
		} // End finally block
		
		return accountList;
	} // End method

	public List<VirtualDollarsUser> findAllUsers() {
		List<VirtualDollarsUser> userList = new ArrayList<>();
		final String SQL = "select * from users";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet set = null;
		
		try {
			conn = ConnectionHandler.getConnection();
			stmt = conn.prepareStatement(SQL);
			set = stmt.executeQuery();
			
			while(set.next()) {
				userList.add(new VirtualDollarsUser(
						set.getString(1),
						set.getString(2),
						set.getString(3),
						set.getString(4),
						set.getString(5),
						set.getString(6),
						set.getString(7),
						findByAccountId(set.getInt(8))
						));
			} // End if statement
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionClosers.closeConnection(conn);
			ConnectionClosers.closeResultSet(set);
			ConnectionClosers.closeStatement(stmt);
		} // End finally block
		
		return userList;
	} // End method

	public void updateAccountData(VirtualDollarsAccount account) {
		final String SQL = "update accounts set account_balance = ? where account_id = ?";
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = ConnectionHandler.getConnection();
			stmt = conn.prepareStatement(SQL);
			stmt.setDouble(1, account.getAccountBalance());
			stmt.setInt(2, account.getAccountId());
			stmt.execute();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionClosers.closeConnection(conn);
			ConnectionClosers.closeStatement(stmt);
		} // End finally block
	} // End method

	public void updateUserData(VirtualDollarsUser user) {
		final String SQL = "update users set first_name = ?, last_name = ?, pass_word = ?, date_of_birth = ?, email = ?, "
				+ "address = ? where user_name = ?";
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = ConnectionHandler.getConnection();
			stmt = conn.prepareStatement(SQL);
			stmt.setString(1, user.getFirstName());
			stmt.setString(2, user.getLastName());
			stmt.setString(3, user.getPassword());
			stmt.setString(4, user.getDateOfBirth());
			stmt.setString(5, user.getEmail());
			stmt.setString(6, user.getAddress());
			stmt.setString(7, user.getUsername());
			stmt.execute();
			System.out.println("Changes have been saved.");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionClosers.closeConnection(conn);
			ConnectionClosers.closeStatement(stmt);
		} // End finally block
	} // End method

	public void deleteAccount(VirtualDollarsAccount account) {
		final String SQL = "delete from accounts where account_id = ?"; // Accounts are on delete cascade
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = ConnectionHandler.getConnection();
			stmt = conn.prepareStatement(SQL);
			stmt.setInt(1, account.getAccountId());
			stmt.execute();
			System.out.println("Account, with account ID: " + account.getAccountId() + ", has been successfully deleted.");
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionClosers.closeConnection(conn);
			ConnectionClosers.closeStatement(stmt);
		} // End finally block
	} // End method

	public void deleteUser(VirtualDollarsUser user) {
		final String SQL = "delete from users where user_name = ?";
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = ConnectionHandler.getConnection();
			stmt = conn.prepareStatement(SQL);
			stmt.setString(1, user.getUsername());
			stmt.execute();
			System.out.println("User, " + user.getUsername() + ", has been been successfully deleted.");
			/*
			 * Check if any users are still attached to the account. If none are, delete account as well.
			 */
			List<VirtualDollarsUser> userCheck = findUsersByAccountId(user.getAccount().getAccountId());
			if (userCheck.isEmpty()) {
				System.out.println("No users associated with account ID: " + user.getAccount().getAccountId() + " remaining.\nDeleting account...\n");
				deleteAccount(user.getAccount());
			} // End if statement
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionClosers.closeConnection(conn);
			ConnectionClosers.closeStatement(stmt);
		} // End finally block
	} // End method
} // End class
