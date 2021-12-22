package com.revature;

import com.revature.model.Ingredient;
import com.revature.repository.IngredientRepository;
import com.revature.repository.IngredientRepositoryImpl;

public class Driver {

	public static void main(String[] args) {

		IngredientRepository ingredientRepository = new IngredientRepositoryImpl();

		// Creating a new Ingredient to persist:

		Ingredient newIngredient = new Ingredient(11, "Lawry's Seasoning Salt", "savory", 0.0f);
		ingredientRepository.save(newIngredient);

		System.out.println(ingredientRepository.findAll());
		System.out.println(ingredientRepository.findById(7));

	}
}
