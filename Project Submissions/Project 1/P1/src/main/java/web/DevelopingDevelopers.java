package web;

import javax.servlet.http.HttpSession;

import io.javalin.Javalin;

public class DevelopingDevelopers {

	public static void main(String[] args) {
		
		Javalin app = Javalin.create(config ->
			config.enableCorsForAllOrigins()).start(7777);
		
		// Look at JWT (JSON Web Tokens) or HttpSession for log in validation
		app.post("/session", ctx -> {
			ctx.req.getSession();
		});
		app.get("/locked-resource", ctx -> {
			HttpSession session = ctx.req.getSession();
			if(session == null) ctx.res.getWriter().write("You are authenticated");
			else ctx.res.getWriter().write("You cannot access this resource");
		});
		app.get("/logout", ctx -> {
			HttpSession session = ctx.req.getSession(false);
			if(session != null) session.invalidate();
		});
		
		app.post("/verify", Controller.fetchEmployeeByName);
		app.post("/empRequests", Controller.fetchEmployeeRequests);
		app.get("/pending-requests", Controller.fetchPendingRequests);
		app.get("/all-requests", Controller.fetchAllRequests);
		app.get("/highest-payout", Controller.fetchHighestAmount);
		app.get("/number-requests", Controller.fetchNumberOfRequests);
		app.get("/average-requested", Controller.fetchAverageAmountReq);
		app.put("/requestSubmit", Controller.saveRequest);
		app.put("/request-update", Controller.changeRequest);
	} // End main
} // End class
