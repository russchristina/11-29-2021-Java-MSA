package com.revature;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.model.UserAccount;
import com.revature.util.ConnectionClosers;
import com.revature.util.ConnectionFactory;

public class Driver {
	
	static Scanner scan = new Scanner(System.in);
	static Logger log = LoggerFactory.getLogger("errorLogger");
	static Logger log2 = LoggerFactory.getLogger("aLogger");
	static Connection conn;
	static ResultSet set;
	static Statement stmnt;
	static UserAccount userAcc;

	public static void main(String[] args) {
		printWelcome();
		
		mainMenu();
		
	}
	
	private static void printWelcome() {
		System.out.println("||||||||||||||||||||||||||||");
		System.out.println("||| Welcome to the bank! |||");
		System.out.println("||||||||||||||||||||||||||||");
//		System.out.println("1. Login");
//		System.out.println("2. Signup");
//		System.out.println("3. Exit");
//		System.out.print("Please enter the number of your choice: ");
	}
	
	private static void printChoices(int i) {
		if(i == 0) {
			System.out.println("1. Login");
			System.out.println("2. Signup");
			System.out.println("3. Exit");
			System.out.print("Please enter the number of your choice: ");
		} else if (i == 1) {
			System.out.println("1. View balance");
			System.out.println("2. Deposit");
			System.out.println("3. Withdraw");
			System.out.println("4. Add users");
			System.out.println("5. Exit");
			System.out.print("Please enter the number of your choice: ");
		} else {
			System.out.println("1. View users");
			System.out.println("2. View user balance");
			System.out.println("3. Cancel user account");
			System.out.println("4. Exit");
			System.out.print("Please enter the number of your choice: ");
		}
	}
	
	private static void mainMenu() {
		printChoices(0);
		if(scan.hasNextInt()) {
			int i = scan.nextInt();
			scan.nextLine();
			
			if (i == 1) {
				login();
			} else if (i == 2) {
				signup();
			} else if (i == 3) {
				scan.close();
				System.exit(0);
			} else {
				System.out.println("Invalid choice, please enter the correct number");
				
				mainMenu();
			}
		} else {
			scan.nextLine();
			System.out.println("Please enter a valid choice.");
			
			mainMenu();
		}
	}
	
	private static void login() {
		final String SQL = "select * from users;";
		
		System.out.print("Username: ");
		String username = scan.nextLine();
		System.out.print("Password: ");
		String password = scan.nextLine();
		
		try {
			conn = ConnectionFactory.getConnection();
			stmnt = conn.createStatement();
			set = stmnt.executeQuery(SQL);
			
			while(set.next()) {
				if (set.getString(2).equals(username) && set.getString(3).equals(password)) {
					System.out.println("Welcome, " + username + "!");
					log2.debug(username + " logged in.");
					if (set.getBoolean(6)) {
						adminMenu();
					} else {
						userMenu(set.getInt(1), set.getInt(7));
					break;
					}
				}
			}
		} catch (SQLException e) {
			log.error("Error creating database connection.");
			e.printStackTrace();
		} finally {
			ConnectionClosers.closeConnection(conn);
			ConnectionClosers.closeResultSet(set);
			ConnectionClosers.closeStatement(stmnt);
			
		}
	}
	
	private static void signup() {
		final String SQL = "insert into users values(default, ?, ?, ?, ?, false, default);";
		final String bankSQL = "insert into bank values(default, 0);";
		final String usersSQL = "select * from users;";
		String username = "";
		
		try {
			conn = ConnectionFactory.getConnection();
			stmnt = conn.createStatement();
			stmnt.execute(bankSQL);
			set = stmnt.executeQuery(usersSQL);
			PreparedStatement pstmnt = conn.prepareStatement(SQL);
			
			System.out.print("Please create a username: ");
			username = scan.nextLine();
			while (set.next()) {
				if (set.getString(2).equals(username)) {
					System.out.println("User already exists!");
					mainMenu();
				} 
			}
			pstmnt.setString(1, username);
			System.out.print("Please create a password: ");
			pstmnt.setString(2, scan.nextLine());
			System.out.print("What is your address?: ");
			pstmnt.setString(3, scan.nextLine());
			System.out.print("What is your birthday?: ");
			pstmnt.setString(4, scan.nextLine());
			
			pstmnt.execute();
			pstmnt.close();
		} catch (SQLException e) {
			log.error("Error creating database connection.");
			e.printStackTrace();
		} finally {
			ConnectionClosers.closeConnection(conn);
			ConnectionClosers.closeResultSet(set);
			ConnectionClosers.closeStatement(stmnt);
			System.out.println("Thank you! Now please sign in!");
			mainMenu();
		}
		
		
	}
	
