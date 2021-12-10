package com.revature.acessmodifers;

/*
 * In Java, there 4 access modifiers: public, protected, default, and private.
 * Access modifiers determine where we can directly access certain methods, fields, and even
 * classes.
 * 
 * Note that classes have access levels as well. Also note that top-level classes can only
 * have public or default access.
 */
public class AccessModifiers {
	
	/*
	 * The syntax for using access modifiers is as follows. Note that "default" access
	 * is given if no access modifier is explicitly specified. If you write the keyword
	 * "default" before a member (e.g. field, method) of a class, the code will not compile
	 * as the "default" keyword has nothing to do with access in Java.
	 */
	
	/*
	 * A public member is directly accessible from everywhere.
	 */
	public int fieldOne;
	/*
	 * A protected member is directly accessible within the same class in which it is
	 * declared, from within the same package, and from within child classes.
	 */
	protected String fieldTwo;
	/*
	 * A default member is directly accessible from the within the same class in which
	 * it is declared and from within the same package. Default access is also referred
	 * to as "package private".
	 */
	boolean fieldThree;
	/*
	 * A private member is directly accessible from within the same class in which it
	 * is declared.
	 */
	private String fieldFour;
	
	public AccessModifiers() {
		this.fieldOne = 1; //This is considered direct access.
		this.fieldTwo = "some string"; //Direct access
		this.fieldThree = true; //Direct access
		this.fieldFour = "this is only allowed within this class because this member is private";
	}
	
	public void doAThing() {
		
	}
	
	public AccessModifiers getInstance() {
		AccessModifiers instance = new AccessModifiers();
		instance.fieldOne = 100; //This is considered direct access.
		return instance;
	}

}
