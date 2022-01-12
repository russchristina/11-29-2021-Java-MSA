package com.revature.repository.DAOClasses;

import com.revature.repository.DAOInteface.LoginInfoInterface;
import com.revature.repository.DTO.EmployeeAccountEntity;
import com.revature.repository.DTO.LoginInfoEntity;

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
import java.util.List;

public class LoginInfoDao implements LoginInfoInterface {

    private final Logger dLog = LoggerFactory.getLogger("dLog");

    @Override
    public Integer insertLoginInfo(LoginInfoEntity loginInfoEntity) {
        Session session = null;
        Transaction tx = null;
        int savedId = 0;
        try{
            session = HibernateSessionFactory.getSession();
            tx = session.beginTransaction();
            savedId = (Integer) session.save(loginInfoEntity);
            tx.commit();
        }catch(HibernateException e){
            if(tx != null) tx.rollback();
            dLog.error(e.getMessage(), e);
        } finally{
            session.close();
        }
        return savedId;
    }

    @Override
    public LoginInfoEntity getLoginInfo(String username) {
        LoginInfoEntity returnLoginInfo = null;
        Session session = null;
        Transaction tx = null;
        try{
            session = HibernateSessionFactory.getSession();
            tx = session.beginTransaction();
            returnLoginInfo = session.createQuery("FROM LoginInfoEntity AS L WHERE L.username = :username", LoginInfoEntity.class)
                    .setParameter("username", username).getSingleResult();
            tx.commit();
        }catch(HibernateException e){
            if(tx != null) tx.rollback();
            dLog.error(e.getMessage(), e);
        } finally{
            session.close();
        }
        return returnLoginInfo;
    }

    @Override
    public Integer updateUsername(LoginInfoEntity loginInfoEntity) {
        Session session = null;
        Transaction tx = null;
        int savedId = 0;
        try{
            session = HibernateSessionFactory.getSession();
            tx = session.beginTransaction();
            savedId = (Integer) session.save(loginInfoEntity);
            tx.commit();
        }catch(HibernateException e){
            if(tx != null) tx.rollback();
            dLog.error(e.getMessage(), e);
        } finally{
            session.close();
        }
        return savedId;
    }

    @Override
    public Integer updatePassword(LoginInfoEntity loginInfoEntity) {
        Session session = null;
        Transaction tx = null;
        int savedId = 0;
        try{
            session = HibernateSessionFactory.getSession();
            tx = session.beginTransaction();
            savedId = (Integer) session.save(loginInfoEntity);
            tx.commit();
        }catch(HibernateException e){
            if(tx != null) tx.rollback();
            dLog.error(e.getMessage(), e);
        } finally{
            session.close();
        }
        return savedId;
    }

    @Override
    public void deleteLoginInfo(LoginInfoEntity loginInfoEntity) {
        Session session = null;
        Transaction tx = null;
        try{
            session = HibernateSessionFactory.getSession();
            tx = session.beginTransaction();
            session.delete(loginInfoEntity);
            tx.commit();
        }catch(HibernateException e){
            if(tx != null) tx.rollback();
            dLog.error(e.getMessage(), e);
        } finally{
            session.close();
        }
    }
}
