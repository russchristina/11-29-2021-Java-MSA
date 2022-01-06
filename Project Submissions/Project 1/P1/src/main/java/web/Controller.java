package web;

import io.javalin.http.Handler;
import models.Employee;
import models.Request;
import utilities.repository.EmployeeRequestRepositoryImpl;

public class Controller {
	
	public static Handler fetchEmployeeByName = ctx -> {
		String name = ctx.formParam("username");
		String pass = ctx.formParam("password");
		System.out.println(name);           // testing
		System.out.println(pass);           // testing
		
		EmployeeRequestRepositoryImpl repo = new EmployeeRequestRepositoryImpl();
		Employee user = repo.findByEmployeeName(name);
		if (user == null) {
			ctx.html("Username not found");
			System.out.println("un bad");    // testing
		} else if (!pass.equals(user.getPassword())) {
			String bad = "Password incorrect";
			ctx.html(bad);
			System.out.println("pw bad");    // testing
		} else {
			// ctx.json(user);
			ctx.json(user);
			System.out.println("success");    // testing
		} // End else statement
	}; // End Handler
	
	public static Handler fetchAllRequests = ctx -> {
		EmployeeRequestRepositoryImpl repo = new EmployeeRequestRepositoryImpl();
		Iterable<Request> allRequests = repo.findAllRequests();
		ctx.json(allRequests);
	};
	
} // End class
