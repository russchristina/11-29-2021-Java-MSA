package com.revature.repository.DAOClasses;

import com.revature.presentation.model.statisticsRequests.response.QuickSortEmployee;
import com.revature.repository.DAOInteface.EmployeeAccountInterface;
import com.revature.repository.DTO.EmployeeAccountEntity;
import com.revature.repository.DTO.EmployeeRoleEntity;
import com.revature.repository.utility.ConnectionFactory;
import com.revature.repository.utility.HibernateSessionFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeAccountDao implements EmployeeAccountInterface {

    private final Logger dLog = LoggerFactory.getLogger(LoginInfoDao.class);

    @Override
    public Integer insertEmployeeAccount(EmployeeAccountEntity employeeAccountEntity) {
        Session session;
        Transaction tx = null;
        int savedId = 0;
        try{
            session = HibernateSessionFactory.getSession();
            tx = session.beginTransaction();
            savedId = (Integer) session.save(employeeAccountEntity);
            tx.commit();
        }catch(HibernateException e){
            tx.rollback();
            dLog.error(e.getMessage(), e);
        }
        return savedId;
    }

    @Override
    public EmployeeAccountEntity getEmployeeAccount(int employeeId) {
        Session session;
        Transaction tx = null;
        EmployeeAccountEntity returnAccountEntity = null;
        try{
            session = HibernateSessionFactory.getSession();
            tx = session.beginTransaction();
            returnAccountEntity = session.get(EmployeeAccountEntity.class, employeeId);
            tx.commit();
        }catch(HibernateException e){
            tx.rollback();
            dLog.error(e.getMessage(), e);
        }
        return returnAccountEntity;
    }

    @Override
    public List<EmployeeAccountEntity> getAllEmployeeAccountList() {
        List<EmployeeAccountEntity> employeeAccountEntities = null;
        Session session;
        Transaction tx = null;
        try{
            session = HibernateSessionFactory.getSession();
            tx = session.beginTransaction();
            employeeAccountEntities = session.createQuery("FROM EmployeeAccountEntity", EmployeeAccountEntity.class).getResultList();
            tx.commit();
        }catch(HibernateException e){
            tx.rollback();
            e.printStackTrace();
        }
        return employeeAccountEntities;
    }

    @Override
    public List<EmployeeAccountEntity> getEmployeeAccountsByRoleId(EmployeeAccountEntity employeeAccountEntity) {
//        List<EmployeeAccountEntity> employeeAccountEntities = null;
//        Session session;
//        Transaction tx = null;
//        try{
//            session = HibernateSessionFactory.getSession();
//            tx = session.beginTransaction();
//            employeeAccountEntities = session.createQuery("FROM EmployeeAccountEntity WHERE roleName =", EmployeeAccountEntity.class).getResultList();
//            tx.commit();
//        }catch(HibernateException e){
//            tx.rollback();
//            e.printStackTrace();
//        }
        return null;
    }

    @Override
    public Integer updateEmployeeRole(EmployeeAccountEntity employeeAccountEntity) {
        Session session;
        Transaction tx = null;
        int savedId = 0;
        try{
            session = HibernateSessionFactory.getSession();
            tx = session.beginTransaction();
            savedId = (Integer) session.save(employeeAccountEntity);
            tx.commit();
        }catch(HibernateException e){
            tx.rollback();
            dLog.error(e.getMessage(), e);
        }
        return savedId;
    }

    @Override
    public void deleteEmployeeAccount(EmployeeAccountEntity employeeAccountEntity) {
        Session session;
        Transaction tx = null;
        try{
            session = HibernateSessionFactory.getSession();
            tx = session.beginTransaction();
            session.delete(employeeAccountEntity);
            tx.commit();
        }catch(HibernateException e){
            tx.rollback();
            dLog.error(e.getMessage(), e);
        }
    }
}
