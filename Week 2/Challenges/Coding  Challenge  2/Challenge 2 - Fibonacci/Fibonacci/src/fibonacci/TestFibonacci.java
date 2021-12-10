package fibonacci;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestFibonacci {

	Fibonacci f;
	
	@BeforeAll
	public void setUp() {
		f = new Fibonacci();
	}
	
	@Test
	public void testFibonacciNull() {
		Assert.assertNotNull(f.calculateFibonacci(0));
	}
	
	@Test
	public void testCalculateFibonacci() {		
		Assert.assertEquals(13, f.calculateFibonacci(7));
	}
	
	@Test
	public void testCalculateFibonacciLow() {		
		Assert.assertEquals(0, f.calculateFibonacci(1));
	}
	
}
