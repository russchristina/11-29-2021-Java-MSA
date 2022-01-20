package com.revature.controllers;

import org.eclipse.jetty.security.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.models.Login;
import com.revature.repo.LoginUserInfo;

import io.javalin.http.Handler;

public class LoginControl {
	

    private Logger dLog = LoggerFactory.getLogger("dLog");
    private Logger tLog = LoggerFactory.getLogger("tLog");

    private LoginService loginService;

    public LoginControl(LoginService loginService) {
        this.loginService = loginService;
    }

    public Handler validateLogin = ctx ->{
        dLog.debug("Validating Login");
        try{
            LoginUserInfo loginInfo = loginService.validateLogin(ctx.bodyAsClass(Login.class));
            LoginResponse loginResponse = new LoginResponse();
            if(loginInfo != null) {
                loginResponse.setStatus(true);
                loginResponse.setEmployeeId(loginInfo.retreiveEmployeeid());
                dLog.debug("Validation Success: " + loginResponse.getEmployeeId());
                ctx.json(loginResponse);
            }else{
                loginResponse.setStatus(false);
                loginResponse.setEmployeeId(0);
                dLog.debug("Validation Fail");
                ctx.json(loginResponse);
            }
        } catch (Exception e){
            dLog.error(e.getMessage(), e);
            ctx.status(400);
        }
    };

}
