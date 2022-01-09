package com.revature.util;

import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

public class JavalinConfig {

	/*
	 * This is just a utility class to abstract the Javalin config options from our driver.
	 * I perhaps got a little carried away and ended up using a builder design pattern just
	 * for some easier method chaining.
	 */
	Javalin app;
	
	public JavalinConfig(Javalin app) {
		this.app = app;
	}
	
	public JavalinConfig configureStaticResources() {
		this.app._conf.addStaticFiles("/static", Location.CLASSPATH);
		return this;
	}
	
	public JavalinConfig configureHttpMethodPreference() {
		this.app._conf.prefer405over404 = true;
		return this;
	}
}
