package db;

import java.util.List;

import models.Account;

public interface AccountRepository {
	
	Account selectEmployee(int empId);
	
	List<Account> selectAllEmployees();
	
//	Account selectHighestAvg();
	
	int validateUser(String username, String password);
	
	void save(Account account);
}
