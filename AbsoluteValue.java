package com.revature;
//staring function class
public class AbsoluteValue{
	//declaring function method 
	static int function(int x ,int y){
		//perform the difference function
		int z = x-y; 
		// Absolute valus is the value without the negative sign
       if (z<0) {
		z=z*-1;
	}
		return z;
		
	};
	public static void main(String[] args) {
		//test 1 for positive value 
		System.out.println(function(5, 4));
		//test 2 for negative value
		System.out.println(function(4, 5));
		//test 3 for zero
		System.out.println(function(5, 5));
		
	}
	
};