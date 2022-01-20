package util;

import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

public class JavalinConfig {
	
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
	
	public JavalinConfig enableCorsForAllOrigins() {
		this.app._conf.enableCorsForAllOrigins();
		return this;
	}

}
