package Challenge2;

public class Pyramid {
	
	public static void main(String[] args) {
		
		// Set integer to be passed through
		int n = 7;

		// For loop to iterate number or asterisks
	    for (int i = 1; i <= n; ++i) {
	    	
	    	// For loop to iterate the number or row printed.
	    	for (int r = 1; r <= i; ++r) {
	    	  
	    		System.out.print("* ");
	      }
	      System.out.println();
	    }

	}
}
