package com.revature.presentation.staticHTML;

import io.javalin.http.Context;

import java.io.IOException;

public class StaticHTMLHandler {

    public void getLoginPage(Context ctx) {
        try {
            ctx.res.getWriter().write("LOGIN PAGE");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getEmployeeHomepage(Context ctx) {
        try {
            ctx.res.getWriter().write("EMPLOYEE HOMEPAGE");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getManagerHomepage(Context ctx) {
        try {
            ctx.res.getWriter().write("MANAGER HOMEPAGE");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getStatisticsPage(Context ctx) {
        try {
            ctx.res.getWriter().write("STATISTICS PAGE");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
