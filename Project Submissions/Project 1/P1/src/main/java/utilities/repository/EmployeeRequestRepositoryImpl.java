package utilities.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import models.Employee;
import models.Request;
import utilities.connect.ConnectionClosers;
import utilities.connect.ConnectionHandler;
import utilities.connect.HibernateSessionFactory;

public class EmployeeRequestRepositoryImpl implements EmployeeRequestRepository{
	
	@Override
	public void createRequest(Request r) {
		Session session = null;
		Transaction transaction = null;
		
		try {
			session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			session.save(r); 
			transaction.commit();
		}catch(HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close(); 
		} // End finally block
	} // End method
	
	@Override
	public Employee findByEmployeeName(String name) {
		Employee emp = null;
		Session session = null;
		Transaction transaction = null;
		
		try {
			session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			emp = session.get(Employee.class, name);
		} catch(HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close(); 
		} // End finally block
		return emp;
	} // End method

	@Override
	public Request findByRequestId(int id) {
		Request r = null;
		final String SQL = "select * from requests where request_id = ?";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet set = null;
		
		try {
			conn = ConnectionHandler.getConnection();
			stmt = conn.prepareStatement(SQL);
			stmt.setInt(1, id);
			set = stmt.executeQuery();
			
			if (set.next()) {
				r = new Request(
						set.getInt(1),
						set.getString(2),
						set.getDouble(3),
						set.getString(4),
						set.getString(5),
						set.getString(6)
						);
			} // End if statement
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionClosers.closeConnection(conn);
			ConnectionClosers.closeResultSet(set);
			ConnectionClosers.closeStatement(stmt);
		} // End finally block
		
		return r;
	} // End method

	@Override
	public List<Request> findRequestsByEmployeeName(String name) {
		List<Request> requestList = null;
		Session session = null;
		Transaction transaction = null;
		
		try {
			session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<Request> cq = cb.createQuery(Request.class);
			Root<Request> root = cq.from(Request.class);
			
			cq.select(root).where(cb.equal(root.get("employeeName"), name));
			Query<Request> query = session.createQuery(cq);
			
			requestList = query.getResultList();
			
			transaction.commit();
		} catch(HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		}  finally {
			session.close(); 
		} // End finally block
		return requestList;
	} // End method

	@Override
	public List<Request> findAllRequests() {
		List<Request> requestList = null;
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			requestList = session.createQuery("FROM Request", Request.class).getResultList();
			transaction.commit();
		} catch(HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close(); 
		} // End finally block
		return requestList;
	}
	
	@Override
	public List<Request> findPendingRequests() {
		List<Request> requestList = null;
		Session session = null;
		Transaction transaction = null;
		
		try {
			session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<Request> cq = cb.createQuery(Request.class);
			Root<Request> root = cq.from(Request.class);
			
			cq.select(root).where(cb.equal(root.get("status"), "Pending"));
			Query<Request> query = session.createQuery(cq);
			
			requestList = query.getResultList();
			
			transaction.commit();
		} catch(HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		}  finally {
			session.close(); 
		} // End finally block
		return requestList;
	} // End method

	@Override
	public void updateRequest(Request r) {
		Session session = null;
		Transaction transaction = null;
		
		try {
			session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			session.update(r);
			transaction.commit();
		}catch(HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close(); 
		} // End finally block
	} // End method
	
	@Override
	public Request highestSpender() {
		Request r = null;
		Session session = null;
		Transaction transaction = null;
		
		try {
			session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<Request> cq = cb.createQuery(Request.class);
			Root<Request> root = cq.from(Request.class);
			
			cq.select(root).where(cb.equal(root.get("status"), "Approved")).orderBy(cb.desc(root.get("amount")));
			Query<Request> query = session.createQuery(cq);
			query.setMaxResults(1);
			r = query.list().get(0);
			
			transaction.commit();
		} catch(HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		}  finally {
			session.close(); 
		} // End finally block
		return r;
	} // End method

	@Override
	public int numberOfRequests() {
		int count = 0;
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			count = session.createQuery("FROM Request", Request.class).getResultList().size();
			transaction.commit();
		} catch(HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		}  finally {
			session.close(); 
		} // End finally block
		return count;
	} // End method

	@Override
	public double averageCost() {
		double avg = 0;
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			String SQL_QUERY = "select avg(amount) from Request r";
			avg = (double) session.createQuery(SQL_QUERY).list().get(0);
			transaction.commit();
		} catch(HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		}  finally {
			session.close(); 
		} // End finally block
		return avg;
	} // End method
} // End class
