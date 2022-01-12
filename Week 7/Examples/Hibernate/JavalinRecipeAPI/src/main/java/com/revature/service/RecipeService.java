package com.revature.service;

import java.util.List;

import com.revature.model.Author;
import com.revature.model.Recipe;
import com.revature.repository.RecipeRepository;
import com.revature.repository.RecipeRepositoryImpl;

public class RecipeService {

	private RecipeRepository recipeRepository;
	
	public RecipeService() {
		this.recipeRepository = new RecipeRepositoryImpl();
	}
	
	public void save(Recipe recipe) {
		this.recipeRepository.save(recipe);
	}
	
	public List<Recipe> findAll(){
		return this.recipeRepository.findAll();
	}
	
	public List<Recipe> findAllByAuthor(Author author){
		return this.recipeRepository.findAllByAuthor(author);
	}
}
