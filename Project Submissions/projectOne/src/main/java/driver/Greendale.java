package driver; 

import present.login.LoginControl;
import present.login.LoginInput;
import repo.UserLoginDAO;
import service.UserLoginService;

public class Greendale {
	
	public static void main(String[] args) {
		//System.out.println(findAll());
	
		LoginControl loginControl = new LoginControl(new UserLoginService(new UserLoginDAO()));
		System.out.println(loginControl.authenticateLogin(new LoginInput("gooddean", "dalmat1an")));
		
	}
	
//public static List<Employee> findAll(){
//		
//		List<Employee> employees = new ArrayList<>();
//		
//		final String SQL = "select * from Employee";
//		Connection connection = null;
//		Statement stmt = null;
//		ResultSet set = null;
//		
//		try {
//			connection = ConnectUtil.getConnection();
//			stmt = connection.createStatement();
//			set = stmt.executeQuery(SQL);
//			while(set.next()) {
//				employees.add(
//						new Employee(set.getInt(1), 
//								set.getString(2), 
//								set.getString(3), 
//								set.getString(4),
//								set.getString(5), 
//								set.getString(6), 
//								set.getString(7))
//						);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//			System.out.println("No Employees Found");
//		}finally {
//			ConnectClose.closeConnection(connection);
//			ConnectClose.closeStatement(stmt);
//			ConnectClose.closeResultSet(set);
//		}
//			return employees;
//	}
	

	
}
