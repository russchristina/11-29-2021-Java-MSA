package com.revature.repository.DAOClasses;

import com.revature.repository.DAOInteface.LoginInfoInterface;
import com.revature.repository.DTO.LoginInfoEntity;

import com.revature.repository.utility.HibernateSessionFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.NoResultException;

public class LoginInfoDao implements LoginInfoInterface {

    private final Logger dLog = LoggerFactory.getLogger("dLog");

    @Override
    public LoginInfoEntity getLoginInfo(LoginInfoEntity loginInfo) {
        dLog.debug("Retrieving LoginInfoEntity from database: " + loginInfo);
        LoginInfoEntity returnLoginInfo = null;
        Transaction tx = null;
        try(
                Session session = HibernateSessionFactory.getSession();
                ){
            tx = session.beginTransaction();
            returnLoginInfo = session.createQuery("FROM LoginInfoEntity AS L WHERE L.username = :username AND L.password = :password", LoginInfoEntity.class)
                    .setParameter("username", loginInfo.getUsername()).setParameter("password", loginInfo.getPassword()).getSingleResult();
            tx.commit();
        }catch(HibernateException | NoResultException e){
            if(tx != null)
                if(!tx.isActive()) tx.rollback();
            dLog.error(e.getMessage(), e);
        }
        if(returnLoginInfo != null) dLog.debug("Successful retrieval of LoginInfoEntity from Database: " + returnLoginInfo);
        else dLog.debug("Failed retrieval of LoginInfoEntity from Database: " + loginInfo);
        return returnLoginInfo;
    }

}
