package pyramid;

import java.util.Scanner;

public class Pyramid {

	
	void makePyramid(int n) {
		String row = "";
		for (int i = 0; i < n; i++) {
			row += "*";
			System.out.println(row);
		}
	}

	public static void main(String[] args) {		
		Scanner s =  new Scanner(System.in);
		System.out.print("Enter row height: ");
		if (s.hasNextInt()) {
			int n = s.nextInt();
			if (n > 0) {
				new Pyramid().makePyramid(n);
			} else if (n == 0) {
				System.out.println("That is the height of the ground (a.k.a. our starting height). \nThis makes "
						+ "building a pyramid quite the challenge indeed...");
			} else {
				System.out.println("I refuse to dig.");
			}
		} else {
			System.out.println("Error: invalid input");
		}
		s.close(); 
	}
}
