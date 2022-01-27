package com.revature.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.model.Author;
import com.revature.model.Recipe;
import com.revature.repository.RecipeRepository;

@Service("recipeService")
public class RecipeService {

	@Autowired
	private RecipeRepository recipeRepository;
	
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
