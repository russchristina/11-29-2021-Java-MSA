package com.revature.presentation.manageEmployee;

import com.revature.presentation.manageEmployee.inteface.EmployeeControllerInterface;
import com.revature.presentation.model.Employee;
import com.revature.repository.DAOClasses.EmployeeAccountDao;
import com.revature.repository.DTO.LoginInfoEntity;
import com.revature.service.handleEmployee.EmployeeService;

public class EmployeeController implements EmployeeControllerInterface {

    private EmployeeService employeeService;

    public EmployeeController() {
        this.employeeService = new EmployeeService(new EmployeeAccountDao());
    }

    @Override
    public Employee getEmployee(int employeeId) {
        return employeeService.convertEmployeeEntityToEmployee(employeeService.getEmployeeAccountById(employeeId));
    }
}
