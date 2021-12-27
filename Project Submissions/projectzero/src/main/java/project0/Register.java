package project0;

import java.util.HashMap;
import java.util.Map;

import project0.models.User;

public class Register {
	
static public	Map<String, String> register = new HashMap<String, String>();


	
	public static void main(String[] args) {
		
		User newUser = new User() ;
		
		User[] customers = {new User()};
		
		System.out.println(customers);
		
		
		
		 register.put("mike", "password");
		 
//		 System.out.println(infoMap);
	}

	
}
