package com.revature.lambdas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

/*
 * If I want to be able to sort instances of the Kitten class, this class must implement Comparable.
 */
class Kitten implements Comparable<Kitten>{
	
	private int age;
	private int adorableness;
	private String name;
	private String furColor;
	
	public Kitten() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Kitten(int age, int adorableness, String name, String furColor) {
		super();
		this.age = age;
		this.adorableness = adorableness;
		this.name = name;
		this.furColor = furColor;
	}

	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFurColor() {
		return furColor;
	}
	public void setFurColor(String furColor) {
		this.furColor = furColor;
	}
	
	
	public int getAdorableness() {
		return adorableness;
	}

	public void setAdorableness(int adorableness) {
		this.adorableness = adorableness;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + adorableness;
		result = prime * result + age;
		result = prime * result + ((furColor == null) ? 0 : furColor.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Kitten other = (Kitten) obj;
		if (adorableness != other.adorableness)
			return false;
		if (age != other.age)
			return false;
		if (furColor == null) {
			if (other.furColor != null)
				return false;
		} else if (!furColor.equals(other.furColor))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Kitten [age=" + age + ", adorableness=" + adorableness + ", name=" + name + ", furColor=" + furColor
				+ "]";
	}

	@Override
	public int compareTo(Kitten o) {
		return this.adorableness - o.adorableness;
	}
}

/*
 * If you wish to play around with Functional Interfaces beyond the two shown below, you can look into:
 * 
 * Supplier
 * Consumer
 * BiConsumer
 * Function
 * BiFunction
 * BiPredicate
 */
public class PracticalLambdas {

	public static void main(String[] args) {
		
		/*
		 * Collections and Lambdas play quite nicely together in Java. Let's take a look at
		 * Comparator and Predicate.
		 */
		
		List<Kitten> kittens = new ArrayList<>();
		kittens.add(new Kitten(2, 89, "Mittens", "Sandy Brown"));
		kittens.add(new Kitten(1, 900, "Boots", "Black"));
		kittens.add(new Kitten(3, 23, "Socks", "Black With White Feet"));
		kittens.add(new Kitten(1, 101, "Oven Mitts", "Calico"));
		
		Collections.sort(kittens);
		
		System.out.println(kittens);
		
		/*
		 * Comparator is a Functional Interface which allows us to define a custom way of sorting
		 * instances of our classes. Though this sounds exactly like Comparable, you should note
		 * that Comparator is external to the class that is being compared; the class itself does
		 * not directly implement Comparator. Comparator is typically used when you have already
		 * determined a "natural ordering" of a type but you suddenly need to sort the instances
		 * differently for some purpose.
		 */
		
		// This sorts kittens by their age a single time.
		Collections.sort(kittens, (kitten1, kitten2) -> kitten1.getAge() - kitten2.getAge());
		
		System.out.println(kittens);
		
		/*
		 * Predicate is a functional interface which has one abstract method that returns a boolean.
		 * It can be easily used for filtering out entities (e.g. kittens in a list of kittens).
		 * For example, let's say that we wish to remove all of our kittens that are under the age
		 * of 2 from our List.
		 */
		
		/*
		 * If you prefer, you can create a reference to your predicate before passing it into 
		 * the "removeIf" method.
		 */
//		Predicate<Kitten> agedLessThan2 = kitten -> kitten.getAge() < 2;
//		kittens.removeIf(agedLessThan2);
		
		kittens.removeIf((Kitten kitten) -> {return kitten.getAge() < 2;});
		
		System.out.println(kittens);
		
		/*
		 * You don't have to know this, but forEach takes a Consumer. A Consumer takes in an
		 * argument and returns nothing. You usually use for Consumer for data transformation
		 * or simply producing a side effect such as printing to the console.
		 */
		kittens.forEach(kitten -> System.out.println(kitten));
		
		/*
		 * This is equivalent to the forEach method call above.
		 */
		for(Kitten kitten : kittens) {
			System.out.println(kitten);
		}
		
		
	}
}
