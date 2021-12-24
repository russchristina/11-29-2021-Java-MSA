package com.revature.project.util;

import java.sql.Connection;
import java.sql.SQLException;

public class CloseDB {
    public static void closeConnection(Connection connection){
      try{
          connection.close();
      }catch (SQLException e){

      }
    }
}
