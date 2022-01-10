package com.revature.controller;

import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.path;
import static io.javalin.apibuilder.ApiBuilder.post;

import io.javalin.Javalin;

/*
 * We're technically trying to build a "RESTful" API. REST stands for Representational
 * State Transfer. Our representation for our state happens to be JSON as our two applications
 * are communicating with each by sending and receiving JSON. A RESTful API should:
 * 
 * 1. There should be a client-server relationship (a separation of concerns). 
 * 2. Be "stateless": The server should not store information about client sessions.
 * 3. Responses should be cacheable. This helps improve performance as the client doesn't
 * 		needlessly make new HTTP requests.
 * 4. Have a layered Architecture: Your entire system (your application) should layered. Each
 * 		layer would be broken up into different components that communication with each
 * 		other.
 * 5. Provide Code on Demand (Optional): Clients can receive executables from the server.
 * 6. Should have Uniform Interface: Client should be able to identify resources using URLs,
 * 		manipulate resources through representations, they should have access to 
 * 		"self-descriptive" messages (responses)
 */

public class IngredientController {

	/*
	 * We need an instance of the Javalin app in order to call methods on it and
	 * handlers (e.g. methods for handling POST requests, GET requests, etc.).
	 */
	private Javalin app;
	private IngredientHandlers ingredientHandler;
	
	public IngredientController(Javalin app) {
		this.app = app;
		this.ingredientHandler = new IngredientHandlers();
	}
	
	public void initEndpoints() {
		//The EndpointGroup defined by Javalin is a functional interface, so we pass in
		//a lambda expression to the "routes" method. An endpoint group is a group of
		//endpoints (defined using get(), post(), etc.).
		this.app.routes(() -> {
			// We start by defining the prefix for all of the routes related to ingredients.
			path("/ingredient", () -> {
				//We can now define our subroutes inside of this lambda expression:
				path("/all", () -> {
					//This endpoint should return all ingredients
					//We want this to be a get request, so...
					get(this.ingredientHandler.findAllIngredients);
				});
				
				path("/new", () -> {
					//This endpoint should allow the client to add a new ingredient to our DB
					post(this.ingredientHandler.saveIngredient);
				});
				
				/*
				 * We have defined a path variable that we referring to as "id" here.
				 * This allows to client to pass back different ids to search for a
				 * particular ingredients.
				 */
				path("/id/{id}", () -> {
					get(this.ingredientHandler.findIngredientById);
				});
			});
		});
	}
	
	
	
	
	
}
