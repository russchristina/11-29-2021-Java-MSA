package com.revature;

import com.revature.model.User;
import com.revature.service.UserService;
import com.revature.util.JavalinConfig;

import io.javalin.Javalin;

public class Driver {

	public static void main(String[] args) {
		
		Javalin app = Javalin.create().start(9000);
		
		JavalinConfig config = new JavalinConfig(app).configureStaticResources();
		
		//Let's create the endpoint that we're trying to use to intercept the data from
		//the client:
		app.post("/user/auth", ctx -> {
			/*
			 * Proof of concept: We are retrieving the JSON that was sent in the request
			 * body by the client.
			 */
			System.out.println(ctx.body());
			
			/*
			 * Let's have Jackson deserialize the JSON in the request body into a Java object.
			 */
			
			User retrievedUser = ctx.bodyAsClass(User.class);
			
			UserService userService = new UserService();
			userService.save(retrievedUser);
		});
	}
}
