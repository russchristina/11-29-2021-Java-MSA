package com.revature.inheritance;

/*
 * Just like an abstract class, an interface is abstract. You can type the "abstract"
 * keyword before the "interface" keyword, but doing so is redundant/unnecessary. As such,
 * we are ALLOWED to have abstract methods on our interface.
 */
public interface Readable {
	
	/*
	 * You can have variables/fields on an interface, BUT they must be public, static, and
	 * final.
	 */
	public static final int someNum = 10;

	/*
	 * In fact, everything about interfaces is ASSUMED to be abstract.
	 */
	abstract void anAbstractMethod();
	
	/*
	 * All methods on interfaces are assumed to be public and abstract, so you do not
	 * have to write "public" or "abstract" before a method.
	 */
	void anotherAbstractMethod();
	
	void abstractMethod();
	
	/*
	 * If you want a method to have an implementation on an interface, you can achieve
	 * this in one of two ways:
	 * 
	 * 1) Use the "default" keyword
	 * 2) Use the "static" keyword
	 * 
	 * In fact, if you use either of these keywords, the methods MUST have implementations
	 * or the code won't compile.
	 */
	
	default void aConcreteMethod() {
		//This method has an implementation
		
	}
	
	public static void anotherConcreteMethod() {
		//This method has an implementation
	}
	
}

/*
 * Yes, child interfaces are acceptable and common.
 */
interface ReadableSubinterface extends Readable{
	
}
