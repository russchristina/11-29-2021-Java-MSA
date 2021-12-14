package palindrome;

import java.util.ArrayList;

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

		ArrayList<Integer> arrLi = new ArrayList<>();
		arrLi.add(2);
		arrLi.add(1);
		arrLi.add(3);
		System.out.println(arrLi);
		arrLi.sort(null);
		System.out.println(arrLi);
	}
}
