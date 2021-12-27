package project0.implement;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import project0.models.Ledger;
import project0.repos.LedgerRepo;
import project0.util.ConnectCloser;
import project0.util.ConnectUtil;

public class LedgerRepoImpl implements LedgerRepo {

	@Override
	public Ledger findById(int id) {
		Ledger ledger = null;
		final String SQL = "select * from ledger where invoice_id = " + id;
		Connection conn = null;
		Statement stmt = null;
		ResultSet set = null;
		
		try {
			conn = ConnectUtil.getConnection();
			stmt = conn.createStatement();
			set = stmt.executeQuery(SQL);
			
			if(set.next()) {
				ledger = new Ledger(set.getInt(1),
						set.getString(2),
						null, 
						set.getInt(4),
						set.getDate(5));
						}
		} catch (SQLException e) {
			System.out.println("No records found.  Please check the ID Number");
		}finally {
			ConnectCloser.closeConnection(conn);
			ConnectCloser.closeStatement(stmt);
			ConnectCloser.closeResultSet(set);
		}
			return ledger;
	}

	@Override
	public Ledger findByBuyer(String buyer) {
		Ledger ledger = null;
		final String SQL = "select * from ledger where buyer = '" + buyer + "'";
		Connection conn = null;
		Statement stmt = null;
		ResultSet set = null;
		
		try {
			conn = ConnectUtil.getConnection();
			stmt = conn.createStatement();
			set = stmt.executeQuery(SQL);
			
			if(set.next()) {
				ledger = new Ledger(set.getInt(1),
						set.getString(2),
						null, 
						set.getInt(4),
						set.getDate(5));
						}
		} catch (SQLException e) {
			System.out.println("No records found.  Please check the Buyer");
		}finally {
			ConnectCloser.closeConnection(conn);
			ConnectCloser.closeStatement(stmt);
			ConnectCloser.closeResultSet(set);
		}		
		return null;
	}

	@Override
	public Ledger findByDate(Date date) {
		Ledger ledger = null;
		final String SQL = "select * from ledger where invoice_date = " + date ;
		Connection conn = null;
		Statement stmt = null;
		ResultSet set = null;
		
		try {
			conn = ConnectUtil.getConnection();
			stmt = conn.createStatement();
			set = stmt.executeQuery(SQL);
			
			if(set.next()) {
				ledger = new Ledger(set.getInt(1),
						set.getString(2),
						null, 
						set.getInt(4),
						set.getDate(5));
						}
		} catch (SQLException e) {
			System.out.println("No records found.  Please check the Buyer");
		}finally {
			ConnectCloser.closeConnection(conn);
			ConnectCloser.closeStatement(stmt);
			ConnectCloser.closeResultSet(set);
		}
		return null;
	}

}
