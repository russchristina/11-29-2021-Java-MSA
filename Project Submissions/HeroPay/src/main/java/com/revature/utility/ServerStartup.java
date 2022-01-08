package com.revature.utility;
import com.revature.presentation.manageEmployee.controller.EmployeeController;
import com.revature.presentation.manageLogin.controller.LoginController;
import com.revature.presentation.manageRequest.controller.RequestController;
import com.revature.presentation.model.requests.PendingRequest;
import com.revature.repository.DAOClasses.CompletedRequestDao;
import com.revature.repository.DAOClasses.LoginInfoDao;
import com.revature.repository.DAOClasses.PendingRequestDao;
import com.revature.service.handleLogin.LoginService;
import com.revature.service.handleRequest.CompletedRequestService;
import com.revature.service.handleRequest.PendingRequestService;
import io.javalin.Javalin;

public class ServerStartup {

    private Javalin app;

    public ServerStartup(Javalin app) {
        this.app = app;
    }

    public void configureServer(){
        this.app._conf.enableCorsForAllOrigins();
        this.app._conf.prefer405over404 = true;
        Endpoints endpoints = new Endpoints(
                new LoginController(new LoginService(new LoginInfoDao())),
                new EmployeeController(),
                new RequestController(new PendingRequestService(new PendingRequestDao()), new CompletedRequestService(new CompletedRequestDao())));
        endpoints.initializeEndpoints(this.app);
    }



}
