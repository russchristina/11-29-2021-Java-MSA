package controller;

import db.Query;
import io.javalin.Javalin;
import io.javalin.http.*;
import models.LoginReceived;
import service.LoginService;
import models.*;

import static io.javalin.apibuilder.ApiBuilder.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AccountController {
	private final Logger dLog = LoggerFactory.getLogger("dLog");
	private Javalin app;
	private Query query;
	
	public AccountController(Javalin app) {
		this.app = app;
		this.query = new Query();
	}
	
	public void initEndpoints() {
		this.app.routes(() -> {
			path("/Account", () -> {
				
				path("/login", () -> {
					post(validateAccount);
				});
				
			});
		});
	}
	
	private Handler validateAccount = ctx -> {
		dLog.debug("validating account");
		
		LoginReceived loginReceived = ctx.bodyAsClass(LoginReceived.class);
		LoginService loginService = new LoginService();
		
		Account account = loginService.authenicateLogin(loginReceived);
		
		System.out.println(account);
		
		try {
			ctx.json(account);
		} catch (Exception e) {
			dLog.error(e.getMessage(), e);
		}
		
	};
	
	
}

	
