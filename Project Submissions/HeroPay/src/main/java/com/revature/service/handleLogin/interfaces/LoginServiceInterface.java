package com.revature.service.handleLogin.interfaces;

import com.revature.presentation.model.loginRequests.LoginInput;
import com.revature.repository.DTO.LoginInfoEntity;

public interface LoginServiceInterface {

    LoginInfoEntity validateLogin(LoginInput loginInput);

}
