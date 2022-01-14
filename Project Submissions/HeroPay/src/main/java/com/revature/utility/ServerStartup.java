package com.revature.utility;

import com.revature.presentation.manageEmployee.controller.EmployeeController;
import com.revature.presentation.manageFiles.controller.FileController;
import com.revature.presentation.manageLogin.controller.LoginController;
import com.revature.presentation.manageRequest.controller.RequestController;
import com.revature.presentation.manageStatistics.controller.StatisticsController;
import com.revature.repository.DAOClasses.*;
import com.revature.service.handleEmployee.EmployeeService;
import com.revature.service.handleEmployee.RoleService;
import com.revature.service.handleLogin.LoginService;
import com.revature.service.handleRequest.CompletedRequestService;
import com.revature.service.handleRequest.PendingRequestService;
import com.revature.service.handleRequest.RequestTypeService;
import com.revature.service.handleRequest.SortingService;
import com.revature.service.handleStatistics.StatisticsService;
import io.javalin.Javalin;

public class ServerStartup {

    private final Javalin app;

    public ServerStartup(Javalin app) {
        this.app = app;
    }

    public void configureServer(){
        this.app.before(ctx -> {
            ctx.header("Access-Control-Allow-Origin", "*");
            ctx.header("Access-Control-Allow-Methods: GET, POST, PATCH, PUT, DELETE, OPTIONS");
            ctx.header("Access-Control-Allow-Headers: Origin, Content-Type, X-Auth-Token");
        });
        LoginInfoDao lid = new LoginInfoDao();
        PendingRequestDao pid = new PendingRequestDao();
        CompletedRequestDao crd = new CompletedRequestDao();
        EmployeeAccountDao eid = new EmployeeAccountDao();
        EmployeeRoleDao erd = new EmployeeRoleDao();
        RequestTypeDao rtd = new RequestTypeDao();


        LoginService ls = new LoginService(lid);
        RoleService rs = new RoleService(erd);
        EmployeeService es = new EmployeeService(eid);
        RequestTypeService rts = new RequestTypeService(rtd);
        PendingRequestService prs = new PendingRequestService(pid);
        CompletedRequestService crs = new CompletedRequestService(crd);


        SortingService os = new SortingService();
        StatisticsService ss = new StatisticsService(prs, es, rs, os, crs, rts);

        LoginController lc = new LoginController(ls);
        EmployeeController ec = new EmployeeController();
        RequestController rc = new RequestController(prs, crs, os);
        StatisticsController sc = new StatisticsController(ss);
        FileController fc = new FileController();

        Endpoints endpoints = new Endpoints(lc, ec, rc, sc, fc);
        endpoints.initializeEndpoints(this.app);
    }



}
