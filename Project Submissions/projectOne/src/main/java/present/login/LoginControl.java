package present.login;

import service.UserLoginService;

public class LoginControl implements LoginControlInterface{

	
	private UserLoginService userLoginService;
	
	
	
	public LoginControl(UserLoginService userLoginService) {
		super();
		this.userLoginService = userLoginService;
	}



	@Override
	public UserModel authenticateLogin(LoginInput loginInput) {
		
		UserModel userModel = userLoginService.authenticateLoginInput(loginInput);
		
		
		return userModel;
	}

}
