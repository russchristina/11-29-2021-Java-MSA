package impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dao.EmployeeDAO;
import models.Employee;
import models.Request;
import util.GreendaleLogger;
import util.HibernateSessionFactory;

public class EmployeeImpl implements EmployeeDAO{
	
	private static final Logger myLogger = LoggerFactory.getLogger(GreendaleLogger.class);


	@Override
	public void submitNew(Request request) {
		Session session = null;
		Transaction transaction = null;
				
		try {
			session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			session.save(request);
			transaction.commit();
		}catch (HibernateException e) {
			transaction.rollback();
			myLogger.error("Hibernate Exception: ", e);
		}finally {
			session.close();
		}
	}

	@Override
	public void update(Request request) {
		Session session = null;
		Transaction transaction = null;
		
		try {
			session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			session.update(request);
			transaction.commit();
		}catch(HibernateException e) {
			transaction.rollback();
			myLogger.error("Hibernate Exception: ", e);
		}finally {
			session.close();
		}		
	}

	@Override
	public void delete(Request request) {
		Session session = null;
		Transaction transaction = null;
		
		try {
			session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			session.delete(request);
			transaction.commit();
		}catch(HibernateException e) {
			transaction.rollback();
			myLogger.error("Hibernate Exception: ", e);
					
		}finally {
			session.close();
	}
	}

	@Override
	public List<Request> viewAllPending(String status) {
		List<Request> requests = null;
		Session session = null;
		Transaction transaction = null;
				
		try {
			session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			requests = session.createQuery("FROM Requests where status = 'Pending'", Request.class).getResultList();
			transaction.commit();
		}catch(HibernateException e) {
			transaction.rollback();
			myLogger.error("Hibernate Exception: ", e);
		}
				
		return requests;
	}

	@Override
	public List<Request> viewAllComplete(String status) {
		List<Request> requests = null;
		Session session = null;
		Transaction transaction = null;
				
		try {
			session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			requests = session.createQuery("FROM Requests where status = 'Approved' AND 'Denied'", Request.class).getResultList();
			transaction.commit();
		}catch(HibernateException e) {
			transaction.rollback();
			myLogger.error("Hibernate Exception: ", e);
		}finally {
			session.close();
	}
				
		return requests;
	}

	@Override
	public List<Request> viewAll() {
		List<Request> requests = null;
		Session session = null;
		Transaction transaction = null;
				
		try {
			session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			requests = session.createQuery("FROM Requests", Request.class).getResultList();
			transaction.commit();
		}catch(HibernateException e) {
			transaction.rollback();
			myLogger.error("Hibernate Exception: ", e);
			
		}finally {
			session.close();
	}
				
		return requests;
	}

	
	

}