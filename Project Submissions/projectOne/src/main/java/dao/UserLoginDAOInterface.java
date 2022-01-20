package dao;

import java.util.List;

import models.Login;
import models.UserLogin;

public interface UserLoginDAOInterface {

/*
 * Create Read Update Delete	
 */

	//Create
	
	UserLogin insertUser(String username, String password, boolean isManager);
	
	//Read
	
	UserLogin getUser(String username);

	List<Login> findAll();
	
	
}
