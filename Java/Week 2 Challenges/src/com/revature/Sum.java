package com.revature;

public class Sum {
	
	/*
	 * Adding numbers together in real life is simple, you simply 
	 * take them one at a time and add them together. Likewise, 
	 * in Java you simply take the series of numbers and add them 
	 * together, it is just a bit more complex reading that list.
	 * I used a for each loop that goes through each number in the 
	 * array and adds them together.
	 */
	
	public static void main(String[] args) {
		int answer = 0;
		int[] nums = {1, 3, 5, 9, 15, 42, 50};
		answer = addNums(nums);
		System.out.println(answer);
	}
	
	public static int addNums(int[] nums) {
		int sum = 0;
		
		for (int i : nums) {
			sum += i;
		}
		
		return sum;
	}
}