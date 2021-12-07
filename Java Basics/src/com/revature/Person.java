package com.revature;

/*
 * Classes are often used to represent real-world data. One of the benefits of OOP is that it becomes easy to represent this type of data.
 */
public class Person {
	
	/*
	 * Recall that each instance of a class gets its own copies of instance variables. So let's declare some instance variables.
	 * Typically, When we use the term "model", we're referring to a class that details a real-world idea/concepts.
	 * In essence, we use classes as blueprints. They define all of the properties and the behaviors that we associate with 
	 * the real-world concept. 
	 */
	String firstName;
	String lastName;
	int age;
	int height;
	String hairColor;
	String eyeColor;
	
	/*
	 * as you can see below, we directly accessed the properties of a person (e.g. firstName, age) using the reference. This is a headache,
	 * however, as you can imagine how many statements we would need to write to make all of the property assignments
	 * 
	 * You generally want to use a "constructor" which takes an argument to make it easier to initialize all of the field with some starting value. 
	 * In fact, we've already been using a "constructor":
	 *  
	 *  Person brandon = new Person(); <-- Person() is the constructor.
	 *  
	 *   This is a default constructor that Java provided for us because we didn't define our own constructor.
	 *   That said, it is good practice to define your own no-args constructor. The stynax for defining a constructor is as follows:
	 *   
	 *   1. The constructor cannot have a return type.
	 *   2. The constructor must match that of the class
	 */
	
	// The minute you define your own constructor, you're no longer using the default constructor. This is what we refer to as a 
	// "no-args" constructor. 
	// This just means that we don't pass in arguments when we call it. 
	public Person() {
		/*
		 * Note that we frequently use out constructor to initialize the fields to some default values. 
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
	

	public static void main(String []args) {
		
		Person brandon = new Person();
		brandon.firstName = "Brandon";
		brandon.age = 35;
		
		Person kimberly = new Person();
		kimberly.firstName = "Kimberly";
		kimberly.age = 50;
		
		Person florence = new Person("Florence", 76);
		
		System.out.println(brandon.firstName);
		System.out.println(brandon.age);
		
		System.out.println(kimberly.firstName);
		System.out.println(kimberly.age);
		
		System.out.println(florence.firstName);
		System.out.println(florence.age);
		
		brandon.eat("apples");
		kimberly.eat("grapes");
		florence.eat("strawberries");
	}

	/*
	 * Methods add behaviors to classes. In doing do, we're modeling the behaviors of a person. 
	 * 
	 * If a method is not static( and most methods are not static), you must create an instance of its containing class 
	 * in order to call it. 
	 */
	public void eat(String foodName) {
		
		/*
		 * Note that we can use "this" keyword to refer to this specific instance's copy of the "firstName" instance variable/field.
		 */
		
		System.out.println(this.firstName +" is eating " + foodName + ".");
	}

	/*
	 * 
	 */
}
