package com.revature.repository;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.revature.model.Recipe;

/*
 * This annotation is what we call a Spring "stereotype". We use our stereotypes to denote that Spring
 * should create bean of this type. Note that we can still give our bean a name as an attribute for
 * the annotation.
 */
@Repository("recipeRepository")
public class RecipeRepository {

	public List<Recipe> findAll() {
		return Arrays.asList(new Recipe(1, "Pancakes", "flat bread"),
				new Recipe(2, "Grits", "some weird type of corn product"), new Recipe(3, "Shrimp", "bug of the sea"));
	}
}
