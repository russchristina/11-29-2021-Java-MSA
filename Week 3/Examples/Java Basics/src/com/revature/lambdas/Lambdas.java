package com.revature.lambdas;

/*
 * Lambda expressions are used in conjunction with functional interfaces. Lambda expressions were
 * introduced in Java 8 in order to allow for more "functional" programming.
 */
public class Lambdas {

	public static void main(String[] args) {
		
		Lambdas lambda = new Lambdas();

		/*
		 * First things first, let's see the syntax for a Lambda Expression. A Lambda Expression
		 * requires:
		 * 
		 * - A type. This type must match the type of a functional interface.
		 * - A reference name
		 * - An assignment operator
		 * - The lambda expression (on the right side of the assignment operator) has
		 * 		- a list of parameters
		 * 		- a "->" symbol; looks like an arrow; the arrow separates the parameters and implementation
		 * 		- the function implementation, which should match the method signature of the single
		 * 		  abstract method on the interface
		 */
		
		MathOperation addition = (num1, num2) -> num1 + num2;
		/*
		 * Yes, you can respecify the parameter types, but if you do, you must do it for all parameters.
		 */
		MathOperation subtraction = (int x, int y) -> x - y;
		/*
		 * The curly braces surrounding the implementation are required if you want to use the
		 * "return" keyword or you want to have multiple statements in your implementation.
		 */
		MathOperation multiplication = (x, y) -> {
			int result = x * y;
			return result;};
		
		/*
		 * You do not need to create references to lambda expressions to pass them into methods
		 * as arguments. In fact, it is common to see just an expression passed in.
		 */
		System.out.println(lambda.selectMathOp(8, 9, (x, y) -> x + y));
	}
	
	
/*
 * Lambda expressions are treated like objects, so we can pass them around to methods.
 */
	
	public int selectMathOp(int num1, int num2, MathOperation op) {
		return op.doMathOp(num1, num2);
	}
}

@FunctionalInterface
interface MathOperation{
	
	int doMathOp(int num1, int num2);
	
}
