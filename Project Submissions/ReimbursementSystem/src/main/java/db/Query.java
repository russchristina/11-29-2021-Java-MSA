package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.Account;
import models.RepayForm;
import util.EasyConnect;

public class Query extends EasyConnect implements AccountRepository, FormRepository{

	@Override
	public ArrayList<Integer> selectRequestsById(int empId) {
		String SQL = "select emp_id from request r where r.emp_id = ?";
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet set = null;
		ArrayList<Integer> formIds = new ArrayList<>();
				
		try {
			conn = this.getConnection();
			stmt = conn.prepareStatement(SQL);
			stmt.setInt(1, empId);
			set = stmt.executeQuery();
			
			while(set.next()) {
				formIds.add(set.getInt(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.closeRead(conn, stmt, set);
		}
		
		return formIds;
	}

	@Override
	public ArrayList<RepayForm> selectAllNullRequests() {
		String SQL = "select * from request where approval = null";
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet set = null;
		ArrayList<RepayForm> nullRequests = new ArrayList<>();
		
		try {
			conn = this.getConnection();
			stmt = conn.prepareStatement(SQL);
			set = stmt.executeQuery();
			
			while(set.next()) {
				nullRequests.add(new RepayForm(
						set.getInt(1),
						set.getInt(2),
						set.getFloat(3),
						set.getString(4)
						));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.closeRead(conn, stmt, set);
		}
		
		return nullRequests;
	}

	@Override
	public RepayForm selectForm(int formId) {
		String SQL = "select * from request where pKey = ?";
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet set = null;
		RepayForm form = new RepayForm();
				
		try {
			conn = this.getConnection();
			stmt = conn.prepareStatement(SQL);
			stmt.setInt(1, formId);
			set = stmt.executeQuery();
			
			if(set.next()) {
				form.setRequestId(set.getInt(1));
				form.setEmployeeId(set.getInt(2));
				form.setAmount(set.getFloat(3));
				form.setComment(set.getString(4));
				form.setManager(set.getBoolean(5));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.closeRead(conn, stmt, set);
		}
		
		return form;
	}

	@Override
	public void insertForm(RepayForm form) {
		String SQL = "insert into request values(default, ?, ?, ?)";
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = this.getConnection();
			stmt = conn.prepareStatement(SQL);
			stmt.setInt(1, form.getEmployeeId());
			stmt.setFloat(2, form.getAmount());
			stmt.setString(3, form.getComment());
			
		} catch (SQLException e) {
			
			
		} finally {
			this.closeWrite(conn, stmt);
			
		}
	}

	@Override
	public Account selectEmployee(int empId) {
		String SQL = "select * from account where emp_id = ?";
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet set = null;
		Account emp = new Account();
		
		try {
			conn = this.getConnection();
			stmt = conn.prepareStatement(SQL);
			stmt.setInt(1, empId);
			set = stmt.executeQuery();
			
			if(set.next()) {
				emp.setEmp_id(set.getInt(1));
				emp.setUsername(set.getString(2));
				emp.setPassword(set.getString(3));
				emp.setfName(set.getString(4));
				emp.setlName(set.getString(5));
				emp.setAddress(set.getString(6));
				emp.setCity(set.getString(7));
				emp.setState(set.getString(8));
				emp.setZip(set.getInt(9));
				emp.setSocial_number(set.getString(10));
				emp.setBalance(set.getFloat(11));
				emp.setManager(set.getBoolean(12));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.closeRead(conn, stmt, set);
			
		}
		return emp;
	}

	@Override
	public void updateFormStatus(int formId, boolean approval) {
		String SQL = "update request r set approval = ? where r.empId = ?"; 
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = this.getConnection();
			stmt = conn.prepareStatement(SQL);
			stmt.setBoolean(1, approval);
			stmt.setInt(2, formId);
			stmt.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.closeWrite(conn, stmt);
			
		}
	}

	@Override
	public int validateUser(String username, String password) {
		String SQL = "select emp_id from account where username = ? and pass = ?";
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet set = null;
		int emp_id = 0;
		
		try {
			conn = this.getConnection();
			stmt = conn.prepareStatement(SQL);
			stmt.setString(1, username);
			stmt.setString(2, password);
			set = stmt.executeQuery();
			
			if(set.next()) {
				emp_id = set.getInt(1);
			}
			
		} catch (SQLException e) {
			System.out.println("invalid username or password");
		} finally {
			this.closeRead(conn, stmt, set);
		}
		
		System.out.println(emp_id);
		return emp_id;
	}
	
}
