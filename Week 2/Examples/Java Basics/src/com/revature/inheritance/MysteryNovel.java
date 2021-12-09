package com.revature.inheritance;

/*
 * Concrete classes are NOT ALLOWED to declare abstract methods.
 * 
 * You can only extend one class in Java, but you can implement as many interfaces as you
 * want to.
 * 
 * 
 */
public class MysteryNovel extends Book implements Readable{
	
	public static void main(String[] args) {
		
		/*
		 * Using a super type as the reference type is known as "covariance".
		 * Note that the instance is separate from the reference, so the object is
		 * still constructed as a MysteryNovel. This means that the underlying object
		 * still has access to methods that the reference does not.
		 * 
		 * Still, it is worth noting that your reference type has access only to the methods
		 * that are present on its type. In other words, an "Object" reference can only access
		 * methods that are present on the object class even if the underlying instance it points
		 * to has access to several more methods.
		 */
		Object stillABook = new MysteryNovel();
		Book aMysteryNovel = new MysteryNovel();
		Readable readable = new MysteryNovel();
	}

	/*
	 * Concrete classes are REQUIRED to override the abstract methods they've inherited.
	 * The source code will not compile if you do not do this.
	 */
	@Override
	public void abstractMethod() {
		System.out.println("This book is a mystery novel.");
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
