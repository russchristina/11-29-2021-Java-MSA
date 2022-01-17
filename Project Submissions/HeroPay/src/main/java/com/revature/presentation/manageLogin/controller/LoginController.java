package com.revature.presentation.manageLogin.controller;

import com.revature.presentation.model.loginRequests.LoginInput;
import com.revature.presentation.model.loginRequests.LoginResponse;
import com.revature.repository.DTO.LoginInfoEntity;
import com.revature.service.handleLogin.LoginService;
import io.javalin.http.Handler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginController {

    private final Logger dLog = LoggerFactory.getLogger("dLog");
    private final Logger tLog = LoggerFactory.getLogger("tLog");

    private LoginService loginService = null;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    public final Handler validateLogin = ctx ->{
        dLog.debug("Validating Login");
        try{
            LoginInfoEntity loginInfo = loginService.validateLogin(ctx.bodyAsClass(LoginInput.class));
            LoginResponse loginResponse = new LoginResponse();
            if(loginInfo != null) {
                loginResponse.setStatus(true);
                loginResponse.setEmployeeId(loginInfo.getEmployeeAccount().getId());
                dLog.debug("Validation Success: " + loginResponse.getEmployeeId());
            }else{
                loginResponse.setStatus(false);
                loginResponse.setEmployeeId(0);
                dLog.debug("Validation Fail");
            }
            ctx.json(loginResponse);
        } catch (Exception e){
            dLog.error(e.getMessage(), e);
            ctx.status(406);
        }
    };


}
