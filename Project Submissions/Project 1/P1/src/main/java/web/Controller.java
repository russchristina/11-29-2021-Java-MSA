package web;

import io.javalin.http.Handler;
import models.Employee;
import models.Request;
import utilities.repository.EmployeeRequestRepositoryImpl;

public class Controller {
	
	public static Handler fetchEmployeeByName = ctx -> {
		String name = ctx.formParam("username");
		String pass = ctx.formParam("password");
		
		EmployeeRequestRepositoryImpl repo = new EmployeeRequestRepositoryImpl();
		Employee user = repo.findByEmployeeName(name);
		if (user == null) {
			ctx.html("Username not found");
		} else if (!pass.equals(user.getPassword())) {
			ctx.html("Password incorrect");
		} else {
			ctx.json(user);
		} // End else statement
	}; // End Handler
	
	public static Handler fetchEmployeeRequests = ctx -> {
		Employee r = ctx.bodyAsClass(Employee.class);
		EmployeeRequestRepositoryImpl repo = new EmployeeRequestRepositoryImpl();
		Iterable<Request> employeeRequests = repo.findRequestsByEmployeeName(r.getName());
		ctx.json(employeeRequests);
	};
	
	public static Handler fetchAllRequests = ctx -> {
		EmployeeRequestRepositoryImpl repo = new EmployeeRequestRepositoryImpl();
		Iterable<Request> allRequests = repo.findAllRequests();
		ctx.json(allRequests);
	};
	
	
	
	public static Handler saveRequest = ctx -> {
		Request r = ctx.bodyAsClass(Request.class);
		System.out.println(r);
	};
	
} // End class
