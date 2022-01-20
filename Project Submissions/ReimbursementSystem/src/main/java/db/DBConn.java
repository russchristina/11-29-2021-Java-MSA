package db;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import models.Account;
import models.PastRequests;
import models.RepayForm;
import util.EasyConnect;

public class DBConn extends EasyConnect implements AccountRepository, FormRepository, PRRepository{
	private final Logger dLog = LoggerFactory.getLogger("dLog");
	public int id = 0;
	
	@Override
	public void save(RepayForm form) {
		Session session = null;
		Transaction transaction = null;
		
		try {
			session = this.getConnection();
			transaction = session.beginTransaction();
			session.save(form);
			transaction.commit();
		}catch(HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
	}
	
	@Override
	public void save(Account account) {
		Session session = null;
		Transaction transaction = null;
		
		try {
			session = this.getConnection();
			transaction = session.beginTransaction();
			session.save(account);
			transaction.commit();
		}catch(HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
	}
	
	@Override
	public void merge(RepayForm form) {
		Session session = null;
		Transaction transaction = null;
		
		try {
			session = this.getConnection();
			transaction = session.beginTransaction();
			session.merge(form);
			transaction.commit();
		}catch(HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
	}
	
	@Override
	public void save(PastRequests pr) {
		Session session = null;
		Transaction transaction = null;
		
		try {
			session = this.getConnection();
			transaction = session.beginTransaction();
			session.save(pr);
			transaction.commit();
		}catch(HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
	}

	
	@Override
	public List<RepayForm> selectRequestsById(int empId) {
		Session session = null;
		Transaction transaction = null;
		List<RepayForm> requests = new ArrayList<>();
				
		try {
			session = this.getConnection();
			transaction = session.beginTransaction();
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<RepayForm> cq = cb.createQuery(RepayForm.class);
			Root<RepayForm> root = cq.from(RepayForm.class);
			
			cq.select(root).where(cb.equal(root.get("fKey"), empId));
			
			Query<RepayForm> query = session.createQuery(cq);
			
			requests = query.getResultList();
			
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return requests;
	}
	
	@Override
	public List<RepayForm> selectAllRequests() {
		Session session = null;
		Transaction transaction = null;
		List<RepayForm> allRequests = new ArrayList<>();
		
		try {
			session = this.getConnection();
			transaction = session.beginTransaction();
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<RepayForm> cq = cb.createQuery(RepayForm.class);
			Root<RepayForm> root = cq.from(RepayForm.class);
			
			cq.select(root);
			
			Query<RepayForm> query = session.createQuery(cq);
			
			allRequests = query.getResultList();
			
			transaction.commit();
		}catch(HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		return allRequests;
	}

	@Override
	public List<RepayForm> selectAllNullRequests() {
		Session session = null;
		Transaction transaction = null;
		List<RepayForm> nullRequests = new ArrayList<>();
		
		try {
			session = this.getConnection();
			transaction = session.beginTransaction();
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<RepayForm> cq = cb.createQuery(RepayForm.class);
			Root<RepayForm> root = cq.from(RepayForm.class);
			
			cq.select(root).where(cb.isFalse(root.get("approval")));
			
			Query<RepayForm> query = session.createQuery(cq);
			
			nullRequests = query.getResultList();
			
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return nullRequests;
	}

	@Override
	public RepayForm selectForm(int formId) {
		Session session = null;
		Transaction transaction = null;
		RepayForm form = new RepayForm();
		
		try {
			session = this.getConnection();
			transaction = session.beginTransaction();
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<RepayForm> cq = cb.createQuery(RepayForm.class);
			Root<RepayForm> root = cq.from(RepayForm.class);
			
			cq.select(root).where(cb.equal(root.get("pKey"), formId));
			
			Query<RepayForm> query = session.createQuery(cq);
			
			form = query.getSingleResult();
			
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return form;
	}

	@Override
	public Account selectEmployee(int empId) {
		Session session = null;
		Transaction transaction = null;
		Account ac = new Account();
		
		try {
			session = this.getConnection();
			transaction = session.beginTransaction();
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<Account> cq = cb.createQuery(Account.class);
			Root<Account> root = cq.from(Account.class);
			
			cq.select(root)
				.where(cb.equal(root.get("emp_id"), empId));
			
			Query<Account> query = session.createQuery(cq);
			
			try {
				ac = query.getSingleResult();
			} catch (NoResultException e) {
				dLog.debug("No result from query");
				e.printStackTrace();
			}
			
			this.id = ac.getEmp_id();
			
			transaction.commit();
		}catch(HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
			
		}finally {
			session.close();
		}
		return ac;
	}

	
	@Override
	public int validateUser(String username, String password) {
		Session session = null;
		Transaction transaction = null;
		List<Account> ac = null;
		
		try {
			session = this.getConnection();
			transaction = session.beginTransaction();
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<Account> cq = cb.createQuery(Account.class);
			Root<Account> root = cq.from(Account.class);
			
			Predicate un = cb.equal(root.get("username"), username);
			Predicate up = cb.equal(root.get("pass"), password);

			cq.select(root).where(cb.and(un, up));
				
			Query<Account> query = session.createQuery(cq);
				
			ac = query.getResultList();
		
			try {
				this.id = ac.get(0).getEmp_id();
			} catch(IndexOutOfBoundsException e) {
				dLog.debug("Invalid credentials");
				e.printStackTrace();
			}
			transaction.commit();
		}catch(HibernateException e) {
			transaction.rollback();
			dLog.debug("HibernateException in int DBConn.validateUser(String, String)");
			e.printStackTrace();
		}catch(NullPointerException e){
			transaction.rollback();
			dLog.debug("NullPointerException in int DBConn.validateUser(String, String)");
			e.printStackTrace();
		}finally {
			session.close();
		}
		return this.id;
	}

	@Override
	public List<Account> selectAllEmployees() {
		Session session = null;
		Transaction transaction = null;
		List<Account> la = null;
		
		try {
			session = this.getConnection();
			transaction = session.beginTransaction();
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<Account> cq = cb.createQuery(Account.class);
			Root<Account> root = cq.from(Account.class);
			
			cq.select(root);
			
			Query<Account> query = session.createQuery(cq);
			
			try {
				la = query.getResultList();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			transaction.commit();
		} catch(HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
		return la;
	}

}
