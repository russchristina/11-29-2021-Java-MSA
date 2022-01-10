package web;

import io.javalin.Javalin;

public class DevelopingDevelopers {

	public static void main(String[] args) {
		
		Javalin app = Javalin.create(config ->
			config.enableCorsForAllOrigins()).start(7777);
		
		//JavalinConfig jConfig = new JavalinConfig(app).configureHttpMethodPreference().enableCORS();
//		app.before(ctx ->{
//			ctx.header("Access-Control-Allow-Origin", "*");
//	        ctx.header("Access-Control-Allow-Methods: GET, POST, PUT, DELETE");
//	        ctx.header("Access-Control-Allow-Headers: Origin, Content-Type, X-Auth-Token");
//		});
		
		app.post("/verify", Controller.fetchEmployeeByName);
		app.post("/empRequests", Controller.fetchEmployeeRequests);
		app.get("/all-requests", Controller.fetchAllRequests);
		app.put("/requestSubmit", Controller.saveRequest);
		app.put("/request-update", Controller.changeStatus);
		app.put("/request-update-note", Controller.changeStatusAndNote);
	} // End main
} // End class
