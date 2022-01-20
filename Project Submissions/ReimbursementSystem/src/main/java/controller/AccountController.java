package controller;

import io.javalin.Javalin;
import io.javalin.http.*;
import models.LoginReceived;
import service.AccountService;
import service.LoginService;
import models.*;

import static io.javalin.apibuilder.ApiBuilder.*;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import db.DBConn;

public class AccountController {
	private final Logger dLog = LoggerFactory.getLogger("dLog");
	private Account acc;
	private Javalin app;
	
	public AccountController(Javalin app) {
		this.app = app;
		this.acc = new Account();
	}
	
	public void initEndpoints() {
		this.app.routes(() -> {
			path("/Account", () -> {
				
				path("/login", () -> {
					post(validateAccount);
				});
				
				path("/user", () -> {
					get(user);
				});
				
				path("/target", () -> {
					post(target);
				});
				
				path("/selectall", () -> {
					get(selectAllEmployees);
				});
				
			});
		});
	}
	
	private final Handler selectAllEmployees = ctx -> {
		DBConn conn = new DBConn();
		
		List<Account> ac = conn.selectAllEmployees(); 
		try {
			ctx.json(ac);
		}catch(Exception e) {
			dLog.error(e.getMessage(), e);
		}
	};
	
	private Handler validateAccount = ctx -> {
		dLog.debug("validating account");
		
		LoginReceived loginReceived = ctx.bodyAsClass(LoginReceived.class);
		LoginService loginService = new LoginService();
		
		this.acc = loginService.authenicateLogin(loginReceived);
		
		try {
			ctx.json(this.acc);
		} catch (Exception e) {
			dLog.error(e.getMessage(), e);
		}
		
	};
	
	private Handler user = ctx -> {
		dLog.debug("GET USER");
		
		try {
		ctx.json(this.acc);
		}catch(Exception e){
			dLog.error(e.getMessage(), e);
		}
	};
	
	private Handler target = ctx -> {
		dLog.debug("targeting account");
		
		Account targeted = ctx.bodyAsClass(Account.class);
		AccountService as = new AccountService();
		int id = targeted.getEmp_id();
		
		targeted = as.selectEmployee(id);
		
		try {
			ctx.json(targeted);
		}catch(Exception e) {
			dLog.error(e.getMessage(), e);
		}
	};
	
	
}

	
