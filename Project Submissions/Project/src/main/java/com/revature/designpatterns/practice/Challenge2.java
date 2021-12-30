package com.revature.designpatterns.practice;//package com.revature.practice;
//
//import org.junit.jupiter.api.Test;
//
//
//
///**
// *
// * ## Challenge 2: Sum
// *
// * Write a method that takes any number of integers and returns the sum of all
// * of those integers.
// *
// * Process: I would need a way to hold dynamic amount of integers, as the exact
// * amount isn't specified. There needs to be a way for me to keep track of all
// * those numbers, so that I can accurately calculate the sum when I have stopped
// * receiving numbers.
// *
// */
//
//
///**
// *
// * Old Code
// * public static void main(String[] args) { int count = 0; String endWord =
// * "done";
// *
// * Scanner counter = new Scanner(System.in); System.out.println("Enter any
// * amount of whole numbers you would like then press enter. When finished, type
// * \'done\'");
// *
// *
// * while (counter.hasNextInt()) { count+= counter.nextInt();
// * System.out.println(" Current count is " + count); counter.nextLine();
// * System.out.println("Continue with the numbers");
// *
// *
// * } if (counter.hasNext(endWord)) { System.out.println(" Final count is " +
// * count); counter.close(); } else { System.out.println("Invalid. Please restart
// * :( "); counter.close();
// *
// * } }
// **/
//public class Challenge2 extends Challenge2Class{
//
//		public static void main(String...args) {
//			Challenge2Class argsClass = new Challenge2Class();
//
//			int result = argsClass.getArgs(2,23,454,67678);
//			System.out.println("Your count is: " + result);
//
//		}
//
//
//}
