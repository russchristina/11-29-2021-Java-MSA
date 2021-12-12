package com.revature;
public class Sum{
	//Initialize variables 
	static int Summation;
	int y;
	//creating Summer method using Varargs to take any numbers of integers 
	//holding the first number in x 
	//
	static void Summer(int x, int... manyInts ){
		// Y is to sum all other number ( except  the first one) 
		int y = 0; 
		//looping through the args that will store in an array manyInts
		for (int i = 0; i < manyInts.length; i++) {
			  
			  y= y + manyInts[i];
			};
			// summing the first int with the others 
		Summation=x +y;
		System.out.println(Summation);
	};
	//main method

	public static void main(String[] args) {
		//test with two arg
		Summer(1,2);
		//test with multiple args 
		
		
		Summer(1,5,6,9);
	};
};