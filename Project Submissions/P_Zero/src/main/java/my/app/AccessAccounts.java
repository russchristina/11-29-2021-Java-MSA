package my.app;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccessAccounts {

	public Integer getIdByName(String name) {
		String SQL = "select user_id from account where user_name = ?";

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet set = null;
		Integer foreign_id = 0;

		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.prepareStatement(SQL);
			stmt.setString(1, name);
			set = stmt.executeQuery();
			
			if(set.next()) {
				foreign_id = set.getInt(1);
			}

		} catch (SQLException e) {
			System.out.println("...\nFailed to retrieve a user from database...\n");
		} finally {
			ConnectionClosers.closeConnection(conn);
			ConnectionClosers.closeStatement(stmt);
			ConnectionClosers.closeResultSet(set);
		}

		return foreign_id;
	}
	
	public Account getAccountById(int id) {
		String SQL = "select * from account where user_id = ?";
		
		Account retrieved = new Account();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet set = null;
		
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.prepareStatement(SQL);
			stmt.setInt(1, id);
			set = stmt.executeQuery();
			

			while(set.next()) {
			retrieved = new Account(
					set.getInt(1),
					set.getString(2),
					set.getString(3),
					set.getString(4),
					set.getString(5),
					set.getString(6),
					set.getString(7),
					set.getInt(8),
					set.getInt(9),
					set.getFloat(10),
					set.getBoolean(11),
					set.getBoolean(12)
					);
			}	
		} catch (SQLException e) {
			System.out.println("...\nFailed to update retrieve account...\n");
		} finally {
			ConnectionClosers.closeConnection(conn);
			ConnectionClosers.closeStatement(stmt);
			ConnectionClosers.closeResultSet(set);
		}
		
		return retrieved;
	}
	
	public void delete(String username) {
		String SQL = "delete * from account where user_name = ?";
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.prepareStatement(SQL);
			stmt.setString(1, username);
			stmt.execute();
			
		} catch (SQLException e) {
			System.out.println("...\nFailed to update database...\n");
		}
	}

	public void save() {
		String SQL = "update account set "
				+ "user_pass = ?, "
				+ "first_name = ?, "
				+ "last_name = ?, "
				+ "address = ?, "
				+ "state = ?, "
				+ "zipCode = ?, "
				+ "linked_accounts = ?, "
				+ "balance = ? "
				+ "where user_name = ? and user_id = ?";
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.prepareStatement(SQL);
			
			stmt.setString(1, Driver.account.getPassword());
			stmt.setString(2, Driver.account.getFirst_name());
			stmt.setString(3, Driver.account.getLast_name());
			stmt.setString(4, Driver.account.getAddress());
			stmt.setString(5, Driver.account.getState());
			stmt.setInt(   6, Driver.account.getZipCode());
			stmt.setInt(   7, Driver.account.getLinked());
			stmt.setFloat( 8, Driver.account.getBalance());
			stmt.setString(9, Driver.account.getUsername());
			stmt.setInt(  10, Driver.account.getUser_id());
			
			stmt.execute();
			
		} catch (SQLException e) {
			System.out.println("...\nFailed to update account...\n");
		} finally {
			ConnectionClosers.closeConnection(conn);
			ConnectionClosers.closeStatement(stmt);
		}
		
	}
	
	public void setFirstNameById(int id, String firstname) {
		String SQL = "update account set first_name = ? where user_id = ?";
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.prepareStatement(SQL);
			stmt.setString(1, firstname);
			stmt.setInt(2, id);
			stmt.execute();
			
		} catch (SQLException e) {
			System.out.println("...\nFailed to update...\n");
		} finally {
			ConnectionClosers.closeConnection(conn);
			ConnectionClosers.closeStatement(stmt);
		}
		
	}
	
	public void setLastNameById(int id, String lastname) {
		String SQL = "update account set last_name = ? where user_id = ?";
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.prepareStatement(SQL);
			stmt.setString(1, lastname);
			stmt.setInt(2, id);
			stmt.execute();
			
		} catch (SQLException e) {
			System.out.println("...\nFailed to update...\n");
		} finally {
			ConnectionClosers.closeConnection(conn);
			ConnectionClosers.closeStatement(stmt);
		}
	}
	
	public void setStreetById(int id, String address) {
		String SQL = "update account set address = ? where user_id = ?";
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.prepareStatement(SQL);
			stmt.setString(1, address);
			stmt.setInt(2, id);
			stmt.execute();
			
		} catch (SQLException e) {
			System.out.println("...\nFailed to update...\n");
		} finally {
			ConnectionClosers.closeConnection(conn);
			ConnectionClosers.closeStatement(stmt);
		}
	}
	
	public void setStateById(int id, String state) {
		String SQL = "update account set state = ? where user_id = ?";
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.prepareStatement(SQL);
			stmt.setString(1, state);
			stmt.setInt(2, id);
			stmt.execute();
			
		} catch (SQLException e) {
			System.out.println("...\nFailed to update...\n");
		} finally {
			ConnectionClosers.closeConnection(conn);
			ConnectionClosers.closeStatement(stmt);
		}
	}
	
	public void setZipById(int id, int zip) {
		String SQL = "update account set zipCode = ? where user_id = ?";
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.prepareStatement(SQL);
			stmt.setInt(1, zip);
			stmt.setInt(2, id);
			stmt.execute();
			
		} catch (SQLException e) {
			System.out.println("...\nFailed to update...\n");
		} finally {
			ConnectionClosers.closeConnection(conn);
			ConnectionClosers.closeStatement(stmt);
		}
	}
	
	public void setPassById(int id, String pass) {
		String SQL = "update account set user_pass = ? where user_id = ?";
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.prepareStatement(SQL);
			stmt.setString(1, pass);
			stmt.setInt(2, id);
			stmt.execute();
			
		} catch (SQLException e) {
			System.out.println("...\nFailed to update...\n");
		} finally {
			ConnectionClosers.closeConnection(conn);
			ConnectionClosers.closeStatement(stmt);
		}
	}

	public Account login() {
		String SQL = "select * from account where user_name = ? and user_pass = ?";

		Account verify = new Account();
		String username;
		String password;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet set = null;
		
		Driver.scanner.nextLine();
		System.out.print("Username: ");
		username = Driver.scanner.nextLine();
		System.out.print("Password: ");
		password = Driver.scanner.nextLine();

		System.out.println("\nLogging in...\n");

		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.prepareStatement(SQL);
			stmt.setString(1, username);
			stmt.setString(2, password);
			set = stmt.executeQuery();

			if (set.next()) {
				verify = new Account(
						set.getInt(1), 
						set.getString(2), 
						set.getString(3), 
						set.getString(4),
						set.getString(5),
						set.getString(6), 
						set.getString(7), 
						set.getInt(8), 
						set.getInt(9),
						set.getFloat(10), 
						set.getBoolean(11), 
						set.getBoolean(12));
			}

		} catch (SQLException e) {
			System.out.println("Failed to connect..");
			Driver.ux.entry();
		} finally {
			ConnectionClosers.closeConnection(conn);
			ConnectionClosers.closeStatement(stmt);
			ConnectionClosers.closeResultSet(set);
		}

		if(Driver.aa.userPassMatch(username, password) == 0) {
			Driver.ux.entry();
		}

		return verify;
	}

	public void register() {
		String SQL = "insert into account values(default, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		Connection conn = null;
		PreparedStatement stmt = null;

		String username;
		String password;
		String first_name;
		String last_name;
		String address;
		String state;
		int zipCode;
		
		Driver.scanner.nextLine();
		System.out.print("Choose a username: ");
		username = Driver.scanner.nextLine();
		
		if (this.getIdByName(username) > 0) {
			System.out.println("Name already exists. Try again..");
			this.register();
			
		} else {

			try {
				conn = ConnectionFactory.getConnection();
				stmt = conn.prepareStatement(SQL);

				System.out.print("Choose a password: ");
				password = Driver.scanner.nextLine();
				stmt.setString(1, username);
				stmt.setString(2, password);

				System.out.print("First name: ");
				first_name = Driver.scanner.nextLine();
				stmt.setString(3, first_name);

				System.out.print("Last name: ");
				last_name = Driver.scanner.nextLine();
				stmt.setString(4, last_name);

				System.out.print("Address: ");
				address = Driver.scanner.nextLine();
				stmt.setString(5, address);

				System.out.print("State: ");
				state = Driver.scanner.nextLine();
				stmt.setString(6, state);

				System.out.print("Zip code: ");
				zipCode = Driver.scanner.nextInt();
				
				stmt.setInt(7, zipCode);
				stmt.setInt(8, 0);
				stmt.setFloat(9, 0);
				stmt.setBoolean(10, false);
				stmt.setBoolean(11, false);
				stmt.execute();

				System.out.println("\n...Account Created!");

			} catch (SQLException e) {
				System.out.println("Unable to register.. Try again");
			} finally {
				ConnectionClosers.closeConnection(conn);
				ConnectionClosers.closeStatement(stmt);
				Driver.ux.entry();
			}

		}

	}
	
	public int userPassMatch(String username, String password) {
		String SQL = "select user_id from account where user_name = ? and user_pass = ?";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet set = null;
		int result = 0;
		
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.prepareStatement(SQL);
			stmt.setString(1, username);
			stmt.setString(2, password);
			set = stmt.executeQuery();
			
			if(set.next()) {
				result = set.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionClosers.closeConnection(conn);
			ConnectionClosers.closeStatement(stmt);
			ConnectionClosers.closeResultSet(set);
		}
		return result;
	}

	public void linkAccount(int linked_id) {
		String SQL = "update account set linked_accounts = ? where user_id = ?";

		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.prepareStatement(SQL);
			stmt.setInt(1, linked_id);
			stmt.setInt(2, Driver.account.getUser_id());
			stmt.execute();
			
			Driver.account.setLinked(linked_id);
			this.save();
			System.out.println("\n...Account linked!\n");
			
		} catch (SQLException e) {
			System.out.println("Failed to link account...");
			e.printStackTrace();
		} finally {
			ConnectionClosers.closeConnection(conn);
			ConnectionClosers.closeStatement(stmt);
		}
		Driver.ux.accountManager();
	}

	public float addMoney() {
		String SQL = "update account set balance = balance + ? where user_id = ?";

		Connection conn = null;
		PreparedStatement stmt = null;

		float amount = 0;

		System.out.println("----------------- Add funds -------------------");
		System.out.print("Amount: ");
		amount = Driver.scanner.nextFloat();
		int tryCounter = 0;
		while (amount < 0 && tryCounter < 3) {
			System.out.println("...Invalid input");
			System.out.print("Amount: ");
			amount = Driver.scanner.nextFloat();
			tryCounter += 1;
		}
		
			try {
				conn = ConnectionFactory.getConnection();
				stmt = conn.prepareStatement(SQL);
				stmt.setFloat(1, amount);
				stmt.setInt(2, Driver.account.getUser_id());
				stmt.execute();

				amount += Driver.account.getBalance();
				
				if (amount > 0) System.out.println("...Balance has been updated!");
				
			} catch (SQLException e) {
				amount = Driver.account.getBalance();
				e.printStackTrace();
			} finally {
				ConnectionClosers.closeConnection(conn);
				ConnectionClosers.closeStatement(stmt);
			}
			
		return amount;
	}
	
	public float removeMoney() {
		String SQL = "update account set balance = balance - ? where user_id = ?";

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet set = null;

		float amount = 0;

		System.out.println("---------- Remove funds -----------------------");
		System.out.print("Amount: ");
		amount = Driver.scanner.nextFloat();
		int tryCounter = 0;
		while (amount < 0 || amount > Driver.account.getBalance()) {
			System.out.println("...Invalid input");
			System.out.print("Amount: ");
			amount = Driver.scanner.nextFloat();
			tryCounter += 1;
			if(tryCounter == 3) break;
		}
		
			try {
				conn = ConnectionFactory.getConnection();
				stmt = conn.prepareStatement(SQL);
				stmt.setFloat(1, amount);
				stmt.setInt(2, Driver.account.getUser_id());
				stmt.execute();

				amount = Driver.account.getBalance() - amount;
				
				if (amount > 0) System.out.println("...Balance has been updated!");
				
			} catch (SQLException e) {
				amount = Driver.account.getBalance();
				e.printStackTrace();
			} finally {
				ConnectionClosers.closeConnection(conn);
				ConnectionClosers.closeStatement(stmt);
				ConnectionClosers.closeResultSet(set);
			}
			
		return amount;
	}
	
	public float getBalanceById (int id) {
		String SQL = "select balance from account where user_id = ?";
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet set = null;
		float balance = 0;
		
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.prepareStatement(SQL);
			stmt.setInt(1, id);
			set = stmt.executeQuery();
			
			if (set.next()) {
				balance = set.getFloat(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionClosers.closeConnection(conn);
			ConnectionClosers.closeStatement(stmt);
			ConnectionClosers.closeResultSet(set);
		}
		return balance;
	}
	
	public void setBalanceById (float amount, int id) {
		String SQL = "update account set balance = ? where user_id = ?";
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.prepareStatement(SQL);
			stmt.setFloat(1, amount);
			stmt.setInt(2, id);
			stmt.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionClosers.closeConnection(conn);
			ConnectionClosers.closeStatement(stmt);
		}
	}

}
