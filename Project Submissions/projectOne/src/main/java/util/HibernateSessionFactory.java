package util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HibernateSessionFactory {

	private static final Logger myLogger = LoggerFactory.getLogger(GreendaleLogger.class);
	
	private static SessionFactory sessionFactory;
	
	public static Session getSession() {
		if(sessionFactory == null) {
	}
		try {
			sessionFactory = new Configuration().configure()
					.setProperty("hibernate.connection.url", System.getenv("db_url"))
					.setProperty("hibernate.connection.username", System.getenv("db_username"))
					.setProperty("hibernate.connection.password", System.getenv("db_password"))
					.buildSessionFactory();
		}catch(HibernateException e) {
			myLogger.debug("HibernateException", e);
		}
	
	return sessionFactory.getCurrentSession();
}
}
