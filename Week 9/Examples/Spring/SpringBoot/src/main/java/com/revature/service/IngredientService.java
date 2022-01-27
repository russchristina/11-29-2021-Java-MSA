package com.revature.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.model.Ingredient;
import com.revature.repository.IngredientRepository;

@Service("ingredientService")
public class IngredientService {

	@Autowired
	private IngredientRepository ingredientRepository;

	public List<Ingredient> findAllOrderedByName() {

		List<Ingredient> retrievedIngredients = ingredientRepository.findAll();

		Collections.sort(retrievedIngredients, (i1, i2) -> i1.getName().compareTo(i2.getName()));

		return retrievedIngredients;
	}

	public List<Ingredient> findAll() {
		return this.ingredientRepository.findAll();
	}

	public void save(Ingredient ingredient) {
		this.ingredientRepository.save(ingredient);
	}

	public Ingredient findById(int id) {
		return this.ingredientRepository.findById(id);
	}
}
