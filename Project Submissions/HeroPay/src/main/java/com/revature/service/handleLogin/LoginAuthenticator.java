package com.revature.service.handleLogin;

import com.revature.presentation.model.Employee;
import com.revature.presentation.model.LoginInput;
import com.revature.repository.DAOClasses.LoginInfoDao;
import com.revature.repository.DTO.LoginInfoEntity;
import com.revature.service.handleLogin.interfaces.AuthenticateLogin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

public class LoginAuthenticator implements AuthenticateLogin {

    private final Logger logger = LoggerFactory.getLogger(LoginInfoDao.class);
    private final Logger transactionLogger = LoggerFactory.getLogger("transactionLogger");

    private LoginInfoDao loginInfoDao;

    public LoginAuthenticator(LoginInfoDao loginInfoDao) {
        this.loginInfoDao = loginInfoDao;
    }

    @Override
    public boolean validateLogin(LoginInput loginInput) {
        LoginInfoEntity loginInfo = getLoginInfo(loginInput);
        try{
            if(loginInfo.getUsername().contentEquals(loginInput.getUsername())) return loginInfo.getPassword().contentEquals(loginInput.getPassword());
        } catch(NullPointerException e){
            logger.debug(String.valueOf(e));
        }

        return false;
    }

    @Override
    public Employee getEmployee(LoginInput loginInput) {
        try {
            return loginInfoDao.getEmployeeWithLogin(loginInput.getUsername(), loginInput.getPassword());
        } catch (SQLException e) {
            logger.error(String.valueOf(e));
        }
        return null;
    }

    @Override
    public LoginInfoEntity getLoginInfo(LoginInput loginInput) {
        try {
            return loginInfoDao.getLoginInfo(loginInput.getUsername(), loginInput.getPassword());
        } catch (SQLException e) {
            logger.error(String.valueOf(e));
        }
        return null;
    }


}
