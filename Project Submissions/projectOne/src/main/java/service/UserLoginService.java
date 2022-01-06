package service;

import dao.UserEntity;
import present.login.LoginInput;
import present.login.UserModel;
import repo.UserLoginDAO;

public class UserLoginService implements UserLoginServiceInterface{

	private UserLoginDAO userLoginDAO;
	
	
	
	public UserLoginService(UserLoginDAO userLoginDAO) {
		super();
		this.userLoginDAO = userLoginDAO;
	}



	@Override
	public UserModel authenticateLoginInput(LoginInput loginInput) {
		UserEntity userLoginEntity = userLoginDAO.getUser(loginInput.getUsername());
		
		return convertUserEntity(userLoginEntity);
	}



	private UserModel convertUserEntity(UserEntity userLoginEntity) {

		return new UserModel(
				userLoginEntity.getUsername(), userLoginEntity.isManager());
	}

}
