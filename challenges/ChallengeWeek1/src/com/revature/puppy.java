package com.revature;

public class puppy {
	String firstName;
	String dogBreed;
	
	public puppy() {
		firstName = "n/a";
		dogBreed = "n/a";
	}
	
	public puppy(String firstName, String dogBreed) {
		this.firstName = firstName;
		this.dogBreed = dogBreed;
		}
	
	public static void main(String[]args) {
		
		puppy Rex = new puppy(); 
		Rex.firstName = "Rex";
		Rex.dogBreed = "Poodle";
		
		puppy Cody = new puppy();
		Cody.firstName = "Cody";
		Cody.dogBreed = "German Shepherd";
		
		System.out.println("This puppy's name is " + Rex.firstName + ". He is a " + Rex.dogBreed + ".");
		System.out.println("This puppy's name is " + Cody.firstName + ". He is a " + Cody.dogBreed + ".");
		
		
		
	}
}
