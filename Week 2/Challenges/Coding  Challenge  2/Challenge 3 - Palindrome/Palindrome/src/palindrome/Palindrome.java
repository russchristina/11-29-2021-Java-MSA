package palindrome;

public class Palindrome {

	protected boolean isPalindrome(String s) {
		
		s = s.toLowerCase().replace(" ", "");
		
		for(int i = 0, j = s.length() - 1; i < j; i++, j--) {
			if(s.charAt(i) != s.charAt(j)) {
				return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args) {

	}
}
