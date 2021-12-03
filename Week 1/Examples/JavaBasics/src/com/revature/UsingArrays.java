package com.revature;

public class UsingArrays {

	public static void main(String []args) {
		/*
		 * Would you want to have to use your name in
		 * an application like this (as individual letters)?
		 */
		char firstLetterOfName = 'C';
		char secondLetter = 'h';
		char thirdLetter = 'r';
		char fourthLetter = 'i';
		char fifthLetter = 's';
		char sixthLetter = 't';
		char seventhLetter = 'i';
		char eighthLetter = 'n';
		char ninthLetter = 'a';
		
		/*
		 * We can instead use an array to manage all of these characters in a name
		 * together. An array allows us to use a single reference (variable) to manage
		 * multiple elements. When creating an array, use the "[]" braces after the type
		 * which should be contained within the array. Please note that once an array is
		 * created, you can't change its size.
		 * 
		 * Notice that there are many ways to initialize an array. The consistency across
		 * all 3 methods is that you have to know the size of the array when you create it.
		 */
		
		char[] firstName = {'C', 'h', 'r', 'i', 's', 't', 'i', 'n', 'a'};
		char[] lastName = new char[] {'R', 'u', 's', 's'};
		char[] middleName = new char[8];
		
		/*
		 * You can also have multidimensional arrays: arrays inside of other arrays.
		 * The outer array's dimensions have to be know if you're not going to initialize
		 * it immediately.
		 */
		
		char[][] twoDimensionalArray = {{'c', 'h', 'r'}, {'r', 'u', 's', 's'}};
		char[][] anotherTwoDimensionalArray = new char[3][];
		
		/*
		 * In order to access all of the characters in this array, we can use the same
		 * "firstName" reference in conjunction with an index. An "index" is a number
		 * which represents a position in the array. The first index is 0.
		 */
		
		System.out.println(firstName[0]);
		System.out.println(twoDimensionalArray[1][1]);
		
		/*
		 * In order to change an element in an array:
		 */
		
		middleName[0] = 'S';
		
		/*
		 * To print the entire name, we can just use a for loop. Note that "length"
		 * is a property of an array which tells you how many elements are the in array.
		 */
		
		for(int i = 0; i < firstName.length; i++) {
			System.out.print(firstName[i]);
		}
		
		//NEW LINE FOR PRESENTATION PURPOSES
		System.out.println();
		
		/*
		 * You can also use an "enhanced for loop" or a "for each loop". This is just
		 * another version of a standard for loop.
		 */
		for(char letter : firstName) { //for each char letter in the firstName array
			System.out.print(letter); //print the letter
		}
	}
}
