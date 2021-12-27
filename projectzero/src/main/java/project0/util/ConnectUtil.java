package project0.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectUtil {

	 public static Connection getConnection() throws SQLException {
			
			return DriverManager.getConnection("jdbc:postgresql://lvdatabase.chxg5v0vyn1y.us-east-2.rds.amazonaws.com:5432/lakeview", "merchant", "He1j4rch3n"
//					System.getenv("lv_url"),
//					System.getenv("lv_username"),
//					System.getenv("lv_password")
					);
		}
}
