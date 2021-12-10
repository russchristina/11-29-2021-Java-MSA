package com.revature;

public class ObjectClass{

	/*
	 * We've been working with objects in Java since day 1 of Java on the curriculum.
	 * Whenever we use the "new" keyword and a class's constructor, we're creating an
	 * object.
	 * 
	 * An is: 1) an instance of a class and 2) data stored somewhere in memory
	 * 
	 * Any class that you create in Java is implicitly inheriting from a class called
	 * Object. This class provides a baseline of functionality for all of our types as it
	 * defines commonly used methods (e.g. toString, equals, hashCode).
	 */
	public static void main(String[] args) {
		//In fact, you can create an instance of Object:
		
		Object obj = new Object();
		
		Object anotherObjReference = new Person();
	}
}
