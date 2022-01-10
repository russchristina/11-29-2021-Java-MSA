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
	
	public static Handler saveRequest = ctx -> {
		String name = ctx.formParam("empName");
		String amount = ctx.formParam("reqAmount");
		String reason = ctx.formParam("reqReason");
		System.out.println(name + " " + amount + " " + reason);
		try {
			double numberAmount = Double.parseDouble(amount);
			Request r = new Request(name, numberAmount, reason);
			EmployeeRequestRepositoryImpl repo = new EmployeeRequestRepositoryImpl();
			repo.createRequest(r);
			ctx.html("Request submitted successfully");
		} catch (NumberFormatException e) {
			ctx.html("Invalid input. Request not created");
		} // End catch block
	}; // End Handler
	
	public static Handler fetchEmployeeRequests = ctx -> {
		Employee r = ctx.bodyAsClass(Employee.class);
		EmployeeRequestRepositoryImpl repo = new EmployeeRequestRepositoryImpl();
		Iterable<Request> employeeRequests = repo.findRequestsByEmployeeName(r.getName());
		ctx.json(employeeRequests);
	}; // End Handler
	
	public static Handler fetchAllRequests = ctx -> {
		EmployeeRequestRepositoryImpl repo = new EmployeeRequestRepositoryImpl();
		Iterable<Request> allRequests = repo.findAllRequests();
		ctx.json(allRequests);
	}; // End Handler
	
	public static Handler changeStatus = ctx -> {
		Request r = ctx.bodyAsClass(Request.class);
		EmployeeRequestRepositoryImpl repo = new EmployeeRequestRepositoryImpl();
		if (r.getStatus().equals("Approved") || r.getStatus().equals("Denied")) {
			repo.updateRequestStatus(r);
			if(r.getStatus().equals("Appoved")) {
				ctx.html("Request Approved");
			} else if (r.getStatus().equals("Denied")) {
				ctx.html("Request Denied");
			} // End else if statement
		} else {
			ctx.html("Action failed");
		} // End else statement
	}; // End Handler
	
	public static Handler changeStatusAndNote = ctx -> {
		Request r = ctx.bodyAsClass(Request.class);
		EmployeeRequestRepositoryImpl repo = new EmployeeRequestRepositoryImpl();
		if (r.getStatus().equals("Approved") || r.getStatus().equals("Denied")) {
			repo.updateRequestStatusAndNote(r);
			if(r.getStatus().equals("Appoved")) {
				ctx.html("Request Approved");
			} else if (r.getStatus().equals("Denied")) {
				ctx.html("Request Denied");
			} // End else if statement
		} else {
			ctx.html("Action failed");
		} // End else statement
	}; // End Handler
} // End class
