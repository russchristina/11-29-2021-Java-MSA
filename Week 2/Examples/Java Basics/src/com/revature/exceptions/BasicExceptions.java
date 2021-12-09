package com.revature.exceptions;

/*
 * An exception interrupts the expected/normal flow of our application. There is built-in
 * exception handling in Java as these built-in exceptions make it easy to correct mistakes
 * that we may have made while writing our application.
 * 
 * We will not use the terms "error" and "exception" interchangeably in Java because there is
 * an "Exception" class and an "Error" class. These are sibling classes that both inherit directly
 * from the "Throwable" class.
 * 
 * We differentiate between Error and Exception because exceptions can be reasonably handled
 * whereas errors cannot.
 */
public class BasicExceptions {

	public static void main(String[] args) {
		
		//the firstName reference is "null", which means that it doesn't point to an object.
		String firstName = null;
		
		int[] numArray = {2, 3, 4, 3};
		/*
		 * This line of code causes an exception to be "thrown" at runtime.
		 * 
		 * That said, there are ways to handle exceptions in Java. By "handling", I simply
		 * mean that we can course-correct our application when something should go wrong.
		 * 
		 * We do this by using "try-catch" blocks. A "try" block contains the offending
		 * code (code which caused an exception to be thrown). A "catch" block contains
		 * the code you wish to run if such an exception is thrown.
		 * 
		 * Please note that your broadest exception type should NOT be caught first as
		 * your code won't compile as some of the code beneath this catch block will
		 * become unreachable.
		 * 
		 * You should also note that try-finally is an acceptable combination that 
		 * compiles.
		 */
		
		try {
			firstName = firstName.concat("Christina");
			numArray[8] = 8;
		}
		catch(NullPointerException e) {
			System.out.println("Catching a NullpointerException.");
		}catch(ArrayIndexOutOfBoundsException e){
			e.printStackTrace();
			System.out.println("This is the catch blocking running after the exception"
					+ " has been caught.");
		}finally {
			/*
			 * A catch block always executes unless you call System.exit() or there is
			 * some sort of unrecoverable error.
			 */
			System.out.println("I always execute.");
		}
				
		System.out.println("Executing a statement after accessing my array elements.");
		
	}
	
	/*
	 * This causes a StackOverflowError if you call it. This can't be reasonably recovered
	 * from.
	 */
	public void recursiveMethod() {
		recursiveMethod();
	}
}
