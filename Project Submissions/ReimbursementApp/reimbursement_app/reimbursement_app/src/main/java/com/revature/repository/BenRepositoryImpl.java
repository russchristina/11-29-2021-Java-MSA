package com.revature.repository;

import java.util.List;

import com.revature.model.Reimbursements_Ben;
import com.revature.util.HibernateSessionFactory;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class BenRepositoryImpl implements BenRepository {

  @Override
  public List<Reimbursements_Ben> findAllBen() {

    List<Reimbursements_Ben> reimbursements = null;

    Session session = null;
    Transaction transaction = null;

    try {
      session = HibernateSessionFactory.getSession();
      transaction = session.beginTransaction();
      reimbursements = session.createQuery("FROM Reimbursements_Ben", Reimbursements_Ben.class).getResultList();

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
  public void submitReimbursement(Reimbursements_Ben reimbursements) {

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
