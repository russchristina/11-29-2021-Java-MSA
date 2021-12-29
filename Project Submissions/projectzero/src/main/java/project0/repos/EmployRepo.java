package project0.repos;

import java.util.List;

import project0.models.User;

public interface EmployRepo {

	
User findByUsername(String username);
	
	
	User findByName(String fullName);
	
	
	List<User> findAll();
	
	
	void delete(User user);
}
