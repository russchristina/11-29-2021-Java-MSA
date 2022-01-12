package com.revature.service.handleEmployee.interfaces;

import com.revature.presentation.model.employeeRequests.Employee;
import com.revature.presentation.model.statisticsRequests.response.QuickSortEmployee;
import com.revature.repository.DTO.EmployeeAccountEntity;
import com.revature.repository.DTO.EmployeeRoleEntity;

import java.util.List;
import java.util.Map;

public interface EmployeeServiceInterface {

    EmployeeAccountEntity getEmployeeAccountById(int employeeId);

    EmployeeRoleEntity getEmployeeRole(int employeeRoleId);

    Employee convertEmployeeEntityToEmployee(EmployeeAccountEntity employeeAccountEntity);

    Employee getEmployee(int employeeId);

    Map<Integer, String> getEmployeeRoleMap();

    List<Employee> getAllEmployees();

    List<QuickSortEmployee> getQuickSort();

    List<EmployeeAccountEntity> getAllEmployeesNotConverted();

    List<EmployeeRoleEntity> getAllEmployeeRoles();

    List<EmployeeAccountEntity> getAllEmployeesByRole(int roleId);
}
