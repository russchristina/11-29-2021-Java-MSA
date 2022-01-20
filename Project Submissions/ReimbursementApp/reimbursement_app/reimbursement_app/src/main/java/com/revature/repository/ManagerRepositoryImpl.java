package com.revature.repository;

import java.util.List;

import com.revature.model.Manager_login;
import com.revature.model.Reimbursements_Alli;
import com.revature.model.Reimbursements_Ben;
import com.revature.model.Reimbursements_Sam;
import com.revature.util.HibernateSessionFactory;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ManagerRepositoryImpl implements ManagerRepository {

  @Override
  public Reimbursements_Alli getAlliPendingOrPast(int id) {

    Reimbursements_Alli reimburse = null;
    Session session = null;
    Transaction transaction = null;

    try {
      session = HibernateSessionFactory.getSession();
      transaction = session.beginTransaction();

      reimburse = session.get(Reimbursements_Alli.class, id);
      transaction.commit();

    } catch (HibernateException e) {

      transaction.rollback();
      e.printStackTrace();

    } finally {
      session.close();
    }
    return reimburse;
  }

  @Override
  public Reimbursements_Ben getBenPendingOrPast(int id) {

    Reimbursements_Ben reimburse = null;
    Session session = null;
    Transaction transaction = null;

    try {
      session = HibernateSessionFactory.getSession();
      transaction = session.beginTransaction();

      reimburse = session.get(Reimbursements_Ben.class, id);
      transaction.commit();

    } catch (HibernateException e) {

      transaction.rollback();
      e.printStackTrace();

    } finally {
      session.close();
    }
    return reimburse;
  }

  @Override
  public Reimbursements_Sam getSamPendingOrPast(int id) {
    Reimbursements_Sam reimburse = null;
    Session session = null;
    Transaction transaction = null;

    try {
      session = HibernateSessionFactory.getSession();
      transaction = session.beginTransaction();

      reimburse = session.get(Reimbursements_Sam.class, id);
      transaction.commit();

    } catch (HibernateException e) {

      transaction.rollback();
      e.printStackTrace();

    } finally {
      session.close();
    }
    return reimburse;
  }

  @Override
  public void updateAlliApproveOrNotApprove(Reimbursements_Alli reimbursements_Alli) {
    Session session = null;
    Transaction transaction = null;

    try {
      session = HibernateSessionFactory.getSession();
      transaction = session.beginTransaction();

      session.update(reimbursements_Alli);
      transaction.commit();

    } catch (HibernateException e) {

      transaction.rollback();
      e.printStackTrace();

    } finally {
      session.close();
    }
  }

  @Override
  public void updateBenApproveOrNotApprove(Reimbursements_Ben reimbursements_Ben) {
    Session session = null;
    Transaction transaction = null;

    try {
      session = HibernateSessionFactory.getSession();
      transaction = session.beginTransaction();

      session.update(reimbursements_Ben);
      transaction.commit();

    } catch (HibernateException e) {

      transaction.rollback();
      e.printStackTrace();

    } finally {
      session.close();
    }

  }

  @Override
  public void updateSamApproveOrNotApprove(Reimbursements_Sam reimbursements_Sam) {
    Session session = null;
    Transaction transaction = null;

    try {
      session = HibernateSessionFactory.getSession();
      transaction = session.beginTransaction();

      session.update(reimbursements_Sam);
      transaction.commit();

    } catch (HibernateException e) {

      transaction.rollback();
      e.printStackTrace();

    } finally {
      session.close();
    }

  }

  @Override
  public List<Manager_login> findManagerLogin() {
    List<Manager_login> employee = null;

    Session session = null;
    Transaction transaction = null;

    try {
      session = HibernateSessionFactory.getSession();
      transaction = session.beginTransaction();
      employee = session.createQuery("FROM Manager_login", Manager_login.class).getResultList();

      transaction.commit();

    } catch (HibernateException e) {

      transaction.rollback();
      e.printStackTrace();
    } finally {
      session.close();
    }
    return employee;
  }

}
