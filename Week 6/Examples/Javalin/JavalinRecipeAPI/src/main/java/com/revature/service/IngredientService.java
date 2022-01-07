package com.revature.service;

import java.util.Collections;
import java.util.List;

import com.revature.model.Ingredient;
import com.revature.repository.IngredientRepository;
import com.revature.repository.IngredientRepositoryImpl;

/*
 * A "service" layer is a standard addition to many applications. A service layer should
 * contain business logic. Tasks such as data transformation for presentation purposes,
 * for instance, might be considered business logic. Comparator
 */
public class IngredientService {
	
	/*
	 * This is a dependency as the IngredientService cannot function properly without
	 * the IngredientRepository.
	 */
	private IngredientRepository ingredientRepository = new IngredientRepositoryImpl();
	
	/*
	 * Note that you can use this constructor if you prefer to use constructor injection
	 * with Mockito.
	 */
//	public IngredientService(IngredientRepository ingredientRepository) {
//		this.ingredientRepository = ingredientRepository;
//	}

	public List<Ingredient> findAllOrderedByName(){
		
		List<Ingredient> retrievedIngredients = ingredientRepository.findAll();
		
		Collections.sort(retrievedIngredients, 
				(i1, i2) -> i1.getName().compareTo(i2.getName()));
		
		return retrievedIngredients;
	}
	
	public List<Ingredient> findAll(){
		return this.ingredientRepository.findAll();
	}
	
	public void save(Ingredient ingredient) {
		this.ingredientRepository.save(ingredient);
	}
	
	public Ingredient findById(int id) {
		return this.ingredientRepository.findById(id);
	}
}
