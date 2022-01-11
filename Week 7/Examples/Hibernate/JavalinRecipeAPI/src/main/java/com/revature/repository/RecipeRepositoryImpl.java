package com.revature.repository;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.model.Recipe;
import com.revature.util.HibernateSessionFactory;

public class RecipeRepositoryImpl implements RecipeRepository{

	@Override
	public List<Recipe> findAll() {
		List<Recipe> recipes = null;
		
		Session session = null;
		Transaction transaction = null;
		
		try {
			session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			recipes = session.createQuery("FROM Recipe", Recipe.class).getResultList();
			transaction.commit();
		}catch(HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		return recipes;
	}

	/*
	 * Hibernate objects exist in 1 of 3 states:
	 * 
	 * 1) Transient: An object that has not been associated with a Hibernate session
	 * 2) Persistent: An object that has been associated with a Hibernate session and has a
	 * 		unique identifier associated with it
	 * 3) Detached: An object that was associated with an open session but no longer is (e.g.
	 * 		because the session has been closed)
	 */
	@Override
	public void save(Recipe recipe) { //TRANSIENT
		Session session = null;
		Transaction transaction = null;
		
		try {
			session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			session.save(recipe); //PERSISTENT
			transaction.commit();
		}catch(HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		}finally {
			session.close(); //DETACHED
		}
		
	}

}
