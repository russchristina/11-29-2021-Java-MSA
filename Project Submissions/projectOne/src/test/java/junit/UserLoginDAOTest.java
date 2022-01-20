package junit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import control.login.LoginInput;
import models.Login;
import repo.LoginDAO;
import repo.LoginImpl;

@TestInstance(Lifecycle.PER_CLASS)
public class UserLoginDAOTest {
	
	private LoginDAO loginDAO;
	
	
	@BeforeAll
	public void setup() {
		loginDAO = new LoginImpl();
	}
	
	@Test
	public void testGetUser() {
		Login retrievedUser = new Login();
		
		Assertions.assertNotNull(retrievedUser);
	}
	
	@Test
	public void testCheckUser() {
		LoginInput loginInput = new LoginInput("frankydart", "olditlady");
		Login retrievedUser = loginDAO.getUser(loginInput.getUsername());
		
		Assertions.assertNotNull(retrievedUser);
		Assertions.assertNotSame(loginInput, retrievedUser);
	}
	
	
}
