package com.revature;

public class AbsoluteValue {
	
	/*
	 * In real life, this would be easier to do by taking the larger
	 * of the two integers and subtracting the smaller one, but in
	 * Java, you can achieve this by using the Math.abs() function to 
	 * make sure that the answer you get by subtracting x from y is 
	 * the absolute value of that difference no matter what integers
	 * you pass in.
	 */

	public static void main(String[] args) {
		int answer = absVal(-5100, 420);
		System.out.println(answer);
	}
	
	public static int absVal(int a, int b) {
		int val = a - b;
		val = Math.abs(val);
		return val;
	}
}