package com.revature.repository.DAOClasses;

import com.revature.repository.DAOInteface.PendingRequestInterface;
import com.revature.repository.DTO.EmployeeAccountEntity;
import com.revature.repository.DTO.PendingRequestEntity;
import com.revature.repository.DTO.RequestTypeEntity;
import com.revature.repository.utility.HibernateSessionFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import java.util.List;

public class PendingRequestDao implements PendingRequestInterface {
    private final Logger dLog = LoggerFactory.getLogger(LoginInfoDao.class);

    @Override
    public List<PendingRequestEntity> getPendingRequestsByTypeId(RequestTypeEntity requestType) {
        dLog.debug("Getting PendingRequestEntity's from database by Request Type: " + requestType);
        List<PendingRequestEntity> pendingRequestEntities = null;
        Transaction tx = null;
        try (
                Session session = HibernateSessionFactory.getSession()
        ) {
            tx = session.beginTransaction();
            pendingRequestEntities = session.createQuery("FROM PendingRequestEntity AS E WHERE E.requestType.id = :typeId", PendingRequestEntity.class)
                    .setParameter("typeId", requestType.getId()).getResultList();
            tx.commit();
        } catch (HibernateException | NoResultException e) {
            if (tx != null)
                if (!tx.isActive()) tx.rollback();
            dLog.error(e.getMessage(), e);
        }
        if (pendingRequestEntities != null)
            dLog.debug("Successful retrieval of PendingRequestEntity's from database associated by RequestType: " + requestType);
        else
            dLog.debug("Failed to retrieve PendingRequestEntity's from database associated by RequestType: " + requestType);
        return pendingRequestEntities;
    }

    @Override
    public List<PendingRequestEntity> getPendingRequestByStatus(boolean status) {
        dLog.debug("Getting pendingRequestEntity's from database by status: " + status);
        List<PendingRequestEntity> pendingRequestEntities = null;
        Transaction tx = null;
        try (
                Session session = HibernateSessionFactory.getSession()
        ) {
            tx = session.beginTransaction();
            pendingRequestEntities = session.createQuery("FROM PendingRequestEntity AS E WHERE E.status = :status", PendingRequestEntity.class)
                    .setParameter("status", status).getResultList();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                if (!tx.isActive()) tx.rollback();
            dLog.error(e.getMessage(), e);
        }
        if (pendingRequestEntities != null)
            dLog.debug("Successful retrieval of PendingRequests by Status in database: " + status);
        else dLog.debug("Failed retrieval of PendingRequests by status in database: " + status);
        return pendingRequestEntities;
    }

    @Override
    public Integer insertPendingRequest(PendingRequestEntity pendingRequestEntity) {
        dLog.debug("Inserting pending request entity in database: " + pendingRequestEntity);
        System.out.println(pendingRequestEntity);
        Transaction tx = null;
        Integer savedId = 0;
        try (
                Session session = HibernateSessionFactory.getSession()
        ) {
            tx = session.beginTransaction();
            savedId = (Integer) session.save(pendingRequestEntity);
            tx.commit();
        }catch(PersistenceException e){
            if(tx != null)
                if(!tx.isActive()) tx.rollback();
            dLog.error(e.getMessage(), e);
            return -1;
        }
        if(savedId != 0) {
             dLog.debug("Successful insert of PendingRequest in database returned ID of: " + savedId);
             return savedId;
         }else{
             dLog.debug("Failed to insert PendingRequest in database: " + pendingRequestEntity);
             return -1;
         }
    }

    @Override
    public PendingRequestEntity getPendingRequestByRequestId(int requestId) {
        dLog.debug("Getting pending request by ID from database: " + requestId);
        Transaction tx = null;
        PendingRequestEntity returnPendingRequest = null;
        try(
                Session session = HibernateSessionFactory.getSession()
                ){
            tx = session.beginTransaction();
            returnPendingRequest = session.get(PendingRequestEntity.class, requestId);
            tx.commit();
        }catch(HibernateException | NoResultException e){
            if(tx != null)
                if(!tx.isActive()) tx.rollback();
            dLog.error(e.getMessage(), e);
        }
        if(returnPendingRequest != null) dLog.debug("Successful retrieval of PendingRequestEntity from database by requestId: " + requestId + " " + returnPendingRequest);
        else dLog.debug("Failed retrieval of PendingRequestEntity from database by requestId: " + requestId);
        return returnPendingRequest;
    }

