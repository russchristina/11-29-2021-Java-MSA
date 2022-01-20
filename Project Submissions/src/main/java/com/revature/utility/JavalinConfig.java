package com.revature.utility;

import io.javalin.Javalin;

public class JavalinConfig {
Javalin app;
	
	public JavalinConfig(Javalin app) {
		this.app = app;
	}
	
	public JavalinConfig enableCORS() {
		this.app._conf.enableCorsForAllOrigins();
		return this;
	}
	
	public JavalinConfig configureHttpMethodPreference() {
		this.app._conf.prefer405over404 = true;
		return this;
	}
}