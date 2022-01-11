package service;

import controller.AccountController;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

public class Driver {
	
	public static void main(String[] args) {
		
		Javalin app = Javalin.create().start(700);
		
		AccountController ac = new AccountController(app);
		
		app._conf.addStaticFiles("/static", Location.CLASSPATH);
		app._conf.prefer405over404 = true;
		
		ac.initEndpoints();
	}
}
