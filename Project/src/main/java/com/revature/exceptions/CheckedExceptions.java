package com.revature.exceptions;
/*
 * Exceptions such as nulls and arrays do not  need to be wrapped in try-catch blocks. THese are calls 'unchecked exceptions'.
 * aka RunTime exception, because you don't find out about it until the code runs
 *  That said, it's still good practice to handle them.
 *  
 *  THere are some exceptions that we are required to handle. THese are knwon as 'checked exceptions'. Your source 
 *  code will not compile if you don't handle your checked exceptions.
 *  The Exception class itself has a child class known as 'RuntimeException'. THis is where
 *  our unchecked exceptions descend from.
 *  Any exception that is a child of Exception but not a child of RuntimeException is considered a 
 *  checked exception.
 * 
 */

public class CheckedExceptions extends KymonsException {
	public static void main(String[] args) {
		CheckedExceptions checkedExceptions = new CheckedExceptions();
		try {
			checkedExceptions.throwException("Kymon");
		}catch(KymonsException e) {
			int[] a = new int [100];
			for (int b : a) {
				System.out.println("code broke pls fix");
				e.printStackTrace();
			}
		}
	}
}

/*
 * In order to make an exception, just extend the Exception class. If you wish
 * to make a RuntimeException, extend RuntimeException instead
 */
class KymonsException extends RuntimeException {
	/**
	 * Here to remove the warning about the lack of an ID for serialization purposes
	 * (bruh what?)
	 */
	private static final long serialVersionUID = 1L;

	public KymonsException() {
		super();
	}

	/*
	 * The 'throws' keyword is used to indicate that the method potentially throws an exception
	 * to the caller to be handled by the caller
	 */
	public void throwException(String name) throws KymonsException {
		if (name.equals("Kymon"))
			throw new KymonsException();

	}

	public KymonsException(String message) {
		super(message);
	}
}