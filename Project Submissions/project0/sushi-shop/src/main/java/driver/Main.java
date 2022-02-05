package driver;

import java.util.Scanner;

import models.Accounting;
import models.Customer;
import models.Employee;
import models.Login;
import models.Menu;
import models.Registration;

public class Main {
	public static void main(String[] args) {
	int option = 0; 
	Menu menu = new Menu();
	Registration register = new Registration();
	Login login = new Login();
	Employee employee = new Employee();
	Customer customer = new Customer();
	Accounting accounting = new Accounting();


do {	
	System.out.println("");
	System.out.println("");
menu.printBanner();
System.out.println("1.login");
System.out.println("2.Register");
System.out.println("3.employee Login");
System.out.println("4.exit");
Scanner scan = new Scanner(System.in);
option = scan.nextInt();

switch(option)
{
case 1:
login.getLoginDetails();
menu.userMenu();
break;
	
case 2:
register.getUserDetails();
register.sendUserDetails();
break;
	
	
case 3:
employee.getEmployeeLoginDetails();
menu.employeeMenu();
break;

case 4: break;


default: System.out.println("pick a number between 1-4");
}
}
while(option!=4);
System.out.println("thanks for visiting space cowboy");

	
	}
}

