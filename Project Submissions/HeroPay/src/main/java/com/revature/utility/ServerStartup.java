package com.revature.utility;
import com.revature.presentation.manageEmployee.controller.EmployeeController;
import com.revature.presentation.manageLogin.controller.LoginController;
import com.revature.presentation.manageRequest.controller.RequestController;
import com.revature.presentation.manageStatistics.controller.StatisticsController;
import com.revature.presentation.model.requests.PendingRequest;
import com.revature.repository.DAOClasses.CompletedRequestDao;
import com.revature.repository.DAOClasses.EmployeeAccountDao;
import com.revature.repository.DAOClasses.LoginInfoDao;
import com.revature.repository.DAOClasses.PendingRequestDao;
import com.revature.service.handleEmployee.EmployeeService;
import com.revature.service.handleLogin.LoginService;
import com.revature.service.handleManager.ManagerService;
import com.revature.service.handleRequest.CompletedRequestService;
import com.revature.service.handleRequest.OrderingService;
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

        LoginService ls = new LoginService(lid);
        EmployeeService es = new EmployeeService(eid);
        PendingRequestService prs = new PendingRequestService(pid);
        CompletedRequestService crs = new CompletedRequestService(crd);

        ManagerService ms = new ManagerService(prs, crs);
        OrderingService os = new OrderingService(prs, crs);
        StatisticsService ss = new StatisticsService(prs, es, os);

        LoginController lc = new LoginController(ls);
        EmployeeController ec = new EmployeeController();
        RequestController rc = new RequestController(prs, crs);
        StatisticsController sc = new StatisticsController(ss);

        Endpoints endpoints = new Endpoints(lc, ec, rc, sc);
        endpoints.initializeEndpoints(this.app);
    }



}
