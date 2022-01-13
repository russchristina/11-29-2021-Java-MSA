package com.revature.repository.DAOClasses;

import com.revature.repository.DAOInteface.EmployeeRoleInterface;
import com.revature.repository.DTO.EmployeeRoleEntity;
import com.revature.repository.utility.HibernateSessionFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class EmployeeRoleDao implements EmployeeRoleInterface {
    private final Logger dLog = LoggerFactory.getLogger("dLog");

    @Override
    public List<EmployeeRoleEntity> getAllEmployeeRoles() {
        dLog.debug("Getting all EmployeeRoleEntity's from database");
        List<EmployeeRoleEntity> employeeRoleEntities = null;
        Transaction tx = null;
        try(
                Session session = HibernateSessionFactory.getSession();
                ){
            tx = session.beginTransaction();
            employeeRoleEntities = session.createQuery("FROM EmployeeRoleEntity", EmployeeRoleEntity.class).getResultList();
            tx.commit();
        }catch(HibernateException e){
            if(tx != null)
                if(!tx.isActive()) tx.rollback();
            dLog.error(e.getMessage(), e);
        }
        if(employeeRoleEntities != null) dLog.debug("Successful retrieval of all EmployeeRoleEntities from Database");
        else dLog.debug("Failed to retrieve all EmployeeRoleEntities from database");
        return employeeRoleEntities;
    }

    @Override
    public EmployeeRoleEntity getEmployeeRoleById(EmployeeRoleEntity employeeRole) {
        dLog.debug("Getting EmployeeRoleEntity from database: " + employeeRole);
        Transaction tx = null;
        EmployeeRoleEntity returnRoleEntity = null;
        try(
                Session session = HibernateSessionFactory.getSession();
                ){
            tx = session.beginTransaction();
            returnRoleEntity = session.get(EmployeeRoleEntity.class, employeeRole.getId());
            tx.commit();
        }catch(HibernateException e){
            if(tx != null)
                if(!tx.isActive()) tx.rollback();
            dLog.error(e.getMessage(), e);
        }
        if(returnRoleEntity != null) dLog.debug("Successful retrieval of EmployeeRoleEntity from Database ID - " + employeeRole + " " + returnRoleEntity);
        else dLog.debug("Failed to retrieve EmployeeRoleEntity from database: " + employeeRole);
        return returnRoleEntity;
    }

}
