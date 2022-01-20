package repo;

import java.util.List;

import control.login.LoginInput;
import io.javalin.http.Handler;
import models.Login;

public interface LoginDAO {
	
	/*
	 * Create Read Update Delete
	 */
	
	//Create
	Login insertUser(String username, String password, boolean isManager);
	
	//Read
	Login getUser(String username);
	
	List<Login> findAll();
	
	Login checkUser(LoginInput loginInput);
	
	//Update
	
	Login updateUsername(int id, String newUsername);
	
	//Delete
	
	void deleteUser(String username);
	

}
