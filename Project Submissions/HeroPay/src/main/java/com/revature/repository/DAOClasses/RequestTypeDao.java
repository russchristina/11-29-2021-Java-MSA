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

public class RequestTypeDao implements RequestTypeInterface {
    private final Logger dLog = LoggerFactory.getLogger("dLog");
    @Override
    public Integer insertRequestType(RequestTypeEntity requestTypeEntity) {
        Session session;
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
    public RequestTypeEntity getRequestTypeWithString(String requestTypeName) {
        Session session;
        Transaction tx = null;
        RequestTypeEntity returnRequestType = null;
        try{
            session = HibernateSessionFactory.getSession();
            tx = session.beginTransaction();
            returnRequestType = session.get(RequestTypeEntity.class, requestTypeName);
            tx.commit();
        }catch(HibernateException e){
            tx.rollback();
            dLog.error(e.getMessage(), e);
        }
        return returnRequestType;
    }

    @Override
    public Integer updatePendingRequestType(RequestTypeEntity requestTypeEntity) {
        Session session;
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
}
