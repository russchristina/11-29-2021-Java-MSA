package com.revature;

/*
 * I decided to focus on the important information regarding the dog,
 * namely the name, breed, and age of the dog. I included the no args
 * constructor so that in the case of someone not putting in arguments
 * it did not have to use the default constructor Java provides.
 */

public class Puppy {
	String name;
	String breed;
	int age;
	
	public Puppy() {
		
	}
	
	public Puppy(String name, String breed, int age) {
		this.name = name;
		this.breed = breed;
		this.age = age;
	}
	
	public static void main(String[] args) {
		Puppy copper = new Puppy("Copper", "Golden Retriever", 8);
		printDog(copper);
	}
	
	public static void printDog(Puppy dog) {
		System.out.println(dog.name + " is a " + dog.breed + " that is "
				+ dog.age + " years old!");
	}
}