package com.revature.solutions;

public class Palindrome {
	
	public static void main(String[] args) {
		
		System.out.println(3/4); // Java division takes the floor.
		
		Palindrome palindrome = new Palindrome();
		
		System.out.println(palindrome.determineIfPalindrome("aibohphobia"));
		System.out.println(palindrome.determineIfPalindrome(""));
		System.out.println(palindrome.determineIfPalindrome("a"));
		System.out.println(palindrome.determineIfPalindrome("civics"));

	}

	/*
	 * refer
	 * madam
	 * 
	 */
	public boolean determineIfPalindrome(String word) {
				
		/*
		 * You don't actually need to iterate over the entire String to figure out whether
		 * or not it's a palindrome. You can actually just iterate over the first half
		 * of the word and compare those characters to the last half of the characters.
		 */
		for(int i = 0; i < word.length()/2; i++) {
			if(word.charAt(i) != word.charAt(word.length() - i - 1)) {
				return false;
			}
		}
		return true;
	}
	
}
