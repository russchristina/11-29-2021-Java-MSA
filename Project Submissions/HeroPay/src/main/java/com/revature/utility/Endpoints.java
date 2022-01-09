package com.revature.utility;

import com.revature.presentation.manageEmployee.controller.EmployeeController;
import com.revature.presentation.manageLogin.controller.LoginController;
import com.revature.presentation.manageRequest.controller.RequestController;
import com.revature.presentation.manageStatistics.controller.StatisticsController;
import io.javalin.Javalin;

import static io.javalin.apibuilder.ApiBuilder.*;

public class Endpoints {

    private LoginController loginController;
    private EmployeeController employeeController;
    private RequestController requestController;
    private StatisticsController statisticsController;

    public Endpoints(LoginController loginController, EmployeeController employeeController, RequestController requestController, StatisticsController statisticsController) {
        this.loginController = loginController;
        this.employeeController = employeeController;
        this.requestController = requestController;
        this.statisticsController = statisticsController;
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

                });
            });

        });
    }

//    public void generatePostEndpoints() {
//        this.app.routes(() -> {
//            path("/authenticate", () -> {
//
//                path("/login", () -> {
//
//                }) ;
//            });
//        });
//
//        String verifyUser = "/auth";
//
//
//        this.app.post(verifyUser, ctx -> {
//            LoginInput loginInput = objectMapper.readValue(ctx.body(), LoginInput.class);
//            LoginInfoEntity loginInfoEntity = loginController.authenticateLogin(loginInput);
//            Employee employee = employeeController.getEmployee(loginInfoEntity.getEmployeeId());
//            if(employee != null) ctx.res.getWriter().write(objectMapper.writeValueAsString(employee));
//            else ctx.res.getWriter().write(String.valueOf(false));
//        });
//
//    }
//
//    private void generatePutEndpoints() {
//
//        String createRequest = "/create-request";
//
//
//        this.app.put(createRequest, ctx -> {
//            String request = objectMapper.writeValueAsString(ctx.body());
//            ctx.res.getWriter().write(request);
//        });
//
//
//
//    }
//
//    private void generateGetEndpoints() {
//        String loginPage = "/login-page";
//        String homepage = "/homepage";
//        String statistics = "/statistics";
//
//
//        app.get(loginPage, ctx ->{
//            staticHTML.getLoginPage(ctx);
//        });
//
//        app.get(homepage, ctx ->{
//            staticHTML.getEmployeeHomepage(ctx);
//        });
//
//        app.get(statistics, ctx ->{
//            staticHTML.getStatisticsPage(ctx);
//        });
//
//
//
//    }
}
