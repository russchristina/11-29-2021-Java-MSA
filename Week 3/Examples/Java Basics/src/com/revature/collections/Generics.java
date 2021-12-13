package com.revature.collections;

import java.util.ArrayList;
import java.util.List;

public class Generics {

	public static void main(String []args) {
		
		/*
		 * Please note that Generics add compile-time safety to our code. Specifying a type using
		 * generics allows us to impose a specific type on our List in this case. Generics can be
		 * used with all of our Collection implementations.
		 */
		List<String> myList = new ArrayList<>();
		
		myList.add("christina");
		/*
		 * We've commented out the following code because it doesn't compile; we're no longer allowed
		 * to add non-String types to this list.
		 */
//		myList.add(2);
//		myList.add(3.4f);
		
		/*
		 * Now let's create a list of numbers (ints). Note that generics do NOT support primitives.
		 * 
		 * Fortunately, Java has built-in "wrapper classes" that provide object representations of
		 * our primitive data types. The wrapper classes are as follows:
		 * 
		 * int -> Integer
		 * double -> Double
		 * float -> Float
		 * boolean -> Boolean
		 * char -> Character
		 * long -> Long
		 * byte -> Byte
		 * short -> Short
		 */
		
		List<Integer> myNums = new ArrayList<>();
		
		/*
		 * You might be wondering why we can, for example, add int literals using the "add" method
		 * if we're not allowed to use primitive data types. This is allowed because Java "autoboxes"
		 * primitives as their object representations for us. That said, you should not box primitives
		 * on your own as it is bad practice.
		 */
		myNums.add(3);
		myNums.add(7);
		
		/*
		 * As an aside, Java will unbox values for you as well. In this case, Java is unboxing the 
		 * Integer objects as their primitive values so that we can perform simple arithmetic and store
		 * the result in an int reference.
		 */
		
		Integer num1 = 2;
		Integer num2 = 4;
		
		int result = num1 + num2;
		
		
	}
}
