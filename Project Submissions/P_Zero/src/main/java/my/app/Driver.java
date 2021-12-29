package my.app;

import java.util.Scanner;

/*
 *      SPAGHETTI CODY'S ITALIAN CUISINE
 *      Designed and developed by: Corey Kinnaird
 *      
 *      P_Zero
 *      This is the first project as assigned in training at Revature.
 *      
 *      The project is a model of an app which simulates simple transactions
 *      for a chosen themed business.
 *      
 *      This project features all required technologies, formats, and functionality.
 *      Technologies:
 *      -Java 8
 *      -Maven
 *      -JUnit
 *      -Logback
 *      -PostgreSQL
 *      -JDBC
 *      
 *      Format:
 *      -Built using Java 8
 *      -Built as a Maven standard
 *      -All user interactions are through the console
 *      -All information is persisted using a PostgreSQL database
 *      -Hosted with AWS RDS
 *      -JDBC is used with DAO design
 *      -Logging is achieved with Logback
 *      -All transactions are logged
 *      
 *      Functionality:
 *      -Customers may register accounts
 *      -Customers may have multiple accounts
 *      -Customers may link secondary accounts
 *      -Customers can add, remove, and transfer funds between accounts
 *      -User input validation
 *      -Employees may view customer account information
 *      -Employees may cancel accounts
 *      -Administrators may modify accounts
 *      
 *      The idea behind the design is that database connections involving 
 *      the user are handled by the class AccessAccounts. Console prompts
 *      and user interactions are handled by methods of the class UX. entry()
 *      is called once in the Driver class main method and the flow of the 
 *      application from there is dependent on user input -- navigating 
 *      interfaces to purchase items or manage their account. Each menu method
 *      continues forward into a sub-method, backward into the previous method,
 *      or returns to entry(). All classes are made available to each other
 *      with static declarations in Driver. That was probably a bad design decision
 *      in hindsight, but I remain proud of the progress I have made.  
 */

public class Driver {
	static UX ux = new UX();
	static AccessAccounts aa = new AccessAccounts();
	static Account account = new Account();
	static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
			ux.entry();
	}
}
