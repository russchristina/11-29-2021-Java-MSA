package my.app;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UX {
	
	public void entry() {
		
		System.out.println("/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\");
		System.out.println("||||||||||||||||||||||||||||||||||||||||||||||");
		System.out.println("||||||||||||   SPAGHETTI CODY'S   ||||||||||||");
		System.out.println("||||||||||||||||||||||||||||||||||||||||||||||");
		System.out.println("||||||||||||||| Italian Cuisine ||||||||||||||");
		System.out.println("||||||||||||||||||||||||||||||||||||||||||||||");
		System.out.println("\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/");
		System.out.println("+==+====+====+==== Main Menu ===+====+====+==+");
		System.out.println("----------------------------------------------");
		System.out.println("\t\t  1) login");
		System.out.println("\t\t  2) register");
		System.out.println("\t\t  3) exit");
		
		int nav = 0;
		
			try {
				nav = Driver.scanner.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("...Invalid input. try again.");
				Driver.scanner.nextLine();
				this.entry();
			}
		
		switch (nav) {
		case 1:
			Driver.account = Driver.aa.login();
			this.mainMenu();
			break;
		case 2:
			Driver.aa.register();
			break;
		case 3:
			System.out.println("\n...Terminated.");
			Driver.scanner.close();
			System.exit(0);
			break;
		}
	}
	
	public void mainMenu() {
		System.out.println("-----------------------------------------------");
		System.out.println("------------------- Navigation ----------------");
		System.out.println("-----------------------------------------------");
		System.out.println("		1) Manage Account\n"
				+ "\t\t2) Shop\n"
				+ "\t\t3) Logout\n");

		int nav = 0;
		try {
			nav = Driver.scanner.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("...Invalid input. try again.");
			Driver.scanner.nextLine();
			this.mainMenu();
		}
		
		switch(nav) {
		case 1:
			if(!Driver.account.isEmployee()) this.accountManager();
			else if(Driver.account.isAdmin()) this.admin();
			else this.employee();
			break;
		case 2:
			this.shop();
			break;
		case 3:
			this.entry();
			break;
		}
	}
	
	public void shop() {
		Driver.scanner.nextLine();
		System.out.println("-----------------   SHOP   --------------------");
		System.out.println("------------  SPAGHETTI CODY'S  ---------------");
		System.out.println("-----------------------------------------------");
		System.out.println("------------------------------ balance: " + Driver.account.getBalance());

		HashMap<String, Float> shoplist = getShopList();
		Set<String> keyset = shoplist.keySet();
		Logger transactionLogger = LoggerFactory.getLogger("myLogger");
		keyset.forEach(name -> System.out.println("\n" + name + " : " + shoplist.get(name)));
		
		System.out.println("-----------------------------------------------");
		System.out.println("Selection is case sensitive.");
		System.out.println("Enter item name to purchase or 'x' to exit: ");
		
		int amount = 0;
		String input = Driver.scanner.nextLine();
		
		if (input.equals("x")) {
			this.mainMenu();
		}
		else if (shoplist.containsKey(input)) {
			System.out.println("Enter amount to purchase or 0 to exit.");
			System.out.print("Amount: ");
			amount = Driver.scanner.nextInt();
			float total = amount * shoplist.get(input);
			
			if (total > 0 && total <= Driver.account.getBalance()) {
				Driver.account.setBalance(Driver.account.getBalance() - total);
				Driver.aa.save();
				System.out.println("\n...You bought " + amount + " " + input 
						+ "(s) for $" + total + "\n");
				transactionLogger.info(Driver.account.getUsername() + " bought "
						+ amount + " " + input + "(s) for " + total);
			}
			if (amount == 0) {
				this.shop();
			}
		}
		
		this.shop();
		
	}
	
	public void accountManager() {
		System.out.println("-----------------------------------------------");
		System.out.println("--------------- Account Manager ---------------");
		System.out.println("-----------------------------------------------");
		System.out.println("------------------------------ Balance: " + Driver.account.getBalance());
		System.out.println("		1) Link account");
		System.out.println("		2) Add Money");
		System.out.println("		3) Remove Money");
		System.out.println("		4) Transfer Money");
		System.out.println("		5) Exit");

		int nav = 0;
		
		try {
			nav = Driver.scanner.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("Invalid input...");
			Driver.scanner.nextLine();
			this.accountManager();
		}
		
		if(nav > 0 && nav < 6) {
			switch(nav) {
			case 1:
				this.link();
				this.accountManager();
				break;
			case 2:
				Driver.account.setBalance(Driver.aa.addMoney());
				this.accountManager();
				break;
			case 3:
				Driver.account.setBalance(Driver.aa.removeMoney());
				this.accountManager();
				break;
			case 4:
				this.transfer();
				break;
			case 5:
				this.mainMenu();
				break;
		}
		}
	}
	
	public void admin() {
		System.out.println("-------------------- ADMIN ---------------------");
		System.out.println("--------------- Account Manager ----------------");
		System.out.println("------------------------------------------------");
		System.out.println("		1) User lookup ");
		System.out.println("		2) Modify account");
		System.out.println("		3) Cancel account");
		System.out.println("		4) logout");

		int nav = 0;
		
		try {
			nav = Driver.scanner.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("Invalid input...");
			Driver.scanner.nextLine();
			this.admin();
		}
		
		Driver.scanner.nextLine();
		
		switch(nav) {
		case 1:
			this.userLookup();
			break;
		case 2:
			this.modifyAccount();
			break;
		case 3:
			this.cancelAccount();
			break;
		case 4:
			this.entry();
		}
	}
	
	public void employee() {
		Driver.scanner.nextLine();
		System.out.println("------------------ EMPLOYEE --------------------");
		System.out.println("--------------- Account Manager ----------------");
		System.out.println("------------------------------------------------");
		System.out.println("		1) User lookup ");
		System.out.println("		2) Cancel account");
		System.out.println("		3) logout");
		
		int nav = 0;
		
		try {
			nav = Driver.scanner.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("Invalid input...");
			Driver.scanner.nextLine();
			this.employee();
		}
		
		if(nav > 0 && nav < 4) {
			switch(nav) {
			case 1:
				this.userLookup();
				break;
			case 2:
				this.cancelAccount();
				break;
			case 3:
				this.entry();
				break;
			}
		}
	}
	
	public void modifyAccount() {
		System.out.println("--------------- Modify Account -----------------");
		System.out.println("1) Change password");
		System.out.println("2) Change name");
		System.out.println("3) Change address");
		System.out.println("4) New balance");
		System.out.println("5) Exit");
		
		String username = null;
		int nav = 0;
		
		try {
			nav = Driver.scanner.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("Invalid input...");
			this.modifyAccount();
		}
		
		Driver.scanner.nextLine();
		
		if(nav != 5) {
		System.out.print("Username: ");
		username = Driver.scanner.nextLine();
		}
		
		int id = Driver.aa.getIdByName(username);
		
		switch(nav) {
		case 1:
			this.setUserPass(id);
			break;
		case 2:
			this.setFullName(id);
			break;
		case 3:
			this.setAddress(id);
			break;
		case 4:
			this.setUserBalance(id);
			break;
		case 5:
			this.admin();
			break;
		}
	}
	
	public void userLookup() {
		System.out.print("Find user: ");
		String input = Driver.scanner.nextLine();
		int user_id = Driver.aa.getIdByName(input);
		Account user = Driver.aa.getAccountById(user_id);
		
		System.out.println("\nId: " + user.getUser_id());
		System.out.println("Username: " + user.getUsername());
		System.out.println("Password: " + user.getPassword());
		System.out.println("Balance: " + user.getBalance());
		System.out.println("Linked account: " + Driver.aa.getAccountById(user.getLinked()).getUsername());
		System.out.println("Full name: " + user.getFirst_name() + " " + user.getLast_name());
		System.out.println("Address: " + user.getAddress() + " [unimplemented town variable] " + user.getState() + ", " + user.getZipCode());
		
		if(Driver.account.isAdmin()) this.admin(); 
		else this.employee();
	}
	
	public void setAddress(int id) {
		System.out.print("Street address: ");
		String street = Driver.scanner.nextLine();
		System.out.print("State: ");
		String state = Driver.scanner.nextLine();
		System.out.print("Zip code: ");
		int zip = Driver.scanner.nextInt();
		
		Driver.aa.setStreetById(id, street);
		Driver.aa.setStateById(id, state);
		Driver.aa.setZipById(id, zip);
	}
	
	public void setFullName(int id) {
		System.out.print("First name: ");
		String firstname = Driver.scanner.nextLine();
		System.out.print("Last name: ");
		String lastname = Driver.scanner.nextLine();
		
		Driver.aa.setFirstNameById(id, firstname);
		Driver.aa.setLastNameById(id, lastname);
		this.modifyAccount();
	}
	
	public void setUserPass(int id) {
		System.out.print("Enter new pass: ");
		String password = Driver.scanner.nextLine();
		Driver.aa.setPassById(id, password);
		this.modifyAccount();
	}
	
	public void setUserBalance(int id) {
		System.out.print("Enter new balance: ");
		float newBalance = Driver.scanner.nextFloat();
		Driver.aa.setBalanceById(newBalance, id);
		this.modifyAccount();
	}
	
	public void cancelAccount() {
		System.out.println("--------------- Delete Account -----------------");
		System.out.print("Delete username: ");
		String username = Driver.scanner.nextLine();
		
		if(Driver.aa.getIdByName(username) > 0) {
			Driver.aa.delete(username);
		}
		
		if(Driver.account.isAdmin()) this.admin();
		else this.employee();
	}
	
	public void link() {
		System.out.println("---------------- Link Account ----------------");
		System.out.print("Account: ");
		String username = Driver.scanner.nextLine();
		System.out.print("Password: ");
		String password = Driver.scanner.nextLine();
		
		int verifyLink = Driver.aa.userPassMatch(username, password);
		
		if(verifyLink > 0) {
			System.out.println("Link verified...");
			Driver.aa.linkAccount(verifyLink);
			Driver.account.setLinked(verifyLink);
		}
	}
	
	public void transfer() {
		System.out.println("--------------- Transfer Money ----------------");
		System.out.println("Linked to " + Driver.aa.getAccountById(Driver.account.getLinked()));
		System.out.println("0 to exit");
		System.out.print("Amount: ");
		
		float amount = Driver.scanner.nextFloat();
		if (amount < 0 || amount > Driver.account.getBalance()) {
			System.out.println("Invalid amount. Try again.");
			this.transfer();
		}
		else if (amount == 0) {
			this.accountManager();
		} else {
			float linkedBalance = Driver.aa.getBalanceById(Driver.account.getLinked());
			
			Driver.aa.setBalanceById(Driver.account.getBalance() - amount, Driver.account.getLinked());
			Driver.aa.setBalanceById(linkedBalance + amount, Driver.account.getLinked());
		}
		this.accountManager();
	}
	
	static HashMap<String, Float> getShopList(){
		String SQL = "select item_name, item_price from item";
		HashMap<String, Float> items = new HashMap<>();
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet set = null;
		
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.createStatement();
			set = stmt.executeQuery(SQL);
			
			while(set.next()) {
				items.put(set.getString(1), set.getFloat(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionClosers.closeConnection(conn);
			ConnectionClosers.closeStatement(stmt);
			ConnectionClosers.closeResultSet(set);
		}
		
		return items;
	}
}
