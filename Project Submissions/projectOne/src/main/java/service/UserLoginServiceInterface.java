package service;

import present.login.LoginInput;
import present.login.UserModel;

public interface UserLoginServiceInterface {
	
	UserModel authenticateLoginInput(LoginInput loginInput);
	

}
