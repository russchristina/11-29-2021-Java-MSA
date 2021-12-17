package com.revature.collections;

import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;


/*
 * In order to be able to sort a type, it must implement the Comparable interface. String
 */
class Doggo implements Comparable<Doggo>{
	
	private int age;
	private String name;
	private String barkSound;
	
	public Doggo(int age, String name, String barkSound) {
		this.age = age;
		this.name = name;
		this.barkSound = barkSound;
	}
	
	/*
	 * When a class implements the Comparable interface, it must override the "compareTo" method.
	 */
	@Override
	public int compareTo(Doggo o) {
		/*
		 * Let's compare/sort dogs by their age. Note that this methods compares the instance on
		 * which you call the method to another instance (for which a reference is passed in).
		 */
		
		/*
		 * Commented out so that we could replace it with a more common (one-line) implementation.
		 */
//		if(this.age == o.age) return 0;
//		else if(this.age > o.age) return 1;
//		else return -1;
		
		return this.age - o.age; //this is typically how you see it written
	}

	@Override
	public String toString() {
		return "Doggo [age=" + age + ", name=" + name + ", barkSound=" + barkSound + "]";
	}
	
}

public class Comparables {

	public static void main(String[] args) {
		
		Set<Integer> numbers = new TreeSet<>();
		
		numbers.add(3);
		numbers.add(89);
		numbers.add(-8);
		numbers.add(0);
		
		System.out.println(numbers);
		
		/*
		 * As a review, you can use an iterator to iterate over the elements in a Set (or any
		 * implementation of Collection).
		 */
		
		Iterator<Integer> numberIterator = numbers.iterator();
		
		while(numberIterator.hasNext()) {
			System.out.println(numberIterator.next());
		}
		
		/*
		 * What if I wanted a TreeSet of Doggos? String
		 */
		
		Set<Doggo> doggos = new TreeSet<>();
		
		doggos.add(new Doggo(3, "Scooby", "Human talking"));
		doggos.add(new Doggo(2, "Snoopy", "Silence"));
		doggos.add(new Doggo(15, "Beethoven", "Normal Bark"));
		doggos.add(new Doggo(1, "Scrappy Doo", "High-piteched small dog bark"));
		
		System.out.println(doggos);
	}
}
