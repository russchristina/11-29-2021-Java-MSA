package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.revature.model.Employee;
import com.revature.util.ConnectionClosers;
import com.revature.util.ConnectionFactory;

//public class EmployeeDaoImpl implements EmployeeDao {

//	@Override
//	public int createEmployee(Employee employee) {
//		final String SQL = "insert into Employee values(default, ?, ?)";
//		Connection conn = null;
//		PreparedStatement stmt = null;
//		
//		try {
//			conn = ConnectionFactory.getConnection();
//			/*
//			 * The values of the parameters in a PreparedStatement are supplied later after
//			 * the statement has been compiled.
//			 */
//			stmt = conn.prepareStatement(SQL);
//			stmt.setString(1, Employee.geteUserName());
//			stmt.setInt(2, Employee.getePassword());
//			stmt.execute();
//		}catch(SQLException e) {
//			e.printStackTrace();
//		}finally {
//			ConnectionClosers.closeConnection(conn);
//			ConnectionClosers.closeStatement(stmt);
//		}
//		
//	}
//
//	}

//	@Override
//	public int updateEmployee(Employee employee) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public int deleteEmployeeById(int id) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public void deposit(int EmployeeId, double amount) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void withdraw(int EmployeeId, double amount) {
//		// TODO Auto-generated method stub
//		
//	}
//
//}