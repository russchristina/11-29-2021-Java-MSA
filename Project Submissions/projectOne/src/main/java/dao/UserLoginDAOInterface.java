package dao;

public interface UserLoginDAOInterface {

/*
 * Create Read Update Delete	
 */

	//Create
	
	UserEntity insertUser(String username, String password, boolean isManager);
	
	//Read
	
	UserEntity getUser(String username);
	
	//Update
	UserEntity updateUsername(int id, String newUsername);
	
	//Delete
	UserEntity deleteUser(int id);
	
	
	
	
	
}
