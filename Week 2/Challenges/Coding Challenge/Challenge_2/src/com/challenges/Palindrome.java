package com.challenges;

public class Palindrome {
	
	public boolean isPalindrome(String input) {
		/*
		 * This method checks that an input fits the criteria for being a palindrome.
		 * To be a palindrome, a string should contain the same letters, in order, reading left to right or right to left.
		 * With this in mind, I have solved the problem with a boolean method using the String API. With String.replaceAll(), I used regex 
		 * to remove all whitespace, and non alphabetical characters. Then, the string is converted to lower case to ensure no issues
		 * arise in matching. Once I have processed the string to be workable, I iterate through 
		 * the half the string, checking the opposite position to match. If a character does not match at any point, the method returns false.
		 * If the loop completes without a return, the method returns true.
		 */
		input = input.replaceAll("[\\W\\s\\d]", "").toLowerCase();
		System.out.println(input);
		
		for (int i = 0; i < input.length()/2; i++) {
			if(input.charAt(i) != input.charAt(input.length() - i - 1)) {
				return false;
			}
		}
		return true;
	}
}
