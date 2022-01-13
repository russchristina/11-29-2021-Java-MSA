package web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
		
		try {
			double numberAmount = Double.parseDouble(amount);
			Request r = new Request(name, numberAmount, reason);
			EmployeeRequestRepositoryImpl repo = new EmployeeRequestRepositoryImpl();
			repo.createRequest(r);
			Logger myLogger = LoggerFactory.getLogger("infoLogger");
			myLogger.info("Reimbursement request for " + String.format("%.2f", r.getAmount()) + " submitted by " + r.getEmployeeName() + " for " + r.getReason().toLowerCase() + ".");
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
	
	public static Handler fetchPendingRequests = ctx -> {
		EmployeeRequestRepositoryImpl repo = new EmployeeRequestRepositoryImpl();
		Iterable<Request> allRequests = repo.findPendingRequests();
		ctx.json(allRequests);
	}; // End Handler
	
	public static Handler fetchAllRequests = ctx -> {
		EmployeeRequestRepositoryImpl repo = new EmployeeRequestRepositoryImpl();
		Iterable<Request> allRequests = repo.findAllRequests();
		ctx.json(allRequests);
	}; // End Handler
	
	public static Handler changeRequest = ctx -> {
		Request r = ctx.bodyAsClass(Request.class);
		EmployeeRequestRepositoryImpl repo = new EmployeeRequestRepositoryImpl();
		if (r.getStatus().equals("Approved") || r.getStatus().equals("Denied")) {
			repo.updateRequest(r);
			Logger myLogger = LoggerFactory.getLogger("infoLogger");
			if(r.getStatus().equals("Approved")) {
				myLogger.info("Reimbursement request for " + r.getReason().toLowerCase() + " submitted by " + r.getEmployeeName() + " has been approved");
				ctx.html("Reimbursement request for " + r.getReason().toLowerCase() + " submitted by " + r.getEmployeeName() + " has been approved");
			} else if (r.getStatus().equals("Denied")) {
				myLogger.info("Reimbursement request for " + r.getReason().toLowerCase() + " submitted by " + r.getEmployeeName() + " has been denied");
				ctx.html("Reimbursement request for " + r.getReason().toLowerCase() + " submitted by " + r.getEmployeeName() + " has been denied");
			} // End else if statement
		} else {
			ctx.html("Action failed");
		} // End else statement
	}; // End Handler
	
	public static Handler fetchHighestAmount = ctx -> {
		EmployeeRequestRepositoryImpl repo = new EmployeeRequestRepositoryImpl();
		Request r = repo.highestSpender();
		ctx.json(r);
	}; // End Handler
	
	public static Handler fetchNumberOfRequests = ctx -> {
		EmployeeRequestRepositoryImpl repo = new EmployeeRequestRepositoryImpl();
		int numRequests = repo.numberOfRequests();
		ctx.html(String.valueOf(numRequests));
	}; // End Handler
	
	public static Handler fetchAverageAmountReq = ctx -> {
		EmployeeRequestRepositoryImpl repo = new EmployeeRequestRepositoryImpl();
		double avgReqAmt = repo.averageCost();
		ctx.html(String.format("%.2f", avgReqAmt));
	}; // End Handler
} // End class
