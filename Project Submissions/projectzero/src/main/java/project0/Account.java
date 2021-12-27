package project0;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;
import project0.Bidness;
import project0.implement.UserRepoImpl;
import project0.util.ConnectCloser;
import project0.util.ConnectUtil;
import project0.models.User;
import project0.repos.UserRepo;

public abstract class Account{
	
	
		
public void signUp() {
		
		Scanner scan = new Scanner(System.in);
			System.out.println("Welcome!  Please input a Username: ");
				String newUser = scan.next();
				System.out.println("Your username is: " + newUser);
			System.out.println("Please input a Password: ");
				String newPass = scan.next();
			System.out.println("Your password has been saved. Don't forget it!\nPlease enter your first and last name: ");
				String fullName = scan.next();
				scan.nextLine();
			System.out.println("Please enter the Month you were born in, 1-12: ");
				int birthMonth = scan.nextInt();
				if(birthMonth < 1 | birthMonth > 12) {
					try{ System.out.println("Invalid input. Please try again.");
					birthMonth = scan.nextInt();
					} catch(InputMismatchException e) {
						System.out.println("Invalid Input. Please enter numbers 1-12 only.");
						birthMonth = scan.nextInt(); } }
			System.out.println("Please enter the date you were born: ");
				int birthDay = scan.nextInt();
				if(birthDay < 1 | birthDay > 31) {
					try{ System.out.println("Invalid input. Please try again.");
					birthDay = scan.nextInt();
					} catch(InputMismatchException e) {
						System.out.println("Invalid Input. Please enter numbers 1-31 only.");
						birthDay = scan.nextInt();} }
				scan.nextLine();
			System.out.println("Please enter your street address: ");
				String address = scan.nextLine();
			System.out.println("Please enter your city: ");
				String city = scan.next();
				scan.nextLine();
			System.out.println("Please enter your state: ");
				String state = scan.next();
				scan.nextLine();
			System.out.println("Please enter your phone number: ");
				String phone = scan.next();
				scan.nextLine();
			System.out.println("Please review your information:\nUsername = " + newUser + 
					"\nName: " + fullName + "\nBirthdate: " + birthMonth + " / " + birthDay + "\nAddress: "
					+ address + "\n" + city + ", " + state + "\nPhone #: " + phone);
			User newAcct = new User(newUser, newPass, fullName, birthMonth, birthDay, address, city, state, phone);
			UserRepo userRepo = new UserRepoImpl();
			userRepo.save(newAcct);
			
		scan.close();
		
	}

public void login() {
	
	Scanner scan = new Scanner(System.in);
	System.out.println("Hello!\n 1: Login \n 2: Return");
	int option = scan.nextInt();
	switch(option) {
		case 1:
			System.out.println("Please enter your username:");
			String username = scan.next();
				if(username.equals(username)) {
					System.out.println("Please enter your password:");
					String password = scan.next();
					if(password.equals(password)){
						
					}
			
				}
			else {
				System.out.println("Username not recognized. Please try again."); //how do i return to the start of case 1?
			}
		case 2:
			mainMenu();
	}
	scan.close();
}
	
	public void addToBalance() {
		Scanner scan = new Scanner(System.in);
		int balance = 0;
		int added;
		String input;
		int newBalance;
		
			System.out.println("Please input the amount you'd like to add to your account:");
				added = scan.nextInt();
				while(added <= 0) {
				try{
				added = scan.nextInt();
					System.out.println("Invalid Input. Please enter positive numbers only.");
				added = scan.nextInt();
				} catch(InputMismatchException e) {
					System.out.println("Invalid Input. Please enter positive numbers only.");
				added = scan.nextInt();
			} }
				
			if(added > 0) {
				System.out.println("Your account balance is " + (balance + added) + ".");
				}
//			System.out.println("Would you like to add more? (Y/N)");
//				input = scan.next();
//								
//				if(input.equalsIgnoreCase("Y")) {
//					System.out.println("Please input the amount you'd like to add to your account:");
//					added = scan.nextInt();
//					while(added <= 0) {
//						System.out.println("Invalid Input. Please try again.");
//						added = scan.nextInt();
//					}
//						if(added > 0) {
//						newBalance = balance + added;
//						System.out.println("Your account balance is " + (balance + added) + ". Thank you!");
//						}
//					}
//				if(input.equalsIgnoreCase("N")) {// make this go back to User menu method
					System.out.println("Thank you!");
					scan.close();
					}
				 
	public void mainMenu() {
		Scanner scan = new Scanner(System.in);
				System.out.println("Welcome to Lakeview General Goods!  Please choose an option below: \n 1: Create Account\n 2: Login\n 3: Exit");
		int option = scan.nextInt();
		switch(option){
		case 1:
			signUp();
			break;
		case 2:
			login();
			break;
		case 3:
			System.out.println("Goodbye!");
			scan.close();
			break;
			}
	}			
			
	public void userMenu() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Welcome!  What would you like to do?\n 1: Update Information\n "
				+ "2: Check your Account Balance\n 3: Add a User to your Account\n "
				+ "");
		int option = scan.nextInt();
		switch(option) {
		case 1:
			
		}
	}
	
	}
	

