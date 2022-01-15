package com.revature.utility;

import com.revature.presentation.manageEmployee.controller.EmployeeController;
import com.revature.presentation.manageFiles.controller.FileController;
import com.revature.presentation.manageLogin.controller.LoginController;
import com.revature.presentation.manageRequest.controller.RequestController;
import com.revature.presentation.manageStatistics.controller.StatisticsController;
import io.javalin.Javalin;

import static io.javalin.apibuilder.ApiBuilder.*;

public class Endpoints {

    private final LoginController loginController;
    private final EmployeeController employeeController;
    private final RequestController requestController;
    private final StatisticsController statisticsController;
    private final FileController fileController;

    public Endpoints(LoginController loginController, EmployeeController employeeController, RequestController requestController, StatisticsController statisticsController, FileController fileController) {
        this.loginController = loginController;
        this.employeeController = employeeController;
        this.requestController = requestController;
        this.statisticsController = statisticsController;
        this.fileController = fileController;
    }

    public void initializeEndpoints(Javalin app){
        app.routes(() -> {

            path("/login", () -> {

              path("/validate", () -> {
                  post(loginController.validateLogin);
              });
              path("/employee-info", () -> {
                  get(employeeController.findEmployee);
              });
            });


            path("/employee", () ->{

                path("/request", () -> {

                    path("/all", () -> {
                        get(requestController.getEmployeeRequests);
                    });

                    path("/pending", () ->{
                       get(requestController.getEmployeePendingRequests);
                    });

                    path("/new", () -> {
                       post(requestController.createRequest);
                    });

                    path("/file", () ->{
                        post(fileController.handleFileUpload);
                    });

                    path("/treasure", () ->{
                        get(fileController.retrieveFile);
                    });

                });
            });

            path("/manager", () ->{

                path("/total", () ->{
                    get(requestController.getAllRequests);
                });

                path("/respond", () ->{
                    post(requestController.respondToRequest);
                });

                path("/statistic", () ->{

                    path("/general", () ->{
                       get(statisticsController.getGeneralStatistics);
                    });

                    path("/employee-list", () ->{
                       get(statisticsController.getEmployeeGeneralStatistics);
                    });

                    path("/individual", () -> {
                       get(statisticsController.getEmployeeStatistics);
                    });

                });
            });

        });
    }

}
