package Challenge2;

public class Fibonacci {
	
	public static void main(String[] args) {
		
		// set variable to pass through method
		int n = 0;
		
		System.out.println(fib(n));
	}
		
		// Static method to calculate fibonacci
		static int fib(int n) {
			
			// Set initial variables
			int a = 0, b = 1, c;
			
			// Set initial variable so that 0 will calculate to 0
			if (n == 0)
				return a;
			
			// For loop to iterate number from main method to carry out the fibonacci sequence.
			for (int i = 2; i <= n; i++) {
				
				c = a + b;
				a = b;
				b = c;
			}
			
			return b;
	}
}


