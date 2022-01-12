package com.revature.repository;

import java.util.List;

import com.revature.model.Author;
import com.revature.model.Recipe;

public interface RecipeRepository {

	List<Recipe> findAll();
	void save(Recipe recipe);
	List<Recipe> findAllByAuthor(Author author);
}
