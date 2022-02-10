package com.revature.repo;



import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.revature.model.Employee;
import com.revature.model.Reports;
import com.revature.util.HibernateSessionFactory;

import io.javalin.http.Handler;

public class Repository {

	public void save(Reports report) {
		
		Session session = null;
		Transaction transaction = null;
		
		try {
			session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			session.save(report);
			transaction.commit();
		}catch(HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
		
	}
	
	public List<Reports> getReports() {
		Session session = null;
		Transaction transaction = null;
		Query query = null;
		List<Reports> results;
		
		
		try {
			session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			query = session.createSQLQuery("select * from reimbursements order by status;");
			results = query.getResultList();
			session.close();
			return results;
		}catch(HibernateException e) {
			transaction.rollback();
			session.close();
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Employee> getUsers() {
		Session session = null;
		Transaction transaction = null;
		Query query = null;
		List<Employee> results = null;
		
		
		try {
			session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			results = session.createQuery("from employees", Employee.class).getResultList();
			transaction.commit();
		}catch(HibernateException e) {
			//transaction.rollback();
			
			e.printStackTrace();
		}finally {
			session.close();
		}
		return results;
	}
	
	public Handler findAllEmployees = ctx -> {
		
	};
}

