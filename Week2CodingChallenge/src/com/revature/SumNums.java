package com.revature;

public class SumNums {
	/*
	 * getTotal method takes any number of args by way of varargs
	 * loops through array, adding each number to total
	 */
	public static int getTotal(int...numbers) {
		int total = 0;
		for (int nums : numbers) {
			total += nums;
		}
		return total;
	}
}
