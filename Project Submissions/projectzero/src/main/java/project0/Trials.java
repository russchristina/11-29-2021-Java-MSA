package project0;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.slf4j.Logger;

import project0.LakeviewLogger;

public class Trials {
	
static	Trials t = new Trials();


	
	public static void main(String[] args) {
		t.addToBalance();
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
					System.out.println("Invalid Input. Please enter positive numbers only.");
				added = scan.nextInt();
				} catch(InputMismatchException e) {
				
					System.out.println("Invalid Input. Please enter positive numbers only.");
					added = scan.nextInt();
			} 
				}
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
				
					}

//	public void addMore() {
//		
//		Scanner scan = new Scanner(System.in);
//		int balance = 0;
//		String input;
//		System.out.println("Please input the amount you'd like to add to your account:");
//		int addBalance = scan.nextInt();
//			while( addBalance <= 0 ) {
//				System.out.println("Invalid Input. Please try again.");
//				addBalance = scan.nextInt();
//			}
//			if(addBalance > 0) {
//			System.out.println("Your account balance is " + (balance + addBalance) + ". Thank you!");
//			}
//	scan.close();
//	}
		
		
}	
	




