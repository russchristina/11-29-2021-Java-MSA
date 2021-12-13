package com.revature.challenge2;

public class Fibonacci {

	//Fibonacci sequence 1 1 2 3 5 8 13
	
public static void main (String args[]) {
		
		int num1 = 1, num2 = 1;
		 
		int sum = 0;
		
		
		for(int i = 3; i < 7; i++) {
			
			System.out.println(num1 + " " + num2);
			
			
			//Rotating the numbers that should be added to get the next
			sum = num2 + num1;
			num1 = num2;
			num2 = sum;
			
			
			
			
		}
		}

public Fibonacci getFibonacci() {
	// TODO Auto-generated method stub
	return null;
}
	
	
	}
