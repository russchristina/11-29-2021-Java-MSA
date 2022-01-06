package impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.ManagerDAO;
import models.Employee;
import models.Request;
import util.ConnectClose;
import util.ConnectUtil;

public class ManagerImpl implements ManagerDAO{

	@Override
	public Request viewAllPending(String status) {
		Request reimburse = null;
		final String SQL = "select * from reimburse where status = 'pending'";
		Connection connection = null;
		Statement stmt = null;
		ResultSet set = null;

		try {
			connection = ConnectUtil.getConnection();
			stmt = connection.createStatement();
			set = stmt.executeQuery(SQL);
			}catch (SQLException e) {
				e.printStackTrace();
			}finally {
			ConnectClose.closeConnection(connection);
			ConnectClose.closeStatement(stmt);
			ConnectClose.closeResultSet(set);
			}
		return reimburse;
	}
	
		

	@Override
	public Request viewAllComplete(String status) {
		Request reimburse = null;
		final String SQL = "select * from reimburse where status <> 'pending'";
		Connection connection = null;
		Statement stmt = null;
		ResultSet set = null;

		try {
			connection = ConnectUtil.getConnection();
			stmt = connection.createStatement();
			set = stmt.executeQuery(SQL);
			}catch (SQLException e) {
				e.printStackTrace();
			}finally {
			ConnectClose.closeConnection(connection);
			ConnectClose.closeStatement(stmt);
			ConnectClose.closeResultSet(set);
			}
		return reimburse;		
	}

	@Override
	public Request viewAllApproved(String status) {
		Request reimburse = null;
		final String SQL = "select * from reimburse where status = 'approved'";
		Connection connection = null;
		Statement stmt = null;
		ResultSet set = null;

		try {
			connection = ConnectUtil.getConnection();
			stmt = connection.createStatement();
			set = stmt.executeQuery(SQL);
			}catch (SQLException e) {
				e.printStackTrace();
			}finally {
			ConnectClose.closeConnection(connection);
			ConnectClose.closeStatement(stmt);
			ConnectClose.closeResultSet(set);
			}
		return reimburse;
		
	}

	@Override
	public Request viewAllDenied(String status) {
		Request reimburse = null;
		final String SQL = "select * from reimburse where status = 'denied'";
		Connection connection = null;
		Statement stmt = null;
		ResultSet set = null;

		try {
			connection = ConnectUtil.getConnection();
			stmt = connection.createStatement();
			set = stmt.executeQuery(SQL);
			}catch (SQLException e) {
				e.printStackTrace();
			}finally {
			ConnectClose.closeConnection(connection);
			ConnectClose.closeStatement(stmt);
			ConnectClose.closeResultSet(set);
			}
		return reimburse;		
	}

	@Override
	public List<Request> viewAll() {
		List<Request> reimbursements = new ArrayList<>();
		
		final String SQL = "select * from Reimburse";
		Connection connection = null;
		Statement stmt = null;
		ResultSet set = null;		
	
		try {
			connection = ConnectUtil.getConnection();
			stmt = connection.createStatement();
			set = stmt.executeQuery(SQL);
			while(set.next()) {
				reimbursements.add(
						new Request(set.getInt(1), 
								set.getString(2),
								set.getInt(3),
								set.getString(4),
								set.getDate(5),
								set.getString(6),
								set.getString(7))
						);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("No Records Found");
		}finally {
			ConnectClose.closeConnection(connection);
			ConnectClose.closeStatement(stmt);
			ConnectClose.closeResultSet(set);
		}
		return reimbursements;
	}

	@Override
	public void submitNew() {
		Request reimburse = new Request();
		final String SQL = "insert into reimburse values(default,?,?,?,?,'Pending',null";
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = ConnectUtil.getConnection();
			stmt = conn.prepareStatement(SQL);
			
			stmt.setInt(1, reimburse.getId());
			stmt.setString(2, reimburse.getEmployee());
			stmt.setInt(3, reimburse.getAmount());
			stmt.setString(4, reimburse.getNotes());
			stmt.setDate(5, reimburse.getDate());
			stmt.setString(6, reimburse.getStatus());
			stmt.setString(7, reimburse.getApprovedBy());
			stmt.execute();
		}catch (SQLException e) {
			
		}finally {
			ConnectClose.closeConnection(conn);
			ConnectClose.closeStatement(stmt);
		}		
	}

