package com.revature;

public class Casting {

/*
 * We can cast one type as another in Java. This can type useful when you need to move
 * between the data types. That said, please be aware that casting can lead to data loss.
 */
	public static void main(String[] args) {
		
		int anInt = 89;
		short aShort = 87;
		short anotherShort = 890;
		byte aByte = 8;
		byte anotherByte= 89;
		long aLong = 33343434343L;
		double aDouble = 87.5;
		boolean aBoolean = true;
		
		// This is a valid cast (it compiles), but it results in data loss.
		anInt = (int) aLong;
		
		/*
		 * This does not compile because it doesn't make any sense to try to cast a boolean
		 * as a numeric value as booleans are either "true" or "false".
		 */
//		aLong = (long) aBoolean;
		
		//Sometimes implicit casting will occur (regardless of whether or not you want this 
		//to happen).
		
		/*
		 * This implicit casting is the result of "primitive promotion". When doing
		 * arithmetic with primitives, the result will automatically be promoted to that
		 * of a larger data type. This means that the reference used to store the result
		 * will always be a larger type than the operands (int).
		 */
		
		int resultByte = aByte + anotherByte;
		
		int resultShort = aShort + anotherShort;
		
		System.out.println(anInt);
		
		
	}
}
