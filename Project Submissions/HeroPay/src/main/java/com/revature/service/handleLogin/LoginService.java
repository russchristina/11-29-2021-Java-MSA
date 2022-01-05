package com.revature.service.handleLogin;

import com.revature.presentation.model.LoginInput;
import com.revature.repository.DAOClasses.LoginInfoDao;
import com.revature.repository.DTO.LoginInfoEntity;
import com.revature.service.handleLogin.interfaces.LoginServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

public class LoginService implements LoginServiceInterface {

    private final Logger logger = LoggerFactory.getLogger(LoginInfoDao.class);
    private final Logger transactionLogger = LoggerFactory.getLogger("transactionLogger");

    private LoginInfoDao loginInfoDao;

    public LoginService(LoginInfoDao loginInfoDao) {
        this.loginInfoDao = loginInfoDao;
    }

    @Override
    public LoginInfoEntity validateLogin(LoginInput loginInput) {

        try {
            return loginInfoDao.getLoginInfo(loginInput.getUsername(), loginInput.getPassword());
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        } finally{
            logger.debug("User Login Attempt: " + LoginService.class);
        }
        logger.debug("user login validation fail: " + LoginService.class);
        return null;

    }


}
