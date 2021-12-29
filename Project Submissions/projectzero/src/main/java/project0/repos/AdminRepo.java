package project0.repos;

import java.util.List;

import project0.models.User;

public interface AdminRepo {
	
	void save(User user);
	
	
	User findByUsername(String username);
	
	
	User findByName(String fullName);
	
	
	List<User> findAll();
	
	
	void updatePassword(User username);
	
	
	void updateAddress(User address);
	
	
	void updateCity(User city);
	
	
	void updateState(User state);
	
	
	void updatePhone(User phone);
	
	
	void addToBalance(User balance);
	
	
	void delete(User user);

}
