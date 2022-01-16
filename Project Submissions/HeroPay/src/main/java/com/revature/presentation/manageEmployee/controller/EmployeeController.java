package com.revature.presentation.manageEmployee.controller;

import com.revature.presentation.model.employeeRequests.EmployeeResponse;
import com.revature.repository.DAOClasses.EmployeeAccountDao;
import com.revature.service.handleEmployee.EmployeeService;
import com.revature.utility.JWTHandler;
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
                EmployeeResponse employeeResponse = employeeService.getEmployeeModelWithEmployeeId(employeeId);
                if (employeeResponse.isStatus()) {
                    dLog.debug("Found employee: " + ctx.queryParam("employeeId"));
                    ctx.json(employeeResponse);
                    ctx.status(200);
                } else {
                    dLog.debug("Employee not Found: " + ctx.queryParam("employeeId"));
                    ctx.status(500);
                }
            }catch (NumberFormatException e){
                dLog.debug(e.getMessage(),e);
                ctx.status(400);
            }


    };
}
