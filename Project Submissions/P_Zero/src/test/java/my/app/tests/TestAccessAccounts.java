package my.app.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import my.app.AccessAccounts;
import my.app.Account;

public class TestAccessAccounts {
	AccessAccounts aa = new AccessAccounts();
	
	@Test
	public void getIdByNameTestExpected() {
		int id = this.aa.getIdByName("admin");
		Assertions.assertEquals(id, 1);
	}
	
	@Test
	public void getIdByNameTestNoMatch() {
		int id = this.aa.getIdByName("pekachu");
		Assertions.assertEquals(id, 0);
	}
	
	@Test
	public void getAccountByIdTestExpected() {
		Account account = this.aa.getAccountById(4);
		Assertions.assertNotEquals(account, new Account());
	}
	
	@Test
	public void getAccountByIdTestNoMatch() {
		Account account = this.aa.getAccountById(0);
		Assertions.assertEquals(account, new Account());
	}
	
}
