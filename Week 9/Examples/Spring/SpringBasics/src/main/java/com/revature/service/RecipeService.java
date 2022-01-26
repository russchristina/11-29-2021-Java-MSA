package com.revature.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.model.Recipe;
import com.revature.repository.RecipeRepository;

/*
 * The @Service stereotype indicates that this is a class which contains business logic and that
 * Spring should add a bean of this type to the IOC container. And, of course, we give our bean
 * a logical name.
 */

@Service("recipeService")
public class RecipeService {

	/*
	 * In order to wire in beans using annotations, we use the @Autowired annotation either above
	 * a field (is hard to test), a constructor, or a setter. Note that this is our third type of
	 * injection (field injection).
	 */
	@Autowired
	private RecipeRepository recipeRepository;
	
	/*
	 * This time we will opt for setter injection, meaning that Spring will take the setter for the
	 * recipeRepository property and use that to inject the dependency.
	 * 
	 * And, yes, the setter name really does have to be EXACTLY set + the property name.
	 */
	
//	public void setRecipeRepository(RecipeRepository recipeRepository) {
//		this.recipeRepository = recipeRepository;
//	}
	
	public List<Recipe> findAll(){
		return this.recipeRepository.findAll();
	}

}
