package com.revature.util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactory {
  // you only need ONE session factory in the entire app.
  // if you need more, the app will be slow.

  private static SessionFactory sessionFactory;

  public static Session getSession() {
    // i only build my SessionFactory if on has been built yet
    if (sessionFactory == null) {
      // in order to build a SessionFactory, we use the Configuration
      // class. The configuration class reads from the hibernate.cfg.xml
      // file in order to build a SessionFactory with those properties.
      try {
        sessionFactory = new Configuration().configure()
            .setProperty("hibernate.connection.url", System.getenv("db_url"))
            .setProperty("hibernate.connection.username", System.getenv("db_username"))
            .setProperty("hibernate.connection.password", System.getenv("db_password"))
            .buildSessionFactory();
      } catch (HibernateException e) {
        System.out.println("Your database didn't connect.");

        e.printStackTrace();
      }
    }
    return sessionFactory.getCurrentSession();
  }
}