	private static void adminMenu() {
		printChoices(2);
		if (scan.hasNextInt()) {
			int i = scan.nextInt();
			scan.nextLine();
			
			if (i == 1) {
				viewUsers();
			} else if (i == 2) {
				viewBalances();
			} else if (i == 3)  {
				cancelUser();
				
			} else if (i == 4) {
				scan.close();
				System.exit(0);
			}  else {
				System.out.println("Invalid choice, please enter the correct number");
				printChoices(2);
				adminMenu();
			}
		} else {
			scan.nextLine();
			System.out.println("Please enter a valid choice.");
			printChoices(2);
			adminMenu();
		}
	}
	
	private static void userMenu(int userID, int bankID) {
		printChoices(1);
		if(scan.hasNextInt()) {
			int i = scan.nextInt();
			scan.nextLine();
			
			if (i == 1) {
				printBalance(userID, bankID);
			} else if (i == 2) {
				deposit(userID, bankID);
			} else if (i == 3)  {
				withdraw(userID, bankID);
				
			} else if (i == 4) {
				specialSignup(userID, bankID);
			} else if (i == 5) {
				scan.close();
				System.exit(0);
			} else {
				System.out.println("Invalid choice, please enter the correct number");
				printChoices(1);
				userMenu(userID, bankID);
			}
		} else {
			scan.nextLine();
			System.out.println("Please enter a valid choice.");
			printChoices(1);
			userMenu(userID, bankID);
		}
	}
	
	
	
