package project0.junit;

import java.sql.Connection;

public class ConnectTest {

	Connection connection;
	
	@BeforeAll
	public void testConnect() {
		connection = DriverManager.getConnection()
	}
}
