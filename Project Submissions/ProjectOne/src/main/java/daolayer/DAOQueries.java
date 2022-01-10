package daolayer;

import dbutil.CloseDB;
import dbutil.OpenDB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import serviceUtil.ReimbursementBuilder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOQueries {
    //    public UserSpecs checkLogin(){
//        final String SQL = "select * from users";
//        Connection connection = null;
//        PreparedStatement statement = null;
//        ResultSet set = null;
//        try {
//            connection = OpenDB.getConnection();
//            statement = connection.prepareStatement(SQL);
//            set = statement.executeQuery();
//            while (set.next()){
//                new UserSpecs(
//                        set.getInt(1),
//                        set.getString(2),
//                        set.getString(3),
//                        set.getString(4),
//                        set.getString(5),
//                        set.getBoolean(6));
//            }
//
//        }catch (SQLException e){
//            System.out.println("Error in checkLogin");
//            e.printStackTrace();
//        }finally {
//            CloseDB.connectionCloser(connection);
//            CloseDB.statementCloser(statement);
//            CloseDB.setCloser(set);
//        }
//        return specs;
//    }
    Logger exceptionLogger = LoggerFactory.getLogger("EXCEPTIONS");
    ReimbursementBuilder builder = new ReimbursementBuilder();

    //SELECT
    public UserSpecs validateUser(UserSpecs specs) {
        final String SQL = "select * from users where user_login = ? and user_pass = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;
        UserSpecs userSpecs = null;
        try {
            connection = OpenDB.getConnection();
            statement = connection.prepareStatement(SQL);
            statement.setString(1, specs.getUserLogin());
            statement.setString(2, specs.getUserPass());
            set = statement.executeQuery();

            if (set.next()) {
                userSpecs = new UserSpecs(
                        set.getInt(1),
                        set.getString(2),
                        set.getString(3),
                        set.getString(4),
                        set.getString(5),
                        set.getBoolean(6));
            }
        } catch (SQLException e) {
        e.printStackTrace();
            exceptionLogger.debug(e.getMessage(), e);
        } finally {
            CloseDB.setCloser(set);
            CloseDB.statementCloser(statement);
            CloseDB.connectionCloser(connection);
        }
        return userSpecs;
    }

    //If you care for your eyesight, do not open this section

//    public void returnJoinedList() {
//        final String SQL = "select * from reimbursements left join users on user_id = submitted_by";
//        Connection connection = null;
//        PreparedStatement statement = null;
//        ResultSet set = null;
//        List<String> returnedList = null;
//        try {
//            connection = OpenDB.getConnection();
//            statement = connection.prepareStatement(SQL);
//            set = statement.executeQuery();
//
//            while (set.next()) {
//                String requestID = set.getString(1);
//                String submittedBy = set.getString(2);
//                Date submittedDate = set.getDate(3);
//                int requestAmount = set.getInt(4);
//                String submissionReason = set.getString(5);
//                String currentStatus = set.getString(6);
//                int userID = set.getInt(7);
//                String firstName = set.getString(8);
//                String lastName = set.getString(9);
//                boolean isManager = set.getBoolean(12);
//                System.out.println(" Current Status of Request: " + currentStatus.toUpperCase()
//                        + " |Request ID: " + requestID + "| Submitted by User ID # " + submittedBy +
//                        ", " + firstName + " " + lastName + ". Manger: " + isManager + "| Date of submission: " +
//                        submittedDate + "| Requsted Amount: $" +
//                        String.format("%,d", requestAmount) +
//                        "| Reason for Request: " + submissionReason);
//
//
//            }
//        } catch (SQLException e) {
//            exceptionLogger.debug(e.getMessage(), e);
//        } finally {
//            CloseDB.connectionCloser(connection);
//            CloseDB.statementCloser(statement);
//
//        }
//
//    }

    public List<JoinedList> returnMasterList(){
        final String SQL = "select * from users right join reimbursements on user_login = submitted_by";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;
        List <JoinedList> joinedList = new ArrayList<>();
        try {
            connection = OpenDB.getConnection();
            statement = connection.prepareStatement(SQL);
            set = statement.executeQuery();
            while (set.next()) {
                joinedList.add( new JoinedList(set.getInt(1),
                set.getString(2),
                set.getString(3),
                set.getString(4),
                set.getBoolean(6),
                set.getString(7),
                set.getDate(9),
                set.getInt(10),
                set.getString(11),
                set.getString(12)));

            }
        }catch (SQLException e){
            exceptionLogger.debug(e.getMessage(),e);
        }finally {
            CloseDB.connectionCloser(connection);
            CloseDB.statementCloser(statement);
            CloseDB.setCloser(set);
        }
        return joinedList;

    }
    public List<Reimbursements> returnRequests() {
        final String SQL = "select * from reimbursements";
        Connection connection = null;
        Statement statement = null;
        ResultSet set = null;
        List<Reimbursements> reimbursementsList = new ArrayList<>();
        try {
            connection = OpenDB.getConnection();
            statement = connection.createStatement();
            set = statement.executeQuery(SQL);
            while (set.next()) {
                reimbursementsList.add(new Reimbursements(
                        set.getString(1),
                        set.getString(2),
                        set.getDate(3),
                        set.getInt(4),
                        set.getString(5),
                        set.getString(6)));
            }

        } catch (SQLException e) {
            exceptionLogger.debug(e.getMessage(), e);
        } finally {
            CloseDB.connectionCloser(connection);
            CloseDB.statementCloser(statement);
            CloseDB.setCloser(set);
        }
        return reimbursementsList;
    }
    public List<Reimbursements> returnRequestsByLogin(Reimbursements reimbursements) {
        final String SQL = "select * from reimbursements where submitted_by = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;
        List<Reimbursements> reimbursementsList = new ArrayList<>();
        try {
            connection = OpenDB.getConnection();
            statement = connection.prepareStatement(SQL);
            statement.setString(1, reimbursements.getSubmittedBy() );
            set = statement.executeQuery();
            while (set.next()) {
                reimbursementsList.add(new Reimbursements(
                        set.getString(1),
                        set.getString(2),
                        set.getDate(3),
                        set.getInt(4),
                        set.getString(5),
                        set.getString(6)));
            }

        } catch (SQLException e) {
            exceptionLogger.debug(e.getMessage(), e);
        } finally {
            CloseDB.connectionCloser(connection);
            CloseDB.statementCloser(statement);
            CloseDB.setCloser(set);
        }
        return reimbursementsList;
    }
    //END OF SELECTS//

    //INSERT
    public void newRequest(Reimbursements reimbursements) {
        final String SQL = "insert into reimbursements values (?,?,?,?,?,?)";
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = OpenDB.getConnection();
            statement = connection.prepareStatement(SQL);
            statement.setString(1, reimbursements.getRequestID());
            statement.setString(2, reimbursements.getSubmittedBy());
            statement.setDate(3, reimbursements.getSubmittedDate());
            statement.setInt(4, reimbursements.getRequestAmount());
            statement.setString(5, reimbursements.getReason());
            statement.setString(6, reimbursements.getStatus());
            statement.execute();
        } catch (SQLException e) {
            exceptionLogger.debug(e.getMessage(), e);

        } finally {
            CloseDB.connectionCloser(connection);
            CloseDB.statementCloser(statement);
        }
    }
        //END OF INSERTS

        //UPDATE
        public void updateRequest (Reimbursements reimbursements){
            final String SQL = "update reimbursements set current_status = ? where request_id = ?";
            Connection connection = null;
            PreparedStatement statement = null;
            try {
                connection = OpenDB.getConnection();
                statement = connection.prepareStatement(SQL);
                statement.setString(1, reimbursements.getStatus());
                statement.setString(2, reimbursements.getRequestID());
                statement.execute();
            } catch (SQLException e) {
                exceptionLogger.debug(e.getMessage(), e);
            } finally {
                CloseDB.connectionCloser(connection);
                CloseDB.statementCloser(statement);
            }
        }
        //END OF UPDATES

        //DELETE
        public void deleteRequest (Reimbursements reimbursements){
            final String SQL = "delete from reimbursements where request_id = ?";
            Connection connection = null;
            PreparedStatement statement = null;
            try {
                connection = OpenDB.getConnection();
                statement = connection.prepareStatement(SQL);
                statement.setString(1, reimbursements.getRequestID());
                statement.execute();
            } catch (SQLException e) {
                exceptionLogger.debug(e.getMessage(), e);
            }
        }
        //END OF DELETES

}