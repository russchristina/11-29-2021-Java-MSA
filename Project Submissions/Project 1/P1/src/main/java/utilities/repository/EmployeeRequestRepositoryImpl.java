package utilities.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import models.Employee;
import models.Request;
import utilities.connect.ConnectionClosers;
import utilities.connect.ConnectionHandler;
import utilities.connect.HibernateSessionFactory;

public class EmployeeRequestRepositoryImpl implements EmployeeRequestRepository{
	
	@Override
	public void createRequest(Request r) {
		final String SQL = "insert into requests values(default, ?, ?, ?, ?, ?)";
		Connection conn = null;
		PreparedStatement stmt = null;
		 try {
			 conn = ConnectionHandler.getConnection();
			 stmt = conn.prepareStatement(SQL);
			 stmt.setString(1, r.getEmployeeName());
			 stmt.setDouble(2, r.getAmount());
			 stmt.setString(3, r.getReason());
			 stmt.setString(4, r.getStatus());
			 stmt.setString(5, r.getNote());
			 stmt.execute();
		 } catch (SQLException e) {
			 e.printStackTrace();
		 } finally {
			 ConnectionClosers.closeConnection(conn);
			 ConnectionClosers.closeStatement(stmt);
		 } // End finally block
	} // End method

	@Override
	public Employee findByEmployeeName(String name) {
		Employee emp = null;
		final String SQL = "select * from employees where employee_name = ?";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet set = null;
		
		try {
			conn = ConnectionHandler.getConnection();
			stmt = conn.prepareStatement(SQL);
			stmt.setString(1, name);
			set = stmt.executeQuery();
			
			if (set.next()) {
				emp = new Employee(
						set.getString(1),
						set.getString(2),
						set.getBoolean(3)
						);
			} // End if statement
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionClosers.closeConnection(conn);
			ConnectionClosers.closeResultSet(set);
			ConnectionClosers.closeStatement(stmt);
		} // End finally block
		
		return emp;
	} // End method

	@Override
	public Request findByRequestId(int id) {
		Request r = null;
		final String SQL = "select * from requests where request_id = ?";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet set = null;
		
		try {
			conn = ConnectionHandler.getConnection();
			stmt = conn.prepareStatement(SQL);
			stmt.setInt(1, id);
			set = stmt.executeQuery();
			
			if (set.next()) {
				r = new Request(
						set.getInt(1),
						set.getString(2),
						set.getDouble(3),
						set.getString(4),
						set.getString(5),
						set.getString(6)
						);
			} // End if statement
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionClosers.closeConnection(conn);
			ConnectionClosers.closeResultSet(set);
			ConnectionClosers.closeStatement(stmt);
		} // End finally block
		
		return r;
	} // End method

	@Override
	public List<Request> findRequestsByEmployeeName(String name) {
		List<Request> requestList = new ArrayList<>();
		final String SQL = "select * from requests where employee_name = ?";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet set = null;
		
		try {
			conn = ConnectionHandler.getConnection();
			stmt = conn.prepareStatement(SQL);
			stmt.setString(1, name);
			set = stmt.executeQuery();
			
			while (set.next()) {
				requestList.add(new Request(
						set.getInt(1),
						set.getString(2),
						set.getDouble(3),
						set.getString(4),
						set.getString(5),
						set.getString(6)
						));
			} // End while loop
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionClosers.closeConnection(conn);
			ConnectionClosers.closeResultSet(set);
			ConnectionClosers.closeStatement(stmt);
		} // End finally block
		
		return requestList;
	} // End method

	
//	public List<Request> findAllRequestsJ() {
//		List<Request> requestList = new ArrayList<>();
//		final String SQL = "select * from requests";
//		Connection conn = null;
//		PreparedStatement stmt = null;
//		ResultSet set = null;
//		
//		try {
//			conn = ConnectionHandler.getConnection();
//			stmt = conn.prepareStatement(SQL);
//			set = stmt.executeQuery();
//			
//			while (set.next()) {
//				requestList.add(new Request(
//						set.getInt(1),
//						set.getString(2),
//						set.getDouble(3),
//						set.getString(4),
//						set.getString(5),
//						set.getString(6)
//						));
//			} // End while loop
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			ConnectionClosers.closeConnection(conn);
//			ConnectionClosers.closeResultSet(set);
//			ConnectionClosers.closeStatement(stmt);
//		} // End finally block
//		
//		return requestList;
//	} // End method

