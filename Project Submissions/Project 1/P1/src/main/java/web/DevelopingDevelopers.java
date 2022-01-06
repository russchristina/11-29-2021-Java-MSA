package web;

import io.javalin.Javalin;

public class DevelopingDevelopers {

	public static void main(String[] args) {
		
		Javalin app = Javalin.create().start(7777); 	
		app.before(context ->{
			context.header("Access-Control-Allow-Origin", "*");
	        context.header("Access-Control-Allow-Methods: GET, POST, PATCH, PUT, DELETE, OPTIONS");
	        context.header("Access-Control-Allow-Headers: Origin, Content-Type, X-Auth-Token");
		});
		
		app.post("/verify", Controller.fetchEmployeeByName);
		app.get("/requests", Controller.fetchAllRequests);
		
		
	} // End main

} // End class
