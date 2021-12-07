package challenge1;

public class AbsoluteValue {
	
	public static void main(String []args) {
		
		// Set a variable to hold an integer
		int av = -15;
		
		// call to the method
		absoluteValue(av);
		
	}
	// Creating method to get the absolute value. 
	public static void absoluteValue(int av) {
		
		// If statement to convert any negatives to positives 
		// to represent the absolute value of the number.
		if (av < 0) {
			
			av = (-1 * av);
		}
		
		// Print the results
		System.out.println("The absolute value is " + av +".");
	}
}
