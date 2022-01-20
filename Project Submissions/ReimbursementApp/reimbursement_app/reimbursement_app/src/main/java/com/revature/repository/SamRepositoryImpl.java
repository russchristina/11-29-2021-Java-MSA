package com.revature.repository;

import java.util.List;

import com.revature.model.Reimbursements_Sam;
import com.revature.util.HibernateSessionFactory;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class SamRepositoryImpl implements SamRepository {

  @Override
  public List<Reimbursements_Sam> findAllSam() {

    List<Reimbursements_Sam> reimbursements = null;

    Session session = null;
    Transaction transaction = null;

    try {
      session = HibernateSessionFactory.getSession();
      transaction = session.beginTransaction();
      reimbursements = session.createQuery("FROM Reimbursements_Sam", Reimbursements_Sam.class).getResultList();

      transaction.commit();

    } catch (HibernateException e) {

      transaction.rollback();
      e.printStackTrace();
    } finally {
      session.close();
    }
    return reimbursements;
  }

  @Override
  public void submitReimbursement(Reimbursements_Sam reimbursements) {

    Session session = null;
    Transaction transaction = null;

    try {
      session = HibernateSessionFactory.getSession();
      transaction = session.beginTransaction();

      session.save(reimbursements);
      transaction.commit();

    } catch (HibernateException e) {

      transaction.rollback();
      e.printStackTrace();

    } finally {
      session.close();
    }

  }
}
