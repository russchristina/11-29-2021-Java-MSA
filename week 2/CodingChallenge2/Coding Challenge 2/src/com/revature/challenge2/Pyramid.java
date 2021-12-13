package com.revature.challenge2;

public class Pyramid {

	
	//Giving message of what I'm trying to do. Which is print
	//out a star pyramid going to the left. 
	public static void main (String []args) {
		
		System.out.println("Left-aligned pyramid in stars!");
	
	
	
	//Calling "createPyramidStars" which I named (int n). I have created another main and realized
	//I had two and then move it inside the {} of main.
		int n = 10;
		createPyramidStars(n);
		
	}
	
	
	public static void createPyramidStars(int n) {
		
		//n=10 this is giving the number of lines in the pyramid
		for (int a = 0; a < 10; a++) {
			//
			for (int b = 0; b < 10 - a; b++) {
				
				System.out.print(" ");
			}
			// this is for the columns to make sure each row has the correct number of *
			for (int c = 0; c<=a; c++) {
				
				//Print stars using *
				System.out.print("*");
			}
			//Starts new line
			System.out.println();
		}
	}


	public Pyramid getPyramid() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
