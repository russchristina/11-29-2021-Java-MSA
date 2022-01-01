package com.revature.service.handleLogin.interfaces;

import com.revature.presentation.model.Employee;
import com.revature.presentation.model.LoginInput;
import com.revature.repository.DTO.LoginInfoEntity;

public interface AuthenticateLogin {

    boolean validateLogin(LoginInput loginInput);

    Employee getEmployee(LoginInput loginInput);

    LoginInfoEntity getLoginInfo(LoginInput loginInput);

}