	@Override
	public List<Request> findAllRequests() {
		List<Request> requestList = null;
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			requestList = session.createQuery("FROM Request", Request.class).getResultList();
			transaction.commit();
		} catch(HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		}
		return requestList;
	}
	
	@Override
	public List<Request> findPendingRequests() {
		List<Request> requestList = new ArrayList<>();
		final String SQL = "select * from requests where status = 1";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet set = null;
		
		try {
			conn = ConnectionHandler.getConnection();
			stmt = conn.prepareStatement(SQL);
			set = stmt.executeQuery();
			
			while (set.next()) {
				requestList.add(new Request(
						set.getInt(1),
						set.getString(2),
						set.getDouble(3),
						set.getString(4),
						set.getString(5),
						set.getString(6)
						));
			} // End while loop
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionClosers.closeConnection(conn);
			ConnectionClosers.closeResultSet(set);
			ConnectionClosers.closeStatement(stmt);
		} // End finally block
		
		return requestList;
	} // End method

	@Override
	public void updateRequestStatus(Request r) {
		final String SQL = "update requests set status = ? where request_id = ?";
		Connection conn = null;
		PreparedStatement stmt = null;
		 try {
			 conn = ConnectionHandler.getConnection();
			 stmt = conn.prepareStatement(SQL);
			 stmt.setString(1, r.getStatus());
			 stmt.setInt(3, r.getRequestId());
			 stmt.execute();
		 } catch (SQLException e) {
			 e.printStackTrace();
		 } finally {
			 ConnectionClosers.closeConnection(conn);
			 ConnectionClosers.closeStatement(stmt);
		 } // End finally block
	} // End method
	
	@Override
	public void updateRequestStatusAndNote(Request r) {
		final String SQL = "update requests set status = ?, note = ? where request_id = ?";
		Connection conn = null;
		PreparedStatement stmt = null;
		 try {
			 conn = ConnectionHandler.getConnection();
			 stmt = conn.prepareStatement(SQL);
			 stmt.setString(1, r.getStatus());
			 stmt.setString(2, r.getNote());
			 stmt.setInt(3, r.getRequestId());
			 stmt.execute();
		 } catch (SQLException e) {
			 e.printStackTrace();
		 } finally {
			 ConnectionClosers.closeConnection(conn);
			 ConnectionClosers.closeStatement(stmt);
		 } // End finally block
	} // End method
	
	@Override
	public Request highestSpender() {
		Request r = null;
		final String SQL = "select employee_name, amount from requests where status = Approved order by amount  desc limit 1";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet set = null;
		
		try {
			conn = ConnectionHandler.getConnection();
			stmt = conn.prepareStatement(SQL);
			set = stmt.executeQuery();
			
			if (set.next()) {
				r = new Request(
						set.getString(1),
						set.getDouble(2)
						);
			} // End if statement
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionClosers.closeConnection(conn);
			ConnectionClosers.closeResultSet(set);
			ConnectionClosers.closeStatement(stmt);
		} // End finally block
		
		return r;
	} // End method

	@Override
	public int numberOfRequests() {
		final String SQL = "select count(1) from requests";
		int count = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet set = null;
		
		try {
			conn = ConnectionHandler.getConnection();
			stmt = conn.prepareStatement(SQL);
			set = stmt.executeQuery();
			
			if (set.next()) {
				count = set.getInt(1);
			} // End if statement
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionClosers.closeConnection(conn);
			ConnectionClosers.closeResultSet(set);
			ConnectionClosers.closeStatement(stmt);
		} // End finally block
		
		return count;
	} // End method

	@Override
	public double averageCost() {
		double avg = 0;
		double div = 0;
		final String SQL = "select amount from requests";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet set = null;
		
		try {
			conn = ConnectionHandler.getConnection();
			stmt = conn.prepareStatement(SQL);
			set = stmt.executeQuery();
			
			while (set.next()) {
				avg += set.getDouble(1);
				div++;
			} // End while loop
			avg = avg / div;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionClosers.closeConnection(conn);
			ConnectionClosers.closeResultSet(set);
			ConnectionClosers.closeStatement(stmt);
		} // End finally block
		
		return avg;
	} // End method
} // End class
