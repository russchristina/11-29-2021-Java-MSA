package com.revature.designpatterns.practice;

public class Casting {

	/*
	 * We can cast one type as another in Java. THis can type useful when you need to move between
	 * the data types. That said, please be aware that casting can lead to data loss
	 */
	public static void main(String[] args) {
		int anInt=89;
		long aLong = 2222132333322223L;
		double aDouble = 86.5;
		byte aByte= 8;
		byte anotherByte= 89;
		
		
		/*
		 * Sometimes implicit casting will occur whether or not you want it to happen
		 * 
		 * This implicit casting is the result of 'primitive promotion". When doing arithmetic with primitives, the result will automatically
		 * be promoted to that of a larger data type. This means that the reference used to store the value will always be a larger type than 
		 * the operands (int).
		 * 
		 * When doing arithmetic with shorter types like byte and short, you need to store the result in a larger data type.
		 * This is not true with the larger types like int
		 */
		
		anInt = (int) aLong; //This is a valid cast (it compiles), but it results in data loss.
		
	
		
	}

}
