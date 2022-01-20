package service;

import models.LoginInput;
import models.UserModel;

public interface UserLoginServiceInterface {
	
	UserModel authenticateLoginInput(LoginInput loginInput);
	

}
