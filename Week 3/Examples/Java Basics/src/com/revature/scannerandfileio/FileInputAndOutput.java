package com.revature.scannerandfileio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/*
 * Sometimes we want to take data and write it to a file on our machine or read from a file
 * into a program. Every modern programming language provides easy ways of handling this task.
 */
public class FileInputAndOutput {
	
	public static void main(String[] args) {
		/*
		 * I'll create a Scanner so that we can use it to take user input and write that
		 * information to the file:
		 */
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Hi, user. What is your name: ");
		
		String fullName = scanner.nextLine();
		
		/*
		 * Let's first write to a file. Options for writing to a file include:
		 * 
		 * - FileOutputStream - this writes bytes to a file
		 * - FileWriter - writes characters to a file
		 * - BufferedWriter - writes to files line by line; your BufferedWriter is also thread-safe.
		 * 
		 * But how do I represent a file in Java? By using the "File" class.
		 * 
		 * And as a bonus (even though it's not on the curriculum and you don't have to
		 * know it), there is a "Files" or NIO API in Java that is more modern.
		 */
		
		//If this file does not exist, it will be created in my workspace.
		File myFile = new File("people.txt");
		
		try {
			//Here for your reference. This is more useful for writing images.
//			FileOutputStream fos = new FileOutputStream(myFile);
			/*
			 * If you pass in a boolean value of true, this writer will append to the file
			 * rather than overwriting its contents.
			 */
			FileWriter writer = new FileWriter(myFile, true);
			/*
			 * I recommend wrapping the FileWriter inside of a BufferedWriter; this will
			 * allow you to write a line rather than just a single character.
			 */
			BufferedWriter buffWriter = new BufferedWriter(writer);
			
			/*
			 * Now let's just write the user's full name to the file:
			 */
			buffWriter.write(fullName + "\n");
			/*
			 * Every resource you open, you must close.
			 */
			buffWriter.close();
			writer.close();
			
			/*
			 * As far as FileIO, you have to handle FileNotFoundExceptions as they
			 * are checked exceptions.
			 */
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		/*
		 * So how do we read data from a file for use in our program? In order to 
		 * accomplish this, we can use:
		 * 
		 * - FileInputStream - reads bytes from a file
		 * - FileReader - reads characters from a file
		 * - BufferedReader - reads entire lines from a file; it is thread-safe 
		 */
		FileReader reader;
		BufferedReader buffReader;
		
		try {
			//Again, this is more recommended for reading in bytes for images.
//			FileInputStream fis = new FileInputStream(myFile);
			reader = new FileReader(myFile);
			buffReader = new BufferedReader(reader);
			
			while(buffReader.ready()) {
				System.out.println(buffReader.readLine());
			}
			
			/*
			 * Always close your resources.
			 */
			buffReader.close();
			reader.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
