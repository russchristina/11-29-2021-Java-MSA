package com.revature.nonaccessmodifiers;

/*
 * The "static" modifier can be applied to fields and methods. As a fun fact, it can be applied to
 * inner/nested classes, but you don't need to know this.
 */
public class StaticModifier {
	
	//Instance variable
	public int staticModifierInt;
	private String staticModifierString;
	
	//Static variable. Note that unlike instance variables, a new copy of a static variable is NOT
	//created with each instance. A static variable is shared across all instances. As such, we say
	//that static variables belong to the class.
	public static int actuallyAStaticInt;

	public static void main(String[] args) {
		
		StaticModifier staticModifier = new StaticModifier();
		
		beStatic();
	}
	
	public StaticModifier() {
		//All this constructor does is increment the static variable.
		actuallyAStaticInt++;
		//We'll also increment the instance int field for comparison.
		this.staticModifierInt++;
	}
	
	/*
	 * When a method is static, you do NOT need an instance of the class in which it exists to call
	 * it. In fact, it is bad practice to use an instance of the class to call it.
	 */
	public static void beStatic() {
		/*
		 * If you have a static method or field, you don't need to create an object/instance to 
		 * access that method or field. This is because anything that is static is available the
		 * minute the application starts running. The same cannot be said about instance variables;
		 * those only exist if you create an instance of the class. 
		 * 
		 * As such, you cannot access instance non-static members from within a static context.
		 * This is why the commented out code below doesn't compile.
		 */
//		staticModifierInt = 0;
//		this.staticModifierString = "Christina";
		
		actuallyAStaticInt = 0;
	}
	
	/*
	 * This is not a static method.
	 */
	public void doNotBeStatic() {
		beStatic();
		actuallyAStaticInt = 0;
	}
}
