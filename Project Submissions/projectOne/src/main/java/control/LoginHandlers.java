package control;

import control.login.LoginInput;
import io.javalin.http.Handler;
import models.Login;
import repo.LoginDAO;
import service.LoginService;

public class LoginHandlers {
	
	private LoginController loginController;
	private LoginService loginService;
	
	
	public LoginHandlers() {
		loginService = new LoginService();
	}
	
	public Handler authenticateLogin = ctx -> {
		LoginInput loginInput = ctx.bodyAsClass(LoginInput.class);
		loginController.authenticateLogin(loginInput);
	};
	
	public Handler getUser = ctx -> {
		Login login = loginService.getUser(String.valueOf(authenticateLogin));
	};
	
	public Handler findAll = ctx -> {
		ctx.json(loginService.findAll());
	};

}
