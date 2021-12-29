package com.revature;

import java.util.Scanner;

public class MainPage {

	
	//variables
	int choice;
	String picked;
	boolean exit = false;
	Scanner scanner = new Scanner(System.in);
	LoginPage LoginPageObj = new LoginPage();
	SignUp SignUpObj = new SignUp();

	public void displayChoices() {

		
		
		//Main page display menu
		try {
			System.out.println("Welcome to MooV-Night Streaming Services");
			System.out.println(" ");
			System.out.println("Please choose from one of the following:");
			System.out.println("1) Login");
			System.out.println("2) Sign Up");
			System.out.println("3) Exit");

			choice = scanner.nextInt();

			switch (choice) {
			//Chooses to login
			case 1:
			
		
				
				break;

			case 2:
				//Chooses to sign up to create account
				SignUpObj.getSignUp();
				System.out.println("Successfully created account!");
				System.exit(choice);
				break;
				//Chooses to exit application
			case 3:
				picked = "Exit";
				break;
				
				//If user enters a number outside of 1-3
			default:
				System.out.println("Invaild choice. Try again!");
				System.out.println(" ");
				System.out.println(" ");
			}
			//If user tries to enter a letter instead of a numeric value
		} catch (Exception e) {
			System.out.println("Invaild. Enter a numeric value. ");
			System.out.println(" ");
			System.out.println(" ");
		}
		
	}
	
	}	
