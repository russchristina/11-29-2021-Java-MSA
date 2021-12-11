package com.revature;

public class Palindrome {
	
	/*
	 * This problem is simple in that you just need to turn the word around to see if it is the
	 * same as it is originally. For code though, you can also simply compare the first character
	 * with the last one and continue inwards in the same way until they are all compared.
	 */
	
	public static void main(String[] args) {
		Palindrome p = new Palindrome();
		System.out.println(p.palCheck("racecar"));
		System.out.println(p.palCheck("bob"));
		System.out.println(p.palCheck(""));
	}
	
	public boolean palCheck(String s) {
		int check = 0;
		
		if(s == null) {
			return false;
		}
		
		if (s.length() > 0) {
			for (int i = 0; i < s.length(); i++) {
				if(s.charAt(i) == s.charAt(s.length() - (i+1))) {
					check++;
				}
			}
			
			if (check >= s.length()) {
				return true;
			}
		}
		
		return false;
	}

}
