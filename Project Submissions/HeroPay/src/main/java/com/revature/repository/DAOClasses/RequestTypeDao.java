package com.revature.repository.DAOClasses;

import com.revature.repository.DAOInteface.PendingRequestInterface;
import com.revature.repository.DAOInteface.RequestTypeInterface;
import com.revature.repository.DTO.LoginInfoEntity;
import com.revature.repository.DTO.PendingRequestEntity;
import com.revature.repository.DTO.RequestTypeEntity;
import com.revature.repository.utility.HibernateSessionFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class RequestTypeDao implements RequestTypeInterface {
    private final Logger dLog = LoggerFactory.getLogger("dLog");
    @Override
    public Integer insertRequestType(RequestTypeEntity requestTypeEntity) {
        Session session = null;
        Transaction tx = null;
        int savedId = 0;
        try{
            session = HibernateSessionFactory.getSession();
            tx = session.beginTransaction();
            savedId = (Integer) session.save(requestTypeEntity);
            tx.commit();
        }catch(HibernateException e){
            tx.rollback();
            dLog.debug(e.getMessage(), e);
        }
        return savedId;
    }


    @Override
    public RequestTypeEntity getRequestTypeWithString(String type) {
        Session session = null;
        Transaction tx = null;
        RequestTypeEntity returnRequestType = null;
        try{
            session = HibernateSessionFactory.getSession();
            tx = session.beginTransaction();
            returnRequestType = session.createQuery("FROM RequestTypeEntity AS R WHERE R.requestType = :type", RequestTypeEntity.class)
                    .setParameter("type", type).getSingleResult();
            tx.commit();
        }catch(HibernateException e){
            if(tx != null) tx.rollback();
            dLog.error(e.getMessage(), e);
        }finally{
            session.close();
        }
        return returnRequestType;
    }

    @Override
    public Integer updatePendingRequestType(RequestTypeEntity requestTypeEntity) {
        Session session = null;
        Transaction tx = null;
        int savedId = 0;
        try{
            session = HibernateSessionFactory.getSession();
            tx = session.beginTransaction();
            savedId = (Integer) session.save(requestTypeEntity);
            tx.commit();
        }catch(HibernateException e){
            tx.rollback();
            dLog.debug(e.getMessage(), e);
        }
        return savedId;
    }

    @Override
    public List<RequestTypeEntity> getRequestTypes() {
        List<RequestTypeEntity> requestTypes = null;
        Transaction tx= null;
        try(
                Session session = HibernateSessionFactory.getSession()
                ){
            tx = session.beginTransaction();
            requestTypes = session.createQuery("FROM RequestTypeEntity", RequestTypeEntity.class).getResultList();
            tx.commit();
        }catch(HibernateException e){
            if(tx != null) tx.rollback();
            dLog.error(e.getMessage(), e);
        }
        return requestTypes;
    }
}
