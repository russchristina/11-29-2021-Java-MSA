package com.challenges;

public class Fibonacci {
	/*
	 * The Fibonacci Sequence is a sequence of numbers in which each number in the sequence, greater than 1, is an addition 
	 * of the two numbers preceding it.
	 */

	public int sequence(int num) {
		// Since 1 is not handled by the loop logic, I have initialized the variables to represent their values as they would be
		// after the 2nd number and before the 3rd number in the sequence.
		int total = 1;
		int holder1 = 0;
		int holder2 = 1;
		
		// this if else statement handles the first 2 numbers in the sequence, as there cannot be 2 numbers preceding 0 or 1,
		// we handle them manually.
		if (num == 0) {
			return 0;
		}
		else if (num == 1) {
			return 1;
		}
		// starting at the third number in the sequence (represented here by i = 1) we can now start adding the previous numbers
		// and creating our sequence.
		for (int i = 1; i < num; i++) {
			total = holder1 + holder2;
			holder1 = holder2;
			holder2 = total;
		}
		
		return total;
	}
}
