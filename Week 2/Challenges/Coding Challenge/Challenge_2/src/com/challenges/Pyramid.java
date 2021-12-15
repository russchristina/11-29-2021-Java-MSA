package com.challenges;
/*
 * PRINT A PYRAMID OF ASTERISKS
 */
public class Pyramid {
	/*
	 * For this challenge, I used a nested for loop to accomplish
	 * the goal. The reason for this is that for each iteration in
	 * the loop, an asterisk must be printed "i" times. For example, an input
	 * of 9 would mean the loop must be incremented 9 times and each
	 * increment needs to print an asterisk times the current increment. Since you cannot
	 * write something like System.out.print("*" * i) in Java, a nested loop
	 * solves the issue.
	 */
	public void printPyramid(int num) {
		for (int i = 0; i < num; i++) {
			for (int j = 0; j <= i; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
	}
}
