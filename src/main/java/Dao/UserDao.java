package Dao;

import java.util.List;

import com.revature.model.User;

public interface UserDao {
	List<User> findAll();

	void save(User user);

	User findById(int id);

	User findByName(String username);

 void update(User user);
}
