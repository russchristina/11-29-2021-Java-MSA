package Challenge2;

public class Palindrome {
	
	// Main method to set string to pass through aPalindrome method and print results
	public static void main(String[] args) {
		
		String s = "radar";
		
		if (aPalindrome(s))
			
			System.out.println("True");
		
		else
			
			System.out.println("False");
		
	}
	
	static boolean aPalindrome(String s) {
		
		
		// Set variables as pointers i = front of string, j = end of string
		int i = 0, j = s.length() -1;
		
		
		// Use while loop to check to make sure that every character is checked from the string from start to end
		while (i < j) {
			
			
			// Set if statement to check if characters don't match while iterating through string
			if (s.charAt(i) != s.charAt(j))
				return false;
				
			// Increments i while decrementing j to check all characters in string	
			i++;
			j--;
			
		}
		
		return true;
	}

}
