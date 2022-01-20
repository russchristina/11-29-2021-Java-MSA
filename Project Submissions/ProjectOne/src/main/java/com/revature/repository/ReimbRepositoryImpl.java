package com.revature.repository;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.model.Reimb;
import com.revature.util.HibernateSessionFactory;

public class ReimbRepositoryImpl implements ReimbRepository {

	@Override
	public void submitReimb(Reimb reimb) {
		// TODO Auto-generated method stub

	}

	@Override
	public void approveReimb(Reimb reimb) {
		// TODO Auto-generated method stub

	}

	@Override
	public void denyReimb(Reimb reimb) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Reimb> findAll() {
		// TODO Auto-generated method stub
		List<Reimb> reimbs = null;
		
		Session session = null;
		Transaction transaction = null;
		try {
			session=HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			reimbs = session.createQuery("FROM Reimb", Reimb.class).getResultList();
			transaction.commit();
		}catch(HibernateException e){
			transaction.rollback();
			e.printStackTrace();
		}
		return reimbs;
	}

	@Override
	public List<Reimb> findPending() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double findHighest() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double findAvg() {
		// TODO Auto-generated method stub
		return 0;
	}

}
