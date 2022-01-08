package com.revature.service.handleEmployee;

import com.revature.presentation.model.employee.Employee;
import com.revature.repository.DAOClasses.EmployeeAccountDao;
import com.revature.repository.DTO.EmployeeAccountEntity;
import com.revature.repository.DTO.EmployeeRoleEntity;
import com.revature.service.handleEmployee.interfaces.EmployeeServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

public class EmployeeService implements EmployeeServiceInterface {

    private final Logger dLog = LoggerFactory.getLogger("dLog");
    private final Logger tLog = LoggerFactory.getLogger("tLog");

    private EmployeeAccountDao employeeAccountDao;

    public EmployeeService(EmployeeAccountDao employeeAccountDao) {
        this.employeeAccountDao = employeeAccountDao;
    }

    @Override
    public EmployeeAccountEntity getEmployeeAccountById(int employeeId) {
        try {
            return employeeAccountDao.getEmployeeAccount(employeeId);
        } catch (SQLException e) {
            dLog.error(String.valueOf(e));
        } finally{
            dLog.debug("employeeAccount database access attempt");
        }
        dLog.debug("employee account retrieval fail");
        return null;
    }

    @Override
    public EmployeeRoleEntity getEmployeeRole(int employeeRoleId) {
        try {
            return employeeAccountDao.getEmployeeRoleById(employeeRoleId);
        } catch (SQLException e) {
            dLog.error(String.valueOf(e));
        }finally{
            dLog.debug("employee role database access attempt");
        }
        dLog.debug("employee role retrieval fail");
        return null;
    }

    @Override
    public Employee convertEmployeeEntityToEmployee(EmployeeAccountEntity employeeAccountEntity) {
        dLog.debug("employee account entity conversion to employee model attempt");
        boolean managerCheck = false;
        try{
            if(employeeAccountEntity.getRoleId() == 4) managerCheck = true;
            return new Employee(
                    employeeAccountEntity.getId(),
                    employeeAccountEntity.getFirstName(),
                    employeeAccountEntity.getLastName(),
                    getEmployeeRole(employeeAccountEntity.getRoleId()).getRoleName(),
                    managerCheck);
        }catch(NullPointerException e){
            dLog.debug("employee account entity not found");
            return null;
        }

    }

    @Override
    public Employee getEmployee(int employeeId) {
        return convertEmployeeEntityToEmployee(getEmployeeAccountById(employeeId));
    }
}