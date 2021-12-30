package com.revature.solutions;

public class Fibonacci {
	
	public static void main(String[] args) {
		
		Fibonacci fib = new Fibonacci();
		/*
		 * This happens to work out for me because my loop doesn't run when a negative
		 * value is passed. But ideally we should be doing error handling for bad input.
		 */
		System.out.println(fib.calculateFibonacciNumber(-100));
		
		System.out.println(fib.calculateFibonacciNumberRecursively(6));
	}

	public int calculateFibonacciNumber(int fibNum) {
		
		/*
		 * This is a given as you don't calculate the first 2 numbers.
		 */
		if(fibNum == 1 || fibNum == 2) {
			return 1;
		}
		
		/*
		 * We know the first two fibonacci numbers already: 1 and 1. We're imagining
		 * that we're two fibonacci sequence numbers into our sequence.
		 */
		
		int currentNumber = 1;
		int previousNumber = 1;
		int answer = 0;
		
		for(int i = 2; i < fibNum; i++) {
			answer = currentNumber + previousNumber;
			previousNumber = currentNumber;
			currentNumber = answer;
		}
		
		return answer;
	}
	
	/*
	 * Optional recursive method:
	 */
	
	public int calculateFibonacciNumberRecursively(int fibNum) {
		
		if(fibNum <= 0) return 0;
		
		if(fibNum == 1) return 1;
		
		/*
		 * If you pass in 4 to this method: f(4) = f(3) + (f2) + f(1)
		 */
		return calculateFibonacciNumberRecursively(fibNum - 1) + calculateFibonacciNumberRecursively(fibNum - 2);
	}
}
