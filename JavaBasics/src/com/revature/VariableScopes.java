package com.revature;

/*
 * A Variable's' "scope" determines where that variable is available for use. SImply put, this is determined
 * by the "block" in which the variable is declared. 
 */

public class VariableScopes {
	
	/*
	 * You can have variables that are "instance" scoped variables. When you create an instance of a class, an instance 
	 * will get its own copies of an instance variables.
	 * These variables are going to be the most "available" variables in terms of scope; They are in scope everywhere in 
	 * this class(barring static contexts).
	 * 
	 * If you want to refer to an instance variable in any scope, it's good practice to use the "this" keyword in front of it
	 * 
	 * Instance variables are not required to be initialized before use.This is because that have a default values.
	 * 
	 * If the instance variable is an object (e.g. String), it is "null" by default.
	 * If the instance variable is an "int", "short", or "byte", it is "0".
	 * If the instance variable is a "long", it is "0L".
	 * If the instance variable is a "float", it is '0.0f".
	 * If the instance variable is a "double", it is "0.0D".
	 * If the instance variable is a "boolean", it is false.
	 * If the instance variable is a"char", it is '\u000'.
	 */
	
	int interger = 2222;
	String firstName = "Brandon";
	
	{
		int blockVariable =8; // you can have a valid block here but this variable is only to be used in this block only. these are called 
		// these are called an "instance initializer".
	}
	
	public void aMethod() {
		
		// Instance variables are in scope here
		interger =8;
		/*
		 * Local variable are only in scope within the methods in which they are declared. 
		 * Note that you MUST initialize a local variable before using.
		 */
		int localVariable = 22;
		
		for(int i = 0; i < 3; i++) {
			// The scope of "i" is the for loop. You can not use this variable outside of this block.
			String firstName = "Brandon"; // this "firstName" is different from the instance variable
			this.firstName = "Brandon";
			String lastName = "russ";
			localVariable = 9;
		}
	}
	
	// Variables will not be recognized outside of the method or scope
	
	
	/*
	 * Parameters are local variables; This means that you can only use them within this method.
	 */
	public void methodWithParameter(String firstName) {
		// These are two different variables. One is local. one is an instance variable. 
		firstName = "Brandon";
		this.firstName = "Brandon";
	}
}
