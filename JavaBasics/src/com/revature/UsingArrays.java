package com.revature;

public class UsingArrays {
	
	public static void main(String []args) {
		char firstLetterOfName = 'B';
		char secondLetter = 'r';
		char thirdLetter = 'a';
		char fourthLetter = 'n';
		char fifthLetter = 'd';
		char sixthLetter = 'o';
		char seventhLetter = 'n';
		
		/*
		 * We can instead use an array to manage all of the characters in a name together. An array allows us to use
		 * a single reference (variable) to manage multiple elements. When creating an array use "[]" braces after the 
		 * type which should be contained within the array. Please note that once an array is created, you can't change the size. 
		 * 
		 * Notice that there are many ways to initialize an array. The consistency across all 3 methods is that you have to know 
		 * the size of the array when you create it. 
		 */
		
		char[] firstName = {'B', 'r', 'a', 'n', 'd', 'o', 'n'};
		char[] lastName = new char[] {'W', 'o', 'm', 'a', 'c', 'k'};
		char[] middleName = new char[8];
		
		/*
		 * You can also have multidimensional arrays: arrays inside of another arrays.
		 * The outer array's dimensions have to be known if you're not going to initialize it immediately.
		 */
		
		char [][] twoDimensionalArray = {{'c', 'h', 'r'}, {'r', 'u', 's', 's'}};
		char [][] anotherTwoDemensionalArray = new char[3][];
		/* 
		 * In order to access all of the characters in this array, we can use the same "firstName" reference in conjunction with an
		 * index. An"index" is a number which represents a position in the array. The first index is 0.
		 */
		
		System.out.println(firstName[0]);
		System.out.println(twoDimensionalArray[1][1]);
		
		/*
		 * In order to change an element in an array:
		 */
		
		middleName[0] = 'T';
		
		/*
		 * To print the entire name, we can just a "for" loop
		 */
		for(int i = 0; i < firstName.length; i++) {
			System.out.print(firstName[i]);
		}
		
		// NEW LINE FOR PRESENTATION PURPOSE
		System.out.println();
		/*
		 * You can also use an "enhanced for loop" or a "for each loop". This is just another version of a standard loop. 
		 */
		for (char letter : firstName) { // For 
			System.out.print(letter);
			
		}
	}
	

}
