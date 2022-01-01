package com.revature.presentation.manageLogin;

import com.revature.presentation.manageLogin.interfaces.LoginControllerInterface;
import com.revature.presentation.model.LoginInput;
import com.revature.repository.DAOClasses.LoginInfoDao;
import com.revature.service.handleLogin.LoginAuthenticator;

public class LoginController implements LoginControllerInterface {


    @Override
    public LoginInput getClientLogin(String username, String password) {
        return new LoginInput(username, password);
    }

    @Override
    public boolean authenticateLogin(LoginInput loginInput) {
        LoginAuthenticator login = new LoginAuthenticator(new LoginInfoDao());

        return login.validateLogin(loginInput);
    }

    @Override
    public void manageLogin() {
        String username = "knight1";
        String password = "pass1";


        if(authenticateLogin(getClientLogin(username, password))) System.out.println("LOGED IN");
        else System.out.println("epic fail");
    }
}
