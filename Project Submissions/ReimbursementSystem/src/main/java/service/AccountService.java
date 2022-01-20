package service;

import java.util.List;

import db.DBConn;
import models.Account;

public class AccountService {
	DBConn conn = new DBConn();
	
	public void save(Account account) {
		conn.save(account);
	}
	
	public Account selectEmployee(int empId) {
		return conn.selectEmployee(empId);
	}
	
	public int validateUser(String username, String password) {
		return conn.validateUser(username, password);
	}
	
	public List<Account> selectAllEmployees(){
		return conn.selectAllEmployees();
	}
}
