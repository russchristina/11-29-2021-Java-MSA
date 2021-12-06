package com.revature;

public class VariableArguments {

	/*
	 * You can also use varargs with your "main" method signature in place of the standard
	 * array syntax for "args".
	 */
	public static void main(String...args) {
		
		String[] groceryItems = {"milk", "eggs", "bread"};
		
		printGroceryItem(groceryItems[0]);
		printTwoGroceryItems(groceryItems[0], groceryItems[1]);
		printGroceryItems(groceryItems);
		printGroceryItemsWithVarArgs("milk", "cheese", "eggs", "yogurt", "cake", "chips");
		printGroceryItemsWithVarArgs(); //You can even pass no arguments.
		
	}
	
	/*
	 * We could write a method that takes a single grocery item and prints it:
	 */
	static void printGroceryItem(String groceryItem) {
		System.out.println(groceryItem);
	}
	
	/*
	 * We could write a method that takes two grocery items and prints them. Seems a little
	 * redundant, but okay...
	 */
	static void printTwoGroceryItems(String groceryItem1, String groceryItem2) {
		System.out.println(groceryItem1);
		System.out.println(groceryItem2);
	}
	
	/*
	 * This method is a bit better as it takes an array of Strings. This means that we
	 * can print any number of items from the array.
	 */
	static void printGroceryItems(String[] groceryItemsList) {
		for(String item : groceryItemsList) {
			System.out.println(item);
		}
	}
	
	/*
	 * All of the methods above are somewhat inconvenient as we've had to replicate the
	 * same logic for different numbers of parameters. That is not ideal. Fortunately,
	 * there is a syntax in Java that allows us to get around this: varargs (variable args).
	 * 
	 * A variable argument is treated like an array, so you can iterate over it using a for
	 * loop. Note that that you don't lose the option to pass an array in as an argument,
	 * but you can also just in multiple strings.
	 * 
	 * Two Rules To Remember:
	 * 1. You can only have one variable argument in a method signature
	 * 2. The variable argument has to be the last parameter
	 */
	
	static void printGroceryItemsWithVarArgs(String...groceryItemsList) {
		for(String item : groceryItemsList) {
			System.out.println(item);
		}
	}
	
}
