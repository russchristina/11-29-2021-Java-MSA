package com.revature.inheritance;

/**Just like an abstract class, an interface is abstract. You can type the "abstract" keyword before the "interface" keyword,
 * but doing so is redundant
 * 
 * @author Kymon
 *
 */
public interface Readable{
	int someNum = 0;
	/*
	 * In fact, everything about interfaces is assumed to be abstract
	 */
    void anAbstractMethod();
	
	/*
	 * All methods on interfaces are assumed to be public and abstract, so you do not have to write "public" or "abstract"
	 * before a method
	 */
	void anotherAbstractMethod();
	/*
	 * If you want a method to have an implementation on an interface, you can achieve this in one of two ways:
	 * 
	 * 1) Use the "default" keyword
	 * 2) Use the "static" keyword
	 * 	In fact, if you either of these keywords, the methods MUST have implementations or the code won't compile.
	 * 
	 */
	
	default void aConcreteMethod() {
		//This method has an implementation
		
	}
	
	static void anotherConcreteMethod() {
		
	}

	/**Concrete classes are REQUIRED to override the abstract methods they've inherited. 
	 * The source code will not compile if you do not do this
	 * 
	 * Concrete classes cannot declare their own abstract methods.
	 */
	void abstractMethod();
	
}
