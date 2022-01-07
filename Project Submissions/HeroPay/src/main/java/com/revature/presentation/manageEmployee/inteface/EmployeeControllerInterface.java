package com.revature.presentation.manageEmployee.inteface;

import com.revature.presentation.model.Employee;
import com.revature.repository.DTO.LoginInfoEntity;

public interface EmployeeControllerInterface {

    Employee getEmployee(int employeeId);

}
