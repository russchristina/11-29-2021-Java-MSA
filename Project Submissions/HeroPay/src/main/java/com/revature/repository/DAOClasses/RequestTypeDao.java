package com.revature.repository.DAOClasses;

import com.revature.repository.DAOInteface.RequestTypeInterface;
import com.revature.repository.DTO.RequestTypeEntity;
import com.revature.repository.utility.HibernateSessionFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.NoResultException;
import java.io.Serializable;
import java.util.List;

public class RequestTypeDao implements RequestTypeInterface {
    private final Logger dLog = LoggerFactory.getLogger("dLog");

    @Override
    public RequestTypeEntity getRequestTypeEntityWithId(RequestTypeEntity requestType) {
        dLog.debug("Getting RequestTypeEntity with ID from Database: " + requestType);
        Transaction tx = null;
        RequestTypeEntity returnRequestType = null;
        try(
                Session session = HibernateSessionFactory.getSession();
        ){
            tx = session.beginTransaction();
            returnRequestType = session.get(RequestTypeEntity.class, requestType.getId());
            tx.commit();
        }catch(HibernateException e){
            if(tx != null)
                if(!tx.isActive()) tx.rollback();
            dLog.error(e.getMessage(), e);
        }
        if(returnRequestType != null) dLog.debug("Successful retrieval of RequestTypeEntity: " + returnRequestType);
        else dLog.debug("Unsuccessful retrieval of RequestTypeEntity of ID: " + requestType);
        return returnRequestType;    }

    @Override
    public RequestTypeEntity getRequestTypeWithString(RequestTypeEntity requestType) {
        dLog.debug("Getting RequestTypeEntity with String from database: " + requestType);
        Transaction tx = null;
        RequestTypeEntity returnRequestType = null;
        try(
                Session session = HibernateSessionFactory.getSession();
                ){
            tx = session.beginTransaction();
            returnRequestType = session.createQuery("FROM RequestTypeEntity AS R WHERE R.requestType = :type", RequestTypeEntity.class)
                    .setParameter("type", requestType.getRequestType()).getSingleResult();
            tx.commit();
        }catch(HibernateException | NoResultException e){
            if(tx != null)
                if(!tx.isActive()) tx.rollback();
            dLog.error(e.getMessage(), e);
        }

        return returnRequestType;
    }

    @Override
    public List<RequestTypeEntity> getRequestTypes() {
        dLog.debug("Retrieving RequestTypeEntity from database");
        List<RequestTypeEntity> requestTypes = null;
        Transaction tx= null;
        try(
                Session session = HibernateSessionFactory.getSession()
                ){
            tx = session.beginTransaction();
            requestTypes = session.createQuery("FROM RequestTypeEntity", RequestTypeEntity.class).getResultList();
            tx.commit();
        }catch(HibernateException e){
            if(tx != null)
                if(!tx.isActive()) tx.rollback();
            dLog.error(e.getMessage(), e);
        }
        if(requestTypes != null) dLog.debug("Successful retrieval of RequestTypeEntity's from database");
        else dLog.debug("Failed retrieval of RequestTypeEntity's from database");
        return requestTypes;
    }
}
