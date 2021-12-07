package absolueValue;

import java.util.Scanner;

public class AbsoluteValue {

	// Method to calculate the absolute value of the difference between to integers
	public int findAbsoluteValue(int first, int second) {
		int absoluteDifference = Math.abs(first - second);
		return absoluteDifference;
	}
	
	// Main
	public static void main(String[] args) {
		// Instantiate
		AbsoluteValue a = new AbsoluteValue();
		Scanner sc = new Scanner(System.in);
		
		// Gather values from user
		System.out.println("To find the absolute value of the difference of two integers.");
		System.out.println("Enter first integer:");
		int first = sc.nextInt();
		System.out.println("Enter second integer:");
		int second = sc.nextInt();
		
		// Display result
		System.out.println("The absolute value of the difference is: " + a.findAbsoluteValue(first, second));
		sc.close();
	}
}
