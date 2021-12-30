package com.revature.lambdas;

/*
 * A Functional Interface is an interface which contains one AND ONLY ONE abstract method.
 * 
 * Note that you can optionally use the @FunctionalInterface annotation to introduce some compile-
 * time safety as this code will not compile if the interface is not actually a functional interface.
 * This prevents us from accidentally changing our FunctionalInterface in a way that makes it not a
 * Functional Interface.
 */

@FunctionalInterface
public interface MyFunctionalInterface {

	void beAbstract(); //an abstract method
	
//	void anotherAbstractMethod();
	
	/*
	 * Note that the presence of concrete methods does not void this interface as a functional
	 * interface. The rule is simply that there can only be one ABSTRACT method. Predicate
	 */
	public default void aConcreteMethod() {
		
	}
}
