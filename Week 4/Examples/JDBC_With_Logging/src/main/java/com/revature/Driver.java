package com.revature;

import com.revature.model.Ingredient;
import com.revature.repository.IngredientRepository;
import com.revature.repository.IngredientRepositoryImpl;

public class Driver {

	public static void main(String[] args) {

		IngredientRepository ingredientRepository = new IngredientRepositoryImpl();

		// Creating a new Ingredient to persist. Note that I've SQL injected myself here.
		/*
		 * SQL injection occurs when a user provides valid SQL as input in order to manipulate
		 * your database schema/records. In order to prevent SQL injection, we should be using
		 * PreparedStatement instead. PreparedStatement is an interface which extends 
		 * Statement. PreparedStatement prevents SQL injection by pre-compiling SQL statements.
		 * When these statements are pre-compiled, the input for the columns is also compiled
		 * as a specific SQL data type.
		 */

		Ingredient newIngredient = new Ingredient(
				92, 
				"Lawry's Seasoning Salt", "savory'); drop table ingredient cascade; --", 
				0.0f);
		Ingredient nonSqlInjectedIngredient = new Ingredient(-100, "Cheese", "savory", 0.0f);
		
		ingredientRepository.save(nonSqlInjectedIngredient);

		System.out.println(ingredientRepository.findAll());
		System.out.println(ingredientRepository.findById(7));

	}
}
