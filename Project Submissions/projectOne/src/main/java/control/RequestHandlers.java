package control;

import java.util.List;

import io.javalin.http.Handler;
import models.Request;
import service.RequestService;

public class RequestHandlers {
	
	private RequestService requestService;
	
	
	public RequestHandlers() {
		requestService = new RequestService();
		
	}
	
	public Handler viewAll = ctx -> {
		System.out.println("Find all requests has been hit.");
		ctx.json(requestService.viewAll());
	};
	
	public Handler submitNew = ctx -> {
		Request request = ctx.bodyAsClass(Request.class);
		System.out.println(request);
		requestService.submitNew(request);
		ctx.status(201);
	};
	
	public Handler findById = ctx -> {
		Request retrievedRequest = requestService.findById(Integer.parseInt(ctx.pathParam("id")));
		if(retrievedRequest != null)
			ctx.json(retrievedRequest);
		else
			ctx.res.getWriter().write("Sorry, your search did not return any records.");
	};
	
	
	public Handler findByStatus = ctx -> {
		List<Request> retrievedRequests = requestService.findByStatus(String.valueOf(ctx.pathParam("status")));
		if(retrievedRequests != null)
			ctx.json(retrievedRequests);
		else
			ctx.res.getWriter().write("Sorry, your search did not return any records.");
	};
	
	public Handler viewAllPending = ctx -> {
		List<Request> retrievedRequests = requestService.viewAllPending(String.valueOf(ctx.pathParam("Pending")));
		if(retrievedRequests != null)
			ctx.json(retrievedRequests);
		else
			ctx.res.getWriter().write("Sorry, your search did not return any records.");
	};
	
	public Handler viewAllComplete = ctx -> {
		List<Request> retrievedRequests = requestService.viewAllComplete(String.valueOf(ctx.pathParam("Approved AND Denied")));
		if(retrievedRequests != null)
			ctx.json(retrievedRequests);
		else
			ctx.res.getWriter().write("Sorry, your search did not return any records.");
	};
	

	
}
