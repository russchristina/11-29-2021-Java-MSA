package impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dao.UserLoginDAOInterface;
import models.Login;
import models.UserLogin;
import util.GreendaleLogger;
import util.HibernateSessionFactory;

public class UserLoginImpl implements UserLoginDAOInterface{

	private static final Logger myLogger = LoggerFactory.getLogger(GreendaleLogger.class);
	
	@Override
	public UserLogin insertUser(String username, String password, boolean isManager) {
		UserLogin userEntity = null;

		Session session = null;
		Transaction transaction = null;
		
		try {
			session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			session.save(userEntity);
			transaction.commit();
			} catch (HibernateException e) {
				transaction.rollback();
				myLogger.error("Hibernate Exception: ", e);
		
		}finally {
			session.close();
				}
		
		return userEntity;
	}

	@Override
	public UserLogin getUser(String username) {
		UserLogin  userEntity = null;
		
		Session session = null;
		Transaction transaction = null;
		
		try {
			session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			userEntity = session.createQuery(username, UserLogin.class).getSingleResult();
			transaction.commit();
			} catch (HibernateException e) {
				transaction.rollback();
				myLogger.error("Hibernate Exception: ", e);
		
		}finally {
			session.close();
				}
					
					
		return userEntity;
		
	}

	@Override
	public List<Login> findAll() {
		List<Login> logins = null;
		Session session = null;
		Transaction transaction = null;
				
		try {
			session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			logins = session.createQuery("FROM Login", Login.class).getResultList();
			transaction.commit();
		}catch(HibernateException e) {
			transaction.rollback();
			myLogger.error("Hibernate Exception: ", e);
			
		}finally {
			session.close();
		}
				
		return logins;
		
	}

	

}
