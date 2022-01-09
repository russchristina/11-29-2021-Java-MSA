package com.revature;

import com.revature.controller.IngredientController;
import com.revature.util.JavalinConfig;

import io.javalin.Javalin;

public class Driver {

	public static void main(String[] args) {
		
		Javalin app = Javalin.create().start(8000);
		
		//Configure your Javalin app (I created a util class for this):
		JavalinConfig config = new JavalinConfig(app).configureHttpMethodPreference()
				.configureStaticResources();
		
		//Initialize your controllers
		IngredientController ingredientController = new IngredientController(app);
		ingredientController.initEndpoints();
		
		/*
		 * Javalin allows for additional configuration for static resource hosting
		 * (serving HTML documents, etc. from Javalin) and CORS configuration.
		 */
		
		
	}
}
