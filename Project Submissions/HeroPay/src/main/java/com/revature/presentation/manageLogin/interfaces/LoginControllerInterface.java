package com.revature.presentation.manageLogin.interfaces;

import com.revature.presentation.model.LoginInput;

public interface LoginControllerInterface {
    
    LoginInput getClientLogin(String username, String password);

    boolean authenticateLogin(LoginInput loginInput);

    void manageLogin();
}
