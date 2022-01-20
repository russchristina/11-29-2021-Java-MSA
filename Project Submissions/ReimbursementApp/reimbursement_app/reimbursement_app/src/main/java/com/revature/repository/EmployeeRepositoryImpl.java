package com.revature.repository;

import java.util.List;

import com.revature.model.Employee_login;
import com.revature.util.HibernateSessionFactory;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class EmployeeRepositoryImpl implements EmployeeRepository {

  @Override
  public List<Employee_login> findAllEmployeeLogin() {
    List<Employee_login> employee = null;

    Session session = null;
    Transaction transaction = null;

    try {
      session = HibernateSessionFactory.getSession();
      transaction = session.beginTransaction();
      employee = session.createQuery("FROM Employee_login", Employee_login.class).getResultList();

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
