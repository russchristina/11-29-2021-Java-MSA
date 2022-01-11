package com.revature.service.handleEmployee;

import com.revature.presentation.model.employeeRequests.Employee;
import com.revature.presentation.model.statisticsRequests.response.QuickSortEmployee;
import com.revature.repository.DAOClasses.EmployeeAccountDao;
import com.revature.repository.DTO.EmployeeAccountEntity;
import com.revature.repository.DTO.EmployeeRoleEntity;
import com.revature.service.handleEmployee.interfaces.EmployeeServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EmployeeService implements EmployeeServiceInterface {

    private final Logger dLog = LoggerFactory.getLogger("dLog");
    private final Logger tLog = LoggerFactory.getLogger("tLog");

    private final EmployeeAccountDao employeeAccountDao;


    public EmployeeService(EmployeeAccountDao employeeAccountDao) {
        this.employeeAccountDao = employeeAccountDao;
    }

    @Override
    public EmployeeAccountEntity getEmployeeAccountById(int employeeId) {
//        try {
//            return employeeAccountDao.getEmployeeAccount(employeeId);
//        } catch (SQLException e) {
//            dLog.error(String.valueOf(e));
//        } finally{
//            dLog.debug("employeeAccount database access attempt");
//        }
//        dLog.debug("employee account retrieval fail");
        return null;
    }

    @Override
    public EmployeeRoleEntity getEmployeeRole(int employeeRoleId) {
//        try {
//            return employeeAccountDao.getEmployeeRoleById(employeeRoleId);
//        } catch (SQLException e) {
//            dLog.error(String.valueOf(e));
//        }finally{
//            dLog.debug("employee role database access attempt");
//        }
//        dLog.debug("employee role retrieval fail");
        return null;
    }

    @Override
    public Employee convertEmployeeEntityToEmployee(EmployeeAccountEntity employeeAccountEntity) {

        return null;

    }

    @Override
    public Employee getEmployee(int employeeId) {
        return convertEmployeeEntityToEmployee(getEmployeeAccountById(employeeId));
    }

    @Override
    public Map<Integer, String> getEmployeeRoleMap() {
//        try {
//            return employeeAccountDao.getEmployeeRoleMap();
//        } catch (SQLException e) {
//            dLog.debug(e.getMessage(), e);
//        }
        return null;
    }

    @Override
    public List<Employee> getAllEmployees() {
//        try {
//            List<EmployeeAccountEntity> employeeAccountEntities = employeeAccountDao.getAllEmployeeAccountList();
//            List<Employee> employeeList = new ArrayList<>();
//            employeeAccountEntities.forEach(employee -> {
//               employeeList.add(convertEmployeeEntityToEmployee(employee));
//            });
//            return employeeList;
//        } catch (SQLException e) {
//            dLog.debug(e.getMessage(), e);
//        }
        return null;
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
}
