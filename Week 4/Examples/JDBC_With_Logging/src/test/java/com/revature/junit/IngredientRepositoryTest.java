package com.revature.junit;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import com.revature.model.Ingredient;
import com.revature.repository.IngredientRepository;
import com.revature.repository.IngredientRepositoryImpl;


/*
 * For this example, I ran the tests against my database; this is NOT good practice as
 * you should never run tests against your production database. Not only is this dangerous
 * as you're compromising user data, but it is also inefficient as your tests will run
 * slower.
 */

@TestInstance(Lifecycle.PER_CLASS)
public class IngredientRepositoryTest {

	/*
	 * This is our Object Under Test (the object we're actually running tests against)
	 */
	private IngredientRepository ingredientRepository;
	
	/*
	 * We probably should do some setup before writing our tests. For instance, we
	 * need an instance of the IngredientRepositoryImpl class if we want to test the
	 * methods on that type; we'll get a NullPointerException otherwise.
	 * 
	 * We will be using @BeforeAll to perform setup ONCE before any of our tests are run.
	 */
	
	@BeforeAll
	public void setup() {
		ingredientRepository = new IngredientRepositoryImpl();
	}
	
	@Test
	public void testFindAllIngredients() {
		List<Ingredient> retrievedIngredients = ingredientRepository.findAll();
		
		Assertions.assertNotNull(retrievedIngredients);
		Assertions.assertEquals(8, retrievedIngredients.size());
	}
	
	
}
