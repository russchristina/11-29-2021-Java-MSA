package com.revature;

public class Pyramid {
	// Method to print stars
	public static void printStars(int x){
		int i, j;
		// using 2 nested loops to create matrics of stars row*colums
		// outer loop to handle number of rows
		// x in this case
		for(i=0; i<x; i++)
		{
			
			// inner loop to handle number of columns
		
			for(j=0; j<=i; j++)
			{
				// printing stars
				System.out.print("* ");
			}

			// ending line after each row
			System.out.println();
		}
}

	// main method 
	public static void main(String args[])
	{
		int x = 9;
		printStars(x);
	}
}
