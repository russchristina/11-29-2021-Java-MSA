package projectzerotest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import projectzero.FormatHandler;
import projectzero.UserAccountRepositoryImpl;
import projectzero.VirtualDollarsAccount;
import projectzero.VirtualDollarsUser;

@TestInstance(Lifecycle.PER_CLASS)
public class ProjectDBTest {

	private UserAccountRepositoryImpl repo;
	
	@BeforeAll
	public void setup() {
		repo = new UserAccountRepositoryImpl();
	} // End setup
	
	@Test
	public void testGetLastAccountNotNull() {	
		assertNotNull(repo.getLastAccount());
	} // End test
	
	@Test
	public void testCreateAccount() {
		repo.createAccount(); // This will create a new account record!!!
		assertEquals(0, repo.getLastAccount().getAccountBalance());
	} // End test
	
	@Test
	public void testDeleteAccount() {
		VirtualDollarsAccount vda = repo.getLastAccount();
		int id = vda.getAccountId();
		repo.deleteAccount(vda);
		assertNull(repo.findByAccountId(id));
	} // End test
	
	@Test
	public void testFindUsersByAccountId() {
		List<VirtualDollarsUser> userList = repo.findUsersByAccountId(1);	
		assertEquals(2, userList.size());
	} // End test
	
	@Test
	public void testFindUsersByAccountIdNotNull() {
		List<VirtualDollarsUser> userList = repo.findUsersByAccountId(1);
		assertNotNull(userList);
	} // End test
	
	@Test
	public void testFindUsersByAccountIdDoesNotExist() {
		List<VirtualDollarsUser> userList = repo.findUsersByAccountId(40);
		assertNull(userList);
	} // End test
	
	@Test
	public void testFindAccountByIDNotNull() {	
		assertNotNull(repo.findByAccountId(1));
	} // End test
	
	@Test
	public void testFindAccountByIDDoesNotExist() {	
		assertNull(repo.findByAccountId(40));
	} // End test
	
	@Test
	public void testFindByUsernameNotNull() {	
		assertNotNull(repo.findByUsername("Jack"));
	} // End test
	
	@Test
	public void testFindByUsernameDoesNotExist() {	
		assertNull(repo.findByUsername("aaaaa"));
	} // End test
	
	@Test
	public void testFindByEmployeeNameNotNull() {	
		assertNotNull(repo.findByEmployeeName("Alan"));
	} // End test
	
	@Test
	public void testFindByEmployeeNameDoesNotExist() {	
		assertNull(repo.findByEmployeeName("aaaaa"));
	} // End test
	
	@Test
	public void testFindByEmployeeNameAdminFlag() {	
		assertTrue(repo.findByEmployeeName("John").isAdmin());
	} // End test
	
	@Test
	public void testFindByEmployeeNameNoAdminFlag() {
		assertFalse(repo.findByEmployeeName("Alan").isAdmin());
	} // End test
	
	@Test
	public void testFindAllAccountsNotNull() {
		List<VirtualDollarsAccount> accountList = repo.findAllAccounts();
		assertNotNull(accountList);
	} // End test
	
	@Test
	public void testFindAllUsersNotNull() {		
		List<VirtualDollarsUser> userList = repo.findAllUsers();
		assertNotNull(userList);
	} // End test
	
	@Test
	public void testUpdateAccountAdd() {
		VirtualDollarsAccount vda = repo.findByAccountId(1);
		double bal = vda.getAccountBalance();
		vda.addFunds(50);
		vda = repo.findByAccountId(1);
		assertEquals(bal + 50, vda.getAccountBalance());
	} // End test
	
	@Test
	public void testUpdateAccountAddNegative() {
		VirtualDollarsAccount vda = repo.findByAccountId(1);
		double bal = vda.getAccountBalance();
		vda.addFunds(-50);
		vda = repo.findByAccountId(1);
		assertEquals(bal, vda.getAccountBalance());
	} // End test
	
	@Test
	public void testUpdateAccountRemove() {
		VirtualDollarsAccount vda = repo.findByAccountId(1);
		double bal = vda.getAccountBalance();
		vda.removeFunds(50);
		vda = repo.findByAccountId(1);
		assertEquals(bal - 50, vda.getAccountBalance());
	} // End test
	
	@Test
	public void testUpdateAccountRemoveNegative() {
		VirtualDollarsAccount vda = repo.findByAccountId(1);
		double bal = vda.getAccountBalance();
		vda.removeFunds(-50);
		vda = repo.findByAccountId(1);
		assertEquals(bal, vda.getAccountBalance());
	} // End test
	
	@Test
	public void testUpdateAccountOverdraw() {   
		VirtualDollarsAccount vda = repo.findByAccountId(1);
		double bal = vda.getAccountBalance();
		vda.removeFunds(5000);
		vda = repo.findByAccountId(1);
		assertEquals(bal, vda.getAccountBalance());
	} // End test
	
	@Test
	public void testNameFormatCheck() {
		FormatHandler fh = new FormatHandler();
		assertTrue(fh.nameFormatCheck("Chris"));
	} // End test
	
	@Test
	public void testNameFormatCheckFirstLetterCap() {
		FormatHandler fh = new FormatHandler();
		assertFalse(fh.nameFormatCheck("chris"));
	} // End test
	
	@Test
	public void testNameFormatCheckNumbers() {
		FormatHandler fh = new FormatHandler();
		assertFalse(fh.nameFormatCheck("Chr1s"));
	} // End test
	
	@Test
	public void testNameFormatCheckSpecialChar() {
		FormatHandler fh = new FormatHandler();
		assertFalse(fh.nameFormatCheck("C#ris"));
	} // End test
	
	@Test
	public void testNameFormatCheckSpace() {   
		FormatHandler fh = new FormatHandler();
		assertFalse(fh.nameFormatCheck("Ch ris"));
	} // End test
	
	@Test
	public void testDateFormatter() {
		FormatHandler fh = new FormatHandler();
		assertTrue(fh.dateFormatter("01/01/1990"));
	} // End test
	
	@Test
	public void testDateFormatterBad() {
		FormatHandler fh = new FormatHandler();
		assertFalse(fh.dateFormatter("01011990"));
	} // End test
	
	@Test
	public void testEmailFormatter() {
		FormatHandler fh = new FormatHandler();
		assertTrue(fh.emailFormatter("chris@email.com"));
	} // End test
	
	@Test
	public void testEmailFormatterBad() {
		FormatHandler fh = new FormatHandler();
		assertFalse(fh.emailFormatter("asjdfl;kjsad"));
	} // End test
} // End JUnit class
