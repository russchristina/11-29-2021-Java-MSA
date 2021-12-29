package Driver;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import org.slf4j.Logger;

import com.revature.model.User;
import com.revature.util.ConnectionClosers;
import com.revature.util.ConnectionFactory;

import Dao.UserDao;
import Dao.UserDaoImpl;

public class LoginScreen {
static	Scanner sc = new Scanner(System.in);
UserDao userRepo = new UserDaoImpl();

public void options() {
	System.out.println("Welcome to The Fruit Shop"
			+"\n 1. Add funds"
			+"\n 2. Withdraw Funds"
			+"\n 3. Transfer Funds"
			+"\n 4. Exit ");


int choice;
do{choice = sc.nextInt();
switch (choice) {

case 1:
	
	addFunds();
break;

case 2:
withdrawFunds();
break;
case 3:
transferFunds();
break;
case 4:
System.out.println("Going back to Main Screen");
break;
default:
System.out.println("Not a valid choice");
}

} while( choice!=4);


}

private void transferFunds() {
	// TODO Auto-generated method stub
	
}

private void withdrawFunds() {
	
	
}

private void addFunds() {
	
	
		
	}
	
		
	
}
	




