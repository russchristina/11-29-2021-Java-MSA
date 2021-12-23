package com.revature.inheritance;

/**If a concrete class is extending an abstract method, all of the abstract methods must be implemented  
 *
 * @author Kymon
 *
 */
public class MysteryNovel extends Book implements Readable{

	public static void main(String[]args) {
		
		/*
		 * Using a reference type as the supertype is known as "covariance".
		 * Note that the instance is separate from the reference, so the obhect is still constructed as a MysteryNovel. This means that the 
		 * underlying object still has access to methods that the reference does not
		 * 
		 * Your reference type has access only to that present on its type. In other words, an "Object" reference can only use methods 
		 * that are present on the object class even if the underlying 
		 */
		Object stillABook = new MysteryNovel();
		Book aMysteryNovel = new MysteryNovel();
		Readable readable = new MysteryNovel();
	
	}
	
	/**Concrete classes are REQUIRED to override the abstract methods they've inherited. 
	 * The source code will not compile if you do not do this
	 * 
	 * Concrete classes cannot declare their own abstract methods.
	 */
	
	@Override
	public void abstractMethod() {
		System.out.println("This book is a mystery novel");
		
	}

	@Override
	public void anAbstractMethod() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void anotherAbstractMethod() {
		// TODO Auto-generated method stub
		
	}

	
}
