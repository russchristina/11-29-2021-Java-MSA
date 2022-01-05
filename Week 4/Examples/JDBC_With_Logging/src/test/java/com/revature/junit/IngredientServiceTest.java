package com.revature.junit;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.model.Ingredient;
import com.revature.repository.IngredientRepository;
import com.revature.service.IngredientService;

/*
 * Only with field injection with Mockito and JUnit5, you must use a special test runner
 * that is designed for use with both technologies together.
 */
@ExtendWith(MockitoExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
public class IngredientServiceTest {

	// My Object Under Test. We've injected our Mockito mock into the
	// IngredientService
	// as we want Mockito to mock out the actual dependency while the tests are
	// running.
	@InjectMocks
	private IngredientService ingredientService;

	/*
	 * In order to test the IngredientService - and ONLY the IngredientService - we
	 * need to mock out the dependency (remove its dependency for the duration of
	 * the tests). We'll first declare the dependency. We'll then annotate it with
	 * Mockito's @Mock annotation as Mockito, like JUnit, is annotation driven.
	 */
	@Mock
	private IngredientRepository ingredientRepository;

	@BeforeAll
	public void setUp() {
		/*
		 * Even though Mockito is annotation driven, you do have to open the Mocks with
		 * a static method called "openMocks".
		 */
		MockitoAnnotations.openMocks(this);
		ingredientService = new IngredientService();
	}

	@Test
	public void testFindAllOrderByName() {

		/*
		 * Mockito has static methods which allow us to determine what should happen
		 * when a method would ordinarily be called. In this case, we don't want the
		 * actual return value associated a normal call to findAll(). Instead, we want a
		 * dummy list of ingredients. This allows us to stop the actual method
		 * invocation for the findAll() method, meaning that we're ensuring that only
		 * our Service layer method is being called in isolation.
		 */
		Mockito.when(ingredientRepository.findAll())
				.thenReturn(Arrays.asList(new Ingredient(1, "Rice", "bland", 0.0f),
						new Ingredient(2, "Apples", "sweet or sour", 1.1f),
						new Ingredient(3, "Bananas", "very very sweet", 2.2f)));

		List<Ingredient> orderedIngredients = ingredientService.findAllOrderedByName();

		// PROOF OF CONCEPT
		Assertions.assertEquals("Apples", orderedIngredients.get(0).getName());
		Assertions.assertTrue(orderedIngredients.get(0).getName().compareTo(orderedIngredients.get(1).getName()) < 0);
	}
}
