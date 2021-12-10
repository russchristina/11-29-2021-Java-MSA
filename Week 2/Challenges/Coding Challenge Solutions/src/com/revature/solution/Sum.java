package com.revature.solution;

public class Sum {
	
	public static void main(String []args) {
		
		Sum sumInstance = new Sum();
		System.out.println(sumInstance.add(4, 5, 3, 2, 2));
		System.out.println(sumInstance.add());
		System.out.println(sumInstance.add(7, 70, 222));
	}

	/*
	 * We need to write a method that takes any number of integers and returns the sum of all of
	 * those integers.
	 * 
	 * E.g. Given 3, 2, 4, 2, 2, the method should return 13
	 * Or Given 2, 4, the method should return 6
	 * 
	 * Coding challenges are somewhat about understanding the tools a language provides. That said,
	 * we know that Java provides a syntax called "variable arguments" that will allow us to pass
	 * in any number of arguments of a certain type. We can use this to our advantage.
	 */
	
	public int add(int...numbers) {
		
		/*
		 * We need to create a holder variable for the sum of all of the numbers that are passed in.
		 * A good logical starting value for this variable called "sum" is 0.
		 * 
		 * As a reminder, local variables must be initialized before being used.
		 */
		int sum = 0;
		
		/*
		 * We need to iterate over the variable arguments in order to access each number present.
		 * This is how we'll access each number individually.
		 */
		
		for(int i : numbers) {
			sum += i; //this syntax is short for "sum = sum + i"; this works with /, -, and *
		}
		
		return sum; //remember to return the sum
		
	}
}
