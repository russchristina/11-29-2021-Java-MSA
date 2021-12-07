package com.revature.solution;

public class AbsoluteValue {

	public static void main(String[] args) {
		
		AbsoluteValue absoluteValue = new AbsoluteValue();
		System.out.println(absoluteValue.calculateAbsoluteValueOfDifference(0, 7));
		System.out.println(absoluteValue.calculateAbsoluteValueOfDifference(8, 4));

	}

	/*
	 * Write a method that returns the absolute value of the difference of any two
	 * integers. Since we're only the returning the absolute value of 2 numbers, we
	 * know that we need to define just 2 parameters.
	 */

	public int calculateAbsoluteValueOfDifference(int num1, int num2) {
		Math.abs(num1);
		
		/*
		 * You can use the ternary operator to solve this problem as well.
		 */
//		return (num1 - num2 >= 0) ? (num1 - num2):(num2 - num1);

		/*
		 * Because there are no restrictions on what numbers can be passed in, there are
		 * several possibilities:
		 * 
		 * 0, 7 -> -7 
		 * 8, 4 -> 4
		 */

		int difference = num1 - num2;

		/*
		 * You never want to return a negative number, so you should check the sign and
		 * if the sign is negative, just multiple the result by -1.
		 */

		if (difference >= 0)
			return difference;

		else
			return difference * -1;
	}
}
