package com.revature.collections;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.revature.Person;

public class ListDemo {

	public static void main(String[] args) {
		
		Person person1 = new Person("Name", 0);
		
		Person[] people = {
				new Person("Christina", 28),  //0
				new Person("Florence", 75), //1
				new Person("Shawn", 19), //2
				person1 //3
			};
	
		//Just a reminder that this is how you access an element in an array
		System.out.println(people[1]);
		
		//What if I need another person in my array? Well, arrays aren't resizable, so
		//I'd have to make a new array with a different size to accommodate for the extra
		//person.
		
		Person[] peoplePlusOne = new Person[people.length + 1];
		
		//Iterate over the existing people array to grab each individual from that array and
		//put them in the new array:
		
		for(int i = 0; i < people.length; i++) {
			peoplePlusOne[i] = people[i];
		}
		
		//Then add your new person to the end of the array:
		
		peoplePlusOne[peoplePlusOne.length - 1] = new Person("Fred", 45);
		
		//PROOF OF CONCEPT: ITERATE OVER BOTH ARRAYS AND COMPARE THEIR CONTENTS
		for(int i = 0; i < people.length; i++) {
			System.out.println(people[i]);
		}
		
		System.out.println("DIVIDER=============================================");
		
		for(int i = 0; i < peoplePlusOne.length; i++) {
			System.out.println(peoplePlusOne[i]);
		}
		
		/*
		 * Instead of using an array, we can use a List. A List is a type of Java
		 * Collection (this is an interface in Java) that acts like a resizable array.
		 */
		
		/*
		 *                               Iterable (I)
		 *                               	 |
		 *                              Collection (I)
		 *                             / 	 |
		 *                  List (I)
		 *                  /      \
		 *        ArrayList (C)   LinkedList (C)
		 */
		
		/*
		 * It is common to use a super type for the reference (remember that this is
		 * called covariance).
		 * 
		 * FEATURES OF LIST:
		 * - List preserves the order of the elements, meaning that if you add an element 
		 * 		to a List first, it remains the first element unless you change the order
		 * 		yourself.
		 * - A List supports duplicates.
		 * - A List allows us to access any element by its index without iterating over the
		 * 		entire List.
		 * 
		 * Note that currently we can add any type of object to our list. While we can
		 * do this, we should not do this as it's not good practice.
		 */
		
		System.out.println("DIVIDER======================================");
		
		List myArrayList = new ArrayList();
		
		//This is how we add new elements to a List.
		myArrayList.add(new Person("Canisha", 26));
		myArrayList.add(new Person("Canisha", 26));
		myArrayList.add(new Person("Adam", 90));
		myArrayList.add(10);
		myArrayList.add(new Object());
		
		System.out.println(myArrayList);
		
		//This is how we access elements in a List:
		System.out.println(myArrayList.get(0));
		System.out.println(myArrayList.get(3));
		
		//This is how we remove an element from a List
		myArrayList.remove(3);
		
		System.out.println(myArrayList);
		
		/*
		 * LinkedList is also an implementation of List. It is, however, a little different
		 * from ArrayList.
		 * 
		 * To be clear, you still have access to all of your standard List methods. What
		 * changes is the performance you get depending on the task you need to complete.
		 * 
		 * With an ArrayList, all of the elements are stored contiguously in memory. This
		 * is not the case with a LinkedList; in fact the elements are sometimes stored quite
		 * far from each other.
		 * 
		 * Simply put:
		 * 
		 * When using an ArrayList, accessing an element (reading) is faster than doing so when
		 * using an LinkedList.
		 * 
		 * When using an ArrayList, adding an element (writing) is slower than doing so
		 * using a LinkedList.
		 * 
		 * This ultimately means that you should choose an ArrayList if you'll be reading
		 * from a List frequently and that you should choose a LinkedList if you'll be writing
		 * to your List frequently.
		 */
		
		List myLinkedList = new LinkedList();
		System.out.println(myLinkedList.add(new Person("Kimberly", 49)));
		
		
	}
	
}
