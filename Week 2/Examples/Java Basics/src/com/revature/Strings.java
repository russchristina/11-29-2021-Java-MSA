package com.revature;

/*
 * Please note that you are NOT allowed to have child classes of the String class. This
 * is because the String class is a "final" class.
 */
public class Strings /*extends String*/{
/*
 * By now, we realize that the String type is a data type that represents text. Note that
 * the underlying implementation for String in Java is an array of characters.
 * 
 * Please note that the String type is NOT a primitive data type; it's a class. Strings are
 * objects - not primitives.
 */
	private String firstName;
	private String lastName;
	
	public static void main(String []args) {
		
		Strings stringsInstance = new Strings(); //me creating an instance of my own class
		//The first syntax here is the most common; it creates a string using a "string literal"
		String middleName = "Shalonda";
		String middleNameAgain = "Shalonda";
		String middleNameOnceAgain = new String("Shalonda");
		//The second syntax (using the String constructor) is considered bad practice.
		String mom = new String("Kimberly");
		
		/*
		 * Strings are actually stored in their own place in memory on the heap known as
		 * the "string pool". Java will actually check this pool for existing strings so
		 * that it can minimize the number of strings that are created.
		 * 
		 * That said, this will only be true if you use the string literal syntax. The
		 * second syntax (the constructor) will always force the creation of a new string).
		 */
		
		//PROOF OF CONCEPT
		System.out.println(middleName == middleNameAgain);
		System.out.println(middleName.equals(middleNameAgain));
		
		System.out.println(middleName == middleNameOnceAgain);
		System.out.println(middleName.equals(middleNameOnceAgain));
		
		/*
		 * As another note about strings in Java, there are "immutable". This means that
		 * you cannot change a String once you have created it.
		 * 
		 * Many of the methods on the String API actually just return brand new strings.
		 * If you wish to use those returned strings, you have to create a reference to
		 * store them.
		 */
		
		String favoriteCook = "Sanji";
		favoriteCook = favoriteCook.concat(" is the best cook."); //you have to do this
		System.out.println(favoriteCook);
		
		System.out.println(favoriteCook.charAt(0));
		System.out.println(favoriteCook.indexOf("the"));
		String numValueAsString = String.valueOf(8796);
		System.out.println(numValueAsString);
		System.out.println(favoriteCook.length());
	}
}