	@Override
	public void update(Request reimburse) {
		final String SQL = "update reimburse set amount_submit = ? where reimburse_id = ?";
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = ConnectUtil.getConnection();
			stmt = conn.prepareStatement(SQL);
			stmt.setInt(1, reimburse.getAmount());
			stmt.setInt(2,  reimburse.getId());
			stmt.execute();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectClose.closeConnection(conn);
			ConnectClose.closeStatement(stmt);
		}		
	}

	@Override
	public void approve(Request reimburse) {
		final String SQL = "update reimburse set status = ? where reimburse_id = ?";
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = ConnectUtil.getConnection();
			stmt = conn.prepareStatement(SQL);
			stmt.setString(1, reimburse.getStatus());
			stmt.setInt(2,  reimburse.getId());
			stmt.execute();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectClose.closeConnection(conn);
			ConnectClose.closeStatement(stmt);
		}		
	}

	@Override
	public List<Employee> findAllEmp() {
					
			List<Employee> employees = new ArrayList<>();
			
			final String SQL = "select * from Employee";
			Connection connection = null;
			Statement stmt = null;
			ResultSet set = null;
			
			try {
				connection = ConnectUtil.getConnection();
				stmt = connection.createStatement();
				set = stmt.executeQuery(SQL);
				while(set.next()) {
					employees.add(
							new Employee(set.getInt(1), 
									set.getString(2), 
									set.getString(3), 
									set.getString(4),
									set.getString(5), 
									set.getString(6), 
									set.getString(7))
							);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("No Employees Found");
			}finally {
				ConnectClose.closeConnection(connection);
				ConnectClose.closeStatement(stmt);
				ConnectClose.closeResultSet(set);
			}
				return employees;
		}
		
	

	@Override
	public Request findByEmp(String username) {
		Request reimburse= null;
		final String SQL = "select * from reimburse where employee_submit = " + username;
		Connection conn = null;
		Statement stmt = null;
		ResultSet set = null;
				
		try {
			conn= ConnectUtil.getConnection();
			stmt = conn.createStatement();
			set = stmt.executeQuery(SQL);
			
			if(set.next()) {
				reimburse = new Request(set.getInt(1),
						set.getString(2),
						set.getInt(3),
						set.getString(4),
						set.getDate(5),
						set.getString(6),
						set.getString(7)
						);
						
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectClose.closeConnection(conn);
			ConnectClose.closeResultSet(set);
			ConnectClose.closeStatement(stmt);
		}
				
		return reimburse;
		
	}

	@Override
	public Request findByDate(Date date) {
		Request reimburse= null;
		final String SQL = "select * from reimburse where date_submit = " + date;
		Connection conn = null;
		Statement stmt = null;
		ResultSet set = null;
		
		
		try {
			conn= ConnectUtil.getConnection();
			stmt = conn.createStatement();
			set = stmt.executeQuery(SQL);
			
			if(set.next()) {
				reimburse = new Request(set.getInt(1),
						set.getString(2),
						set.getInt(3),
						set.getString(4),
						set.getDate(5),
						set.getString(6),
						set.getString(7)
						);
						
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectClose.closeConnection(conn);
			ConnectClose.closeResultSet(set);
			ConnectClose.closeStatement(stmt);
		}
				
		return reimburse;		
	}

	@Override
	public Request findById(int id) {
		Request reimburse= null;
		final String SQL = "select * from reimburse where reimburse_id = " + id;
		Connection conn = null;
		Statement stmt = null;
		ResultSet set = null;
		
		
		try {
			conn= ConnectUtil.getConnection();
			stmt = conn.createStatement();
			set = stmt.executeQuery(SQL);
			
			if(set.next()) {
				reimburse = new Request(set.getInt(1),
						set.getString(2),
						set.getInt(3),
						set.getString(4),
						set.getDate(5),
						set.getString(6),
						set.getString(7)
						);
						
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectClose.closeConnection(conn);
			ConnectClose.closeResultSet(set);
			ConnectClose.closeStatement(stmt);
		}
				
		return reimburse;		
	}

	@Override
	public Request findByAmount(int amount) {
		Request reimburse= null;
		final String SQL = "select * from reimburse where amount_submit = " + amount;
		Connection conn = null;
		Statement stmt = null;
		ResultSet set = null;
		
		
		try {
			conn= ConnectUtil.getConnection();
			stmt = conn.createStatement();
			set = stmt.executeQuery(SQL);
			
			if(set.next()) {
				reimburse = new Request(set.getInt(1),
						set.getString(2),
						set.getInt(3),
						set.getString(4),
						set.getDate(5),
						set.getString(6),
						set.getString(7)
						);
						
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectClose.closeConnection(conn);
			ConnectClose.closeResultSet(set);
			ConnectClose.closeStatement(stmt);
		}
				
		return reimburse;		
	}

}
