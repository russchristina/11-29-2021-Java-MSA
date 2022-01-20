package impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dao.ManagerDAO;
import models.Employee;
import models.Request;
import util.GreendaleLogger;
import util.HibernateSessionFactory;

public class ManagerImpl implements ManagerDAO{
	
	Logger myLogger = LoggerFactory.getLogger(GreendaleLogger.class);

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
		}
				
		return requests;
	}

	@Override
	public List<Request> viewAllApproved(String status) {
		List<Request> requests = null;
		Session session = null;
		Transaction transaction = null;
				
		try {
			session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			requests = session.createQuery("FROM Requests where status = 'Approved'", Request.class).getResultList();
			transaction.commit();
		}catch(HibernateException e) {
			transaction.rollback();
			myLogger.error("Hibernate Exception: ", e);
		}
				
		return requests;
		
	}

	@Override
	public List<Request> viewAllDenied(String status) {
		List<Request> requests = null;
		Session session = null;
		Transaction transaction = null;
				
		try {
			session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			requests = session.createQuery("FROM Requests where status = 'Denied'", Request.class).getResultList();
			transaction.commit();
		}catch(HibernateException e) {
			transaction.rollback();
			myLogger.error("Hibernate Exception: ", e);
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
			
		}
	}

	@Override
	public void approve(Request request) {
		
		Session session = null;
		Transaction transaction = null;
		
		try {
			session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			session.update(request);
		}catch(HibernateException e) {
			myLogger.error("Hibernate Exception:" ,e);
		}		
	}

	@Override
	public List<Employee> findAllEmp() {
					
			List<Employee> employees = new ArrayList<>();
			Session session = null;
			Transaction transaction = null;
			
			
			try {
				session = HibernateSessionFactory.getSession();
				transaction = session.beginTransaction();
				employees = session.createQuery("FROM Employee", Employee.class).getResultList();
				
				
			} catch (HibernateException e) {
				myLogger.error("Hibernate Exception: ", e);
				System.out.println("No Employees Found");
			}
				return employees;
		}
		
	

	@Override
	public List<Request> findByEmp(String lastName) {
		List<Request> requests = null;
		Session session = null;
		Transaction transaction = null;
				
		try {
			session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			requests = session.createQuery("FROM Requests where employee_submit = ?", Request.class).getResultList();
			transaction.commit();
		}catch(HibernateException e) {
			transaction.rollback();
			myLogger.error("Hibernate Exception: ", e);
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
			requests = session.createQuery("FROM Requests where date_submit = ?", Request.class).getResultList();
			transaction.commit();
		}catch(HibernateException e) {
			transaction.rollback();
			myLogger.error("Hibernate Exception: ", e);
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
			request = session.createQuery("FROM Requests where date_submit = ?", Request.class).getSingleResult();
			transaction.commit();
		}catch(HibernateException e) {
			transaction.rollback();
			myLogger.error("Hibernate Exception: ", e);
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
			request = session.createQuery("FROM Requests where amount_submit = ?", Request.class).getSingleResult();
			transaction.commit();
		}catch(HibernateException e) {
			transaction.rollback();
			myLogger.error("Hibernate Exception: ", e);
		}
				
		return request;
	}



	@Override
	public void addEmployee(Employee employee) {
					Session session = null;
			Transaction transaction = null;
			try {
				session = HibernateSessionFactory.getSession();
				transaction = session.beginTransaction();
				session.save(employee);
				transaction.commit();
			}catch (HibernateException e) {
				transaction.rollback();
				myLogger.error("Hibernate Exception: ", e);
			}finally {
				session.close();
		}
	
		
	}

}
