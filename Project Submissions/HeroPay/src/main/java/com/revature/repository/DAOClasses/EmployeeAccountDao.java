package com.revature.repository.DAOClasses;

import com.revature.repository.DAOInteface.EmployeeAccountInterface;
import com.revature.repository.DTO.EmployeeAccountEntity;
import com.revature.repository.DTO.EmployeeRoleEntity;
import com.revature.repository.utility.HibernateSessionFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class EmployeeAccountDao implements EmployeeAccountInterface {

    private final Logger dLog = LoggerFactory.getLogger(LoginInfoDao.class);

    @Override
    public EmployeeAccountEntity getEmployeeAccount(EmployeeAccountEntity employeeAccount) {
        dLog.debug("Getting EmployeeAccountEntity from Database: " + employeeAccount);
        Transaction tx = null;
        EmployeeAccountEntity returnAccountEntity = null;
        try(
                Session session = HibernateSessionFactory.getSession();
                ){
            tx = session.beginTransaction();
            returnAccountEntity = session.get(EmployeeAccountEntity.class, employeeAccount.getId());
            tx.commit();
        }catch(HibernateException e){
            if(tx != null)
                if(!tx.isActive()) tx.rollback();
            dLog.error(e.getMessage(), e);
        }
        if(returnAccountEntity != null) dLog.debug("Successful retrieval of EmployeeAccountEntity from database: " + returnAccountEntity);
        else dLog.debug("Failed retrieval of EmployeeAccountEntity: " + employeeAccount);
        return returnAccountEntity;
    }

    @Override
    public List<EmployeeAccountEntity> getAllEmployeeAccountList() {
        dLog.debug("Getting all EmployeeAccountEntity's from Database");
        List<EmployeeAccountEntity> employeeAccountEntities = null;
        Transaction tx = null;
        try(
                Session session = HibernateSessionFactory.getSession();
                ){
            tx = session.beginTransaction();
            employeeAccountEntities = session.createQuery("FROM EmployeeAccountEntity", EmployeeAccountEntity.class).getResultList();
            tx.commit();
        }catch(HibernateException e){
            if(tx != null)
                if(!tx.isActive()) tx.rollback();
            dLog.error(e.getMessage(), e);
        }
        if(employeeAccountEntities != null) dLog.debug("Successful retrieval of all EmployeeAccountEntity's from database: " + employeeAccountEntities);
        else dLog.debug("Failed retrieval of all EmployeeAccountEntity's from database");
        return employeeAccountEntities;
    }

    @Override
    public List<EmployeeAccountEntity> getEmployeeAccountsByRoleId(EmployeeRoleEntity employeeRole) {
        dLog.debug("Getting All EmployeeAccountEntity's associated with EmployeeRole from database: " + employeeRole);
        List<EmployeeAccountEntity> employeeAccountEntities = null;
        Transaction tx = null;
        try(
                Session session = HibernateSessionFactory.getSession();
                ){
            tx = session.beginTransaction();
            employeeAccountEntities = session.createQuery("FROM EmployeeAccountEntity AS E WHERE E.employeeRole.id = :roleId", EmployeeAccountEntity.class)
                    .setParameter("roleId", employeeRole.getId()).getResultList();
            tx.commit();
        }catch(HibernateException e){
            if(tx != null)
                if(!tx.isActive()) tx.rollback();
            dLog.error(e.getMessage(), e);
        }
        if(employeeAccountEntities != null) dLog.debug("Successful retrieval of EmployeeAccountEntity's from database by employeeRole - " + employeeRole + " " + employeeAccountEntities);
        else dLog.debug("Failed to retrieve EmployeeAccountEntities by employeeRole from Database: " + employeeRole);
        return employeeAccountEntities;
    }

}
