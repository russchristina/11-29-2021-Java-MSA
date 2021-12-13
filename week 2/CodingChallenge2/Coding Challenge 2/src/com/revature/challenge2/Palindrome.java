package com.revature.challenge2;

public class Palindrome {
 
	public static void main (String [] args) {
		
		// Ex: "radar" is a palindrome so if put in as = to "q" it will return as a palindrome.
		//"tag" is not a palindrome so if put in as = to "q" it will return as not a palindrome.
		
		
		String q = "radar";
		
		
		
		String reverse = "";
		for (int i = q.length() - 1; i >= 0; i--) {
			reverse += q.charAt (i);
			System.out.println(reverse);
		}
		
		
		
		
		boolean palindrome = true;
		for (int i = 0; i < q.length(); i++) {
			
			//If the char isn't the same at reverse as it is on "q" it is not a palindrome
			if(q.charAt(i) != reverse.charAt(i)) {
				palindrome = false;
				
			}
		}
		//PALINDROME
		if (palindrome) {
			System.out.println("It is a palindrome!");
		}
		
		//If not true it will return this statement!
		else {
			System.out.println("It is NOT a palindrome!");
		}
		
	}

	public Palindrome getPalindrome() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
