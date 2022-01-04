package com.revature.presentation.manageLogin.interfaces;

import com.revature.presentation.model.LoginInput;
import com.revature.repository.DTO.LoginInfoEntity;

public interface LoginControllerInterface {

    LoginInfoEntity authenticateLogin(LoginInput loginInput);

}
