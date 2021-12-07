package puppy;

import java.util.Random;

/*
 * This class creates an instance of itself and can generate a random appearance,
 * description, and age for each Puppy object created.
 */
public class Puppy {

	//Fields
	private String appearance;
	private String description;
	private int age;
	
	// Default Constructor
	public Puppy() {
		
	} // End constructor
	
	// Constructor
	public Puppy(String appearance, String description, int age) {
		this.appearance = appearance;
		this.description = description;
		this.age = age;
	} // End constructor
	
	/*
	 * I was not certain that creating an instance from the main method or a constructor
	 * would satisfy the challenge conditions as it states that the method must return an 
	 * instance of the class called Puppy, so I am using this method just to be safe.
	 */
	// Method to instantiate the Puppy class with randomized field values
	public Puppy makePuppy() {
		Random r = new Random();
		String[] randomAppearance = {"Black", "Brown", "White", "Spotted"};
		String[] randomDiscription = {"Cute", "Adorable", "Fluffy"};
		int randomAge = r.nextInt(7);
		Puppy pupper = new Puppy(randomAppearance[r.nextInt(randomAppearance.length)], 
				randomDiscription[r.nextInt(randomDiscription.length)], randomAge);
		return pupper;
	} // End method
	
	// Method to create the String
	public String toString() {
		String str = "Awww! The " + this.description + ", " + this.appearance + " puppy is only " 
						+ this.age + " months old!";
		return str;
	} // End method
	
	// Main
	public static void main(String[] args) {
		Puppy p = new Puppy(); // see makePuppy() comment
		Puppy p1 = p.makePuppy(); 
		Puppy p2 = p.makePuppy();
		
		System.out.println(p1.toString());
		System.out.println(p2.toString());
	} // End main
} // End class
