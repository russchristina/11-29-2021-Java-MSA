package com.revature.solutions;

public class LeftAlignedPyramid {
	
	public static void main(String[] args) {
		
		LeftAlignedPyramid lap = new LeftAlignedPyramid();
		lap.printPyramid(9);
	}

	public void printPyramid(int numRows) {
		
		/*
		 * You know that number of rows you need is equal to numRows.
		 */
		
		for(int i = 1; i <= numRows; i++) {
			
			for(int j = 1; j <= i; j++) {
				System.out.print('*');
			}
			
			System.out.println();
		}
	}
}
