package web;

import io.javalin.Javalin;

public class DevelopingDevelopers {

	public static void main(String[] args) {
		
		Javalin app = Javalin.create().start(7000); 				  
		app.get("/hello", ctx -> ctx.html("Hello, Javalin!"));
		
		app.get("/requests", Controller.fetchAllRequests);
	} // End main

} // End class
