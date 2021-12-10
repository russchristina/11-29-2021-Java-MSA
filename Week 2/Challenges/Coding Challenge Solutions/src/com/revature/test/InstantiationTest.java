package com.revature.test;


import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

/*
 * Because Instantiation is in a different package, we have to import the class for use here:
 */
import com.revature.solution.Instantiation;
import com.revature.solution.Puppy;

/*
 * It is important to test your software. Without tests, you can't prove to yourself or anyone else
 * that you've written quality software. Of course, you could manually test an application, but manually
 * testing a large application doesn't scale well.
 * 
 * As such, most languages have support for testing frameworks. Testing frameworks make testing
 * applications easier by providing out-of-the-box utilities for creating tests and doing basic
 * set up and tear down for suites.
 * 
 * JUnit happens to be the most popular testing framework for Java applications. JUnit is currently
 * on version 5.
 * 
 * The @TestInstance annotations instructs Java to create a single instance of this test class to
 * run all tests as we have specified the "PER_CLASS" approach.
 */

@TestInstance(Lifecycle.PER_CLASS)
public class InstantiationTest {
	
	/*
	 * Declaring this variable here at the instance scope so that it is in scope in all of our
	 * test methods.
	 */
	Instantiation instantiationInstance;
	
	/*
	 * If you want to perform some basic setup that would be shared across all of the tests,
	 * there are annotations that support this. For instance, in all of our tests below, we 
	 * created an instance of "Instantiation". We could have created a single instance before
	 * running all of the tests.
	 * 
	 * The @BeforeAll annotation indicates to Java that it should invoke the "setUp" method once
	 * before running any of the tests. This method only runs once.
	 */
	
	@BeforeAll
	public void setUp() {
		this.instantiationInstance = new Instantiation();
	}
	
	/*
	 * The @BeforeEach annotation indicates to Java that is should invoke the setUpBeforEach method
	 * once before every single test. If there are 3 tests, this method runs 3 times - once before
	 * each test.
	 */
	@BeforeEach
	public void setUpBeforeEach() {
		
	}

	
	/*
	 * Let's say that we want to test the "getPuppy" method we created in the Instantiation class.
	 * Let's start by creating a JUnit test method. Any methods written for the purpose of JUnit
	 * testing should have a "void" return type.
	 * 
	 * @Test is what we refer to as an "annotation" We can annotate methods, fields, and even entire
	 * classes in Java.
	 * 
	 * The purpose of an annotation to tell Java how to handle a specific field, method, or class.
	 * When Java sees an annotation, it effectively knows that you've marked a field, etc. for special
	 * use. In this case, Java knows that the method we've annotation is a JUnit test. This means
	 * that the method needs to be treated specially. In essence, the method will be invoked for us
	 * as a JUnit test when we run our test class later.
	 * 
	 * Please that note that is recommended for developers to follow TDD (Test Driven Development).
	 * Test Driven Development entails writing your tests before writing your source code. Writing
	 * tests is about detecting bugs in your source code. As such, if you have tests written already,
	 * you can immediately run them to detect bugs.
	 * 
	 * NOTE: Annotations are not specific to JUnit.
	 */
	
	@Test
	public void testPuppyInstantiationNotNull() {
		/*
		 * Our very first consideration should be calling the getPuppy method. We have to be able
		 * to call the method to test it. In other words, I need to create an instance of the
		 * "Instantiation" class just to call the method.
		 */
		
		Puppy returnedPuppy = this.instantiationInstance.getPuppy();
		Assert.assertNotNull(returnedPuppy);
		}
	
	
	@Test
	@Disabled("ignoring for demo purposes") //Tests that are annotated with @Ignore will not be run.
	public void testPuppyInstantiationIsAPuppy() {
		//Tests that have not been implemented should be made to fail as otherwise you'll get false positives.
//		Assert.fail("Test not yet implemented.");
		
		Puppy returnedPuppy = this.instantiationInstance.getPuppy();
		Assert.assertTrue(returnedPuppy instanceof Puppy);
	}
	
	/*
	 * There are also "after" version of our "before" annotations.
	 */
	
	/*
	 * AfterEach runs once after ever single single @Test. This means that if there are 3 tests,
	 * this method will run once after each test - 3 times.
	 */
	@AfterEach
	public void tearDownAfterEach() {
		
	}
	
	/*
	 * AfterAll runs a single time after all tests have been executed. It runs once.
	 */
	@AfterAll
	public void tearDown() {
		
	}
}
