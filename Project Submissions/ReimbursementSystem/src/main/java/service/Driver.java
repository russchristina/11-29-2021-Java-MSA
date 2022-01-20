package service;

import controller.AccountController;
import controller.FormController;
import controller.PRController;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import models.PastRequests;
import models.RepayForm;

public class Driver {
	
	public static void main(String[] args) {
		
		Javalin app = Javalin.create().start(700);
		app._conf.addStaticFiles("/static", Location.CLASSPATH);
		app._conf.prefer405over404 = true;
		
		AccountController ac = new AccountController(app);
		ac.initEndpoints();
		
		FormController fc = new FormController(app);
		fc.initEndpoints();
		
		PRController prc = new PRController(app);
		prc.initEndpoints();
	}
}
