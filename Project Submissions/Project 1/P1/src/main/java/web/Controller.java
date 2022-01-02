package web;

import java.util.Objects;

import io.javalin.http.Handler;
import models.Employee;
import models.Request;
import utilities.repository.EmployeeRequestRepositoryImpl;

public class Controller {
	
	public static Handler fetchEmployeeByName = ctx -> {
		String name = Objects.requireNonNull(ctx.formParam("username"));
		String password = Objects.requireNonNull(ctx.formParam("password"));
		EmployeeRequestRepositoryImpl repo = new EmployeeRequestRepositoryImpl();
		Employee user = repo.findByEmployeeName(name);
		if (user == null) {
			ctx.html("Username not found");
		} else if (!password.equals(user.getPassword())) {
			ctx.html("Password incorrect");
		} else {
			ctx.json(user);
		} // End else statement
	}; // End Handler
	
	public static Handler fetchAllRequests = ctx -> {
		EmployeeRequestRepositoryImpl repo = new EmployeeRequestRepositoryImpl();
		Iterable<Request> allRequests = repo.findAllRequests();
		ctx.json(allRequests);
	};
	
} // End class
