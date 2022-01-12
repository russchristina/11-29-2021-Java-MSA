package com.revature.utility;
import com.revature.presentation.manageEmployee.controller.EmployeeController;
import com.revature.presentation.manageLogin.controller.LoginController;
import com.revature.presentation.manageRequest.controller.RequestController;
import com.revature.presentation.manageStatistics.controller.StatisticsController;
import com.revature.repository.DAOClasses.*;
import com.revature.service.handleEmployee.EmployeeService;
import com.revature.service.handleLogin.LoginService;
import com.revature.service.handleManager.ManagerService;
import com.revature.service.handleRequest.CompletedRequestService;
import com.revature.service.handleRequest.SortingService;
import com.revature.service.handleRequest.PendingRequestService;
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
            ctx.header("Access-Control-Allow-Methods: GET, POST, PATCH, PUT, DELETE");
            ctx.header("Access-Control-Allow-Headers: Origin, Content-Type, X-Auth-Token");
        });
        this.app._conf.prefer405over404 = true;
        LoginInfoDao lid = new LoginInfoDao();
        PendingRequestDao pid = new PendingRequestDao();
        CompletedRequestDao crd = new CompletedRequestDao();
        EmployeeAccountDao eid = new EmployeeAccountDao();
        EmployeeRoleDao erd = new EmployeeRoleDao();
        RequestTypeDao rtd = new RequestTypeDao();


        LoginService ls = new LoginService(lid);
        EmployeeService es = new EmployeeService(eid, erd);
        PendingRequestService prs = new PendingRequestService(pid, rtd, es);
        CompletedRequestService crs = new CompletedRequestService(crd);

        ManagerService ms = new ManagerService(prs, crs);
        SortingService os = new SortingService(prs, crs);
        StatisticsService ss = new StatisticsService(prs, es, os, crs);

        LoginController lc = new LoginController(ls);
        EmployeeController ec = new EmployeeController();
        RequestController rc = new RequestController(prs, crs, os);
        StatisticsController sc = new StatisticsController(ss);

        Endpoints endpoints = new Endpoints(lc, ec, rc, sc);
        endpoints.initializeEndpoints(this.app);
    }



}