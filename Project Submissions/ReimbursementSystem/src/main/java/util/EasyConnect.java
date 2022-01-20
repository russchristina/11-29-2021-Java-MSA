package util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class EasyConnect {
	
	private static SessionFactory sessionFactory;
	
	protected Session getConnection() {
		
		if(sessionFactory == null) {
			try {
				sessionFactory = new Configuration().configure()
						.setProperty("hibernate.connection.url", System.getenv("db_url"))
						.setProperty("hibernate.connection.username", System.getenv("db_user"))
						.setProperty("hibernate.connection.password", System.getenv("db_password"))
						.buildSessionFactory();
			}catch(HibernateException e) {
				e.printStackTrace();
			}
		}
		return sessionFactory.getCurrentSession();
	}
	
}
