package service;

import impl.UserLoginImpl;
import models.LoginInput;
import models.UserLogin;
import models.UserModel;

public class UserLoginService implements UserLoginServiceInterface{

	private UserLoginImpl userLoginDAO;
	
	
	
	public UserLoginService(UserLoginImpl userLoginDAO) {
		super();
		this.userLoginDAO = userLoginDAO;
	}



	@Override
	public UserModel authenticateLoginInput(LoginInput loginInput) {
		UserLogin userLoginEntity = userLoginDAO.getUser(loginInput.getUsername());
		
		return convertUserEntity(userLoginEntity);
	}



	private UserModel convertUserEntity(UserLogin userLoginEntity) {

		return new UserModel(
				userLoginEntity.getUsername(), userLoginEntity.isManager());
	}

}
