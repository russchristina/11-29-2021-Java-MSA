package com.revature.repository.DAOClasses;

import com.revature.repository.DAOInteface.EmployeeRoleInterface;
import com.revature.repository.DTO.EmployeeAccountEntity;
import com.revature.repository.DTO.EmployeeRoleEntity;
import com.revature.repository.DTO.LoginInfoEntity;
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
        List<EmployeeRoleEntity> employeeRoleEntities = null;
        Session session;
        Transaction tx = null;
        try{
            session = HibernateSessionFactory.getSession();
            tx = session.beginTransaction();
            employeeRoleEntities = session.createQuery("FROM EmployeeRoleEntity", EmployeeRoleEntity.class).getResultList();
            tx.commit();
        }catch(HibernateException e){
            tx.rollback();
            e.printStackTrace();
        }
        return employeeRoleEntities;
    }

    @Override
    public EmployeeRoleEntity getEmployeeRoleById(int roleId) {
        Session session;
        Transaction tx = null;
        EmployeeRoleEntity returnRoleEntity = null;
        try{
            session = HibernateSessionFactory.getSession();
            tx = session.beginTransaction();
            returnRoleEntity = session.get(EmployeeRoleEntity.class, roleId);
            tx.commit();
        }catch(HibernateException e){
            tx.rollback();
            dLog.error(e.getMessage(), e);
        }
        return returnRoleEntity;
    }

    @Override
    public EmployeeRoleEntity getEmployeeRoleByName(String roleName) {
        Session session;
        Transaction tx = null;
        EmployeeRoleEntity returnRoleEntity = null;
        try{
            session = HibernateSessionFactory.getSession();
            tx = session.beginTransaction();
            returnRoleEntity = session.get(EmployeeRoleEntity.class, roleName);
            tx.commit();
        }catch(HibernateException e){
            tx.rollback();
            dLog.error(e.getMessage(), e);
        }
        return returnRoleEntity;
    }

    @Override
    public Integer updateRole(EmployeeRoleEntity employeeRoleEntity) {
        Session session;
        Transaction tx = null;
        int savedId = 0;
        try{
            session = HibernateSessionFactory.getSession();
            tx = session.beginTransaction();
            savedId = (Integer) session.save(employeeRoleEntity);
            tx.commit();
        }catch(HibernateException e){
            tx.rollback();
            dLog.error(e.getMessage(), e);
        }
        return savedId;
    }
}
