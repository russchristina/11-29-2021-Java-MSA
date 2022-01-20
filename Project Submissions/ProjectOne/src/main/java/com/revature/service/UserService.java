package com.revature.service;

import com.revature.model.User;
import com.revature.repository.UserRepository;
import com.revature.repository.UserRepositoryImpl;

public class UserService {

	private UserRepository userRepositoryImpl;
	
	public UserService() {
		this.userRepositoryImpl= new UserRepositoryImpl();
	}

	public void save(User user) {
		this.userRepositoryImpl.save(user);
	}
}
