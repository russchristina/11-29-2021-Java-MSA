package com.revature.controller;

import com.revature.model.Ingredient;
import com.revature.service.IngredientService;

import io.javalin.http.Handler;

public class IngredientHandlers {

	private IngredientService ingredientService;

	public IngredientHandlers() {
		ingredientService = new IngredientService();
	}

	public Handler findAllIngredients = ctx -> {
		
		System.out.println("Find all ingredients has been hit.");

		/*
		 * As a general note, you can grab a query string (the query parameters added to
		 * the URL using the "?" symbol. These can be useful for allowing the client to
		 * filter results, etc.
		 */

		System.out.println(ctx.queryString());

		// To isolate a parameter:

		System.out.println(ctx.queryParam("minCost"));
		System.out.println(ctx.queryParam("maxCost"));

		/*
		 * The json() method serializes a Java object as JSON. This is necessary if you
		 * want to send data as JSON - otherwise we'd just be writing a Java object to
		 * the response body. Javalin uses Jackson Databind to achieve this.
		 */
		ctx.json(ingredientService.findAll());
	};

	public Handler saveIngredient = ctx -> {
		// Proof of concept that we received the JSON string which the client sent in
		// the
		// request body.
		System.out.println(ctx.body());
		// We'll have Javalin automatically deserialize the JSON string so that it can
		// be
		// stored as an Ingredient immediately. Again, Javalin uses Jackson Databind to
		// achieve this.
		Ingredient retrievedIngredient = ctx.bodyAsClass(Ingredient.class);
		System.out.println(retrievedIngredient);
		// Save the ingredient
		ingredientService.save(retrievedIngredient);
		// We should be intentional about setting response status codes when applicable.
		ctx.status(201);
	};

	public Handler findIngredientById = ctx -> {
		// Proof of Concept
		System.out.println(ctx.pathParam("id"));
		Ingredient retrievedIngredient = ingredientService.findById(Integer.parseInt(ctx.pathParam("id")));
		// Don't forget to write the ingredient to the response body as JSON.
		if (retrievedIngredient != null)
			ctx.json(retrievedIngredient);
		else
			ctx.res.getWriter().write("Sorry. No ingredients that match your search were found.");

	};
}
