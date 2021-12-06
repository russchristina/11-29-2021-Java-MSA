/*
 * The very first statement has to declare which package the class resides
 * within. If this statement was not here, this code would not compile.
 */
package com.revature;

/*
 * It is okay to have classes with the same names as long as those classes
 * are in different packages. This is because Java uses the fully qualified
 * class names for identifying classes. For instance, this class's fully
 * qualified class name is: com.revature.HelloWorld. Notice that the fully
 * qualified class name includes the package name.
 */
public class HelloWorld {
	
	/**
	 * 
	 * @param args Arguments that can be passed to the "java" command
	 * when the program is run.
	 * 
	 * The "main" method is a special method which serves as the entrypoint
	 * to your program. Your code begins execution from within this method.
	 * Yes, the method must be called "main". It also must be public and
	 * static.
	 * 
	 * P.S. This is a special type of comment which generates documentation.
	 * It is called a javadoc! This is a benefit of having a JDK! 
	 */
	public static void main(String []args) {
		/*
		 * A single statement, which ends with a semicolon, is an instruction
		 * to your computer. You must use semicolons in Java.
		 */
		System.out.println("Hello World!");
		
		introduceMyself();
		introduceMyself("Christina");
		introduceMyself("David");
		introduceMyself("Adrian");
		
		String returnedMessage = returnGreetingMessage("Christina");
		System.out.println(returnedMessage);
	}
	
	/*
	 * We have a defined a simple method which prints a message to the
	 * console. Note that defining a method is not the same thing as 
	 * calling/invoking a method. The ONLY method here that Java will
	 * call automatically is the "main" method. In essence, you will
	 * have to call the "introduceMyself" method yourself from the "main"
	 * method in this case.
	 */
	static void introduceMyself() {
		System.out.println("Hi, my name is Christina!");
	}
	
	/**
	 * 
	 * @param name The name of the person we would like to introduce.
	 * 
	 * "name" is a parameter. A parameter is simply a placeholder for
	 * a value that will be passed to the method at a later time.
	 */
	static void introduceMyself(String name) {
		System.out.println("Hi, my name is " + name + "!");
	}
	
	/**
	 * 
	 * @param name The name of the person that we would like to introduce.
	 * @return The String which represents the greeting message.
	 * 
	 * Methods can return any valid Java type. That said, whatever value
	 * is returned MUST match the declared return type for the method.
	 * 
	 * When we return a value, we are passing it to the "caller". The
	 * "caller" is the place from which the method was invoked (e.g. 
	 * where did you call the method?).
	 */
	static String returnGreetingMessage(String name) {
		/*
		 * After you return a value, there will be no more statements
		 * in this method. Every other statement would be "unreachable".
		 */
		return "Hi, my name is " + name + "!";
	}
	
}