	private static void printBalance(int user, int bankID) {
		final String SQL = "select balance from bank where account_id = " + bankID + ";";
		
		try {
			conn = ConnectionFactory.getConnection();
			stmnt = conn.createStatement();
			set = stmnt.executeQuery(SQL);
			if (set.next()) {
				System.out.println("Your balance is: $" + set.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("Error creating database connection.");
		} finally {
			ConnectionClosers.closeConnection(conn);
			ConnectionClosers.closeResultSet(set);
			ConnectionClosers.closeStatement(stmnt);
		}
		
		userMenu(user, bankID);
	}
	
	private static void deposit(int userID, int id) {
		//int newBal = 0;
		int deposit = 0;
		int oldBal = 0;
		
		final String getBalSQL = "select balance from bank where account_id = " + id + ";";
		try {
			conn = ConnectionFactory.getConnection();
			stmnt = conn.createStatement();
			set = stmnt.executeQuery(getBalSQL);
			if (set.next()) {
				oldBal = set.getInt(1);
			}
			System.out.print("Please enter the amount of money to be deposited: ");
			deposit = scan.nextInt();
			scan.nextLine();
			if (deposit >= 0) {
				int newBal = oldBal + deposit;
				final String SQL = "update bank set balance = " + newBal + " where account_id = " + id + ";";
				stmnt.execute(SQL);
				log2.debug("$" + deposit + " deposited in account ID #" + id);
				printBalance(userID, id);
			} else {
				System.out.println("You can only deposit positive values.");
			}
		} catch (SQLException e) {
			log.error("Error creating database connection.");
			e.printStackTrace();
		} finally {
			ConnectionClosers.closeConnection(conn);
			ConnectionClosers.closeResultSet(set);
			ConnectionClosers.closeStatement(stmnt);
		}
		
		userMenu(userID, id);
	}
	
	private static void withdraw(int userID, int id) {
		int newBal = 0;
		int withdraw = 0;
		int oldBal = 0;
		
		final String getBalSQL = "select balance from bank where account_id = " + id + ";";
		try {
			conn = ConnectionFactory.getConnection();
			stmnt = conn.createStatement();
			set = stmnt.executeQuery(getBalSQL);
			if (set.next()) {
				oldBal = set.getInt(1);
			}
			System.out.print("Please enter the amount of money to be withdrawn: ");
			withdraw = scan.nextInt();
			scan.nextLine();
			if (withdraw >= 0) {
				newBal = oldBal - withdraw;
				if (newBal >= 0) {
					final String SQL = "update bank set balance = " + newBal + " where account_id = " + id + ";";
					stmnt.execute(SQL);
					log2.debug("$" + withdraw + " withdrawn from account ID #" + id);
				} else {
					System.out.println("You do not have the funds for that!");
				}
				printBalance(userID, id);
			} else {
				System.out.println("You can only withdraw positive values.");
			}
		} catch (SQLException e) {
			log.error("Error creating database connection.");
			e.printStackTrace();
		} finally {
			ConnectionClosers.closeConnection(conn);
			ConnectionClosers.closeResultSet(set);
			ConnectionClosers.closeStatement(stmnt);
		}
		
		userMenu(userID, id);
	}
	
	private static void specialSignup(int userID, int bankID) {
		final String SQL = "insert into users values(default, ?, ?, ?, ?, false, ?);";
		final String bankSQL = "select * from users;";
		String username = "";
		
		try {
			conn = ConnectionFactory.getConnection();
			stmnt = conn.createStatement();
			set = stmnt.executeQuery(bankSQL);
			PreparedStatement pstmnt = conn.prepareStatement(SQL);
			
			System.out.print("Please create a username: ");
			username = scan.nextLine();
			while (set.next()) {
				if (set.getString(2).equals(username)) {
					System.out.println("User already exists!");
					userMenu(userID, bankID);
				}
			}
			pstmnt.setString(1, username);
			System.out.print("Please create a password: ");
			pstmnt.setString(2, scan.nextLine());
			System.out.print("What is your address?: ");
			pstmnt.setString(3, scan.nextLine());
			System.out.print("What is your birthday?: ");
			pstmnt.setString(4, scan.nextLine());
			pstmnt.setInt(5, bankID);
			
			pstmnt.execute();
			pstmnt.close();
		} catch (SQLException e) {
			log.error("Error creating database connection.");
			e.printStackTrace();
		} finally {
			ConnectionClosers.closeConnection(conn);
			ConnectionClosers.closeResultSet(set);
			ConnectionClosers.closeStatement(stmnt);
			userMenu(userID, bankID);
		}
	}
	
	private static void viewUsers() {
		final String SQL = "select * from users;";
		
		try {
			conn = ConnectionFactory.getConnection();
			stmnt = conn.createStatement();
			set = stmnt.executeQuery(SQL);
			
			while (set.next()) {
				System.out.println("Username: " + set.getString(2) +
						", Address: " + set.getString(4) +
						", Birthday: " + set.getString(5) +
						", Bank Account: " + set.getInt(7));
			}
		} catch (SQLException e) {
			log.error("Error creating database connection.");
			e.printStackTrace();
		} finally {
			ConnectionClosers.closeConnection(conn);
			ConnectionClosers.closeResultSet(set);
			ConnectionClosers.closeStatement(stmnt);
			adminMenu();
		}
	}
	
	private static void viewBalances() {
		String user = "";
		//final String pSQL = "select balance from bank where account_id = ?;";
		final String SQL = "select * from users;";
		try {
			conn = ConnectionFactory.getConnection();
			stmnt = conn.createStatement();
			set = stmnt.executeQuery(SQL);
			//PreparedStatement pstmnt = conn.prepareStatement(pSQL);
			System.out.print("Enter the username of the desired account: ");
			user = scan.nextLine();
			while (set.next()) {
				if (set.getString(2).equals(user)) {
//					pstmnt.setInt(1, set.getInt(1));
//					pstmnt.execute();
					ResultSet set2 = stmnt.executeQuery("select balance from bank where account_id = " + set.getInt(7));
					if (set2.next()) {
						System.out.println(user + "'s balance is $" + set2.getInt(1));
					}
					
							
							
					break;
				}
			}
			
			//pstmnt.close();
		} catch (SQLException e) {
			log.error("Error creating database connection.");
			e.printStackTrace();
		} finally {
			ConnectionClosers.closeConnection(conn);
			ConnectionClosers.closeResultSet(set);
			ConnectionClosers.closeStatement(stmnt);
			adminMenu();
		}
	}

	private static void cancelUser() {
		String user = "";
		final String SQL = "delete from users where user_id = ?;";
		
		try {
			conn = ConnectionFactory.getConnection();
			PreparedStatement pstmnt = conn.prepareStatement(SQL);
			stmnt = conn.createStatement();
			set = stmnt.executeQuery("select * from users;");
			System.out.print("Enter the username of the account: ");
			user = scan.nextLine();
			while (set.next()) {
				if (set.getString(2).equals(user)) {
					pstmnt.setInt(1, set.getInt(1));
					pstmnt.execute();
					break;
				}
			}
			
			pstmnt.close();
		} catch (SQLException e) {
			log.error("Error creating database connection.");
			e.printStackTrace();
		} finally {
			ConnectionClosers.closeConnection(conn);
			ConnectionClosers.closeResultSet(set);
			ConnectionClosers.closeStatement(stmnt);
			adminMenu();
		}
	}
}
