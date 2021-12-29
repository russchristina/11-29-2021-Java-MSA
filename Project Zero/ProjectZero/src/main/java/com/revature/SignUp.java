package com.revature;

import java.util.Scanner;


public class SignUp {
	CreateAccount createaccount = new CreateAccount();
	Scanner scanner = new Scanner(System.in);
		
	public void printData(CreateAccount createaccount) {
		System.out.println(createaccount.getFirstName());
		System.out.println(createaccount.getLastName());
		System.out.println(createaccount.getEmail());
		System.out.println(createaccount.getUserName());
		System.out.println(createaccount.getPassword());
	
	}

 	
		public void getSignUp () {
		
		System.out.print("Enter first name: ");
		String firstName = scanner.nextLine();
		createaccount.setFirstName(firstName);
		
		System.out.print("Enter last name: ");
		String lastName = scanner.nextLine();
		createaccount.setLastName(lastName);
		
		System.out.print("Enter e-mail: ");
		String email = scanner.nextLine();
		createaccount.setEmail(email);
		
		System.out.print("Enter username: ");
		String userName = scanner.nextLine();
		createaccount.setUserName(userName);
		
		System.out.print("Enter password: ");
		String password = scanner.nextLine();
		createaccount.setPassword(password);
		
		}
		
	}

class CreateAccount {
	private String firstName;
	private String lastName; 
	private String email;
	private String userName;
	private String password;
	
	
	
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}