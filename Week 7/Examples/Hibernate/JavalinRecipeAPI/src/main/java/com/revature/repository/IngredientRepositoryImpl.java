package com.revature.repository;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.model.Ingredient;
import com.revature.util.HibernateSessionFactory;

/*
 * This class is an implementation of our IngredientRepository. Any instance of this class
 * will be a Data Access Object (DAO). This is a design pattern that provides a nice abstraction
 * for data access in your applications. This is a dedicated class for data access, which makes
 * it easy to refactor and generally maintain our source code.
 */
public class IngredientRepositoryImpl implements IngredientRepository{

	@Override
	public void save(Ingredient ingredient) {
		/*
		 * We always need a session. And I also need a transaction.
		 */
		Session session = null;
		Transaction transaction = null;
		
		try {
			session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			session.save(ingredient);
			transaction.commit();
		}catch(HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		}
	}

	@Override
	public Ingredient findById(int id) {
		
		return null;
	}

	@Override
	public Ingredient findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Ingredient> findAll() {
		
		List<Ingredient> ingredients = null;
		
		/*
		 * We need a Hibernate session in order to do work on the DB.
		 */
		Session session = null;
		/*
		 * Hibernate also has an interface which is used in lieu of transaction control
		 * language.
		 */
		Transaction transaction = null;
		
		try {
			session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			/*
			 * Hibernate has its own query language called "HQL" - Hibernate Query Language.
			 * HQL allows us to emphasize our Java models rather than the entities in our DB when
			 * we are making queries. This provides a more object-oriented approach to data persistence.
			 * 
			 * As a note, you have the option to use "native SQL" in Hibernate. I've commented out the line which
			 * uses native SQL.
			 */
			ingredients = session.createQuery("FROM Ingredient", Ingredient.class).getResultList();
//			session.createNativeQuery("select * from hibernate_ingredient", Ingredient.class).getResultList();
			transaction.commit();
		}catch(HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		}
		
		return ingredients;
	}

	@Override
	public void update(Ingredient ingredient) {

		Session session = null;
		Transaction transaction = null;
		
		try {
			session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			session.update(ingredient);
			transaction.commit();
		}catch(HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Ingredient ingredient) {
		
		Session session = null;
		Transaction transaction = null;
		
		try {
			session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			session.delete(ingredient);
			transaction.commit();
		}catch(HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		}
		
	}

}
