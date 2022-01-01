package com.revature.service.handleLogin;

import com.revature.repository.DAOClasses.EmployeeAccountDao;
import com.revature.repository.DAOClasses.LoginInfoDao;
import com.revature.repository.DTO.EmployeeAccountEntity;
import com.revature.repository.DTO.LoginInfoEntity;
import com.revature.service.handleLogin.interfaces.GenerateEmployee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

public class EmployeeGenerator implements GenerateEmployee {

    private final Logger logger = LoggerFactory.getLogger(LoginInfoDao.class);
    private final Logger transactionLogger = LoggerFactory.getLogger("transactionLogger");

    private EmployeeAccountDao employeeAccountDao;

    public EmployeeGenerator(EmployeeAccountDao employeeAccountDao) {
        this.employeeAccountDao = employeeAccountDao;
    }


    @Override
    public EmployeeAccountEntity generateEmployee(LoginInfoEntity loginInfoEntity) {
        try {
            return employeeAccountDao.getEmployeeAccount(loginInfoEntity.getEmployeeId());
        } catch (SQLException e) {
            logger.error(String.valueOf(e));
        }
        return null;
    }
}
