package com.revature.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * When someone asks you about the "Collections Framework", they're generally asking about:
 * 
 * 1) The Collection interface/hierarchy
 * 2) The Collections utility class
 */
public class CollectionsUtility {
	
	public static void main(String[] args) {
		List<String> firstNames = new ArrayList<>();
		firstNames.add("Christina");
		firstNames.add("KC");
		firstNames.add("Bach");
		firstNames.add("Carolyn");
		firstNames.add("Ryan");
		firstNames.add("Kyle");
		
		System.out.println(Collections.max(firstNames));
		System.out.println(Collections.min(firstNames));
		
		System.out.println(firstNames);
		Collections.sort(firstNames);
		System.out.println(firstNames);
			
		Collections.shuffle(firstNames);
		System.out.println(firstNames);
		
	}
	
}
