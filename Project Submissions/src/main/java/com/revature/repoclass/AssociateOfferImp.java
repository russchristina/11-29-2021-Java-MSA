package com.revature.repoclass;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.revature.models.Associate;
import com.revature.models.TypeOffer;
import com.revature.utility.HibernateSessionFactory;

public class AssociateOfferImp implements AssociateOffer {

	@Override
	public Associate locateAssociateName(String fullName) {
		Associate asso = null;
		Session session = null;
		Transaction transaction = null;
		
		try {
			session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			asso = session.get(Associate.class, fullName);
		} catch(HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close(); 
		} 
		return asso;
	} 

	@Override
	public void updateOffer(TypeOffer t) {
		Session session = null;
		Transaction transaction = null;
		
		try {
			session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			session.update(t);
			transaction.commit();
		}catch(HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close(); 
		} 
	} 

	@Override
	public TypeOffer TopSpender() {
		TypeOffer r = null;
		Session session = null;
		Transaction transaction = null;
		
		try {
			session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<TypeOffer> cq = cb.createQuery(TypeOffer.class);
			Root<TypeOffer> root = cq.from(TypeOffer.class);
			
			cq.select(root).where(cb.equal(root.get("status"), "Accepted")).orderBy(cb.desc(root.get("amount")));
			Query<TypeOffer> query = session.createQuery(cq);
			query.setMaxResults(1);
			r = query.list().get(0);
			
			transaction.commit();
		} catch(HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		}  finally {
			session.close(); 
		} 
		return r;
	} 


	@Override
	public int numberOffers() {
		int count = 0;
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			count = session.createQuery("FROM TypeOffer", TypeOffer.class).getResultList().size();
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
			String SQL_QUERY = "select avg(amount) from TypeOffer r";
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


	@Override
	public List<TypeOffer> findAllRequests() {
		List<TypeOffer> offerList = null;
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			offerList = session.createQuery("FROM Request", TypeOffer.class).getResultList();
			transaction.commit();
		} catch(HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close(); 
		} // End finally block
		return offerList;
	}
	@Override
	public List<TypeOffer> findPendingOffers() {
		List<TypeOffer> offerList = null;
		Session session = null;
		Transaction transaction = null;
		
		try {
			session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<TypeOffer> cq = cb.createQuery(TypeOffer.class);
			Root<TypeOffer> root = cq.from(TypeOffer.class);
			
			cq.select(root).where(cb.equal(root.get("status"), "Pending"));
			Query<TypeOffer> query = session.createQuery(cq);
			
			offerList = query.getResultList();
			
			transaction.commit();
		} catch(HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		}  finally {
			session.close(); 
		} 
		return offerList;
	} 
	@Override
	public void makeOffer(TypeOffer t) {
		Session session = null;
		Transaction transaction = null;
		
		try {
			session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			session.save(t); 
			transaction.commit();
		}catch(HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close(); 
		} 
	} 
	@Override
	public List<TypeOffer> findOffersAssociateName(String fullName) {
		List<TypeOffer> offerList = null;
		Session session = null;
		Transaction transaction = null;
		
		try {
			session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<TypeOffer> cq = cb.createQuery(TypeOffer.class);
			Root<TypeOffer> root = cq.from(TypeOffer.class);
			
			cq.select(root).where(cb.equal(root.get("associateName"), fullName));
			Query<TypeOffer> query = session.createQuery(cq);
			
			offerList = query.getResultList();
			
			transaction.commit();
		} catch(HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		}  finally {
			session.close(); 
		} 
		return offerList;
	} 


}
