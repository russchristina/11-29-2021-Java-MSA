package service;

import db.DBConn;
import models.Account;
import models.LoginReceived;

public class LoginService {

	public Account authenicateLogin(LoginReceived loginReceived) {
		DBConn q = new DBConn();
		
		String username = loginReceived.getUser();
		String password = loginReceived.getPass();
		
		Account a = q.selectEmployee(q.validateUser(username, password));
		
		return a;
	}
	
}
