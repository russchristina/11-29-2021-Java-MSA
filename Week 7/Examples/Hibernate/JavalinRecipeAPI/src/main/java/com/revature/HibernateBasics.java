package com.revature;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.model.Author;
import com.revature.model.Ingredient;
import com.revature.model.Recipe;
import com.revature.service.RecipeService;
import com.revature.util.HibernateSessionFactory;

public class HibernateBasics {

	public static void main(String[] args) {
		
		/*
		 * We need a RecipeService to call our methods.
		 */
		RecipeService recipeService = new RecipeService();
		
		/*
		 * Let's start by creating a recipe to insert into the database. Note that we
		 * must create an Author instance and a Set of Ingredients to pass to the Recipe
		 * constructor.
		 */
		
		Author author = new Author(1, "Kimberly", "all delicious things");
		Set<Ingredient> ingredients = new HashSet<>();
		ingredients.add(new Ingredient(1, "Salt", "salty", 9.0f));
		ingredients.add(new Ingredient(2, "Brown Sugar", "sweet", 6.0f));
		ingredients.add(new Ingredient(3, "Marshmallows", "sweet", 4.56f));
		
		Recipe recipe = new Recipe(1, "Candied Yams", 100, author, ingredients);
		
//		recipeService.save(recipe);
		
		/*
		 * Let's view all of our recipes by using our findAll method.
		 */
		
//		System.out.println(recipeService.findAll());
		
		/*
		 * Let's explore the Hibernate API a bit as there are several ways to get the
		 * same result in Hibernate. Let's start with persisting records!
		 */
		
		Session session = null;
		Transaction transaction = null;
		
		try {
			session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			/*
			 * Both "save" and "persist" work with transient instances, but "save"
			 * immediately assigns a unique identifier to the record whereas "persist"
			 * only guarantees to do this some time before the session ends.
			 */
//			session.save(recipe);
			session.persist(recipe);
			transaction.commit();
		}catch(HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		}
		
		/*
		 * "merge" vs "update"
		 */
		
		try {
			session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			/*
			 * 
			 */
			session.update(recipe);
			transaction.commit();
		}catch(HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		}
		
	}
}
