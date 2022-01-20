package impl;

import java.sql.Date;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dao.RequestDAO;
import models.Request;
import util.GreendaleLogger;
import util.HibernateSessionFactory;

public class RequestImpl implements RequestDAO{
	
	private static final Logger myLogger = LoggerFactory.getLogger(GreendaleLogger.class);

	@Override
	public List<Request> findByEmp(String lastName) {
		
		List<Request> requests = null;
		Session session = null;
		Transaction transaction = null;
				
		try {
			session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			requests = session.createQuery("FROM Request where employee = ?", Request.class).getResultList();
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
	public List<Request> findByDate(Date date) {
		
		List<Request> requests = null;
		Session session = null;
		Transaction transaction = null;
				
		try {
			session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			requests = session.createQuery("FROM Request where date = ?", Request.class).getResultList();
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
	public Request findById(int id) {
	
		Request request = null;
		Session session = null;
		Transaction transaction = null;
				
		try {
			session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			request = session.get(Request.class, id);
			transaction.commit();
		}catch(HibernateException e) {
			transaction.rollback();
			myLogger.error("Hibernate Exception: ", e);
		}finally {
			session.close();
		}
				
		return request;
		
	}

	@Override
	public Request findByAmount(int amount) {
		
		Request request = null;
		Session session = null;
		Transaction transaction = null;
				
		try {
			session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			request = session.get(Request.class, amount);
			transaction.commit();
		}catch(HibernateException e) {
			transaction.rollback();
			myLogger.error("Hibernate Exception: ", e);
		}finally {
			session.close();
		}
				
		return request;
		
	}

	@Override
	public List<Request> findByStatus(String status) {
		
		List<Request> requests = null;
		Session session = null;
		Transaction transaction = null;
				
		try {
			session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<Request> cq = cb.createQuery(Request.class);
			
			Root<Request> root = cq.from(Request.class);
			cq.select(root).where(cb.equal(root.get("status"), status));
			Query<Request> query = session.createQuery(cq);
			
			requests = query.getResultList();
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
	public List<Request> viewAll() {
		
		List<Request> requests = null;
		Session session = null;
		Transaction transaction = null;
				
		try {
			session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			requests = session.createQuery("FROM Request", Request.class).getResultList();
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
