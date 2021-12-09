package com.revature.inheritance;

public class Seafood extends Food{
	
	public static void main(String[] args) {
		
		Seafood seafood = new Seafood(3, 15, 10, 100);
		System.out.println(seafood);
		seafood.nourishBody();
		
	}
	
	/*
	 * The Seafood class can still declare its own unique fields.
	 */
	private int gramsMercury;

	public Seafood() {
		//Call to the parent class's constructor = a call to the Food class's constructor.
		super();
	}
	
	public Seafood(int gramsCarbohydrates, int gramsProtein, int gramsFat, int gramsMercury) {
		super(gramsCarbohydrates, gramsProtein, gramsFat);
		this.gramsMercury = gramsMercury;
	}
	
	
	/*
	 * Child classes inherit methods from their parent classes. That said, sometimes child
	 * classes want to provide their own unique implementations of methods they have
	 * inherited. This is fine, BUT there are rules to follow when doing so:
	 * 
	 * 1. The method signature has to be the same (e.g. same return type, same method
	 * name)
	 * 
	 * 2. The access level of the method CANNOT be more restrictive than the parent's
	 * access level for the method.
	 * 
	 * Note that providing a different implementation for a method that has been inherited
	 * is known as "overriding". This is a type runtime polymorphism.
	 * 
	 * Also note that the @Override annotation, while not required, tells Java that the
	 * method that is annotated is supposed to overriding a parent' class's method.
	 * It introduces some compile-time safety as this code does not compile if we are
	 * not overriding a parent class's method properly.
	 */
	@Override
	protected void nourishBody() {
		System.out.println("This is the version of nourish body that is called from"
				+ " within the Seafood class.");
	}
	
	/*
	 * As the @Override annotation suggest, this method is inherited. It is inherited from
	 * the Object class. It simply returns a String representation of your object; yes, you
	 * can change the formatting of the returned String.
	 */
	@Override
	public String toString() {
		return "Seafood [gramsMercury=" + gramsMercury + ", gramsCarbohydrates=" + gramsCarbohydrates
				+ ", gramsProtein=" + gramsProtein + ", gramsFat=" + gramsFat + "]";
	}
	
	
}
