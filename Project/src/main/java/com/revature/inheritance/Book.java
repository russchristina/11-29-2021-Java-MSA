package com.revature.inheritance;

/**
 * An Abstract class is deefined by using the "abstract" keyword. An abstract class is a class which is allowed to declare abstract methods. 
 * @author Kymon
 *
 *Abstract class are not rwquired to have concrete methods, you're not allowed to create instances of abstract classes. 
 *So if your abstract method is Book, Book b = new Book(); will not compile. Abstract classes are meant to be extended, not instantiated
 *
 *Note: You can NEVER have a final abstract class. Abstract classes are meant to be exteneded, and final class or methods cannot be
 */
public abstract class Book {
	//you can have concrete methods on an abstract class
	public void notAbstractMethod() {
		//Concrete Method
	}

	//Abstract method: An abstract method is simply a method that does NOT have a method
	//It will receive its implementation in a concrete childclass. In fact, it will receive its implementation in the FIRST
	//concrete class to inherit the method 
	public abstract void abstractMethod();
}
