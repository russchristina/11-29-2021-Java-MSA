package com.revature.test;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import com.revature.Palindrome;

class PalindromeTest {
	
	Palindrome p = new Palindrome();

	@Test
	void testPalNotNull() {
		Assert.assertNotNull(p);
	}
	
	@Test
	void testPalIsPal() {
		Assert.assertTrue(p instanceof Palindrome);
	}
	
	@Test
	void testPal() {
		System.out.println(p.palCheck("racecar"));
	}
	
	@Test
	void testPalShort() {
		System.out.println(p.palCheck("bob"));
	}
	
	@Test
	void testPalNone() {
		System.out.println(p.palCheck(""));
	}
	
	@Test
	void testPalNull() {
		System.out.println(p.palCheck(null));
	}

}
