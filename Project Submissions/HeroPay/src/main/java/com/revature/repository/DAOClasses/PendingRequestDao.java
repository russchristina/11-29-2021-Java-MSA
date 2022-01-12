package com.revature.repository.DAOClasses;

import com.revature.repository.DAOInteface.PendingRequestInterface;
import com.revature.repository.DTO.EmployeeAccountEntity;
import com.revature.repository.DTO.EmployeeRoleEntity;
import com.revature.repository.DTO.PendingRequestEntity;
import com.revature.repository.DTO.RequestTypeEntity;
import com.revature.repository.utility.ConnectionFactory;
import com.revature.repository.utility.HibernateSessionFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PendingRequestDao implements PendingRequestInterface {
    private final Logger dLog = LoggerFactory.getLogger(LoginInfoDao.class);

    @Override
    public List<PendingRequestEntity> getPendingRequestsByTypeId(int typeId) {
        List<PendingRequestEntity> pendingRequestEntities = null;
        Session session;
        Transaction tx = null;
        try{
            session = HibernateSessionFactory.getSession();
            tx = session.beginTransaction();
            pendingRequestEntities = session.createQuery("FROM PendingRequestEntity AS E WHERE E.requestType.id = :typeId", PendingRequestEntity.class)
                    .setParameter("typeId", typeId).getResultList();
            tx.commit();
        }catch(HibernateException e){
            tx.rollback();
            dLog.error(e.getMessage(), e);
        }
        return pendingRequestEntities;
    }

    @Override
    public List<PendingRequestEntity> getPendingRequestByStatus(boolean status) {
        List<PendingRequestEntity> pendingRequestEntities = null;
        Session session;
        Transaction tx = null;
        try{
            session = HibernateSessionFactory.getSession();
            tx = session.beginTransaction();
            pendingRequestEntities = session.createQuery("FROM PendingRequestEntity AS E WHERE E.status = :status", PendingRequestEntity.class)
                    .setParameter("status", status).getResultList();
            tx.commit();
        }catch(HibernateException e){
            tx.rollback();
            dLog.error(e.getMessage(), e);
        }
        return pendingRequestEntities;    }

    public static void main(String[] args) {
//        "PendingRequestEntity":{"id":"0",
//                "employeeAccount":{"EmployeeAccountEntity":{"id":"3", "firstName":"Gwyndolyn", "lastName":"Braveheart",
//                "employeeRole":{"EmployeeRoleEntity":{"id":"1", "roleName":"Knight"}}}},
//            "requestType":{"RequestTypeEntity":{"id":"1",
//                    "requestType":"Travel"}},
//            "requestMessage":"Las Vegas baby",
//                    "amount":"0.05",
//                    "dateSubmission":2022-01-11,
//                    "status":"false"


    }
    @Override
    public Integer insertPendingRequest(PendingRequestEntity pendingRequestEntity) {
        dLog.debug("inserting pending request entity: " + pendingRequestEntity);
        System.out.println(pendingRequestEntity);
        Session session;
        Transaction tx = null;
        Integer savedId = 0;
        try{
            session = HibernateSessionFactory.getSession();
            tx = session.beginTransaction();
            savedId = (Integer) session.save(pendingRequestEntity);
            tx.commit();
        }catch(HibernateException e){
            tx.rollback();
            dLog.error(e.getMessage(), e);
        }
         if(savedId != 0) return savedId;
         else return -1;
    }

    @Override
    public PendingRequestEntity getPendingRequestByRequestId(int requestId) {
        dLog.debug("getting pending request by ID: " + requestId);
        Session session;
        Transaction tx = null;
        PendingRequestEntity returnPendingRequest = null;
        try{
            session = HibernateSessionFactory.getSession();
            tx = session.beginTransaction();
            returnPendingRequest = session.get(PendingRequestEntity.class, requestId);
            tx.commit();
        }catch(HibernateException e){
            tx.rollback();
            dLog.error(e.getMessage(), e);
        }
        return returnPendingRequest;
    }

    @Override
    public List<PendingRequestEntity> getEmployeesPendingRequestList(int employeeId) {
        List<PendingRequestEntity> pendingRequestEntities = null;
        Session session;
        Transaction tx = null;
        try{
            session = HibernateSessionFactory.getSession();
            tx = session.beginTransaction();
            pendingRequestEntities = session.createQuery("FROM PendingRequestEntity AS E WHERE E.employeeAccount.id = :employeeId", PendingRequestEntity.class)
                    .setParameter("employeeId", employeeId).getResultList();
            tx.commit();
        }catch(HibernateException e){
            tx.rollback();
            dLog.error(e.getMessage(), e);
        }
        return pendingRequestEntities;    }

    @Override
    public List<PendingRequestEntity> getAllPendingRequests() {
        List<PendingRequestEntity> pendingRequestEntities = null;
        Session session;
        Transaction tx = null;
        try{
            session = HibernateSessionFactory.getSession();
            tx = session.beginTransaction();
            pendingRequestEntities = session.createQuery("FROM PendingRequestEntity", PendingRequestEntity.class).getResultList();
            tx.commit();
        }catch(HibernateException e){
            tx.rollback();
            dLog.error(e.getMessage(), e);
        }
        return pendingRequestEntities;       }

    @Override
    public void updatePendingRequestStatus(PendingRequestEntity pendingRequestEntity) {
        Session session;
        Transaction tx = null;
        try{
            session = HibernateSessionFactory.getSession();
            tx = session.beginTransaction();
            session.saveOrUpdate(pendingRequestEntity);
            tx.commit();
        }catch(HibernateException e){
            tx.rollback();
            dLog.error(e.getMessage(), e);
        }
    }

    @Override
    public Integer updatePendingRequestMessage(PendingRequestEntity pendingRequestEntity) {
        Session session;
        Transaction tx = null;
        int savedId = 0;
        try{
            session = HibernateSessionFactory.getSession();
            tx = session.beginTransaction();
            savedId = (Integer) session.save(pendingRequestEntity);
            tx.commit();
        }catch(HibernateException e){
            tx.rollback();
            dLog.error(e.getMessage(), e);
        }
        return savedId;       }

    @Override
    public Integer updatePendingRequestAmount(PendingRequestEntity pendingRequestEntity) {
        Session session;
        Transaction tx = null;
        int savedId = 0;
        try{
            session = HibernateSessionFactory.getSession();
            tx = session.beginTransaction();
            savedId = (Integer) session.save(pendingRequestEntity);
            tx.commit();
        }catch(HibernateException e){
            tx.rollback();
            dLog.error(e.getMessage(), e);
        }
        return savedId;       }

    @Override
    public void deletePendingRequest(PendingRequestEntity pendingRequestEntity) {
        Session session;
        Transaction tx = null;
        try{
            session = HibernateSessionFactory.getSession();
            tx = session.beginTransaction();
            session.delete(pendingRequestEntity);
            tx.commit();
        }catch(HibernateException e){
            tx.rollback();
            dLog.error(e.getMessage(), e);
        }
    }

    @Override
    public List<PendingRequestEntity> getAnsweredEmployeePendingRequests(int employeeId) {
        List<PendingRequestEntity> pendingRequestEntities = null;
        Session session;
        Transaction tx = null;
        try{
            session = HibernateSessionFactory.getSession();
            tx = session.beginTransaction();
            pendingRequestEntities = session.createQuery("FROM PendingRequestEntity AS E WHERE E.employeeAccount.id = :employeeId AND E.status = true", PendingRequestEntity.class)
                    .setParameter("employeeId", employeeId).getResultList();
            tx.commit();
        }catch(HibernateException e){
            tx.rollback();
            dLog.error(e.getMessage(), e);
        }
        return pendingRequestEntities;        }

    @Override
    public List<PendingRequestEntity> getAnsweredPendingRequestsByType(int typeId) {
        List<PendingRequestEntity> pendingRequestEntities = null;
        Session session;
        Transaction tx = null;
        try{
            session = HibernateSessionFactory.getSession();
            tx = session.beginTransaction();
            pendingRequestEntities = session.createQuery("FROM PendingRequestEntity AS E WHERE E.requestType.id = :typeId AND E.status = true", PendingRequestEntity.class)
                    .setParameter("typeId", typeId).getResultList();
            tx.commit();
        }catch(HibernateException e){
            tx.rollback();
            dLog.error(e.getMessage(), e);
        }
        return pendingRequestEntities;
    }
}
