package palindrome;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestPalindrome {

	Palindrome p;
	
	@BeforeAll
	public void setUp() {
		p = new Palindrome();
	}
	
	@Test
	public void testIsPalindromeNull() {
		Assert.assertNotNull(p.isPalindrome("Test"));
	}
	
	@Test
	public void testIsPalindromeTrue() {
		Assert.assertTrue(p.isPalindrome("Aibohphobia"));
	}
	
	@Test
	public void testIsPalindromeTrueWithSpaces() {
		Assert.assertTrue(p.isPalindrome("Was it a cat I saw"));
	}
	
	@Test
	public void testIsPalindromeFalse() {
		Assert.assertFalse(p.isPalindrome("Test"));
	}
}
