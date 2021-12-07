package Homewrok;

public class Absolute {

	public static void main(String[] args) {
		int a = -888;
		int b = 999;
		int c = Math.abs(a);
		int d = Math.abs(b);
		int abs = c - d;
		abs = Math.abs(abs);
		System.out.println("the absolute values are"+ "a:"+ c + "and b:" +d);	
		System.out.println("The difference of values is:" + abs);
	}

}
