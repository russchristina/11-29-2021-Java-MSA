package com.revature.practice;
/**
 * 
 * 
 * ## Challenge 1: Instantiation

Write a method that returns an instance of a class called "Puppy". You should create any classes that are mentioned if they need to be created to make the code compile.

Process: For this, I need to figure out a way to 
 *
 */
public class Puppy {
	String name;
	String bark;
	public static void main(String...args) {
	Puppy fido = new Puppy();
	fido.name = "Butch";
	fido.bark = "Arf Arf";
	System.out.println(fido.name + " is excited to meet you! Isn't that right, " + fido.name + "? \n" +  fido.name + ": " + fido.bark);
	
	}
	
	
}

