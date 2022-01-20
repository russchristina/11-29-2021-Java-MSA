package com.revature;


import com.revature.model.User;
import com.revature.service.UserService;
import com.revature.util.JavalinConfig;

import io.javalin.Javalin;

public class Driver {
	public static void main(String[] args) {
		Javalin app = Javalin.create().start(9000);
		
		JavalinConfig config = new JavalinConfig(app).configureStaticResources();
		
		app.post("/user/auth", ctx->{
		System.out.println(ctx.body());
		
		User retrievedUser = ctx.bodyAsClass(User.class);
		
		UserService userService = new UserService();
		userService.save(retrievedUser);
		});

	}
}
