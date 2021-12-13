package com.revature.collections;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapDemo {

	/*
	 * Technically speaking, Map doesn't inherit from the Collection interface. Map is its own
	 * interface that inherits from an older interface called "Dictionary".
	 */
	
	public static void main(String[] args) {
		
		/*
		 * Note that Maps are groups of key-value pairs. We use a known "key" to access an underlying
		 * value. This is works a lot like a dictionary in which you use the word to find its definition.
		 */
		Map<String, String> myMap = new HashMap<>();
		
		/*
		 * How do you add to a Map? Notice that you're adding the key and the value.
		 */
		
		myMap.put("table", "a place where we hold many things that we don't feel like putting away");
		myMap.put("job", "a task completed for money, but hopefully a task you enjoy");
		/*
		 * Be careful as if you specify the same key twice, the underlying value for the existing
		 * key will be overridden with the new value. This implies that every key should be unique.
		 */
		myMap.put("table", "a holder of many objects");
		/*
		 * As a note, "putIfAbsent" is a safe option if you're not sure whether or not a key is already
		 * present in the Map. This will prevent the underlying value associated with an existing key
		 * from being overridden.
		 */
		myMap.putIfAbsent("table", "yet another definition for table");
		
		/*
		 * And yes, you can even have a null key in your Map. That said, you can only have a single
		 * null key as all keys still need to be unique.
		 */
		
//		myMap.put(null, "a null definition"); //COMMENTED OUT BECAUSE IT'S NOT GREAT PRACTICE
		
		/*
		 * How do I access the elements in a Map? If you want to access a value in a Map, you need to
		 * know the key. Using the "get" method passing in this key will return the underlying value.
		 */
		
		System.out.println(myMap.get("table"));
		
		/*
		 * You can of course remove elements from a Map.
		 */
		myMap.remove("job");
		
		System.out.println(myMap);
		
		/*
		 * Please note that Map is NOT iterable. The following commented out code does not compile
		 * because Map is not iterable.
		 */
		
//		for(String key : myMap) {
//			
//		}
		
		/*
		 * That said, Set is iterable, and there is a method which allows us to get all of the keys
		 * in a Map as a Set. This means that we can iterate over the Set of keys and find the key that
		 * we want in order to access its underlying value:
		 */
		
		Set<String> theMapsKeys = myMap.keySet();
		
		for(String key : theMapsKeys) {
			if(key.equals("table")) {
				System.out.println(myMap.get(key));
			}
		}
		
		
		
	}
}
