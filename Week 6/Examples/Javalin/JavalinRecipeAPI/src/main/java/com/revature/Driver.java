package com.revature;

import com.revature.controller.IngredientController;

import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

public class Driver {

	public static void main(String[] args) {
		
		Javalin app = Javalin.create().start(8000);
		
		//Initialize your controllers
		IngredientController ingredientController = new IngredientController(app);
		ingredientController.initEndpoints();
		
		/*
		 * Javalin allows for additional configuration for static resource hosting
		 * (serving HTML documents, etc. from Javalin) and CORS configuration.
		 */
		
		app._conf.addStaticFiles("/static", Location.CLASSPATH);
		app._conf.prefer405over404 = true;
	}
}
