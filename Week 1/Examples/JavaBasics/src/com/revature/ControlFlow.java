package com.revature;

/*
 * Control flow is really about decision-making in your application. In essence, it is
 * rare in reality to always react the exact same way to different input. We would like the
 * ability to make our application branch into different logic/scenarios when certain input
 * is provided.
 */
public class ControlFlow {

	public static void main(String []args) {
		
		critiqueFood("cake");
		critiqueFoodWithSwitch("egg");
		
		praiseFoodNumerousTimes("cake");
		praiseFoodAnUnknownNumberOfTimes("kale");
	}
	
	
	static void critiqueFood(String foodName) {
		/*
		 * The simplest option for control flow is an "if" statement. If statements
		 * take boolean expressions (expressions which evaluate to either "true" or
		 * "false"). If the expression is "true", the code associated with the block
		 * is executed. If the expression is "false", the code associated with the 
		 * block is not executed.
		 * 
		 * You can use "else if" and "else" blocks to follow up an if statement. Note
		 * that you should place "else if" blocks before your "else" block and that you
		 * don't need to use "else" just because you used "else if".
		 */
		if(foodName.equals("pumpkin pie")) {
			System.out.println(foodName + " is delicious!");
		}else if(foodName.equals("cake")) {
			System.out.println(foodName + " is also delicious!");
		}
		else {
			System.out.println(foodName + " is gross!");
		}
	}
	
	static void critiqueFoodWithSwitch(String foodName) {
		
		/*
		 * A switch statement is considered an elegant solution to using multiple
		 * "else if" blocks. You can rewrite the above code using a switch statement
		 * instead.
		 */
		
		switch(foodName) {
		/*
		 * You can specify "cases" to represent the different conditions that we represented
		 * above when using "if".
		 */
		case "pumpkin pie":
			System.out.println(foodName + " is delicious!");
			break; //DO NOT FORGET TO ADD YOUR BREAK STATEMENT
		case "cake":
			System.out.println(foodName + " is also delicious!");
			break;
		/*
		 * The default block handles cases that are not represented above. Notice that
		 * we did not break in the default block because we don't need to; there's no 
		 * case following this one.
		 */
		default:
			System.out.println(foodName + " is gross!");
		}
	}
	
	static void praiseFoodNumerousTimes(String foodName) {
		/*
		 * Sometimes we want to complete a task multiple times. We could type the exact
		 * same code several times, but this makes code harder to read and maintain. It
		 * also takes more time, and we don't want to take more time than we need to 
		 * complete our tasks.
		 * 
		 * In such cases, we can use loops. One of the most common loops is the "for"
		 * loop. A for loop traditionally has a loop variable that is initialized, a
		 * boolean expression which is used to determine when the loop should be
		 * terminated, a statement which increments the loop variable in some way.
		 */
		for(int i = 1; i <= 10; i++) {
			System.out.println(foodName + " is delicious!");
		}
	}
	
	static void praiseFoodAnUnknownNumberOfTimes(String foodName) {
		/*
		 * A "while" loop is used to perform a task several times, but the caveat you
		 * don't know how many times a while loop will run. We can't always know how many
		 * times an action will need to be performed after all.
		 * 
		 * Note that you have to decide what terminates a while loop, meaning that you
		 * will have to make sure that the boolean expression that is used will eventually
		 * be false.
		 * 
		 * Also please: no "while(true)" as this makes your code harder to read.S
		 */
		
		while(foodName.equals("pumpkin pie")) {
			System.out.println(foodName + " is dellicious!");
			break; //Breaking because this is an infinite loop without the break statement
		}
		
		/*
		 * You can also use a do-while loop if you need the loop to run at least once:
		 */
		do {
			System.out.println("I'm praising " + foodName + " at least once.");
		}while(foodName.equals("pumpkin pie"));
	}
}
