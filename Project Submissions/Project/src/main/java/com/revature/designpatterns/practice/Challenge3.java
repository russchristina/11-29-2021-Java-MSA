package com.revature.designpatterns.practice;
import java.util.Scanner;
/**
 * ## Challenge 3: Absolute Value
Write a method that returns the absolute value of the difference of any two integers.

Process: Not exactly sure what the absolute value is, but my brief research tells me that it is basically the distance that a number is away
from 0. This means I'll need a way to handle positive and negative numbers. Since this asking for the difference between two integers,
I'll also need a way to hold both. 

 * @author Kymon
 *
 */
public class Challenge3 {
	public static void main(String...args) {

		Scanner value = new Scanner(System.in);
		int firstValue = 1;
		int secondValue = 1;
		System.out.println("Enter the first value: ");
		if(value.hasNextInt()) {
			firstValue = value.nextInt();
			value.nextLine();
			System.out.println("Enter the second value: ");
			secondValue = value.nextInt();
			
			if (firstValue - secondValue > 0) {
			positiveCounter(firstValue, secondValue);
			}
			else if (firstValue - secondValue < 0) {
					negativeCounter(firstValue, secondValue);
		}
		else {
			System.out.println("invalid input. Please restart");
			
		}
	
		
		}

		}
	/*
	 */
	public static int positiveCounter(int firstValue, int secondValue) {
		
	
		
			int result = Integer.MAX_VALUE;
			int counter = 0;

			do
			{
				result--;
				counter++;
			}
			while (result > 0);
			System.out.println("The absolute value for the difference of " + firstValue + " and " + secondValue + " is "
					+ counter);
			return counter;
	
	}
	
	public static int negativeCounter(int firstValue, int secondValue) {
		int result = firstValue + secondValue;
		int counter = 0;

		do
		{
			result++;
			counter++;
		}
		while (result < 0);
		System.out.println("The absolute value for the difference of " + firstValue + " and " + secondValue + " is "
				+ counter);
		return counter;
		
	}
	
}
	

