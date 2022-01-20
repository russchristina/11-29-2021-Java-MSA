package repo;

import org.hibernate.Transaction;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import control.login.LoginInput;
import models.Login;
import util.GreendaleLogger;
import util.HibernateSessionFactory;

public class LoginImpl implements LoginDAO{
	
	private static final Logger myLogger = LoggerFactory.getLogger(GreendaleLogger.class);

	@Override
	public Login insertUser(String username, String password, boolean isManager) {
		Login login = new Login(username, password, isManager);
		Session session = null;
		Transaction transaction = null;
				
		try {
			session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			session.save(login);
			transaction.commit();
		}catch (HibernateException e) {
			transaction.rollback();
			myLogger.error("Hibernate Exception: ", e);
		}finally {
			session.close();
		}
		
		return login;
	}

	@Override
	public Login getUser(String username) {
		Login login = null;
		Session session = null;
		Transaction transaction = null;
		
		try {
			session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			login = session.get(Login.class, username);
			transaction.commit();
		}catch(HibernateException e) {
			transaction.rollback();
			myLogger.error("Hibernate Exception: ", e);
		}finally {
			session.close();
		}
				
		return login;
	}
	
	

	@Override
	public Login updateUsername(int id, String newUsername) {
		return null;
	}

	@Override
	public void deleteUser(String username) {
		
		Session session = null;
		Transaction transaction = null;
		
		try {
			session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			session.delete(username, Login.class);
			transaction.commit();
		}catch(HibernateException e) {
			transaction.rollback();
			myLogger.error("Hibernate Exception: ", e);
		}finally {
			session.close();
		}
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

	@Override
	public Login checkUser(LoginInput loginInput) {
		Login login = null;
		Session session = null;
		Transaction transaction = null;
		
		try {
			session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			login = session.find(Login.class, loginInput);
			transaction.commit();
			
		}catch(HibernateException e){
			transaction.rollback();
			myLogger.error("Hibernate Exception: ", e);
			
		}finally {
			session.close();
		}
		
		return login;
	}

}
