package com.revature;

/*
 * Classes are an important building block in many OOP languages; Java is no exception.
 * We create methods(Behaviors) within the context of the classes. We also create "instances" of classes. This is useful, for example
 * when we want to use a class to model a real-world data. 
 */

public class ASimpleClass {
	
	public static void main(String...args) {
		
		// What is the stynax for creating an "instance" of a class? Note that "new: is a keyword that we use to help us do this.
		new ASimpleClass();
		
		// Note that you will frequently see a a reference and an assignment made as you create an instance of a class. This is because
		// "new ASimpleClass()" creates an object in memory; without a reference, we have no way of accessing that object later.
		
		// Reference type + reference name(variable) + assignment operator + instance creation
		ASimpleClass aSimpleReference = new ASimpleClass();
	
		// Yes, we can point the variable to a new instance 
		
		aSimpleReference = 
		int aNum = 8;
		aNum = 9;
	}

}
