package com.revature.service.handleEmployee;

import com.revature.presentation.model.employeeRequests.Employee;
import com.revature.presentation.model.statisticsRequests.response.QuickSortEmployee;
import com.revature.repository.DAOClasses.EmployeeAccountDao;
import com.revature.repository.DAOClasses.EmployeeRoleDao;
import com.revature.repository.DTO.EmployeeAccountEntity;
import com.revature.repository.DTO.EmployeeRoleEntity;
import com.revature.service.handleEmployee.interfaces.EmployeeServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeService implements EmployeeServiceInterface {

    private final Logger dLog = LoggerFactory.getLogger("dLog");
    private final Logger tLog = LoggerFactory.getLogger("tLog");

    private final EmployeeAccountDao employeeAccountDao;
    private final EmployeeRoleDao employeeRoleDao;


    public EmployeeService(EmployeeAccountDao employeeAccountDao, EmployeeRoleDao employeeRoleDao) {
        this.employeeAccountDao = employeeAccountDao;
        this.employeeRoleDao = employeeRoleDao;
    }

    @Override
    public EmployeeAccountEntity getEmployeeAccountById(int employeeId) {
        dLog.debug("Getting employee account entity by employee ID");
        return employeeAccountDao.getEmployeeAccount(employeeId);
    }

    @Override
    public EmployeeRoleEntity getEmployeeRole(int employeeRoleId) {
        dLog.debug("Getting employee role entity by ID");
        return employeeRoleDao.getEmployeeRoleById(employeeRoleId);
    }

    @Override
    public Employee convertEmployeeEntityToEmployee(EmployeeAccountEntity employeeAccountEntity) {
        dLog.debug("Converting EmployeeAccountEntity to Employee Model");
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
        return convertEmployeeEntityToEmployee(getEmployeeAccountById(employeeId));
    }

    @Override
    public Map<Integer, String> getEmployeeRoleMap() {
        dLog.debug("Getting List of all Employee Roles");
        Map<Integer, String> employeeRoleMap = new HashMap<>();
        employeeRoleDao.getAllEmployeeRoles().forEach(e -> employeeRoleMap.put(e.getId(), e.getRoleName()));
        return employeeRoleMap;
    }

    @Override
    public List<Employee> getAllEmployees() {
        dLog.debug("Converting all employees entities to employee model");
        List<EmployeeAccountEntity> employeeAccountEntities = employeeAccountDao.getAllEmployeeAccountList();
        List<Employee> employees = new ArrayList<>(employeeAccountEntities.size());
        employeeAccountEntities.forEach(e -> employees.add(convertEmployeeEntityToEmployee(e)));
        return employees;
    }

    @Override
    public List<QuickSortEmployee> getQuickSort() {
//        try {
//            return employeeAccountDao.getQuickSort();
//        } catch (SQLException e) {
//            dLog.debug(e.getMessage(), e);
//        }
        return null;
    }

    @Override
    public List<EmployeeAccountEntity> getAllEmployeesNotConverted() {
        return employeeAccountDao.getAllEmployeeAccountList();
    }
}
