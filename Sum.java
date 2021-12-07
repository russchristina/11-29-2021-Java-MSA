package revature;

public class Sum {
	/* The first step that I would do is create a method signature that allows the user pass
	 * any number of integers which would be Varargs. Then I would make a local variable to start at zero.
	 * From there I need a loop that will iterate through whatever number of integers that
	 * are chosen and provide a sum. From there return the sum. From there test it the method out
	 * by call the method in the the main method and test a random number of integer inputs.
	 */
	public static int sumAll(int... numbers) { 
		int sum = 0; 
		for (int n : numbers) {
			sum += n; 
		}
		return sum; 
	}

	public static void main(String[] args) {
		System.out.println(sumAll(1, 2, 3, 45));
	}
}
