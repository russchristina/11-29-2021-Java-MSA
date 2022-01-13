package com.revature.repository.DAOClasses;

import com.revature.repository.DAOInteface.CompletedRequestInterface;
import com.revature.repository.DTO.CompletedRequestEntity;
import com.revature.repository.DTO.EmployeeAccountEntity;
import com.revature.repository.utility.HibernateSessionFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.TransientObjectException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class CompletedRequestDao implements CompletedRequestInterface {

    private final Logger dLog = LoggerFactory.getLogger(LoginInfoDao.class);

    @Override
    public Integer insertCompletedRequest(CompletedRequestEntity completedRequestEntity) {
        dLog.debug("Beginning insert of Completed Request Entity in Database: " + completedRequestEntity);
        Transaction tx = null;
        int savedId = 0;
        try(
                Session session = HibernateSessionFactory.getSession()
                ){
            tx = session.beginTransaction();
            savedId = (Integer) session.save(completedRequestEntity);
            tx.commit();
        } catch(HibernateException | IllegalStateException e){
            if(tx != null)tx.rollback();
            dLog.error(e.getMessage(), e);
            return -1;
        }
        if(savedId != 0) {
            dLog.debug("Successful insert of completed request into DB with ID - " + savedId + " - " + completedRequestEntity);
            return savedId;
        }else{
            dLog.debug("Unsuccessful insert of completed request into DB");
            return -1;
        }
    }

    @Override
    public List<CompletedRequestEntity> getCompletedRequestByEmployeeId(EmployeeAccountEntity employee) {
        dLog.debug("Getting all CompletedRequestEntity from database with ID: " + employee);
        List<CompletedRequestEntity> completedRequestEntities = null;
        Transaction tx = null;
        try(
                Session session = HibernateSessionFactory.getSession()
                ){
            tx = session.beginTransaction();
            completedRequestEntities = session.createQuery("FROM CompletedRequestEntity AS E WHERE E.employeeAccount.id = :employeeId", CompletedRequestEntity.class)
                    .setParameter("employeeId", employee.getId()).getResultList();
            tx.commit();
        }catch(HibernateException e){
            if(tx != null)tx.rollback();
            dLog.error(e.getMessage(), e);
        }
        if(completedRequestEntities != null) dLog.debug("Successful retrieval of database: " + employee + " " + completedRequestEntities);
        else dLog.debug("Failed to get Completed Request by EmployeeId from Database: " + employee);
        return completedRequestEntities;
    }

    @Override
    public CompletedRequestEntity getCompletedRequestWithUniqueId(int uniqueId) {
        dLog.debug("Getting completed Request by ID: " + uniqueId);
        CompletedRequestEntity completedRequestEntity = null;
        Transaction tx = null;
        try(
                Session session = HibernateSessionFactory.getSession()
                ){
            tx = session.beginTransaction();
            completedRequestEntity = session.get(CompletedRequestEntity.class, uniqueId);
            tx.commit();
        }catch(HibernateException e){
            if(tx != null)tx.rollback();
            dLog.error(e.getMessage(), e);
        }
        if(completedRequestEntity != null) dLog.debug("Successful retrieval of CompletedRequestEntity: " + completedRequestEntity);

        return completedRequestEntity;      }

    @Override
    public List<CompletedRequestEntity> getCompletedRequestByManagerId(EmployeeAccountEntity manager) {
        dLog.debug("Getting all CompletedRequestEntities by manager in database: " + manager);
        List<CompletedRequestEntity> completedRequestEntities = null;
        Transaction tx = null;
        try(
                Session session = HibernateSessionFactory.getSession()
                ){
            tx = session.beginTransaction();
            completedRequestEntities = session.createQuery("FROM CompletedRequestEntity AS E WHERE E.managerAccount.id = :managerId", CompletedRequestEntity.class)
                    .setParameter("managerId", manager.getId()).getResultList();
            tx.commit();
        }catch(HibernateException e){
            if(tx != null)tx.rollback();
            dLog.error(e.getMessage(), e);
        }
        if(completedRequestEntities != null) dLog.debug("Successful retrieval of CompletedRequestEntities from database: " + completedRequestEntities);
        else dLog.debug("Failed to get completed request entities from database by manager ID:" + manager);
        return completedRequestEntities;
    }

    @Override
    public List<CompletedRequestEntity> getAllCompletedRequestList() {
        dLog.debug("Getting all completed request list from database");
        List<CompletedRequestEntity> completedRequestEntities = null;
        Transaction tx = null;
        try(
                Session session = HibernateSessionFactory.getSession()
                ){
            tx = session.beginTransaction();
            completedRequestEntities = session.createQuery("FROM CompletedRequestEntity", CompletedRequestEntity.class).getResultList();
            tx.commit();
        }catch(HibernateException e){
            if(tx != null)tx.rollback();
            dLog.error(e.getMessage(), e);
        }
        if(completedRequestEntities != null) dLog.debug("Successful retrieval of all CompletedRequestEntities");
        else dLog.debug("Failed request to database");
        return completedRequestEntities;
    }

    @Override
    public Integer updateCompletedRequestByManagerId(CompletedRequestEntity completedRequestEntity) {
        Transaction tx = null;
        int savedId = 0;
        try(
                Session session = HibernateSessionFactory.getSession()
                ){
            tx = session.beginTransaction();
            savedId = (Integer) session.save(completedRequestEntity);
            tx.commit();
        }catch(HibernateException e){
            if(tx != null)tx.rollback();
            dLog.error(e.getMessage(), e);
        }
        return savedId;
    }

    @Override
    public Integer updateCompletedRequestStatus(CompletedRequestEntity completedRequestEntity) {

        Transaction tx = null;
        int savedId = 0;
        try(
                Session session = HibernateSessionFactory.getSession()
                ){
            tx = session.beginTransaction();
            savedId = (Integer) session.save(completedRequestEntity);
            tx.commit();
        }catch(HibernateException e){
            if(tx != null)tx.rollback();
            dLog.error(e.getMessage(), e);
        }
        return savedId;
    }

    @Override
    public Integer updateCompletedRequestResponse(CompletedRequestEntity completedRequestEntity) {

        Transaction tx = null;
        int savedId = 0;
        try(
                Session session = HibernateSessionFactory.getSession()
                ){
            tx = session.beginTransaction();
            savedId = (Integer) session.save(completedRequestEntity);
            tx.commit();
        }catch(HibernateException e){
            if(tx != null)tx.rollback();
            dLog.error(e.getMessage(), e);
        }
        return savedId;
    }


    @Override
    public List<CompletedRequestEntity> getCompletedRequestsByStatus(boolean status) {
        dLog.debug("Getting Completed requests by their status: " + status);
        List<CompletedRequestEntity> completedRequestEntities = null;
        Transaction tx = null;
        try(
                Session session = HibernateSessionFactory.getSession()
                ){
            tx = session.beginTransaction();
            completedRequestEntities = session.createQuery("FROM CompletedRequestEntity AS E WHERE E.status = :status", CompletedRequestEntity.class)
                    .setParameter("status", status).getResultList();
            tx.commit();
        }catch(HibernateException e){
            if(tx != null)tx.rollback();
            dLog.error(e.getMessage(), e);
        }
        return completedRequestEntities;
    }
}
