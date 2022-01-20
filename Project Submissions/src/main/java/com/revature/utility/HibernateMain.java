package com.revature.utility;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.revature.models.Associate;

public class HibernateMain {
	
	public static void main(String[]args) {
		Configuration configuration = new Configuration().configure();
		configuration.addAnnotatedClass(com.revature.models.Associate.class);
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
		SessionFactory factory= configuration.buildSessionFactory(builder.build());
		
		Session session=factory.openSession();
		Transaction transaction = session.beginTransaction();
		Associate a1 = new Associate("Jim Rogers","manager", "boo");
		session.save(a1);
		transaction.commit();
		session.close();
}
}
