package projectzero;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class VirtualDollars {

	// Fields
	private Scanner sc;
	private NumberFormat nf;
	private FormatHandler fh;
	private UserAccountRepositoryImpl repo;

	// Constructor
	public VirtualDollars() {
		sc = new Scanner(System.in);
		fh = new FormatHandler();
		repo = new UserAccountRepositoryImpl();
		nf = NumberFormat.getNumberInstance(Locale.ENGLISH);
		nf.setMaximumFractionDigits(2);
		nf.setMinimumFractionDigits(2);
		outputLogin();
	} // End constructor

	// Method to handle account menu display
	private void accountMenu(VirtualDollarsUser v) {
		
		boolean loopController = true;
		
		while (loopController) {
			int option = 0;
			System.out.println("\nHello, " + v.getUsername() + ", what would you like to do?\n (1) Check account balance\n"
					+ " (2) Add funds\n (3) Withdraw Funds\n (4) Transfer funds to another account\n (5) View/edit personal information\n"
					+ " (6) Add another user\n (7) Log out\n (8) Exit");	
			if (sc.hasNextInt()) {
				option = sc.nextInt();
			} // End if statement
			sc.nextLine();
			
			if (option == 1) {		
				System.out.println("Your balance is: " + nf.format(v.getAccount().getAccountBalance()));
			} else if (option == 2) {
				System.out.println("How much would you like to deposit?");		
				if (sc.hasNextDouble()) {
					double amount = sc.nextDouble();
					v.getAccount().addFunds(amount);
				} else {
					System.out.println("\nInvalid input.\n");
				} // End else statement
			} else if (option == 3) {
				System.out.println("How much would you like to withdraw?");
				if (sc.hasNextDouble()) {
					double amount = sc.nextDouble();
					v.getAccount().removeFunds(amount);
				} else {
					System.out.println("\nInvalid input.\n");
				} // End else statement
			} else if (option == 4) {
				System.out.println("Enter the username for the account you would to tranfer funds to.");
				String xferName = sc.next();
				sc.nextLine();
				
				VirtualDollarsUser u = null; // Account to be transfered to
				try {
					u = repo.findByUsername(xferName);
				} catch (Exception e) {
					e.printStackTrace();
				} // End catch block
				
				if (u != null) {	
					System.out.println("How much would you like to transfer?");
					if (sc.hasNextDouble()) {
						double amount = sc.nextDouble();
						sc.nextLine();
						v.getAccount().transferFunds(amount, u.getAccount()); 
					} else {
						System.out.println("Invalid input.");
					} // End else statement
				} else {
					System.out.println("An account does not exist with that username.");
				} // End else statement
				
			} else if (option == 5) {
				
				System.out.println(v);
				
				System.out.println("\nWould you like to edit your user information?\n (1) Yes\n (2) No");
				int editSelection = 0;
				if (sc.hasNextInt()) {
					editSelection = sc.nextInt();
				} // End if statement
				sc.nextLine();
				
				if (editSelection == 1) {
					
					int editOption = 0;
					boolean loopHandler = true;
									
					while (loopHandler) {
						System.out.println("What would you like to edit?\n (1) Password\n (2) Email\n (3) Address\n (4) Nothing");
						if (sc.hasNextInt()) { 
							editOption = sc.nextInt();
						} // End if statement
						sc.nextLine();
						
						if (editOption == 1) {
							System.out.println("Enter new password:");
							String newPassword = sc.next();
							sc.nextLine();
							v.setPassword(newPassword);
						} else if (editOption == 2) {
							System.out.println("Enter new email:");
							String newEmail = sc.next();
							sc.nextLine();
							
							if (fh.emailFormatter(newEmail)) {
								v.setEmail(newEmail);
							} else {
								System.out.println("Invalid email format");
							} // End else statement
						} else if (editOption == 3) {
							System.out.println("Enter new address:");
							String newAddress = sc.nextLine();
							v.setAddress(newAddress);
						} else if (editOption == 4) {
							loopHandler = false;
						} else {
							System.out.println("Invalid input");
						} // End else statement
					} // End while loop
					repo.updateUserData(v);	
				} // End if statement

			} else if (option == 6) {
				
				System.out.print("\nPlease enter your desired username: ");			
				String userName = sc.next();
				sc.nextLine();
				
				VirtualDollarsUser vdu = null;
				try {
					vdu = repo.findByUsername(userName);
				} catch (Exception e) {
					e.printStackTrace();
				} // End catch block
				
				if (vdu == null) { 
					vdu = new VirtualDollarsUser(userName);
					System.out.print("\nYour username is " + vdu.getUsername() + "\nPlease enter a password for your account: ");
					String passWord = sc.next();
					sc.nextLine();
					vdu.setPassword(passWord);
					
					createNewUser(vdu);
					
					vdu.setAccount(v.getAccount());
					
					repo.createUser(vdu);
					
					System.out.println("\nYour account has been successfully created!\nLog in to access your new account!\n");			
				} else {
					System.out.println("That username is unavailable.");				
				} // End else statement
				
			} else if (option == 7) {
				System.out.println("\nLogging out");
				loopController = false;
				outputLogin();
			} else if (option == 8) {
				System.out.println("\n\n*****Exiting application*****");
				sc.close();
				System.exit(0);
			} else {
				System.out.println("\nInvalid input.\n");				
			} // End else statement
			System.out.println("\nReturning to account menu.\n");
		} // End while loop	
	} // End method
	
	// Method to handle employee menu
	private void employeeMenu(VirtualDollarsEmployee employee) {

		int option = 0;
		if (employee.isAdmin()) {
			System.out.println("\n****Administrative access****\n");
		} // End if statement
		
		System.out.println("What would you like to do?\n (1) View user information\n (2) View account information\n (3) Log out\n (4) Exit");
		if (sc.hasNextInt()) { 
			option = sc.nextInt();
		} // End if statement
		sc.nextLine();
		
		if (option == 1) {
			List<VirtualDollarsUser> users = null;
			try {
				users = repo.findAllUsers();
			} catch (Exception e) {
				e.printStackTrace();
			} // End catch block
			
			if (users != null) {
				for (VirtualDollarsUser vdu : users) {
					System.out.println(vdu );
				} // End for loop
				
				int userOption = 0;
				System.out.println("What would you like to do?\n (1) Select a user\n (2) Go back to employee menu");
				if (sc.hasNextInt()) { 
					userOption = sc.nextInt();
				} // End if statement
				sc.nextLine();
				
				if (userOption == 1) {
					VirtualDollarsUser vdu = null;
					System.out.println("Enter the username for the user you want to select.");
					String userInfoOption = sc.next();
					sc.nextLine();
					
					vdu = repo.findByUsername(userInfoOption);
					
					if (vdu != null) {
						System.out.println(vdu);
						
						int userViewOption = 0;
						System.out.println("\nWhat would you like to do?\n (1) Delete account\n (2) Nothing\n");
						if (employee.isAdmin()) {
							System.out.println("Administrative option:\n (3) Edit user information");
						} // End if statement
						if (sc.hasNextInt()) { 
							userViewOption = sc.nextInt();
						} // End if statement
						sc.nextLine();
						
						if (userViewOption == 1) {
							repo.deleteUser(vdu);
						} else if (userViewOption == 2) {
							System.out.println("Returning to employee menu...");
						} else if (employee.isAdmin() && userViewOption == 3) {
							System.out.println(vdu);
							int editOption = 0;
							boolean loopController = true;
							
							while (loopController) {
								System.out.println("What would you like to edit?\n (1) First Name\n (2) Last Name\n (3) Password\n (4) Date of Birth\n "
										+ "(5) Email\n (6) Address\n (7) Nothing");
								if (sc.hasNextInt()) { 
									editOption = sc.nextInt();
								} // End if statement
								sc.nextLine();
								
								if (editOption == 1) {
									System.out.println("Enter new first name:");
									String newFirstName = sc.next();
									sc.nextLine();
									
									if (fh.nameFormatCheck(newFirstName)) {
										vdu.setFirstName(newFirstName);
									} else {
										System.out.println("Invalid format");
									} // End else statement
								} else if (editOption == 2) {
									System.out.println("Enter new last name:");
									String newLastName = sc.next();
									sc.nextLine();
									
									if (fh.nameFormatCheck(newLastName)) {
										vdu.setLastName(newLastName);
									} else {
										System.out.println("Invalid format");
									} // End else statement
								} else if (editOption == 3) {
									System.out.println("Enter new password:");
									String newPassword = sc.next();
									sc.nextLine();
									vdu.setPassword(newPassword);
								} else if (editOption == 4) {
									System.out.println("Enter new date of birth:");
									String newDOB = sc.next();
									sc.nextLine();
									
									if (fh.dateFormatter(newDOB)) {
										vdu.setDateOfBirth(newDOB);
									} else {
										System.out.println("Invalid date formate. Must be mm/dd/yyyy.");
									} // End else statement																		
								} else if (editOption == 5) {
									System.out.println("Enter new email:");
									String newEmail = sc.next();
									sc.nextLine();
									
									if (fh.emailFormatter(newEmail)) {
										vdu.setEmail(newEmail);
									} else {
										System.out.println("Invalid email format");
									} // End else statement
								} else if (editOption == 6) {
									System.out.println("Enter new address:");
									String newAddress = sc.nextLine();
									vdu.setAddress(newAddress);
								} else if (editOption == 7) {
									loopController = false;
								} else {
									System.out.println("Invalid input\n");
								} // End else statement
							} // End while loop
							repo.updateUserData(vdu);
						} else {
							System.out.println("Invalid input\n");
						} // End else statement
						
					} else {
						System.out.println("Username not found\n");
					} // End else statement
	
				} else if (userOption == 2) {
					System.out.println("***Returning to employee menu***\n");
				} else {
					System.out.println("Invalid input\n");
				} // End else statement		
			} else {
				System.out.println("No users found.\n");
			} // End else statement
		} else if (option == 2) {
			List<VirtualDollarsAccount> accounts = null;
			int accountOption = 0;
			try {
				accounts = repo.findAllAccounts();
			} catch (Exception e) {
				e.printStackTrace();
			} // End catch block
			
			if (accounts != null) {
				for (VirtualDollarsAccount vda : accounts) {
					System.out.println(vda);
				} // End for loop
			
				System.out.println("\nWhat would you like to do?\n (1) Select an account\n (2) Go back to employee menu");
				if (sc.hasNextInt()) { 
					accountOption = sc.nextInt();
				} // End if statement
				sc.nextLine();
				
				if (accountOption == 1) {
					VirtualDollarsAccount vda = null;
					int accountInfoOption = 0;
					System.out.println("Enter the account ID for the account you want to select.");
					if (sc.hasNextInt()) { 
						accountInfoOption = sc.nextInt();
						vda = repo.findByAccountId(accountInfoOption);
					} else {
						System.out.println("Invalid input");
					} // End else statement
					sc.nextLine();
					
					if (vda != null) {
						System.out.println(vda);
						int accountViewOption = 0;
						System.out.println("\nWhat would you like to do?\n (1) Display users associated with account\n (2) Delete account\n (3) Nothing");
						if (employee.isAdmin()) {
							System.out.println("Administrative option:\n (4) Edit account balance");
						} // End if statement
						if (sc.hasNextInt()) { 
							accountViewOption = sc.nextInt();
						} // End if statement
						sc.nextLine();
						
						if (accountViewOption == 1) {
							List<VirtualDollarsUser> userList = repo.findUsersByAccountId(vda.getAccountId());
							if (userList != null) {
								System.out.println("\nUsers associated with this account:");
								for (VirtualDollarsUser accountUser : userList) {
									System.out.println(" - " + accountUser.getUsername());
								} // End for each loop
							} else {
								System.out.println("No users found.");
							} // End else statement
						} else if (accountViewOption == 2) {
							repo.deleteAccount(vda);
						} else if (accountViewOption == 3) {
							System.out.println("Returning to employee menu...");
						} else if (employee.isAdmin() && accountViewOption == 4) {
							System.out.println("Please enter the new account balance.");
							if (sc.hasNextInt()) {
								int newBalance = sc.nextInt();
								vda.setAccountBalance(newBalance);								
								System.out.println("Account balance for account ID " + vda.getAccountId() + " is " + vda.getAccountBalance());
							} else {
								System.out.println("Invalid input");
							} // End else statement
							sc.nextLine();
						} else {
							System.out.println("Invalid input");
						} // End else statement
					} else {
						System.out.println("***Account ID not found***");
					} // End else statement
				} else if (accountOption == 2) {
					System.out.println("***Returning to employee menu***\n");
				} else {
					System.out.println("Invalid input");
				} // End else statement	
			} else {
				System.out.println("No accounts found.");
			} // End else statement
		} else if (option == 3) {
			System.out.println("Logging out");
			outputLogin();
		} else if (option == 4) {
			System.out.println("\n\n*****Exiting application*****");
			sc.close();
			System.exit(0);
		}else {
			System.out.println("Invalid input");
		} // End else statement
		employeeMenu(employee);
	} // End method
	
	// Method to handle console login interaction
	private void outputLogin() {
		int option = 0;
		
		System.out.println("\nWhat would you like to do?\n (1) Log in\n (2) Create a new account\n (3) Employee log in\n (4) Exit");
		
		if (sc.hasNextInt()) { 
			option = sc.nextInt();
		} // End if statement
		sc.nextLine();
		
		
		// If 1, 2, 3, or 4 is typed, performs the operation. Otherwise, starts the process over
		if (option == 1) {
			System.out.print("\nPlease enter your user name: ");
			String name = sc.next();
			sc.nextLine();
			
			VirtualDollarsUser v = null;
			try {
				v = repo.findByUsername(name);
			} catch (Exception e) {
				e.printStackTrace();
			} // End catch block
			
			if (v != null) {
				for (int i = 0; i < 3; i++) {
					System.out.print("Please enter your password: ");
					String attempt = sc.next();
					sc.nextLine();
					
					if (attempt.equals(v.getPassword())) {
						accountMenu(v);
						break;
					} // End if statement
					
					System.out.println("**Incorrect password**\n (Attempt: " + (i + 1) + ")\n");
					
					// Check if three attempts
					if (i + 1 == 3) {
						System.out.println("Too many attempts!\n\n*****Exiting application*****");
						sc.close();
						System.exit(0);
					} // End if statement
				} // End for loop	
			} else {
				System.out.println("**Username not found**");
				outputLogin();
			} // End else statement
		} else if (option == 2) {
			System.out.print("\nPlease enter your desired username: ");			
			String userName = sc.next();
			sc.nextLine();
			
			VirtualDollarsUser vdu = null;
			try {
				vdu = repo.findByUsername(userName);
			} catch (Exception e) {
				e.printStackTrace();
			} // End catch block
			
			if (vdu == null) { 
				vdu = new VirtualDollarsUser(userName);
				System.out.print("\nYour username is " + vdu.getUsername() + "\nPlease enter a password for your account: ");
				String passWord = sc.next();
				sc.nextLine();
				vdu.setPassword(passWord);
				
				createNewUser(vdu);
				
				repo.createAccount();
				VirtualDollarsAccount account = repo.getLastAccount();
				vdu.setAccount(account);
				
				repo.createUser(vdu);
				
				System.out.println("\nYour account has been successfully created!\nLog in to access your new account!\n");			
			} else {
				System.out.println("That username is unavailable.");				
			}
			outputLogin();
		} else if (option == 3) {
			System.out.print("\nPlease enter your employee username: ");
			String name = sc.next();
			sc.nextLine();
			
			VirtualDollarsEmployee employee = null;
			try {
				employee = repo.findByEmployeeName(name);
			} catch (Exception e) {
				e.printStackTrace();
			} // End catch block
			
			if (employee != null) {
				for (int i = 0; i < 3; i++) {
					System.out.print("Please enter your password: ");
					String attempt = sc.next();
					sc.nextLine();
					
					if (attempt.equals(employee.getPassword())) {
						employeeMenu(employee);
						break;
					} // End if statement
					
					System.out.println("**Incorrect password**\n (Attempt: " + (i + 1) + ")\n");
					
					// Check if three attempts
					if (i + 1 == 3) {
						System.out.println("Too many attempts!\n\n*****Exiting application*****");
						sc.close();
						System.exit(0);
					} // End if statement
				} // End for loop	
			} else {
				System.out.println("**Employee username not found**");
				outputLogin();
			} // End else statement
		} else if (option == 4) {
			System.out.println("\n\n*****Exiting application*****");
			sc.close();
			System.exit(0);
		} else {
			System.out.println("\n**Invalid response**\n Please type 1, 2, or 3.");
			outputLogin();
		} // End else statement
	} // End method
	
	private void createNewUser(VirtualDollarsUser vdu) {
		int createUserOption = 0;
		boolean firstNameDone = false;
		boolean lastNameDone = false;
		boolean dateOfBirthDone = false;
		boolean emailDone = false;
		boolean addressDone = false;
		boolean loopController = true;
		
		while (loopController) {

			if (!firstNameDone) {
				System.out.println("Enter new first name:");
				String newFirstName = sc.next();
				sc.nextLine();
				
				if (fh.nameFormatCheck(newFirstName)) {
					vdu.setFirstName(newFirstName);
					firstNameDone = true;
				} else {
					System.out.println("Invalid format");
				} // End else statement
			} // End if statement
			
			if (!lastNameDone) {
				System.out.println("Enter new last name:");
				String newLastName = sc.next();
				sc.nextLine();
				
				if (fh.nameFormatCheck(newLastName)) {
					vdu.setLastName(newLastName);
					lastNameDone = true;
				} else {
					System.out.println("Invalid format");
				} // End else statement
			} // End if statement

			if (!dateOfBirthDone) {
				System.out.println("Enter new date of birth:");
				String newDOB = sc.next();
				sc.nextLine();
				
				if (fh.dateFormatter(newDOB)) {
					vdu.setDateOfBirth(newDOB);
					dateOfBirthDone = true;
				} else {
					System.out.println("Invalid date formate. Must be mm/dd/yyyy.");
				} // End else statement																		
			} // End if statement

			if (!emailDone) {
				System.out.println("Enter new email:");
				String newEmail = sc.next();
				sc.nextLine();
				
				if (fh.emailFormatter(newEmail)) {
					vdu.setEmail(newEmail);
					emailDone = true;
				} else {
					System.out.println("Invalid email format");
				} // End else statement
			} // End if statement

			if (!addressDone) {
				System.out.println("Enter new address:");
				String newAddress = sc.nextLine();
				vdu.setAddress(newAddress);
				addressDone = true;
			} // End if statement

			if (firstNameDone && lastNameDone && dateOfBirthDone && emailDone && addressDone) {
				loopController = false;
			} // End if statement
			
			if (loopController) {
				System.out.println("Would you like to continue?\n (1) Yes\n (2) No");
				if (sc.hasNextInt()) { 
					createUserOption = sc.nextInt();
				} // End if statement
				sc.nextLine();
				if (createUserOption == 2) {
					outputLogin();
					break;
				} // End if statement
			} // End if statement
		} // End while loop
	} // End method
	
	// Main
	public static void main(String[] args) {
		System.out.println("Welcome to Virtual Dollars!");
		new VirtualDollars();
	} // End main
} // End class
