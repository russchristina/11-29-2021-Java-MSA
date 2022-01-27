package com.revature.repository;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.revature.model.Author;
import com.revature.model.Recipe;
import com.revature.util.HibernateSessionFactory;

@Repository("recipeRepository")
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

	@Override
	public List<Recipe> findAllByAuthor(Author author) {
		List<Recipe> recipes = null;
		
		Session session = null;
		Transaction transaction = null;
		
		try {
			session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			/*
			 * We are going to use an interface of Hibernate known as "CriteriaBuilder".
			 * A CriteriaBuilder allows us to build out the criteria for our search for a
			 * record. We can use the CriteriaBuilder to later construct a CriteriaQuery, which
			 * is just a Hibernate Query (an object representation of a a SQL query) that has
			 * criteria specified/applied to it.
			 */
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<Recipe> cq = cb.createQuery(Recipe.class);
			
			/*
			 * There is an interface called "Root" which specifies the table we're ultimately
			 * querying. (select * from Root)
			 */
			Root<Recipe> root = cq.from(Recipe.class);
			
			cq.select(root).where(cb.equal(root.get("author"), author));
			
			//Take your CriteriaQuery and create a Query so that we can get the result list
			Query<Recipe> query = session.createQuery(cq);
			
			recipes = query.getResultList();
			
			
			transaction.commit();
		}catch(HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		return recipes;
	}

}
