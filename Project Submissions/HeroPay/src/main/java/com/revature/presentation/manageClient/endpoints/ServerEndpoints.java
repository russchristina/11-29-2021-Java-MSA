package com.revature.presentation.manageClient.endpoints;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.presentation.manageLogin.LoginController;
import com.revature.presentation.model.LoginInput;
import com.revature.presentation.staticHTML.StaticHTMLHandler;
import com.revature.repository.DTO.LoginInfoEntity;
import io.javalin.Javalin;

public class ServerEndpoints {

    private Javalin app;
    private StaticHTMLHandler staticHTML;
    private ObjectMapper objectMapper;

    private LoginController loginController;

    public ServerEndpoints(StaticHTMLHandler staticHTML) {
        this.app = Javalin.create().start(9002);
        loginController = new LoginController();
        app.before(ctx ->{
            ctx.header("Access-Control-Allow-Origin", "*");
            ctx.header("Access-Control-Allow-Methods: GET, POST, PATCH, PUT, DELETE, OPTIONS");
            ctx.header("Access-Control-Allow-Headers: Origin, Content-Type, X-Auth-Token");
        });

        this.staticHTML = staticHTML;
        objectMapper = new ObjectMapper();
        generateEndpoints();
    }

    public void generateEndpoints() {
        generateGetEndpoints();
        generatePutEndpoints();


    }

    private void generatePutEndpoints() {
        String createRequest = "/create-request";
        String verifyUser = "/auth";

        app.put(createRequest, ctx -> {
           String request = objectMapper.writeValueAsString(ctx.body());
           ctx.res.getWriter().write(request);
        });

        app.post(verifyUser, ctx -> {
//            String jsonResponse = objectMapper.writeValueAsString(ctx.body());
            LoginInput loginInput = objectMapper.readValue(ctx.body(), LoginInput.class);
//            System.out.println(loginInput);
            LoginInfoEntity loginInfoEntity = loginController.authenticateLogin(loginInput);
            if(loginInfoEntity != null) ctx.res.getWriter().write(objectMapper.writeValueAsString(loginInfoEntity));
            else ctx.res.getWriter().write(String.valueOf(false));
        });
    }

    private void generateGetEndpoints() {
        String loginPage = "/login-page";
        String homepage = "/homepage";
        String statistics = "/statistics";


        app.get(loginPage, ctx ->{
            staticHTML.getLoginPage(ctx);
        });

        app.get(homepage, ctx ->{
            staticHTML.getEmployeeHomepage(ctx);
        });

        app.get(statistics, ctx ->{
            staticHTML.getStatisticsPage(ctx);
        });

    }

    public static void main(String[] args) {
        ServerEndpoints server = new ServerEndpoints(new StaticHTMLHandler());
    }


}
