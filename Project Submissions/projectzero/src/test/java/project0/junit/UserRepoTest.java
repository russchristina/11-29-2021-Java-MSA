package project0.junit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import project0.implement.UserRepoImpl;
import project0.models.User;
import project0.repos.UserRepo;

@TestInstance(Lifecycle.PER_CLASS)
public class UserRepoTest {

	private UserRepo userRepo;
	
	@BeforeAll
	public void setup() {
		userRepo = new UserRepoImpl();
		
	}
	
	
	@Test
	public void testFindUserByName() {
		User retrievedUser = userRepo.findByName("test");
		Assertions.assertNotNull(retrievedUser);
		
	}
	
	@Test
	public void testFindUserByUsername() {
		User retrievedUser = userRepo.findByUsername("testuser");
		Assertions.assertNotNull(retrievedUser);
	}
}
