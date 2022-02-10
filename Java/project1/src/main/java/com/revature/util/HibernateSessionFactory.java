package com.revature.util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactory {

	private static SessionFactory sessionFactory;

	/*
	 * This method should return should return fresh sessions to the caller.
	 */
	public static Session getSession() {
		
		/*
		 * I only ever build my SessionFactory if one hasn't been built yet.
		 */
		if(sessionFactory == null) {
			/*
			 * In order to build a SessionFactory, we use the Configuration class. The
			 * Configuration class reads from the hibernate.cfg.xml file in order to
			 * build a SessionFactory with those properties.
			 */
			
			try {
				sessionFactory = new Configuration().configure()
						.setProperty("hibernate.connection.url", System.getenv("db_url"))
						.setProperty("hibernate.connection.username", System.getenv("db_username"))
						.setProperty("hibernate.connection.password", System.getenv("db_password"))
						.buildSessionFactory();
			}catch(HibernateException e) {
				e.printStackTrace();
			}
		}
		
		return sessionFactory.getCurrentSession();
	}
}
