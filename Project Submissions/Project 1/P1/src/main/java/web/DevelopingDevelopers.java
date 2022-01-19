package web;

import javax.servlet.http.HttpSession;

import io.javalin.Javalin;


public class DevelopingDevelopers {
	private static HttpSession session;
	public static void main(String[] args) {
		
		Javalin app = Javalin.create(config -> {
			config.enableCorsForAllOrigins();
		}).start(7777);
				
		app.post("/verify", Controller.fetchEmployeeByName);
		
		app.get("/gen", ctx -> {
			session = ctx.req.getSession();
			});
		app.get("/logout", ctx -> {
			if(session != null) {
				session.invalidate();
				session = null;
			}
		});
		app.before("/devdev/*", ctx -> {
			if(session == null) throw new Exception();
		});	
		
		app.post("/devdev/empRequests", Controller.fetchEmployeeRequests);
		app.get("/devdev/pending-requests", Controller.fetchPendingRequests);
		app.get("/devdev/all-requests", Controller.fetchAllRequests);
		app.get("/devdev/highest-payout", Controller.fetchHighestAmount);
		app.get("/devdev/number-requests", Controller.fetchNumberOfRequests);
		app.get("/devdev/average-requested", Controller.fetchAverageAmountReq);
		app.put("/devdev/requestSubmit", Controller.saveRequest);
		app.put("/devdev/request-update", Controller.changeRequest);
		
		app.exception(Exception.class, (e, ctx) -> {
			ctx.status(401).result("You are not authenticated");
		});
	} // End main
} // End class
