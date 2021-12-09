package com.revature.exceptions;

/*
 * Thus far, we've only seen exceptions that we were not required to handle. This means
 * that we didn't have to wrap them in try-catch blocks. These are called "unchecked
 * exceptions". That said, it's still good practice to handle them. NOTE: You might also
 * hear people refer to "unchecked exceptions" as "runtime exceptions".
 * 
 * In any case, there are some exceptions that we are required to handle. These are referred
 * to as "checked exceptions". NOTE: Your source code will not compile if you don't handle
 * your checked exceptions.
 * 
 * The Exception class itself has a child class known as "RuntimeException". This is where
 * our unchecked exceptions descend from. Any exception that is a child of Exception but
 * NOT a child of RuntimeException is considered a checked exception. This means that this
 * type of exception has to be handled.
 * 
 * For this demo, we'll make our checked exception.
 */


public class CheckedExceptions {

	public static void main(String[] args) {
		
		CheckedExceptions checkedExceptions = new CheckedExceptions();
		
		/*
		 * The main method is now responsible for handling the ChristinaException because
		 * the throwException method throws the exception to the caller to handle.
		 */
		try {
			checkedExceptions.throwException("Christina");
		}catch(ChristinaException e) {
			e.printStackTrace();
		}
		
	}
	
	/*
	 * The "throws" keyword is used to indicate that the method potentially throws an
	 * exception to the caller to be handled by the caller.
	 */
	public void throwException(String name) throws ChristinaException{
		
		/*
		 * When something should go wrong, you should "throw" your exception. You decide
		 * when your exception is thrown.
		 */
		if(name.equals("Christina")) {
			throw new ChristinaException();
		}
	}
}

/*
 * In order to make an exception, just an extend the Exception class. If you wish to make
 * a RuntimeException, extend RuntimeException instead.
 */
class ChristinaException extends Exception{
	
	/**
	 * This is here to remove the warning about the lack of an ID for serialization purposes.
	 */
	private static final long serialVersionUID = 1L;

	public ChristinaException() {
		super();
	}
	
	public ChristinaException(String message) {
		super(message);
	}
}
