package projectzero;

import java.text.NumberFormat;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VirtualDollarsAccount {

	// Fields
	private int accountId;
	private double accountBalance;
	private boolean isTransfer = false;
	private boolean isRemoved = false;	
	private NumberFormat nf;
	private UserAccountRepositoryImpl repo;
	private Logger myLogger;

	// Constructor
	public VirtualDollarsAccount(int accountId, double accountBalance) {
		this.accountId = accountId;
		this.accountBalance = accountBalance;
		nf = NumberFormat.getNumberInstance(Locale.ENGLISH);
		nf.setMaximumFractionDigits(2);
		nf.setMinimumFractionDigits(2);
		repo = new UserAccountRepositoryImpl();
		myLogger = LoggerFactory.getLogger("infoLogger");
	} // End constructor

	// Getters/Setters
	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(double accountBalance) {
		if(accountBalance >= 0) {
			this.accountBalance = accountBalance;
			repo.updateAccountData(this);
		} else {
			System.out.println("Value may not be less than zero.");
		} // End else statement
	}
	
	public void setIsTransfer(boolean isTransfer) {
		this.isTransfer = isTransfer;
	}

	// Method to add funds
	public void addFunds(double n) {
		if (n > 0) {
			accountBalance = accountBalance + n;
			repo.updateAccountData(this);
			if (!isTransfer) {
				System.out.println("The funds have been successfully added to the account.");
				myLogger.info("$" + n + " has been deposited into account id: " + this.getAccountId());
			} // End if statement
		} else {
			System.out.println("Value may not be less than zero.");
		} // End else statement
	} // End method

	// Method to withdraw funds
	public void removeFunds(double n) {
		isRemoved = false;
		if (n > 0) {
			if (accountBalance - n >= 0) {
				accountBalance = accountBalance - n;
				isRemoved = true;
				repo.updateAccountData(this);
				if (!isTransfer) {
					System.out.println("The funds have been successfully withdrawn from the account.");
					myLogger.info("$" + n + " has been withdrawn from account id: " + this.getAccountId());
				} // End if statement
			} else {
				System.out.println("Not enough funds available.");
			} // End else statement
		} else {
			System.out.println("Value must be greater than zero.");
		} // End else statement
	} // End method

	// Method to handle transfers to other accounts
	public void transferFunds(double n, VirtualDollarsAccount v) {		
		if (this.getAccountId() != v.getAccountId()) {
			isTransfer = true;
			removeFunds(n);
			if (isRemoved) {
				v.setIsTransfer(true);
				v.addFunds(n);
				v.setIsTransfer(false);
				System.out.println("The funds have been successfully transfered.");
				myLogger.info("$" + n + " has been transfered from account id: " + this.getAccountId() + " to account id: " + v.getAccountId());
			} else {
				System.out.println("\n***Transfer aborted***\n");
			} // End else statement
			isTransfer = false;
		} else {
			System.out.println("You may not transfer to the same or shared account.");
		} // End else statement
	} // End method
	
	@Override
	public String toString() {
		return "VirtualDollarsAccount [ Account Id = " + accountId + ", Account Balance = " + nf.format(accountBalance) + " ]";
	} // End toString
} // End class
