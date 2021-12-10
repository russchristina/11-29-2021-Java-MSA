package com.revature;

public class CastingCustomTypes {

	public static void main(String[] args) {
		
		ClassA a = new ClassA();
		ClassB b = new ClassB();
		ClassC c = new ClassC();
		
		ClassA anotherC = new ClassC();
		
		/*
		 * This is known as "upcasting". Upcasting entails moving from a child type to a
		 * super type. This is going to be safer than downcasting.
		 */
		a = (ClassA) b;
		
		/*
		 * That said, we can safely downcast if we know that the instance is of the type
		 * we wish to cast to.
		 */
		ClassB newClassBRef = (ClassB) a;
		
		/*
		 * This line of code compiles. We must note, however, that it throws a ClassCastException
		 * at runtime. This is known as downcasting. It doesn't work in this case because
		 * the reference "a" never pointed to an instance of ClassB.
		 * 
		 * NOTE: I've added the instanceof operator for some compile-time safety so that
		 * we only attempt to downcast if a points to an instance of ClassB.
		 */
		if(a instanceof ClassB) b = (ClassB) a;
		
		
	}
}

class ClassA{
	
}

class ClassB extends ClassA{
	
}

class ClassC extends ClassB{
	
}
