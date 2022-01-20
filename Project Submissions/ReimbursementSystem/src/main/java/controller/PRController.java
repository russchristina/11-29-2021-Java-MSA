package controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static io.javalin.apibuilder.ApiBuilder.*;
import io.javalin.http.*;

import io.javalin.Javalin;
import io.javalin.http.Handler;
import models.PastRequests;
import service.PRService;

public class PRController {
	private final Logger dLog = LoggerFactory.getLogger("dLog");
	private PastRequests pr;
	private PRService prs;
	private Javalin app;
	
	public PRController(Javalin app) {
		this.app = app;
		this.pr = new PastRequests();
		this.prs = new PRService();
	}
	
	public void initEndpoints() {
		this.app.routes(() -> {
			path("/Approval", () ->{
				
				path("/decision", () -> {
					post(save);
				});
				
			});
		});
	}

	private final Handler save = ctx -> {
		dLog.debug("Accepted reimbursement request");
		
		this.pr = ctx.bodyAsClass(PastRequests.class);
		this.prs.save(this.pr);
		
		try {
			ctx.json(this.pr);
		}catch (Exception e) {
			dLog.debug("Accepting request");
			e.printStackTrace();
		}
	};
}