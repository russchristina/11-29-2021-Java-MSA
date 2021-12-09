package com.revature.collections;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class SetDemo {

	public static void main(String[] args) {
		
		/*
		 *                               Iterable (I)
		 *                               	 |
		 *                              Collection (I)
		 *                             / 	         |
		 *                  List (I)                Set (I)
		 *                  /      \                 |          \
		 *        ArrayList (C)   LinkedList (C)  SortedSet (I)     HashSet (C) 
		 *                                           |
		 *                                        TreeSet (C)
		 */                                          
		
		/*
		 * Like a List, a Set is resizable. That said, using a Set is not equivalent
		 * to using a List. The features of a set include:
		 * 
		 * - Sets do not guarantee that the order of the elements will be maintained
		 * - Sets do not support duplicates
		 * - Sets do not support random access (we can't just get any element by its
		 * 		index at any time as we could with List).
		 */
		
		Set myHashSet = new HashSet();
		
		/*
		 * Notice that the methods for adding and removing elements are the
		 * same ones that we saw with List.
		 */
		myHashSet.add(100);
		myHashSet.add(78);
		myHashSet.add(90);
		myHashSet.add(43);
		myHashSet.add(100);
		
		myHashSet.remove(78);
		
		System.out.println(myHashSet);
		
		/*
		 * Note that it is NOT possible to get() an element from a Set by using its index.
		 * In fact, the Set interface doesn't provide a "get" method.
		 * 
		 * You must iterate over your set in order to access its values.
		 */
		
		for(int i = 0; i < myHashSet.size(); i++) {
			//Notice that there is no get method. So what do you do here?
			//...
		}
		
		//Fortunately, there is an Iterable interface, and Sets are Iterable because
		//they inherit from Collection, which inherits from Iterable.
		
		//We can get an iterator to iterate over a Set like so:
		
		Iterator mySetIterator = myHashSet.iterator();
		
		while(mySetIterator.hasNext()) {
			System.out.println(mySetIterator.next());
		}
		
		System.out.println("DIVIDER=============================================");
		
		/*
		 * Note that you can also use a TreeSet. This implementation is a "sorted"
		 * implementations that stores elements in their "natural order". For instance,
		 * the natural order of numeric types is "least to greatest" whereas the 
		 * natural order for String is alphabetically ordered.
		 * 
		 * TreeSets are great if you need the data to be ordered and you need to read from
		 * your TreeSet frequently. However, they are not a great option if you need to
		 * constantly add to the Set because TreeSets MUST stay in order. This means
		 * that every single time you add a new element, the TreeSet has to shift the
		 * elements to accommodate for it so that the TreeSet elements remain ordered.
		 */
		
		Set myTreeSet = new TreeSet();
		
		myTreeSet.add(900);
		myTreeSet.add(87);
		myTreeSet.add(1);
		myTreeSet.add(5);
		
		System.out.println(myTreeSet);
	}
}
