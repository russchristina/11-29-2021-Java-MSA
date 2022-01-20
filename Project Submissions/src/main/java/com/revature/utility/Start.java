package com.revature.utility;

import javax.servlet.http.HttpSession;
import io.javalin.Javalin;

public class Start {
	private static HttpSession session;

public static void main(String[] args) {

	Javalin app = Javalin.create(config ->
		config.enableCorsForAllOrigins()).start(9000);
	

	app.post("/verify", Control.fetchEmployeeByName);
	
	app.get("/gen", ctx -> {
		session = ctx.req.getSession();
		});
	app.get("/logout", ctx -> {
		if(session != null) {
			session.invalidate();
			session = null;
		}
	});
	app.before("/crazy/*", ctx -> {
		if(session == null) throw new Exception();
	});	
	
    

		app.post("/crazy/verify", Control.fetchEmployeeByName);
		app.post("/crazy/empRequests", Control.fetchEmployeeRequests);
		app.get("/crazy/pending-requests", Control.fetchPendingRequests);
		app.get("/crazy/all-requests", Control.fetchAllOffers);
		app.get("/crazy/highest-payout", Control.fetchHighestAmount);
		app.get("/crazy/number-requests", Control.fetchNumberOffers);
		app.get("/crazy/average-requested", Control.fetchAverageAmountReq);
		app.put("/crazy/requestSubmit", Control.saveRequest);
		app.put("/crazy/request-update", Control.changeRequest);
	} 
}


