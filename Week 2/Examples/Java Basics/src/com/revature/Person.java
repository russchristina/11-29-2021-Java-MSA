package com.revature;

/*
 * Classes are often used to represent real-world data. One of the benefits of OOP is that
 * it becomes easy to represent this type of data.
 */
public class Person {

	/*
	 * Recall that I said that each instance of a class gets its own copies of instance
	 * variables. So let's declare some instance variables. Typically, when we use the
	 * term "model", we're referring to a class that details a real-world idea/concept.
	 * In essence, we use classes as blueprints. They define all of the properties and
	 * behaviors that we associate with the real-world concept.
	 * 
	 * You frequently see these instance variables referred to as "fields".
	 */
	String firstName;
	String lastName;
	int age;
	int height;
	String hairColor;
	String eyeColor;
	
	/*
	 * As you can see below, we directly accessed the properties of a Person (e.g.
	 * firstName, age) using the reference. This is a headache, however, as you can imagine
	 * how many statements we would need to write to make all of the property assignments.
	 * 
	 * You generally want to use a "constructor" which takes argument to make it easier
	 * to initialize all of the fields with some starting value. In fact, we've already
	 * been using a "constructor":
	 * 
	 * Person christina = new Person(); <-- Person() is the constructor.
	 * 
	 * This is a default constructor that Java provided for us because we didn't define
	 * our own constructor.
	 * 
	 * That said, it is good practice to define your own no-args constructor. The syntax
	 * for defining a constructor is as follows:
	 * 
	 * 1. The constructor cannot have a return type.
	 * 2. The constructor must match that of the class.
	 */

	// The minute you define your own constructor, you're no longer using the default
	//constructor. This type of constructor is what we refer to as a "no-args" constructor.
	// This just means that we don't pass in arguments when we call it.
	public Person() {
		/*
		 * Note that we frequently use our constructors to initialize the fields to
		 * some default values.
		 */
		firstName = "n/a";
		lastName = "n/a";
	}
	
	/*
	 * We can also create a constructor that takes fields:
	 */
	
	public Person(String firstName, int age) {
		this.firstName = firstName;
		this.age = age;
	}
	
	public Person(String firstName, String lastName, int age, int height, String hairColor, String eyeColor) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.height = height;
		this.hairColor = hairColor;
		this.eyeColor = eyeColor;
	}

	public static void main(String []args) {
		
		Person christina = new Person();
		christina.firstName = "Christina";
		christina.age = 28;
		
		Person kimberly = new Person();
		kimberly.firstName = "Kimberly";
		kimberly.age = 50;
		
		Person florence = new Person("Florence", 76);
		
		System.out.println(christina.firstName);
		System.out.println(christina.age);
		
		System.out.println(kimberly.firstName);
		System.out.println(kimberly.age);
		
		System.out.println(florence.firstName);
		System.out.println(florence.age);
		
		christina.eat("apples");
		kimberly.eat("strawberries");
		florence.eat("tapioca");
		
	}
	
	/*
	 * Methods add behaviors to classes. In doing so, we're modeling the behaviors of
	 * a person.
	 * 
	 * If a method is not static (and most methods are NOT static in practice), you
	 * must create an instance of its containing class in order to call it.
	 */
	public void eat(String foodName) {
		/*
		 * Note that we can use the "this" keyword to refer to this specific instance's
		 * copy of the "firstName" instance variable/field.
		 */
		System.out.println(this.firstName + " is eating " + foodName + ".");
	}
	
}
