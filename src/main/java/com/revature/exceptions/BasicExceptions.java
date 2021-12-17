package com.revature.exceptions;

public class BasicExceptions {

	/*
	 * Errors and exceptions are not the same thing 
	 * 
	 * Errors terminate the program. while errors end it. 
	 * Exceptions can be handled reasonably, while errors cannot.
	 * 
	 * *If you can't think of code to handle an exception, its probably an error*
	 * 
	 * Your broadest exception (such as Exception) should not be ther first one in your catch block. If the broadest at the top, then the 
	 * ones below it will be unreachable
	 * Try-finally is acceptable and compiles.
	 * 
	 */
		public static void main(String[] args) {
		
			try {
				int [] numArray = {2,3,4,3};
				
				numArray[8] = 8;
			} catch (ArrayIndexOutOfBoundsException e){ //This is bad practice. It's good practice to list the time of exception you were trying to catch
				
				e.printStackTrace();
				
			System.out.println ("Ye stupid fook " + e.getMessage());	
			
			} catch (NullPointerException e) {
				System.out.println("null somewhere....fix it pls");
				e.printStackTrace();
			} catch (Exception e) {
				System.out.println("well this is awkward");
				e.printStackTrace();
			}finally {
				/*
				 * A catch block always executes unless you call System.exit() or there is some sort of 
				 * unrecoverable error.
				 */
				System.out.println("I always execute. ");
			}
			
		}
		
}
