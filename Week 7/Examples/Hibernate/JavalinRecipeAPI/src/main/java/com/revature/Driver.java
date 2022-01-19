package com.revature;

import javax.servlet.http.HttpSession;

import com.revature.controller.IngredientController;
import com.revature.util.JavalinConfig;

import io.javalin.Javalin;

public class Driver {

	public static void main(String[] args) {
		
		Javalin app = Javalin.create().start(8000);
		
		//Configure your Javalin app (I created a util class for this):
		JavalinConfig config = new JavalinConfig(app).configureHttpMethodPreference()
				.configureStaticResources();
		
		//Initialize your controllers
		IngredientController ingredientController = new IngredientController(app);
		ingredientController.initEndpoints();
		
		/*
		 * Javalin also has special handlers which allow you to intercept requests before
		 * they reach other handlers. This type of Handler can be useful if there is some task
		 * that you wish to perform before each route (meaning that it is essentially shared
		 * setup between all of the routes).
		 */
		
		app.before(ctx -> {
			System.out.println("Ayyyye! Jenkins works.");
		});
		
		/*
		 * If there is a "before" handler, yes, there is also an "after" handler.
		 */
		
		app.after(ctx -> {
			System.out.println("This happens after the request has made it to its designated handler.");
		});
		
		/*
		 * You can also define handlers for specific errors/status codes. This specific
		 * handler redirects requests that get 404 status codes as a part of the response
		 * to a new resource, specifically a resource called "404.html".
		 */
		
		app.error(404, ctx -> {
			ctx.redirect("/404.html");
		});
		
		/*
		 * You'll likely want some way of tracking client interactions with the server for
		 * the purpose of "session handling". If a client has a session, it might be allowed
		 * access to specific resources that clients which don't have sesssions will not
		 * have access to. 
		 * 
		 * One (easy) way of handling sessions is by using HttpSession. That said, using HttpSession
		 * means that you are not entirely RESTful as it violates one of the constraints of
		 * REST (the stateless constraint). HttpSession actually store information regarding clients
		 * on the server.
		 * 
		 * For a more sophisticated, proper way of handling sessions, I recommend JWT (JSON
		 * Web Tokens).
		 */
		
		app.post("/session", ctx -> {
			/*
			 * This creates a session for the client.
			 */
			ctx.req.getSession();
		});
		
		/*
		 * The client can only access this resource if they have a resource.
		 */
		app.get("/locked-resource", ctx -> {
			//Checking for the presence of a session.
			
			HttpSession session = ctx.req.getSession(false);
			if(session == null) ctx.res.getWriter().write("Sorry you can't access this resource.");
			else ctx.res.getWriter().write("Congrats. You're authenticated.");
		});
		
		/*
		 * You can also invalidate sessions when they're no longer needed.
		 */
		
		app.get("/logout", ctx -> {
			HttpSession session = ctx.req.getSession(false);
			if(session != null) session.invalidate();
		});

		app.get("/hello-jenkins", ctx -> {
			ctx.res.getWriter().write("ayeeeee jenkins works for real this time with set +e and correct path");
		});
	}
}
