package com.revature;

public class AbsoluteValue {
	
	public int calculateAbsoluteValueofDifference(int num1, int num2) {
		return (num1 -num2 >=0) ?
	
		 int difference = num1 - num2;
		 
		 /*
		  * Never want negative number so you should multiply result be -1.
		  */
		 
		 if(difference >=0) return difference;
		 
		 else return difference * -1;
	}

}
