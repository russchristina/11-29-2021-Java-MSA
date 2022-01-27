package com.revature.util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/*
 * We no longer need our JDBC ConnectionFactory. That said, we still need a way to 
 * pass around "connections" in our application. As such, we'll build a SessionFactory
 * that will return sessions based on the configuration details specified in our 
 * hibernate.cfg.xml file.
 * 
 * SessionFactory is actually an interface of Hibernate. A SessionFactory returns instances
 * of Hibernate sessions. And, yes, Session is also an interface; a Session represents a
 * connection to your database.
 */
public class HibernateSessionFactory {
	
	/*
	 * We only need one SessionFactory in this application. That said, we're going to
	 * create our SessionFactory as a singleton.
	 */
	
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
