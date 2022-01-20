package com.revature.service;

import com.revature.models.Login;
import com.revature.repo.LoginRepo;
import com.revature.repoclass.LoginRepoImp;

public class LoginService {
	private LoginRepo loginRepoImp;

	public LoginService(LoginRepoImp limp) {
		this.loginRepoImp = new LoginRepoImp();
		
	}

	public void save(Login login) {
		this.loginRepoImp.saveLogin(login);
	}
}