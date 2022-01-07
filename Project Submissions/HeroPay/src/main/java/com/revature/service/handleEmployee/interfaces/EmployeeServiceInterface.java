package com.revature.service.handleEmployee.interfaces;

import com.revature.presentation.model.Employee;
import com.revature.repository.DTO.EmployeeAccountEntity;
import com.revature.repository.DTO.EmployeeRoleEntity;
import jdk.jfr.Description;

public interface EmployeeServiceInterface {

    EmployeeAccountEntity getEmployeeAccountById(int employeeId);

    EmployeeRoleEntity getEmployeeRole(int employeeRoleId);

    Employee convertEmployeeEntityToEmployee(EmployeeAccountEntity employeeAccountEntity);

}
