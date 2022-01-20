package com.revature.utility;
import javax.servlet.http.HttpSession;

import com.revature.utility.Control;
import io.javalin.Javalin;

public class Endpoints {

    private final Control control;
    

    public Endpoints(Control control) {
        this.control = control;
       

    
}

    public void initializeEndpoints(Javalin app){
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
    

		app.post("/verify", Control.fetchEmployeeByName);
		app.post("/empRequests", Control.fetchEmployeeRequests);
		app.get("/pending-requests", Control.fetchPendingRequests);
		app.get("/all-requests", Control.fetchAllOffers);
		app.get("/highest-payout", Control.fetchHighestAmount);
		app.get("/number-requests", Control.fetchNumberOffers);
		app.get("/average-requested", Control.fetchAverageAmountReq);
		app.put("/requestSubmit", Control.saveRequest);
		app.put("/request-update", Control.changeRequest);
	} 
} 
