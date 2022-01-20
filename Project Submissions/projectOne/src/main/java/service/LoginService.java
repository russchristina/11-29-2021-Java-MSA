package service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import control.login.LoginInput;
import control.login.UserModel;
import models.Login;
import repo.LoginDAO;
import repo.LoginImpl;
import util.GreendaleLogger;

public class LoginService implements LoginServiceInterface{
	
	Logger myLogger = LoggerFactory.getLogger(GreendaleLogger.class);
	
	private LoginDAO loginImpl;
	
	public LoginService() {
		
		this.loginImpl = new LoginImpl();
	}

	@Override
	public UserModel authenticateLoginInput(LoginInput loginInput) {
		
		Login login = loginImpl.getUser(loginInput.getUsername());
		
		return convertLogin(login);
		}

	private UserModel convertLogin(Login login) {
		
		return new UserModel(login.getUsername(), login.isManager());
	}
	
	public Login getUser(String username) {
		return this.loginImpl.getUser(username);
	}
	
	public Login checkUser(LoginInput loginInput) {
		return this.loginImpl.checkUser(loginInput);
	}

	public List<Login> findAll() {
		
		return this.loginImpl.findAll();
	}

	
		
	
		
	
	

}
