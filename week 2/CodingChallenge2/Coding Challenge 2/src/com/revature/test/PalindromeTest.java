package com.revature.test;

import org.junit.Assert;

import org.junit.jupiter.api.Test;

import com.revature.challenge2.Palindrome;

public class PalindromeTest {

	


	@Test
	public void testPalindromeNotNull() {
		Palindrome palindromeInstance = new Palindrome();
		Palindrome returnedPalindrome = palindromeInstance.getPalindrome();
		Assert.assertNotNull(palindromeInstance);
		
		
	}
	

	
}
