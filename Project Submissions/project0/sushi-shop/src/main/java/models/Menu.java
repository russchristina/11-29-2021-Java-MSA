package models;

import java.util.Scanner;


public class Menu {
boolean exit;
int choice = 0;
int account_id;
String passwords;
Customer customer = new Customer();
Employeefunction emp = new Employeefunction();

	public void printBanner() {
		 System.out.println("--------------------");	
		 System.out.println("The				 ");	
		 System.out.println("  Sushi              ");	
		 System.out.println("     Shop              ");	
		 System.out.println("--------------------");	
}
			

public void userMenu() {
do {
System.out.println("");
System.out.println("");
System.out.println("1. log into a customer account");
System.out.println("2. Make a customer account");
System.out.println("3. logout");
System.out.println("");
System.out.println("The Menu");
System.out.println("Tempura roll - $8.99");
System.out.println("Mystery roll - $18.99");
System.out.println("california roll - $9.99");
System.out.println("");
System.out.println("");
Scanner sc = new Scanner(System.in);
choice = sc.nextInt();


switch(choice)
{
case 1:
	customer.loginCustomer();
	customer.customerMenu();

break;
	
case 2:
	customer.customerReg();
break;

case 3:

break;



default: System.out.println("pick a number between 1-3");
}
}
while(choice!=3);
System.out.println("thanks for visiting space cowboy");
}

public void employeeMenu() {
do {
System.out.println("");
System.out.println("");
System.out.println("1. view customer account");
System.out.println("2. View customer funds");
System.out.println("3. View user information");
System.out.println("4. Delete a account");
System.out.println("5. logout");

Scanner sc = new Scanner(System.in);
choice = sc.nextInt();


switch(choice)
{
case 1:
emp.viewAccount();
break;
	
case 2:
emp.viewfunds();
break;
	
	
case 3:
	emp.viewInfo();
	break;
case 4:
emp.deleteUser();
break;

case 5:

break;



default: System.out.println("pick a number between 1-5");
}
}
while(choice!=5);
System.out.println("thanks for visiting space cowboy");



}
}