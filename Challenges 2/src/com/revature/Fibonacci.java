package com.revature;
//creating class for sequence 
public class Fibonacci {
	//method for solution logic 
	static int Fibo(int n){
		//eliminating zeros and negative numbers 
	if (n<=1) {
		return n;} else {
			//Recursion 
		return Fibo(n-1)+Fibo(n-2);}
	}
	
	
	public static void main (String args[]){
	int n = 7;
	System.out.println(Fibo(n));
	}
}
