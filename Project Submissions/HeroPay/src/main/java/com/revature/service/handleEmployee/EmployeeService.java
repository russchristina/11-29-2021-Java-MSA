package com.revature.service.handleEmployee;

import com.revature.presentation.model.employeeRequests.Employee;
import com.revature.presentation.model.employeeRequests.EmployeeResponse;
import com.revature.repository.DAOClasses.EmployeeAccountDao;
import com.revature.repository.DTO.EmployeeAccountEntity;
import com.revature.repository.DTO.EmployeeRoleEntity;
import com.revature.service.handleEmployee.interfaces.EmployeeServiceInterface;
import com.revature.utility.JWTHandler;
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
        try {
            boolean managerCheck = employeeAccountEntity.getEmployeeRole().getRoleName().contentEquals("Manager");
            return new Employee(
                    employeeAccountEntity.getId(),
                    employeeAccountEntity.getFirstName(),
                    employeeAccountEntity.getLastName(),
                    employeeAccountEntity.getEmployeeRole().getRoleName(),
                    managerCheck
            );
        }catch(NullPointerException e){
            dLog.debug("Null Employee Account Entity conversion attempt, returning null");
            return null;
        }
    }

    @Override
    public EmployeeResponse getEmployeeModelWithEmployeeId(int employeeId) {
        dLog.debug("Getting employee with ID and converting them: " + employeeId);
        Employee employee = convertEmployeeEntityToEmployee(getEmployeeAccountById(employeeId));
        EmployeeResponse employeeResponse = new EmployeeResponse();
        employeeResponse.setStatus(true);
        employeeResponse.setFirstName(employee.getFirstName());
        employeeResponse.setLastName(employee.getLastName());
        employeeResponse.setRole(employee.getRole());
        employeeResponse.setManager(employee.isManager());
        if(employee.isManager()){
            employeeResponse.setJWT(JWTHandler.generateManagerJwt());
        }else{
            employeeResponse.setJWT(JWTHandler.generateEmployeeJwt());
        }
        return employeeResponse;
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
