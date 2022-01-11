package service;

import db.Query;
import models.Account;
import models.LoginReceived;

public class LoginService {

	public Account authenicateLogin(LoginReceived loginReceived) {
		Query q = new Query();
		
		String username = loginReceived.getUser();
		String password = loginReceived.getPass();
		
		Account a = q.selectEmployee(q.validateUser(username, password));
		
		return a;
	}

}
