package com.revature.test;


import static org.junit.Assert.assertTrue;


import org.junit.Assert;
import org.junit.jupiter.api.Test;



/*Pyramid is in a different package, we need to import for use
 */

import com.revature.challenge2.Pyramid;



public class PyramidTest {
	
	
	@Test
	public void testPyramidNotNull() {
		
		
	Pyramid pyramidInstance = new Pyramid();	
	Pyramid returnedPyramid = pyramidInstance.getPyramid();
		Assert.assertNotNull(pyramidInstance);
	
	
	}

	@Test
	public void testPyramidIsCorrect() {
		
		
		//Assert.fail("Not ready"); = If you haven't written test yet
		
		Pyramid pyramidInstance = new Pyramid();	
		Pyramid returnedPyramid = pyramidInstance.getPyramid();
		Assert.assertTrue (returnedPyramid instanceof Pyramid);
		
		
		
		
	}
	

}
