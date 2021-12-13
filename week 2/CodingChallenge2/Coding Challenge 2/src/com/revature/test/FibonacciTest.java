package com.revature.test;

import org.junit.Assert;

//Importing the @Test annotation
import org.junit.jupiter.api.Test;



//Importing Fibonacci from different package
import com.revature.challenge2.Fibonacci;

public class FibonacciTest {
	
	
	//Testing not null. Had to insert a not null return to the original Fibonacci class
@Test
	public void testFibonacciNotNull() {
		
		
		Fibonacci fibonacciInstance = new Fibonacci();	
		Fibonacci returnedFibonacci = fibonacciInstance.getFibonacci();
			Assert.assertNotNull(fibonacciInstance);
		
		
		}

}
