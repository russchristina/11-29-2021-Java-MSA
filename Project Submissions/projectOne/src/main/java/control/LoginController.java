package control;

import control.login.LoginInput;
import control.login.UserModel;
import io.javalin.Javalin;
import models.Login;
import repo.LoginDAO;
import repo.LoginImpl;
import service.LoginService;
import static io.javalin.apibuilder.ApiBuilder.*;

public class LoginController implements LoginControlInterface{

	private Javalin app;
	private LoginService loginService;
	private LoginHandlers loginHandler;
	private LoginDAO loginDAO;
	
		
	public LoginController(Javalin app) {
		this.app = app;
		this.loginHandler = new LoginHandlers();
		this.loginDAO = new LoginImpl();
	}

	public UserModel authenticateLogin(LoginInput loginInput) {
		
		UserModel userModel = loginService.authenticateLoginInput(loginInput);
		
		return userModel;
	}
	
	public void initEndpoints() {
		
		this.app.routes(() -> {
			path("/greendale", () -> {
			path("/user", () -> {
				path("/auth", () -> {
					post(this.loginHandler.authenticateLogin);
			});
				path("/login", () -> {
					get(this.loginHandler.getUser);
				});
				path("/all", () -> {
					get(this.loginHandler.findAll);
				});
		});
		});		
		});
	}


}
