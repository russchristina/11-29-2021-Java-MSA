package revature;

public class AbsValue {

	/* To create a method that returns the absolute value of the difference of any two integers
	 * you need to make sure that if the difference of those two integers is negative or less 
	 * than zero you convert that number into a positive. To do this you simply have to multiply 
	 * that sum by -1.
	 * In this method FindAbsoluteValue we check to see if a number is negative and
	 * if that number is negative we multiply that number by -1 to turn that number
	 * positive thus finding the absolute value.
	 */
	public static int findAbsoluteValue(int number) {
		if (number < 0) {
			number *= -1;
		}
		return number;
	}

	public static void main(String[] args) {
		int a = 7;
		int b = -13;
		System.out.println(findAbsoluteValue(b - a));
	}
}
