package Com.revature;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.DriverPropertyInfo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Logger;
public class Driver implements java.sql.Driver {

	static final String Jdbc_Driver ="org.postgresql.Driver";
	static final String DB_URL = "Jdbc:postgresql://database-3.clzirnqm0xdy.us-east-2.rds.amazonaws.com:5432/postgresql";
	static final String USER = "postgres";
	static final String Pass = "Rammstein1";
	 static final String QUERY = "UPDATE workID, firstName, City, Task FROM Workers";
	
	public static void main(String[] args)  {
		
			Connection conn = null;
		Statement stmt = null;
		
		
		try { System.out.println("Connecting to a database");
		conn = DriverManager.getConnection(DB_URL, USER, Pass);
		
		//execute 
		
	         stmt = conn.createStatement();
              ResultSet rs = stmt.executeQuery(QUERY);
              while(rs.next()){
                 //Display values
                 System.out.print("1245 " + rs.getInt("WorkID"));
                 System.out.print(", Tevon " + rs.getInt("firstname"));
                 System.out.print(", Alton " + rs.getString("city"));
                 System.out.println(", Manager " + rs.getString("task"));
              }
              rs.close();
		}
	          catch(Exception e)
	          {
	        	  e.printStackTrace();
	        	  System.out.println("got ya");
	          }
		
		// TODO Auto-generated method stub
		
		finally{
			
		
		}
	}

	@Override
	public Connection connect(String url, Properties info) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean acceptsURL(String url) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getMajorVersion() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMinorVersion() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean jdbcCompliant() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}
		
	}

