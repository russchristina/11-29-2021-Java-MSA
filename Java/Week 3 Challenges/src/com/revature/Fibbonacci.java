package com.revature;

public class Fibbonacci {
	
	/*
	 * This sequence is created by adding the two previous values together, starting with 
	 * two 1s. This means, apart from memorizing the sequence, a person just needs to go
	 * through the sequence one position at a time until they reach the position they are 
	 * looking for. 
	 */
	
	public Fibbonacci() {
		
	}
	
	public static void main(String[] args) {
		Fibbonacci f = new Fibbonacci();
		System.out.println(f.sequenceAdd(7));
	}
	
	public int sequenceAdd(int n) {
		int x = 1;
		int y = 1;
		int z = 0;
		
		
		
		if (n > 2) {
			for (int i = 3; i <= n; i++) {
				z = x + y;
				x = y;
				y = z;
			}
			return z;
		} else if (2 >= n && n > 0) {
			return 1;
		} else {
			return 0;
		}
		
	}
}
