package dbutil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CloseDB {
    public static void connectionCloser(Connection connection) {
        try{
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public static void statementCloser(Statement statement) {
        try{
            statement.close();
        }catch (SQLException e){
            e.printStackTrace();
        }    }
    public static void setCloser(ResultSet set) {
        try{
            set.close();
        }catch (SQLException e){

            e.printStackTrace();
        }
    }
}
