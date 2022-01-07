package com.revature.presentation.manageClient.endpoints;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.presentation.staticHTML.StaticHTMLHandler;
import io.javalin.Javalin;

public class ServerEndpoints {

    private Javalin app;
    private StaticHTMLHandler staticHTML;
    private ObjectMapper objectMapper;

    public ServerEndpoints(StaticHTMLHandler staticHTML) {
        this.app = Javalin.create().start(9002);
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
            String request = objectMapper.writeValueAsString(ctx.body());
            ctx.res.getWriter().write(request);
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
