package com.revature;

public class StringBuilderAndBuffer {

	public static void main(String[] args) {
		
		String myName = "Christina";
		myName = myName.concat(" Russ");
		System.out.println(myName);
		
		/*
		 * Recall that Strings are immutable. You cannot change a String once you've created it.
		 * This is fine, but sometimes it is easier to work with what feels like a mutable String.
		 * Enter StringBuilder and StringBuffer. They behave as mutable Strings. These two classes
		 * are really wrappers around a String.
		 */
		
		StringBuilder builder = new StringBuilder("Christina");
		builder.append(" Russ");
		System.out.println(builder);
		
		/*
		 * StringBuffer is the original StringBuilder. The difference between StringBuilder and
		 * StringBuffer lies in performance. StringBuffer is thread-safe, which means that it is
		 * safe for use in multi-threaded environments. Unfortunately, this makes it slower than
		 * StringBuilder. As a result, it is generally recommended that you use StringBuilder
		 * unless you absolutely need StringBuffer (meaning you're working in a multi-threaded
		 * environment).
		 */
		StringBuffer buffer = new StringBuffer("Christina");
		buffer.append(" Russ");
		System.out.println(buffer);

	}
}
