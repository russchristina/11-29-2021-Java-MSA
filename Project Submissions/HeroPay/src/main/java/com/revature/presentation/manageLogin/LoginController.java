package com.revature.presentation.manageLogin;

import com.revature.presentation.manageLogin.interfaces.LoginControllerInterface;
import com.revature.presentation.model.LoginInput;
import com.revature.repository.DAOClasses.LoginInfoDao;
import com.revature.repository.DTO.LoginInfoEntity;
import com.revature.service.handleLogin.LoginService;

public class LoginController implements LoginControllerInterface {

    @Override
    public LoginInfoEntity authenticateLogin(LoginInput loginInput) {

        LoginService login = new LoginService(new LoginInfoDao());
        return login.validateLogin(loginInput);
    }


}
