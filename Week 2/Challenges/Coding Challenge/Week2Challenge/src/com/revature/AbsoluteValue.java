package com.revature;
/*
 * For this challenge, I researched how to get the absolute value of an int in java. In order
 * to do so, I had to refer to the Math class. I assume there is a more primitive way of coming 
 * to absolute value. Can I somehow read the abs method within the Math class?
 * 
 * At any rate, I declared two ints, found the difference, and then printed the difference with
 * the abs method included.
 */
public class AbsoluteValue {

	public static void main(String []args) {
		
		int i = 4;
		int j = 5;
		int difference = i - j;
	
		System.out.println(Math.abs(difference));
		
		
	}
	
	
	
}
