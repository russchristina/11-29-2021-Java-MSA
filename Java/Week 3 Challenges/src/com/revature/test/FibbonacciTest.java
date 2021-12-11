package com.revature.test;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import com.revature.Fibbonacci;

class FibbonacciTest {

	Fibbonacci f = new Fibbonacci();
	
	@Test
	void testFNotNull() {
		Assert.assertNotNull(f);
	}
	
	@Test
	void testFibIsFib() {
		Assert.assertTrue(this.f instanceof Fibbonacci);
	}
	
	@Test
	void testFibPrinter() {
		System.out.println(f.sequenceAdd(1));
	}
	
	@Test
	void testFibPrinterTwo() {
		System.out.println(f.sequenceAdd(7));
	}
	
	@Test
	void testFibPrinterThree() {
		System.out.println(f.sequenceAdd(-1));
	}

}
