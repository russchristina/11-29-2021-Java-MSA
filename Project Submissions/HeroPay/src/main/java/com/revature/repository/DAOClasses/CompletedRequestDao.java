package com.revature.repository.DAOClasses;

import com.revature.repository.DAOInteface.CompletedRequestInterface;
import com.revature.repository.DTO.CompletedRequestEntity;
import com.revature.repository.DTO.PendingRequestEntity;
import com.revature.repository.utility.ConnectionFactory;
import com.revature.repository.utility.HibernateSessionFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CompletedRequestDao implements CompletedRequestInterface {

    private final Logger dLog = LoggerFactory.getLogger(LoginInfoDao.class);

    @Override
    public Integer insertCompletedRequest(CompletedRequestEntity completedRequestEntity) {
        Session session = null;
        Transaction tx = null;
        int savedId = 0;
        try{
            session = HibernateSessionFactory.getSession();
            tx = session.beginTransaction();
            savedId = (Integer) session.save(completedRequestEntity);
            tx.commit();
        }catch(HibernateException e){
            if(tx != null)tx.rollback();
            dLog.error(e.getMessage(), e);
        }finally{
            session.close();
        }
        if(savedId != 0) dLog.debug("Successful insert of completed request into DB: " + completedRequestEntity);
        else dLog.debug("Unsuccessful insert of completed request into DB");
        return savedId;       }

    @Override
    public List<CompletedRequestEntity> getCompletedRequestByEmployeeId(int employeeId) {
        List<CompletedRequestEntity> completedRequestEntities = null;
        Session session = null;
        Transaction tx = null;
        try{
            session = HibernateSessionFactory.getSession();
            tx = session.beginTransaction();
            completedRequestEntities = session.createQuery("FROM CompletedRequestEntity AS E WHERE E.employeeAccount.id = :employeeId", CompletedRequestEntity.class)
                    .setParameter("employeeId", employeeId).getResultList();
            tx.commit();
        }catch(HibernateException e){
            if(tx != null)tx.rollback();
            dLog.error(e.getMessage(), e);
        }finally{
            session.close();
        }
        return completedRequestEntities;    }

    @Override
    public CompletedRequestEntity getCompletedRequest(int requestId) {
        CompletedRequestEntity completedRequestEntities = null;
        Session session = null;
        Transaction tx = null;
        try{
            session = HibernateSessionFactory.getSession();
            tx = session.beginTransaction();
            completedRequestEntities = session.get(CompletedRequestEntity.class, requestId);
            tx.commit();
        }catch(HibernateException e){
            if(tx != null)tx.rollback();
            dLog.error(e.getMessage(), e);
        }finally{
            session.close();
        }
        return completedRequestEntities;      }

    @Override
    public List<CompletedRequestEntity> getCompletedRequestByManagerId(int managerId) {
        List<CompletedRequestEntity> completedRequestEntities = null;
        Session session = null;
        Transaction tx = null;
        try{
            session = HibernateSessionFactory.getSession();
            tx = session.beginTransaction();
            completedRequestEntities = session.createQuery("FROM CompletedRequestEntity AS E WHERE E.managerAccount.id = :managerId", CompletedRequestEntity.class)
                    .setParameter("managerId", managerId).getResultList();
            tx.commit();
        }catch(HibernateException e){
            if(tx != null)tx.rollback();
            dLog.error(e.getMessage(), e);
        }finally{
            session.close();
        }
        return completedRequestEntities;
    }

    @Override
    public List<CompletedRequestEntity> getAllCompletedRequestList() {
        List<CompletedRequestEntity> completedRequestEntities = null;
        Session session = null;
        Transaction tx = null;
        try{
            session = HibernateSessionFactory.getSession();
            tx = session.beginTransaction();
            completedRequestEntities = session.createQuery("FROM CompletedRequestEntity", CompletedRequestEntity.class).getResultList();
            tx.commit();
        }catch(HibernateException e){
            if(tx != null)tx.rollback();
            dLog.error(e.getMessage(), e);
        }finally{
            session.close();
        }
        return completedRequestEntities;
    }

    @Override
    public Integer updateCompletedRequestByManagerId(CompletedRequestEntity completedRequestEntity) {
        Session session = null;
        Transaction tx = null;
        int savedId = 0;
        try{
            session = HibernateSessionFactory.getSession();
            tx = session.beginTransaction();
            savedId = (Integer) session.save(completedRequestEntity);
            tx.commit();
        }catch(HibernateException e){
            if(tx != null)tx.rollback();
            dLog.error(e.getMessage(), e);
        }finally{
            session.close();
        }
        return savedId;
    }

    @Override
    public Integer updateCompletedRequestStatus(CompletedRequestEntity completedRequestEntity) {

        Session session = null;
        Transaction tx = null;
        int savedId = 0;
        try{
            session = HibernateSessionFactory.getSession();
            tx = session.beginTransaction();
            savedId = (Integer) session.save(completedRequestEntity);
            tx.commit();
        }catch(HibernateException e){
            if(tx != null)tx.rollback();
            dLog.error(e.getMessage(), e);
        }finally{
            session.close();
        }
        return savedId;
    }

    @Override
    public Integer updateCompletedRequestResponse(CompletedRequestEntity completedRequestEntity) {

        Session session = null;
        Transaction tx = null;
        int savedId = 0;
        try{
            session = HibernateSessionFactory.getSession();
            tx = session.beginTransaction();
            savedId = (Integer) session.save(completedRequestEntity);
            tx.commit();
        }catch(HibernateException e){
            if(tx != null)tx.rollback();
            dLog.error(e.getMessage(), e);
        }finally{
            session.close();
        }
        return savedId;
    }

    @Override
    public void deleteCompletedRequest(CompletedRequestEntity completedRequestEntity) {
        Session session = null;
        Transaction tx = null;
        try{
            session = HibernateSessionFactory.getSession();
            tx = session.beginTransaction();
            session.delete(completedRequestEntity);
            tx.commit();
        }catch(HibernateException e){
            if(tx != null)tx.rollback();
            dLog.error(e.getMessage(), e);
        }finally{
            session.close();
        }


    }   @Override
    public List<CompletedRequestEntity> getCompletedRequestsByStatus(boolean status) {
        List<CompletedRequestEntity> completedRequestEntities = null;
        Session session = null;
        Transaction tx = null;
        try{
            session = HibernateSessionFactory.getSession();
            tx = session.beginTransaction();
            completedRequestEntities = session.createQuery("FROM CompletedRequestEntity AS E WHERE E.status = :status", CompletedRequestEntity.class)
                    .setParameter("status", status).getResultList();
            tx.commit();
        }catch(HibernateException e){
            if(tx != null)tx.rollback();
            dLog.error(e.getMessage(), e);
        }finally{
            session.close();
        }
        return completedRequestEntities;
    }
}
