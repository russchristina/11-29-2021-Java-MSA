package test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import db.AccountRepository;
import models.Account;
import service.AccountService;

public class AccountServiceTest implements AccountRepository {
	
	@Mock
	AccountService as = mock(AccountService.class);
	
	@Test
	public Account selectEmployee(int empId) {
		return as.selectEmployee(empId);
	}

	@Test
	public List<Account> selectAllEmployees() {
		return as.selectAllEmployees();
	}

	@Test
	public int validateUser(String username, String password) {
		return as.validateUser(username, password);
	}

	@Test
	public void save(Account account) {
		as.save(account);
	}

}
