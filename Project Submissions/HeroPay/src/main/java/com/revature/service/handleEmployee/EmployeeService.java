package com.revature.service.handleEmployee;

import com.revature.presentation.model.Employee;
import com.revature.repository.DAOClasses.EmployeeAccountDao;
import com.revature.repository.DAOClasses.LoginInfoDao;
import com.revature.repository.DTO.EmployeeAccountEntity;
import com.revature.repository.DTO.EmployeeRoleEntity;
import com.revature.service.handleEmployee.interfaces.EmployeeServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

public class EmployeeService implements EmployeeServiceInterface {

    private final Logger logger = LoggerFactory.getLogger(LoginInfoDao.class);
    private final Logger transactionLogger = LoggerFactory.getLogger("transactionLogger");

    private EmployeeAccountDao employeeAccountDao;

    public EmployeeService(EmployeeAccountDao employeeAccountDao) {
        this.employeeAccountDao = employeeAccountDao;
    }

    @Override
    public EmployeeAccountEntity getEmployeeAccountById(int employeeId) {
        try {
            return employeeAccountDao.getEmployeeAccount(employeeId);
        } catch (SQLException e) {
            logger.error(String.valueOf(e));
        } finally{
            logger.debug("employeeAccount database access attempt");
        }
        logger.debug("employee account retrieval fail");
        return null;
    }

    @Override
    public EmployeeRoleEntity getEmployeeRole(int employeeRoleId) {
        try {
            return employeeAccountDao.getEmployeeRoleById(employeeRoleId);
        } catch (SQLException e) {
            logger.error(String.valueOf(e));
        }finally{
            logger.debug("employee role database access attempt");
        }
        logger.debug("employee role retrieval fail");
        return null;
    }

    @Override
    public Employee convertEmployeeEntityToEmployee(EmployeeAccountEntity employeeAccountEntity) {
        logger.debug("employee account entity conversion to employee model attempt");
        return new Employee(
                employeeAccountEntity.getId(),
                employeeAccountEntity.getFirstName(),
                employeeAccountEntity.getLastName(),
                getEmployeeRole(
                        employeeAccountEntity.getRoleId()).getRoleName());
    }
}
