package com.revature.service.handleEmployee.interfaces;

import com.revature.presentation.model.employeeRequests.Employee;
import com.revature.presentation.model.employeeRequests.EmployeeResponse;
import com.revature.presentation.model.statisticsRequests.response.QuickSortEmployee;
import com.revature.repository.DTO.EmployeeAccountEntity;
import com.revature.repository.DTO.EmployeeRoleEntity;

import java.util.List;
import java.util.Map;

public interface EmployeeServiceInterface {

    EmployeeAccountEntity getEmployeeAccountById(int employeeId);

    Employee convertEmployeeEntityToEmployee(EmployeeAccountEntity employeeAccountEntity);

    EmployeeResponse getEmployeeModelWithEmployeeId(int employeeId);

    List<EmployeeAccountEntity> getAllEmployeesAccountEntities();

    List<EmployeeAccountEntity> getAllEmployeesByRole(int roleId);
}
