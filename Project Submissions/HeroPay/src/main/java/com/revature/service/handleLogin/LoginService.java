package com.revature.service.handleLogin;

import com.revature.presentation.model.loginRequests.LoginInput;
import com.revature.repository.DAOClasses.LoginInfoDao;
import com.revature.repository.DTO.EmployeeAccountEntity;
import com.revature.repository.DTO.LoginInfoEntity;
import com.revature.service.handleLogin.interfaces.LoginServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

public class LoginService implements LoginServiceInterface {

    private final Logger dLog = LoggerFactory.getLogger("dLog");
    private final Logger tLog = LoggerFactory.getLogger("tLog");

    private final LoginInfoDao loginInfoDao;

    public LoginService(LoginInfoDao loginInfoDao) {
        this.loginInfoDao = loginInfoDao;
    }

    @Override
    public LoginInfoEntity validateLogin(LoginInput loginInput) {
        dLog.debug("Validating login for: " + loginInput.getUsername());
        return loginInfoDao.getLoginInfo(new LoginInfoEntity(0, loginInput.getUsername(), loginInput.getPassword(), new EmployeeAccountEntity()));
    }
}
