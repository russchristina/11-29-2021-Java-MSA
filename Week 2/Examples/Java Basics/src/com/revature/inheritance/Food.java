package com.revature.inheritance;

/*
 * This class is a basic model for food as all food has some ratio of macronutrients.
 * 
 * NOTE: If you do NOT want a class to be extended by any other classes, use the "final"
 * keyword before the "class" keyword.
 */
public /*final*/ class Food {
	
	/*
	 * I'm making these fields protected so that they
	 * will be directly accessible from within child classes.
	 */
	protected int gramsCarbohydrates;
	protected int gramsProtein;
	protected int gramsFat;
	
	public Food() {
		//Call to the Object class's constructor; note that this call happens explicitly
		//even if it is not written here. If you are explicitly making this call (writing
		//it yourself), it MUST be the first statement within the constructor.
		super();
	}
	
	public Food(int gramsCarbohydrates, int gramsProtein, int gramsFat) {
		this.gramsCarbohydrates = gramsCarbohydrates;
		this.gramsProtein = gramsProtein;
		this.gramsFat = gramsFat;
	}
	
	protected void nourishBody() {
		System.out.println("I'm food and I'm giving bodies energy!");
	}
	
	/*
	 * If you do not want a method to ever be overridden in the child class, you can use
	 * the "final" keyword to prevent it from being overridden.
	 */
	protected final void beDigested() {
		System.out.println("I am being digested!");
	}
}
