package util;

import java.util.List;

import control.LoginController;
import control.RequestController;
import control.login.LoginInput;
import io.javalin.Javalin;
import models.Login;
import models.Request;
import repo.LoginDAO;
import repo.LoginImpl;
import service.EmployeeService;
import service.LoginService;
import service.ManagerService;
import service.RequestService;

public class GreendaleJavalin {

	public static void main(String[] args) {
		
		Javalin app = Javalin.create(config -> config.enableCorsForAllOrigins()).start(8000);
		
		JavalinConfig config = new JavalinConfig(app).configureHttpMethodPreference().configureStaticResources();
		
		RequestController requestController = new RequestController(app);
		requestController.initEndpoints();
		
		LoginController loginController = new LoginController(app);
		loginController.initEndpoints();
		
		
		app.post("/request/new", ctx ->{
			System.out.println(ctx.body());
			
			Request submittedRequest = ctx.bodyAsClass(Request.class);
			
			RequestService requestService = new RequestService();
			requestService.submitNew(submittedRequest);
			
		});
		
		app.get("/request/all", ctx ->{
			System.out.println(ctx.body());
			List<Request> retrievedRequests = (List<Request>) ctx.bodyAsClass(Request.class);
			RequestService requestService = new RequestService();
			requestService.viewAll();
			
		});
		
		app.get("/request/pending", ctx ->{
			System.out.println(ctx.body());
			List<Request> retrievedRequests = (List<Request>) ctx.bodyAsClass(Request.class);
			RequestService requestService = new RequestService();
			requestService.findByStatus("Pending");
			
		});
		
		app.get("/request/complete", ctx ->{
			System.out.println(ctx.body());
			List<Request> retrievedRequests = (List<Request>) ctx.bodyAsClass(Request.class);
			RequestService requestService = new RequestService();
			requestService.findByStatus("Approved AND Denied");
			
		});
		
		app.patch("/request/approve", ctx ->{
			System.out.println(ctx.body());
			Request retrievedRequest = ctx.bodyAsClass(Request.class);
			ManagerService managerService = new ManagerService();
			int id = retrievedRequest.getId();
			managerService.approve(id);
			
		});
		
		app.get("/request/stats", ctx ->{
			ctx.res.getWriter().write("to view statistics of requests");
			
		});
		
		app.patch("/request/update", ctx ->{
			System.out.println(ctx.body());
			Request retrievedRequest = ctx.bodyAsClass(Request.class);
			EmployeeService employeeService = new EmployeeService();
			int id = retrievedRequest.getId();
			employeeService.update(id);
						
		});
		
		app.delete("/request/delete", ctx ->{
			System.out.println(ctx.body());
			Request retrievedRequest = ctx.bodyAsClass(Request.class);
			EmployeeService employeeService = new EmployeeService();
			int id = retrievedRequest.getId();
			employeeService.delete(id);
			
		});
		
		app.post("/user/login", ctx -> {
			System.out.println(ctx.body());
			LoginInput loginInput = ctx.bodyAsClass(LoginInput.class);
			LoginDAO loginDAO = new LoginImpl();
			LoginService loginService = new LoginService();
			loginService.authenticateLoginInput(loginInput);
			
		});
		
		app.get("/user/auth", ctx ->{
			LoginInput loginInput = ctx.bodyAsClass(LoginInput.class);
			LoginDAO loginDAO = new LoginImpl();
			LoginService loginService = new LoginService();
			loginService.checkUser(loginInput);
			
		});
		
		app.get("/user/all", ctx -> {
			System.out.println(ctx.body());
			Login retrievedUsers = ctx.bodyAsClass(Login.class);
			LoginDAO loginDAO= new LoginImpl();
			loginDAO.findAll();
		});
	}
}
