package com.revature;

public class AbsoluteDifference {
	public static int getDifference(int num1, int num2) {
		if (num1 >= num2) {
			return num1 - num2;
		}
		else {
			return num2 - num1;
		}	
	}
}
