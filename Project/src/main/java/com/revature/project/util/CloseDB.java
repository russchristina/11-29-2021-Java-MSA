package com.revature.project.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CloseDB {

    public static void closeConnection(Connection connection){
      try{
          connection.close();
      }catch (SQLException e){

      }
    }
    public static void closeResultSet(ResultSet set){
        try{
            set.close();
        }catch (SQLException e){

        }
    }
    public static void closeStatement(Statement statement){
        try {
            statement.close();
        }catch (SQLException e){

        }
    }
}
