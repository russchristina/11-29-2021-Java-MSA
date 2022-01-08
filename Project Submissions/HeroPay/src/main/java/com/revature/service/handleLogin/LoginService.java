package com.revature.service.handleLogin;

import com.revature.presentation.model.login.LoginInput;
import com.revature.repository.DAOClasses.LoginInfoDao;
import com.revature.repository.DTO.LoginInfoEntity;
import com.revature.service.handleLogin.interfaces.LoginServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

public class LoginService implements LoginServiceInterface {

    private final Logger dLog = LoggerFactory.getLogger("dLog");
    private final Logger tLog = LoggerFactory.getLogger("tLog");

    private LoginInfoDao loginInfoDao;

    public LoginService(LoginInfoDao loginInfoDao) {
        this.loginInfoDao = loginInfoDao;
    }


    @Override
    public LoginInfoEntity validateLogin(LoginInput loginInput) {

        try {
            return loginInfoDao.getLoginInfo(loginInput.getUsername(), loginInput.getPassword());
        } catch (SQLException e) {
            dLog.error(e.getMessage(), e);
        } finally{
            dLog.debug("User Login Attempt: " + LoginService.class);
        }
        dLog.debug("user login validation fail: " + LoginService.class);
        return null;

    }


}
