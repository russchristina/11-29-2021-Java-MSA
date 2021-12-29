package Driver;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.model.User;
import com.revature.util.ConnectionClosers;
import com.revature.util.ConnectionFactory;

import ch.qos.logback.core.net.SyslogOutputStream;


public class Driver {
	static Scanner sc = new Scanner(System.in);
	static User user = new User();
public static void customerPress() {
	System.out.println("Welcome to The Fruit Shop"
			+"\n 1. Login"
			+"\n 2. Create a New Account"
			+"\n 3. Employee Login"
			+"\n 4. Exit ");

//Switch statement to check for Login or Create a New Account
int choice;
do{choice = sc.nextInt();
switch (choice) {

case 1:
login();
break;

case 2:
RegisterAcct();
break;
case 3:
adminLogin();
break;
case 4:
System.out.println("Thank you for your business. Goodbye");
break;
default:
System.out.println("Not a valid choice");
}
System.out.println("Welcome to The Fruit Shop"
		+"\n 1. Login"
		+"\n 2. Create a New Account"
		+"\n 3. Employee Login"
		+"\n 4. Exit ");
} while( choice!=4);
}




public static void login() {
Scanner sc1 = new Scanner(System.in);
//String SQL = "select*From Customers where customer_username =? and customer_userpassword =?";
User user = new User();
//LoginScreen logscreen = new LoginScreen();
String SQL = "select customer_username, customer_userpassword From Customers ";
String username;
String password;
System.out.println("Please Enter your username");
username = sc1.nextLine();
System.out.println("Please Enter your password");
password = sc1.nextLine();
System.out.println("Attempting to Login");
Connection conn = null;
PreparedStatement stmt = null;
ResultSet set = null;
try {
conn = ConnectionFactory.getConnection();
stmt = conn.prepareStatement(SQL);

set= stmt.executeQuery();
if(set.next()){


if(username.equals(set.getString(1))) {
if(password.equals(set.getString(2))) {
System.out.println("Logged in");
LoginScreen logscreen = new LoginScreen();
logscreen.options();

}
}else {System.out.println("Incorrect Login");
sc1.close();
}
}

}	

catch(SQLException e) {
e.printStackTrace();
}finally {
ConnectionClosers.closeConnection(conn);
ConnectionClosers.closeStatement(stmt);
ConnectionClosers.closeResultSet(set);}


}


private static void RegisterAcct() {
// TODO Auto-generated method stub
Scanner scan = new Scanner(System.in);
final String SQL = "insert into Customers(customer_username,customer_userpassword)" +"values(?,?)";
Connection conn = null;
PreparedStatement  stmt = null;
System.out.println("Enter a username");
String username = scan.nextLine();
System.out.println("Enter a password");
String password = scan.nextLine();
try {
conn = ConnectionFactory.getConnection();
stmt = conn.prepareStatement(SQL);
stmt.setString(1, username);
stmt.setString(2, password);
stmt.executeUpdate();

}catch(SQLException e) {
e.printStackTrace();
}finally {
ConnectionClosers.closeConnection(conn);
ConnectionClosers.closeStatement(stmt);
}

}
private static void adminLogin() { 
boolean done = false;
int id;

while (!done) {
System.out.println("Employee Main Menu " +
"\n1. Account Summary for a Customer " +
"\n2. Cancel Account " +
"\n3. Exit");
int opt = sc.nextInt();
switch (opt) {
case 1:
System.out.println("Please insert ID for retrival");
System.out.print("ID: ");
id = sc.nextInt();

break;
case 2:

break;


case 3:
//Exit
done = true;
break;
default:
System.out.println("Please choose a valid option (1, 2, 3, ).");
}
}
}



	public static void main(String[] args) {
Driver.customerPress();
		
		
	}
}