    @Override
    public List<PendingRequestEntity> getEmployeesPendingRequestList(EmployeeAccountEntity employeeAccount) {
        dLog.debug("Retrieving all Employee PendingRequestEntities from database: " + employeeAccount);
        List<PendingRequestEntity> pendingRequestEntities = null;
        Transaction tx = null;
        try(
                Session session = HibernateSessionFactory.getSession()
                ){
            tx = session.beginTransaction();
            pendingRequestEntities = session.createQuery("FROM PendingRequestEntity AS E WHERE E.employeeAccount.id = :employeeId", PendingRequestEntity.class)
                    .setParameter("employeeId", employeeAccount.getId()).getResultList();
            tx.commit();
        }catch(HibernateException e){
            if(tx != null)
                if(!tx.isActive()) tx.rollback();
            dLog.error(e.getMessage(), e);
        }
        if(pendingRequestEntities != null) dLog.debug("Successful retrieval of Employee Pending Request Entities from Database: " + employeeAccount);
        else dLog.debug("Failed to retrieve Employee Pending Request Entity from Database: " + employeeAccount);
        return pendingRequestEntities;    }

    @Override
    public List<PendingRequestEntity> getAllPendingRequests() {
        dLog.debug("Retrieving All Pending Request Entities from database");
        List<PendingRequestEntity> pendingRequestEntities = null;
        Transaction tx = null;
        try(
                Session session = HibernateSessionFactory.getSession()
                ){
            tx = session.beginTransaction();
            pendingRequestEntities = session.createQuery("FROM PendingRequestEntity", PendingRequestEntity.class).getResultList();
            tx.commit();
        }catch(HibernateException e){
            if(tx != null)
                if(!tx.isActive()) tx.rollback();
            dLog.error(e.getMessage(), e);
        }
        if(pendingRequestEntities != null) dLog.debug("Successful retrieval of All Pending Request Entities from database");
        else dLog.debug("Failed to retrieve all pending request entities from database");
        return pendingRequestEntities;       }

    @Override
    public void updatePendingRequest(PendingRequestEntity pendingRequestEntity) {
        dLog.debug("Updating Pending request entity from database: " + pendingRequestEntity);
        Transaction tx = null;
        try(
                Session session = HibernateSessionFactory.getSession()
                ){
            tx = session.beginTransaction();
            session.update(pendingRequestEntity);
            tx.commit();
        }catch(HibernateException e){
            if(tx != null)
                if(!tx.isActive()) tx.rollback();
            dLog.error(e.getMessage(), e);
        }
    }

    @Override
    public void deletePendingRequest(PendingRequestEntity pendingRequestEntity) {
        dLog.debug("Deleting PendingRequestEntity from database: " + pendingRequestEntity);
        Transaction tx = null;
        try(
                Session session = HibernateSessionFactory.getSession()
                ){
            tx = session.beginTransaction();
            session.delete(pendingRequestEntity);
            tx.commit();
        }catch(HibernateException | NoResultException e){
            if(tx != null)
                if(!tx.isActive()) tx.rollback();
            dLog.error(e.getMessage(), e);
        }
    }

    @Override
    public List<PendingRequestEntity> getAnsweredEmployeePendingRequests(EmployeeAccountEntity employee) {
        dLog.debug("Retrieving answered pending requests by Employee from database: " + employee);
        List<PendingRequestEntity> pendingRequestEntities = null;
        Transaction tx = null;
        try(
                Session session = HibernateSessionFactory.getSession()
                ){
            tx = session.beginTransaction();
            pendingRequestEntities = session.createQuery("FROM PendingRequestEntity AS E WHERE E.employeeAccount.id = :employeeId AND E.status = true", PendingRequestEntity.class)
                    .setParameter("employeeId", employee.getId()).getResultList();
            tx.commit();
        }catch(HibernateException e){
            if(tx != null)
                if(!tx.isActive()) tx.rollback();
            dLog.error(e.getMessage(), e);
        }
        if(pendingRequestEntities != null) dLog.debug("Successful retrieval of Answered Employee PendingRequest Entities from database: " + employee);
        else dLog.debug("Failed retrieval of Answered Employee Pending Request Entities from database: " + employee);
        return pendingRequestEntities;        }

    @Override
    public List<PendingRequestEntity> getAnsweredPendingRequestsByType(RequestTypeEntity requestType) {
        dLog.debug("Getting answered pending requests from database by request type: " + requestType);
        List<PendingRequestEntity> pendingRequestEntities = null;
        Transaction tx = null;
        try(
                Session session = HibernateSessionFactory.getSession()
                ){
            tx = session.beginTransaction();
            pendingRequestEntities = session.createQuery("FROM PendingRequestEntity AS E WHERE E.requestType.id = :typeId AND E.status = true", PendingRequestEntity.class)
                    .setParameter("typeId", requestType.getId()).getResultList();
            tx.commit();
        }catch(HibernateException e){
            if(tx != null)
                if(!tx.isActive()) tx.rollback();
            dLog.error(e.getMessage(), e);
        }
        if(pendingRequestEntities != null) dLog.debug("Successful retrieval of answered pending requests from database by request Type: " + requestType);
        else dLog.debug("Failed retrieval of answered pending requests from database by request type: " + requestType);
        return pendingRequestEntities;
    }
}
