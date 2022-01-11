package db;

import models.Account;

public interface AccountRepository {
	
	Account selectEmployee(int empId);
	
	int validateUser(String username, String password);
}
