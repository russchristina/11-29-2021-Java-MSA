package tester;


import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import util.HibernateSessionFactory;

public class testImpl implements TestRepo {

	@Override
	public void save(TestItem testItem) {

		Session session = null;
		Transaction transaction = null;
		
		try {
			session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			session.save(testItem);
			transaction.commit();
			
		}catch(HibernateException e){
			transaction.rollback();
			e.printStackTrace();
			
		}finally {
			session.close();
		}
	}

	@Override
	public List<TestItem> findAll() {
		List<TestItem> items = null;
		
		Session session = null;
		Transaction transaction = null;
		
		try {
			session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			items = session.createQuery("FROM TestItem", TestItem.class).getResultList();
			transaction.commit();
		}catch(HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		
		
		return items;
	
		
	}

}
