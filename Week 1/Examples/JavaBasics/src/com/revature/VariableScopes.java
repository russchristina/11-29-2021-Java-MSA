package com.revature;

/*
 * A variable's "scope" determines where that variable is available for use. Simply put,
 * this is determined by the "block" in which the variable is declared.
 */
public class VariableScopes {
	
	/*
	 * You can have variables that are "instance" scoped variables. When you create
	 * an instance of a class, an instance will get its own copies of instance variables.
	 * These variables are going to the most "available" variables in terms of scope; they
	 * are in scope everywhere in this class (barring static contexts).
	 * 
	 * If you want to refer to an instance variable in any scope, it's good practice to
	 * use the "this" keyword in front of it (e.g. this.firstName).
	 * 
	 * Instance variables are not required to be initialized before use. This is because
	 * they have default values.
	 * 
	 * If the instance variable is an object (e.g. String), it is "null" by default.
	 * If the instance variable is an "int", "short", or "byte", it is "0".
	 * If the instance variable is a "long", it is "0L".
	 * If the instance variable is a "float", it is "0.0f".
	 * If the instance variable is a "double", it is "0.0D".
	 * If the instance variable is a "boolean", it is "false".
	 * If the instance variable is a "char", it is '\u0000'.
	 */
	int integer;
	String firstName;
	
	public void aMethod() {
		//Instance variables are in scope here.
		integer = 8;
		/*
		 * Local variables are only in scope within the methods in which they are declared.
		 * Note that you MUST initialize a local variable before using.
		 */
		int localVariable = 8;
		
		for(int i = 0; i < 3; i++) {
			//The scope of "i" is the for loop. You cannot use this variable outside of
			//this block.
			
			String firstName = "christina"; //this "firstName" is different from the instance "firstName"
			this.firstName = "christina"; 
			String lastName = "russ";
			localVariable = 9;
		}
	}
	
	public void anotherMethod() {
//		localVariable = 3; DOES NOT COMPILE BECAUSE localVariable IS NOT IN SCOPE HERE
		
		this.integer = 98;
	}
	
	/*
	 * Note also that parameters are local variables; this means that you can only use
	 * them within this method.
	 */
	public void methodWithParameter(String firstName) {
		//These are two different variables. One is local. One is an instance variable.
		firstName = "christina";
		this.firstName = "christina";
	}

}
