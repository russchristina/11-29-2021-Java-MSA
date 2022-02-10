package com.revature;

import java.util.List;

import com.revature.model.Employee;
import com.revature.repo.Repository;
import com.revature.util.JavalinConfig;

import io.javalin.Javalin;

public class Driver {

	public static void main(String[] args) {
		
		Javalin app = Javalin.create(config -> {
			config.enableCorsForAllOrigins();
		}).start(9000);
		
		
		JavalinConfig config = new JavalinConfig(app).configureStaticResources();
		
		Repository repo = new Repository();
//		List<Employee> list = repo.getUsers();
//		Employee user;
//		
//		for(int i = 0; i < list.size(); i++) {
//			user = list.get(i);
//			System.out.println(user.toString());
//		}
		
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
			
			//User retrievedUser = ctx.bodyAsClass(User.class);
			
			//UserService userService = new UserService();
			//userService.save(retrievedUser);
		});
	}
}
