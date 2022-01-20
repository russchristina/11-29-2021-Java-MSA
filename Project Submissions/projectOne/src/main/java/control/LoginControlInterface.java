package control;

import control.login.LoginInput;
import control.login.UserModel;

public interface LoginControlInterface {
	
	UserModel authenticateLogin(LoginInput loginInput);
	
	

}
