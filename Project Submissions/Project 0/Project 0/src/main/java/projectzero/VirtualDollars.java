package projectzero;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

public class VirtualDollars {

	// Fields
	private Scanner sc;
	private NumberFormat nf;
	String uName = "Chris"; // For testing
	String pw = "pass123"; // For testing
	double bal = 300; // For testing
	VirtualDollarsAccount a = new VirtualDollarsAccount("Josh", "111", 100);

	// Constructor
	public VirtualDollars() {
		sc = new Scanner(System.in);
		nf = NumberFormat.getNumberInstance(Locale.ENGLISH);
		nf.setMaximumFractionDigits(2);
		nf.setMinimumFractionDigits(2);
		outputLogin();
	} // End constructor

	// Method to handle account menu display
	private void accountMenu(VirtualDollarsAccount v) {
		v.setAccountBalance(bal); // (temp) testing waiting database
		v.setAddress("123 Super Fun Dr"); // (temp) testing waiting database
		v.setEmail("itsami_aMario@email.com"); // (temp) testing waiting database
		v.setDateOfBirth("Jan 1, 2000"); // (temp) testing waiting database
		
		boolean loopController = true;
		
		while (loopController) {
			int option = 0;
			System.out.println("\nHello, " + v.getUserName() + ", what would you like to do?\n (1) Check account balance\n"
					+ " (2) Add funds\n (3) Withdraw Funds\n (4) Transfer funds to another account\n (5) View/edit personal information\n"
					+ " (6) Add another user\n (7) Log out\n (8) Exit");	
			if (sc.hasNextInt()) {
				option = sc.nextInt();
			} // End if statement
			sc.nextLine();
			
			if (option == 1) {		
				System.out.println("Your balance is: " + nf.format(v.getAccountBalance()));
			} else if (option == 2) {
				System.out.println("How much would you like to deposit?");		
				if (sc.hasNextDouble()) {
					double amount = sc.nextDouble();
					v.addFunds(amount);
				} else {
					System.out.println("\nInvalid input.\n");
				} // End else statement
			} else if (option == 3) {
				System.out.println("How much would you like to withdraw?");
				if (sc.hasNextDouble()) {
					double amount = sc.nextDouble();
					v.removeFunds(amount);
				} else {
					System.out.println("\nInvalid input.\n");
				} // End else statement
			} else if (option == 4) {
				System.out.println("Enter the username for the account you would to tranfer funds to.");
				String xferName = sc.next();
				/*
				 * will search all account usernames to attempt to find a match. maybe via ArrayList
				 */
				if (xferName.equals(a.getUserName())) {
					/*
					 * here we will populate transfer account data and assign it to a reference
					 */
					System.out.println("How much would you like to transfer?");
					if (sc.hasNextDouble()) {
						double amount = sc.nextDouble();
						sc.nextLine();
						v.transferFunds(amount, a); // remember to use reference account!!!!!!!
					} else {
						System.out.println("Invalid input.");
					}
				} else {
					System.out.println("An account does not exist with that username.");
				}
			} else if (option == 5) {
				
			} else if (option == 6) {
				
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
			}		
			System.out.println("\nReturning to account menu.\n");
		} // End while loop	
	} // End method
	
	// Method to handle employee menu
	private void employeeMenu() {
		/*
		 * Give employees direct access to account info without need for customer passwords. Enable account 
		 * info viewing and the ability to delete accounts.
		 * 
		 * Flag if admin and give edit privileges 
		 * 
		 * View and edit via getters/setters or similar
		 */
	} // End method
	
	// Method to handle console login interaction
	private void outputLogin() {
		int option = 0;
		
		System.out.println("\nWhat would you like to do?\n (1) Log in\n (2) Create a new account\n (3) Exit");
		
		if (sc.hasNextInt()) { 
			option = sc.nextInt();
		} else {
			sc.next();
		} // End else statement
		sc.nextLine();
		
		
		// If 1, 2, or 3 is typed, performs the operation. Otherwise, starts the process over
		if (option == 1) {
			System.out.print("\nPlease enter your user name: ");
			
			String name = sc.next();
			sc.nextLine();
			
			if (name.equals(uName)) { // Here we will implement code to search database for user name
				
				/*
				 * Possibly check if username belongs to an employee/admin
				 */
				
				VirtualDollarsAccount v = new VirtualDollarsAccount(); // (Temp) waiting database
				/*
				 * Here we will implement code to create and instance of VirtualDollarsAccount that is
				 * populated with data from the database that is associated with the user name.
				 */
				v.setUserName(name);
				v.setPassword(pw); // (Temp) waiting database
				
				for (int i = 0; i < 3; i++) {
					System.out.print("Please enter your password: ");
					
					String attempt = sc.next();
					sc.nextLine();
					
					if (attempt.equals(v.getPassword())) {
						/*
						 * Possibly handle privileged logins here
						 */
						accountMenu(v); // (Temp-ish) give access to account (for customers)
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
			/*
			 * Conditional to check database if username does not exist. Maybe populate an ArrayList with usernames 
			 * and use .contains()
			 */
			String userName = sc.next();
			sc.nextLine();
			if (userName.equals("Josh")) { // This will change to the above mentioned
				VirtualDollarsAccount vda = new VirtualDollarsAccount();
				vda.setUserName(userName);
				System.out.print("\nYour username is " + userName + "\nPlease enter a password for your account: ");
				String passWord = sc.next();
				sc.nextLine();
				vda.setPassword(passWord);
				
				/*
				 * Make a class called FormatHandler that contain methods to ensure proper formatting for personal information.
				 * The new user will need to fill out the information (First/Last name, email, DoB, etc.) which will then be
				 * assigned to the account. Ensure their is a way to loop and maybe cancel in the event there are mistakes.
				 * 
				 * Once complete, the database should be populated.
				 */
				
				System.out.println("\nYour account has been successfully created!\nLog in to access your new account!\n");			
			} else {
				System.out.println("That username is unavailable.");				
			}
			outputLogin();
		} else if (option == 3) {
			System.out.println("\n\n*****Exiting application*****");
			sc.close();
			System.exit(0);
		} else {
			System.out.println("\n**Invalid response**\n Please type 1, 2, or 3.");
			outputLogin();
		} // End else statement
	} // End method
	
	// Main
	public static void main(String[] args) {
		System.out.println("Welcome to Virtual Dollars!");
		new VirtualDollars();
	} // End main
} // End class
