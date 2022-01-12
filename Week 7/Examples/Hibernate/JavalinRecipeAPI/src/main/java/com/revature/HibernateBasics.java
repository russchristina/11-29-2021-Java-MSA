package com.revature;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

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
		
		Author author = new Author(2, "Kimberly", "all delicious things");
		Set<Ingredient> ingredients = new HashSet<>();
		ingredients.add(new Ingredient(1, "Salt", "salty", 9.0f));
		ingredients.add(new Ingredient(2, "Brown Sugar", "sweet", 6.0f));
		ingredients.add(new Ingredient(3, "Marshmallows", "sweet", 4.56f));
		
		Recipe recipe = new Recipe(100, "Oops Revamped Candied Yams with new flavor Merged", 101, author, ingredients);
		
//		recipeService.save(recipe);
		
		/*
		 * Let's view all of our recipes by using our findAll method.
		 */
		
//		System.out.println(recipeService.findAll());
		
		//Can we get all our recipes with a specific author?
		System.out.println(recipeService.findAllByAuthor(author));
		
		/*
		 * Let's explore the Hibernate API a bit as there are several ways to get the
		 * same result in Hibernate. Let's start with persisting records!
		 */
		
		Session session = null;
		Transaction transaction = null;
		
//		try {
//			session = HibernateSessionFactory.getSession();
//			transaction = session.beginTransaction();
//			/*
//			 * Both "save" and "persist" work with transient instances, but "save"
//			 * immediately assigns a unique identifier to the record whereas "persist"
//			 * only guarantees to do this some time before the session ends.
//			 */
////			session.save(recipe);
//			session.persist(recipe);
//			transaction.commit();
//		}catch(HibernateException e) {
//			transaction.rollback();
//			e.printStackTrace();
//		}
		
		/*
		 * "merge" vs "update"
		 */
		
//		try {
//			session = HibernateSessionFactory.getSession();
//			transaction = session.beginTransaction();
//			/*
//			 * The "update" method should only be used with detached instances otherwise
//			 * an exception will be thrown. In other words, the unique identifier for the 
//			 * recipe MUST exist already (in the DB) for this to work.
//			 * 
//			 * The "merge" method, on the other hand, accepts transient instances. Note that "merge"
//			 * will create a new record if the id for the instance does not exist already
//			 * in the DB.
//			 */
////			session.update(recipe);
//			session.merge(recipe);
//			transaction.commit();
//		}catch(HibernateException e) {
//			transaction.rollback();
//			e.printStackTrace();
//		}
		
		//"get" vs "load"
		
//		try {
//			session = HibernateSessionFactory.getSession();
//			transaction = session.beginTransaction();
//			/*
//			 * There is a really simple way of grabbing a record by its ID in Hibernate:
//			 * using either "get" or "load".
//			 * 
//			 * The "get" method returns null if no such record exists. The "load" method
//			 * might actually return a Hibernate "proxy" as it lazily loads an instance.
//			 * If there is no record with the ID you pass to "load", then the method throws
//			 * an ObjectNotFoundException.
//			 * 
//			 * 
//			 * NOTE: Hibernate proxies are used with lazy loading; a proxy is a stand-in object
//			 * for the actual object that hasn't been retrieved.
//			 */
////			Recipe retrievedRecipe = session.get(Recipe.class, 200);
//			Recipe retrievedRecipe = session.load(Recipe.class, 2);
//			System.out.println(retrievedRecipe);
//			transaction.commit();
//		}catch(HibernateException e) {
//			transaction.rollback();
//			e.printStackTrace();
//		}
//		
	}
}
