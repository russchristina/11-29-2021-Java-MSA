package impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dao.RequestDAO;
import models.Request;
import util.ConnectClose;
import util.ConnectUtil;

public class RequestImpl implements RequestDAO{

	@Override
	public Request findByEmp(String username) {
		Request request= null;
		final String SQL = "select * from requests where employee_submit = " + username;
		Connection conn = null;
		Statement stmt = null;
		ResultSet set = null;
				
		try {
			conn= ConnectUtil.getConnection();
			stmt = conn.createStatement();
			set = stmt.executeQuery(SQL);
			
			if(set.next()) {
				request = new Request(set.getInt(1),
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
				
		return request;
		
	}

	@Override
	public Request findByDate(Date date) {
		Request request= null;
		final String SQL = "select * from requests where date_submit = " + date;
		Connection conn = null;
		Statement stmt = null;
		ResultSet set = null;
		
		
		try {
			conn= ConnectUtil.getConnection();
			stmt = conn.createStatement();
			set = stmt.executeQuery(SQL);
			
			if(set.next()) {
				request = new Request(set.getInt(1),
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
				
		return request;
		
	}

	@Override
	public Request findById(int id) {
		Request request= null;
		final String SQL = "select * from requests where request_id = " + id;
		Connection conn = null;
		Statement stmt = null;
		ResultSet set = null;
		
		
		try {
			conn= ConnectUtil.getConnection();
			stmt = conn.createStatement();
			set = stmt.executeQuery(SQL);
			
			if(set.next()) {
				request = new Request(set.getInt(1),
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
				
		return request;
		
	}

	@Override
	public Request findByAmount(int amount) {
		Request request= null;
		final String SQL = "select * from requests where amount_submit = " + amount;
		Connection conn = null;
		Statement stmt = null;
		ResultSet set = null;
		
		
		try {
			conn= ConnectUtil.getConnection();
			stmt = conn.createStatement();
			set = stmt.executeQuery(SQL);
			
			if(set.next()) {
				request = new Request(set.getInt(1),
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
				
		return request;
		
	}

	@Override
	public Request findByStatus(String status) {
		Request request= null;
		final String SQL = "select * from requests where status = " + status;
		Connection conn = null;
		Statement stmt = null;
		ResultSet set = null;
		
		
		try {
			conn= ConnectUtil.getConnection();
			stmt = conn.createStatement();
			set = stmt.executeQuery(SQL);
			
			if(set.next()) {
				request = new Request(set.getInt(1),
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
				
		return request;
		
	}

	
	@Override
	public void submitNew(int id, String employee, int amount, String notes, Date date, String status, String approvedBy) {
		Request request = new Request();
		final String SQL = "insert into requests values(default,?,?,?,?,'Pending',null) returning *";
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = ConnectUtil.getConnection();
			stmt = conn.prepareStatement(SQL);
			
			stmt.setString(1, request.getEmployee());
			stmt.setInt(2, request.getAmount());
			stmt.setString(3, request.getNotes());
			stmt.setDate(4, request.getDate());

			stmt.execute();
		}catch (SQLException e) {
			
		}finally {
			ConnectClose.closeConnection(conn);
			ConnectClose.closeStatement(stmt);
		}
		
	}

	@Override
	public void update(Request request) {
		final String SQL = "update requests set amount_submit = ? where request_id = ?";
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = ConnectUtil.getConnection();
			stmt = conn.prepareStatement(SQL);
			stmt.setInt(1, request.getAmount());
			stmt.setInt(2, request.getId());
			stmt.execute();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectClose.closeConnection(conn);
			ConnectClose.closeStatement(stmt);
		}
		
	}

	@Override
	public void delete(Request request) {
		final String SQL = "delete from requests where request_id = ?";
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = ConnectUtil.getConnection();
			stmt = conn.prepareStatement(SQL);
			stmt.setInt(1, request.getId());
			stmt.execute();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectClose.closeConnection(conn);
			ConnectClose.closeStatement(stmt);
		}
		
	}

}
