package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EasyConnect {
	
	protected Connection getConnection() throws SQLException {
		Connection conn =  DriverManager.getConnection(
				System.getenv("db_url"), 
				System.getenv("db_user"),
				System.getenv("db_password")
				);
		conn.setSchema("p1");
		
		return conn;
	}

	
	protected void closeRead(Connection conn, Statement stmt, ResultSet set) {
		closeConnection(conn);
		closeStatement(stmt);
		closeResultSet(set);
	}
	
	protected void closeWrite(Connection conn, Statement stmt) {
		closeConnection(conn);
		closeStatement(stmt);
	}
	
	private void closeConnection(Connection conn) {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void closeStatement(Statement stmt) {
		try {
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void closeResultSet(ResultSet set) {
//		try {
//			set.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
	}
	
}
