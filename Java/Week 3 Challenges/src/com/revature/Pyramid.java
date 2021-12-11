package com.revature;

public class Pyramid {
	public int size;
	
	public Pyramid(int n) {
		size = n;
	}
	
	public Pyramid() {
		size = 0;
	}
	/*
	 * If I were to solve this problem without using code, I would simply take the number of rows
	 * needed and create each row one at a time to create the pyramid, increasing the number
	 * of dots per one each time.
	 */
	public static void main(String[] args) {
		
		printPyramid((int) (Math.random() * 10));
	}
	
	public static void printPyramid(int num) {
		if (num == 0) {
			System.out.println("Size = 0");
		} else {
			for(int i = 1; i <= num; i++) {
				for(int j = 0; j < i; j++) {
					System.out.print("*");
				}
				System.out.println("");
			}
		}
	}
}
