package com.revature.designpatterns.practice;

public class CastingCustomTypes {
	public static void main(String[] args) {
		
		ClassA a = new ClassA();
		
		ClassB b = new ClassB();
		
		ClassC c = new ClassC();
		
		ClassA anotherC = new ClassC();
		//ClassC anotherA = new ClassA();
		
		/*
		 * This line of code compiles. We must note, however, that it throws a ClassCastException
		 * at runtime. This is known as downcasting. It doesn't work in this case because the reference "a" never pointed to an instance
		 * of ClassB
		 * 
		 */
		//b = (ClassB) a;
		
		
		/*
		 * You can use the instaceOf; in an if/else statement to test
		 * if (a instanceof ClassB) b = (ClassB) a;
		 */
		
		/*
		 * This is known as upcasting. Upcasting entails moving from a child type to a super type. This is going to be safer
		 * than downcasting.
		 */
		a = b;
		
		/*
		 * We can safely downcast if we know that the instance is of the appropriate type we wish to cast to
		 */
		ClassB newClassBRef = (ClassB) a ;
	}
}



class ClassA{
	
}


class ClassB extends ClassA{
	
}

class ClassC extends ClassB{
	
}