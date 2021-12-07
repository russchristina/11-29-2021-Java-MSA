
/* The first step is to make some instance variables then I make a constructor choosing
 * not to use the default constructor. Then from there I make a main method and make the instance of Puppy. I fill 
 * the new instance of puppy with the instance variables from earlier and test.
 * 
 */
package revature;

public class Puppy {
	public String name;
	public int age;
	public String breed;

	public Puppy(String name, int age, String breed) {
		this.name = name;
		this.age = age;
		this.breed = breed;
	}

	public void bark() {
		System.out.println("woof woof");
	}

	public static void main(String[] args) {
		Puppy p1 = new Puppy("Skippy", 15, "Lab");
		p1.bark();
		System.out.println(p1.name);

	}
}
