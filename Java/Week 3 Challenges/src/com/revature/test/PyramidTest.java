package com.revature.test;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.revature.Pyramid;

class PyramidTest {
	Pyramid pyr;
	
	@BeforeAll
	void PyramidSetup() {
		pyr = new Pyramid((int) (Math.random() * 10));
	}
	
	@Test
	void testPyrIsPyr() {
		Assert.assertTrue(this.pyr instanceof Pyramid);
	}
	
	@Test
	void testPyramidNotNull() {
		Assert.assertNotNull(pyr);
	}
	
	@Test
	void testPyramidPrinter() {
		pyr.printPyramid(pyr.size);
	}

}
