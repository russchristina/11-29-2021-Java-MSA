package com.revature.utility;

import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

public class JavalinConfig {
	Javalin app = Javalin.create(config -> {
	    config.autogenerateEtags = true;
	    config.addStaticFiles("/public", Location.CLASSPATH);
	    config.asyncRequestTimeout = 10000L;
	    config.enforceSsl = true;
	});
}