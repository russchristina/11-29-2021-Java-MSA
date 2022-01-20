package com.revature.repoclass;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.Login;
import com.revature.repo.LoginRepo;
import com.revature.utility.HibernateSessionFactory;

public class LoginRepoImp implements LoginRepo {

	@Override
	public void saveLogin(Login login) {
		Session session = null;
		Transaction transaction = null;
		
		try {
			session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			session.save(login);
			transaction.commit();
		}catch(HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
		
	}

}

