package com.revature.solution;

public class Instantiation {

	/*
	 * We know that we need to write a method that returns an instance of a class called "Puppy".
	 * That said, if we write our method signature before creating a "Puppy" class, the code won't
	 * compile. This just means that we have to create a class "Puppy".
	 */
	
	public Puppy getPuppy() {
		return new Puppy();
	}
}

/*
 * I've moved this puppy class to a different file just so that it can be imported elsewhere.
 */
//class Puppy{
//	/*
//	 * This is all you need to do in order to be able to create an instance of "Puppy" since
//	 * Java provides a default constructor all classes.
//	 */
//}
