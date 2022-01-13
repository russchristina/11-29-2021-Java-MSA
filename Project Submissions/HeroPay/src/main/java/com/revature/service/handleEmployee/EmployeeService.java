package com.revature.service.handleEmployee;

import com.revature.presentation.model.employeeRequests.Employee;
import com.revature.repository.DAOClasses.EmployeeAccountDao;
import com.revature.repository.DTO.EmployeeAccountEntity;
import com.revature.repository.DTO.EmployeeRoleEntity;
import com.revature.service.handleEmployee.interfaces.EmployeeServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class EmployeeService implements EmployeeServiceInterface {

    private final Logger dLog = LoggerFactory.getLogger("dLog");
    private final Logger tLog = LoggerFactory.getLogger("tLog");

    private final EmployeeAccountDao employeeAccountDao;

    public EmployeeService(EmployeeAccountDao employeeAccountDao) {
        this.employeeAccountDao = employeeAccountDao;
    }

    @Override
    public EmployeeAccountEntity getEmployeeAccountById(int employeeId) {
        dLog.debug("Getting employee account entity by employee ID: " + employeeId);
        return employeeAccountDao.getEmployeeAccount(new EmployeeAccountEntity(employeeId, "", "", new EmployeeRoleEntity()));
    }

    @Override
    public Employee convertEmployeeEntityToEmployee(EmployeeAccountEntity employeeAccountEntity) {
        dLog.debug("Converting EmployeeAccountEntity to Employee Model: " + employeeAccountEntity);
        boolean managerCheck = employeeAccountEntity.getEmployeeRole().getId() == 4;
        return new Employee(
                employeeAccountEntity.getId(),
                employeeAccountEntity.getFirstName(),
                employeeAccountEntity.getLastName(),
                employeeAccountEntity.getEmployeeRole().getRoleName(),
                managerCheck
        );
    }

    @Override
    public Employee getEmployee(int employeeId) {
        dLog.debug("Getting employee with ID and converting them: " + employeeId);
        return convertEmployeeEntityToEmployee(getEmployeeAccountById(employeeId));
    }

    @Override
    public List<EmployeeAccountEntity> getAllEmployeesAccountEntities() {
        dLog.debug("Getting all employee account entities");
        return employeeAccountDao.getAllEmployeeAccountList();
    }

    @Override
    public List<EmployeeAccountEntity> getAllEmployeesByRole(int roleId) {
        dLog.debug("getting all employee by a role ID: " + roleId);
        return employeeAccountDao.getEmployeeAccountsByRoleId(new EmployeeRoleEntity(roleId, ""));
    }
}
