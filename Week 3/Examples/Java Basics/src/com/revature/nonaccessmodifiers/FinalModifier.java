package com.revature.nonaccessmodifiers;

import java.util.ArrayList;
import java.util.List;

public final class FinalModifier {
	
	/*
	 * If you are using final with an instance variable, note that the code will NOT compile if you
	 * do not initialize the variable either:
	 * 
	 * 1) inline as you're declaring the variable
	 * 2) in the constructor for the class
	 * 3) in an instance initializer
	 */
	final int finalInt = 0;

	public static void main(String[] args) {
		/*
		 * The "final" keyword can also be used with reference variables. A common misconception
		 * about the final keyword was it is used with reference variables is that it prevents a
		 * the underlying object from being changed.
		 */
		final List<String> myStrings = new ArrayList<>();
		
		/*
		 * Notice that I can add to my final ArrayList, thereby changing its size. This is perfectly
		 * acceptable.
		 */
		myStrings.add("string 1");
		myStrings.add("string 2");
		myStrings.add("and yet another string");
		
		/*
		 * What I cannot do, however, is change where the reference variable points. It must always
		 * point to the same object in memory. Note that the following code doesn't compile because
		 * it attempts to reassign the "myStrings" reference.
		 */
//		myStrings = new ArrayList<>();
	}
	
}

/*
 * Will NOT compile if FinalModifier is marked as a final class
 */
//class FinalChild extends FinalModifier{
//	
//}

class ParentClass{
	public final void doParentTask() {
		
	}
}

class ChildClass extends ParentClass{
	
	/*
	 * This will NOT compile as we are not allowed to override a parent method that is marked as
	 * final.
	 */
//	public void doParentTask() {
//		System.out.println("Overriding parent method.");
//	}
}
