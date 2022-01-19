package daolayer;

import dbutil.HibernateSessionFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import Driver.serviceUtil.ReimbursementBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DAOQueries {

    Logger exceptionLogger = LoggerFactory.getLogger("EXCEPTIONS");
    ReimbursementBuilder builder = new ReimbursementBuilder();

    //SELECT
    public List<UserSpecs> validateUser(UserSpecs specs) {
      Session session = null;
      Transaction transaction = null;
      List<UserSpecs> specsList = null;
      try {
          session = HibernateSessionFactory.getSession();
          transaction = session.beginTransaction();

          specsList = session.createQuery("FROM UserSpecs s WHERE s.userLogin = :login AND s.userPass = :pass"
                          ,UserSpecs.class).
                  setParameter("login",
                          specs.getUserLogin()).setParameter("pass",specs.getUserPass()).getResultList();
          transaction.commit();

      }catch (HibernateException e){
          transaction.rollback();
          e.printStackTrace();
      }
        return specsList;
    }


//
//    public List<JoinedList> returnMasterList(){
//        final String SQL = "select * from users right join reimbursements on user_login = submitted_by";
//        Connection connection = null;
//        PreparedStatement statement = null;
//        ResultSet set = null;
//        List <JoinedList> joinedList = new ArrayList<>();
//        try {
//            connection = OpenDB.getConnection();
//            statement = connection.prepareStatement(SQL);
//            set = statement.executeQuery();
//            while (set.next()) {
//                joinedList.add( new JoinedList(set.getInt(1),
//                set.getString(2),
//                set.getString(3),
//                set.getString(4),
//                set.getBoolean(6),
//                set.getString(7),
//                set.getDate(9),
//                set.getInt(10),
//                set.getString(11),
//                set.getString(12)));
//
//            }
//        }catch (SQLException e){
//            exceptionLogger.debug(e.getMessage(),e);
//        }finally {
//            CloseDB.connectionCloser(connection);
//            CloseDB.statementCloser(statement);
//            CloseDB.setCloser(set);
//        }
//        return joinedList;

//    }
    public List<UserSpecs> returnAllUsers(){
        List<UserSpecs> reimbursementsList = null;
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateSessionFactory.getSession();
            transaction = session.beginTransaction();
            reimbursementsList = session.createQuery("FROM UserSpecs" , UserSpecs.class).getResultList();
            transaction.commit();
        }catch (HibernateException e){
            transaction.rollback();
            e.printStackTrace();
        }
        return reimbursementsList;
    }

    public List<Reimbursements> returnRequests() {
      List<Reimbursements> reimbursementsList = null;
      Session session = null;
      Transaction transaction = null;
      try{
          session = HibernateSessionFactory.getSession();
          transaction = session.beginTransaction();
          reimbursementsList = session.createQuery("FROM Reimbursements",Reimbursements.class).getResultList();
//          session.createNativeQuery("select * from reimbursements", Reimbursements.class).getResultList();
          transaction.commit();

      }catch (HibernateException e){
          transaction.rollback();

          e.printStackTrace();
      }
      return  reimbursementsList;
        }
        public List<Long> returnSum (String name) {
            List<Long> reimbursementsList = null;
            Reimbursements reimbursements = new Reimbursements();
            Session session = null;
            Transaction transaction = null;
            try {
            session = HibernateSessionFactory.getSession();
            transaction = session.beginTransaction();
            reimbursementsList = session.createQuery
                    ("select SUM(r.requestAmount) FROM Reimbursements r where r.submittedBy = :login", Long.class)
                    .setParameter("login", name).getResultList();
            transaction.commit();
            }catch (HibernateException e){
                transaction.rollback();
                e.printStackTrace();
            }
            return  reimbursementsList;
        }
    public List<Long> returnRequestNum (String name) {
        List<Long> reimbursementsList = null;
        Reimbursements reimbursements = new Reimbursements();
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateSessionFactory.getSession();
            transaction = session.beginTransaction();
            reimbursementsList = session.createQuery
                    ("select COUNT(*) from Reimbursements r where r.submittedBy = :login", Long.class)
                            .setParameter("login",name).getResultList();
            transaction.commit();
        }catch (HibernateException e){
            transaction.rollback();
            e.printStackTrace();
        }
        return  reimbursementsList;
    }
    public Double returnMean (){
        Session session = null;
        Transaction transaction = null;
        Double num = null;
        try {
            session = HibernateSessionFactory.getSession();
            transaction = session.beginTransaction();
             num = session.createQuery
                    ("select AVG(r.requestAmount) FROM Reimbursements r",Double.class).getSingleResult();
            transaction.commit();
        }catch (HibernateException e){
            transaction.rollback();
            e.printStackTrace();

        }
        return num;
    }
    public List<Reimbursements> returnRequestsByLogin(Reimbursements reimbursements) {
        Session session = null;
        Transaction transaction = null;
        List<Reimbursements> reimbursementsList = null;
        try {
            session = HibernateSessionFactory.getSession();
            transaction = session.beginTransaction();
            reimbursementsList = session.createQuery("FROM Reimbursements WHERE submittedBy = ?0",
                    Reimbursements.class).setParameter(0, reimbursements.getSubmittedBy()).getResultList();
        transaction.commit();
        }catch (HibernateException e){
            transaction.rollback();
            e.printStackTrace();
        }
        return reimbursementsList;
    }
    //END OF SELECTS//

    //INSERT
    public void newRequest(Reimbursements reimbursements) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateSessionFactory.getSession();
            transaction = session.beginTransaction();
            session.save(reimbursements);
            transaction.commit();
        }catch (HibernateException e){
            transaction.rollback();
            e.printStackTrace();
        }
    }
        //END OF INSERTS

        //UPDATE
        public void updateRequest (Reimbursements reimbursements){
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateSessionFactory.getSession();
            transaction = session.beginTransaction();
//            CriteriaBuilder cb = session.getCriteriaBuilder();
//            CriteriaQuery<Reimbursements> cq = cb.createQuery(Reimbursements.class);
//            Root<Reimbursements> root = cq.from(Reimbursements.class);
//            cq.select(root).where(cb.equal(root.get("")))
            session.createQuery("update Reimbursements r set r.status = :status WHERE r.requestID = :request")
                    .setParameter("status",reimbursements.getStatus()).setParameter("request",reimbursements.getRequestID()).executeUpdate();
            transaction.commit();
        }catch (HibernateException e){
            transaction.rollback();
            e.printStackTrace();
        }
        }
        //END OF UPDATES

        //DELETE
        public void deleteRequest (Reimbursements reimbursements){
           Session session = null;
           Transaction transaction = null;
           try {
            session = HibernateSessionFactory.getSession();
            transaction = session.beginTransaction();
            session.delete(reimbursements);
            transaction.commit();
           }catch (HibernateException e){
               transaction.rollback();
               e.printStackTrace();
           }
        }
        //END OF DELETES
//PIPE DREAM//
        public List<String> returnLoginsForCalc(){
        Session session = null;
        Transaction transaction = null;
        List<String> loginList = null;
        try {
            session = HibernateSessionFactory.getSession();
            transaction = session.beginTransaction();
            loginList = session.createQuery
                    ("SELECT DISTINCT r.submittedBy from Reimbursements r",String.class).getResultList();
            transaction.commit();
        }catch (HibernateException e ){
            transaction.rollback();
            e.printStackTrace();
        }
        return loginList;
        }
}