package com.revature.inheritance;

/*
 * An abstract is defined by using the "abstract" keyword. An abstract class is a class
 * which is ALLOWED to declare abstract methods.
 * 
 * As abstract classes are not required to have concrete methods, you're not allowed
 * to create instances of abstract classes. Abstract classes are meant to be extended -
 * not instantiated.
 * 
 * NOTE: "final" + "abstract" = BAD. It doesn't compile as it doesn't make sense for
 * a class or method to be final and abstract.
 */
public abstract class Book {

	/*
	 * NOTE: The "abstract" keyword + {} = BAD. It doesn't compile.
	 */
	
	public void concreteMethod() {
		//Concrete method
	}
	
	//Abstract method: An abstract method is simply a method that does NOT have a method
	//implementation. It will receive its implementation in a concrete childclass. In fact,
	//it will receive its implementation in the FIRST concrete class to inherit the method.
	public abstract void abstractMethod();
}


