package com.revature.fileio;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileIOTryWithResources {

	public static void main(String[] args) {
		
		/*
		 * A review of writing to a file:
		 */
		
		File myFile = new File("myFile.txt");
		
		/*
		 * Though you can handle your resources like we've done below, this is not the best way
		 * of doing so as there is no guarantee that the resources will be closed within this
		 * try block.
		 */
				
//		try {
//			FileWriter writer = new FileWriter(myFile, true);
//			BufferedWriter buffWriter = new BufferedWriter(writer);
//			buffWriter.write("writing to my file once again\n");
//			buffWriter.close();
//			writer.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		/*
		 * We will instead use "try with resources". This is a feature that was introduced in Java 7.
		 * It allows us to have Java automatically close resources for us that are declared using
		 * the try-with-resources with syntax.
		 * 
		 * Please note that in order to use a resource in a try-with-resources statement, it has to
		 * be "AutoCloseable", meaning that it inherits from the AutoCloseable interface.
		 * 
		 * If you use a resource in a try-with-resources statement, it MUST be treated as "effectively
		 * final". This means that we should use it as if it is marked with the "final" keyword. In other
		 * words, do not use the assignment operator with the references again.
		 */
		
		try(FileWriter writer = new FileWriter(myFile, true); BufferedWriter buffWriter = new BufferedWriter(writer);) {
			buffWriter.write("i'm using try with resources\n");
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
