package com.revature.presentation.manageEmployee.controller;

import com.revature.presentation.model.employeeRequests.Employee;
import com.revature.presentation.model.employeeRequests.EmployeeResponse;
import com.revature.repository.DAOClasses.EmployeeAccountDao;
import com.revature.service.handleEmployee.EmployeeService;
import io.javalin.http.Handler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmployeeController {
    private final Logger dLog = LoggerFactory.getLogger("dLog");
    private final Logger tLog = LoggerFactory.getLogger("tLog");

    private EmployeeService employeeService = null;

    public EmployeeController() {
        this.employeeService = new EmployeeService(new EmployeeAccountDao());
    }

    public final Handler findEmployee = ctx -> {
            dLog.debug("Finding employee: " + ctx.queryParam("employeeId"));
            try {
                int employeeId = Integer.parseInt(ctx.queryParam("employeeId"));
                Employee employee = employeeService.getEmployeeModelWithEmployeeId(employeeId);
                EmployeeResponse employeeResponse = new EmployeeResponse();
                if (employee != null) {
                    employeeResponse.setStatus(true);
                    employeeResponse.setFirstName(employee.getFirstName());
                    employeeResponse.setLastName(employee.getLastName());
                    employeeResponse.setRole(employee.getRole());
                    employeeResponse.setManager(employee.isManager());
                    dLog.debug("Found employee: " + ctx.queryParam("employeeId"));
                    ctx.json(employeeResponse);
                } else {
                    employeeResponse.setStatus(false);
                    employeeResponse.setFirstName("");
                    employeeResponse.setLastName("");
                    employeeResponse.setRole("");
                    employeeResponse.setManager(false);
                    dLog.debug("Employee not Found: " + ctx.queryParam("employeeId"));
                    ctx.json(employeeResponse);
                    ctx.status(406);
                }
            }catch (NumberFormatException e){
                dLog.debug(e.getMessage(),e);
                ctx.status(400);
            }


    };
}
