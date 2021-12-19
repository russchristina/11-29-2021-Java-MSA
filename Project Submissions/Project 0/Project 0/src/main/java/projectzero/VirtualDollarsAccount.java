package projectzero;

public class VirtualDollarsAccount {

	// Fields
	private String username;
	private String firstName;
	private String lastName;
	private String password;
	private String dateOfBirth;
	private String email;
	private String address;
	private double accountBalance;
	private boolean isTransfer = false;
	private boolean isRemoved = false;		

	// Constructor
	public VirtualDollarsAccount(String username, String password, double accountBalance) {
		this.setUserName(username);
		this.setPassword(password);
		this.setAccountBalance(accountBalance);
	} // End constructor

	// No-arg constructor
	public VirtualDollarsAccount() {

	} // End constructor

	// Getters/Setters
	public String getUserName() {
		return username;
	}

	public void setUserName(String userName) {
		this.username = userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}
	
	public void setIsTransfer(boolean isTransfer) {
		this.isTransfer = isTransfer;
	}

	// Method to add funds
	protected void addFunds(double n) {
		if (n > 0) {
			accountBalance = accountBalance + n;
			if (!isTransfer) {
				System.out.println("The funds have been successfully added to the account.");
			} // End if statement
		} else {
			System.out.println("Value must be greater than zero.");
		} // End else statement
	} // End method

	// Method to withdraw funds
	protected void removeFunds(double n) {
		isRemoved = false;
		if (n > 0) {
			if (accountBalance - n >= 0) {
				accountBalance = accountBalance - n;
				isRemoved = true;
				if (!isTransfer) {
					System.out.println("The funds have been successfully withdrawn from the account.");
				} // End if statement
			} else {
				System.out.println("Not enough funds available.");
			} // End else statement
		} else {
			System.out.println("Value must be greater than zero.");
		} // End else statement
	} // End method

	// Method to handle transfers to other accounts
	protected void transferFunds(double n, VirtualDollarsAccount v) {		
		if (n > 0) {
			if (accountBalance - n >= 0 && v.getAccountBalance() - n >= 0) {
				isTransfer = true;
				removeFunds(n);
				if (isRemoved) {
					v.setIsTransfer(true);
					v.addFunds(n);	
					v.setIsTransfer(false);
					System.out.println("The funds have been successfully transfered.");
				} else {
					System.out.println("\n***Transfer aborted***\n");
				} // End else statement
				isTransfer = false;
			} else {
				System.out.println("Not enough funds available.");
			} // End else statement
		} else {
			System.out.println("Value must be greater than zero.");
		} // End else statement
	} // End method
} // End class
