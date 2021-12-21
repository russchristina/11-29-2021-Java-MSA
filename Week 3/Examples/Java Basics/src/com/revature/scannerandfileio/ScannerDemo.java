package com.revature.scannerandfileio;

import java.util.InputMismatchException;
import java.util.Scanner;

/*
 * Sometimes we need to take user input when building applications. Our simplest option
 * for doing so is the Scanner class. Note that Scanner is a parser which you pass
 * System.in to. System.in is an input stream which contains data passed in from standard
 * input (assumed to be the keyboard).
 */
public class ScannerDemo {

	public static void main(String[] args) {

		String usersName = null;
		int age = 0;

		/*
		 * The syntax for creating a Scanner is fairly standard; you create an instance
		 * of the Scanner class. Note that there is no no-args constructor for the
		 * Scanner class; you just pass some of file or input stream to the constructor.
		 * We're choosing System.in as we intend to take user input via the keyboard and
		 * parse that input.
		 */
		Scanner scanner = new Scanner(System.in);

		/*
		 * Once you have instance of Scanner, taking the input is simple as Scanner has
		 * convenient methods for parsing input.
		 */

		System.out.print("Hi, user! Please enter your name: ");

		usersName = scanner.nextLine();

		System.out.println("So your name is " + usersName + "? Is that right?");

		System.out.println("Enter your age, " + usersName + ": ");

		/*
		 * Please note that if the parsing method you've chosen will only parse the data
		 * in the stream as a specific type (e.g. int, double), the input MUST match the
		 * expectation or you'll get an exception (e.g. InputMistmatchException). As
		 * such, you'll need to do your own exception handling.
		 */
		
		while(age <= 0){
			
			if(scanner.hasNextInt()) age = scanner.nextInt();
			/*
			 * You'll often need to consume the remainder of the input in the stream
			 * by calling a method such as "nextLine".
			 */
			scanner.nextLine();
			
			System.out.println("Sorry. That's not a valid number. Try again.");
			
		}
		
		System.out.println("Your age is " + age + ".");
		
		/*
		 * When you're done with the Scanner, you want to close it as you should not
		 * leave any resources open. That said, once you close your standard input stream,
		 * it is closed for the remainder of the application.
		 */
		scanner.close();
	}
}
