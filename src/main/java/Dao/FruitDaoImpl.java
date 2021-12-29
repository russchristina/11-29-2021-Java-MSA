package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.Fruit;

import com.revature.util.ConnectionClosers;
import com.revature.util.ConnectionFactory;

public class FruitDaoImpl implements FruitDao{

	@Override
	public List<Fruit> findAll() {
		List<Fruit> Fruits = new ArrayList<>();
		final String SQL = "Select * From Fruits";
		Connection conn = null;
		Statement stmt = null;
		ResultSet set = null;
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.createStatement();
			set = stmt.executeQuery(SQL);
			while(set.next()) {
				Fruits.add(
						new Fruit(
									set.getInt(1),
									set.getString(2),
									set.getInt(3))
								);
			}
			}catch(SQLException e) {
				e.printStackTrace();
				
			}finally {
				ConnectionClosers.closeConnection(conn);
				ConnectionClosers.closeStatement(stmt);
				ConnectionClosers.closeStatement(stmt);
			}
		return Fruits;
	}

	@Override
	public void save(Fruit Fruit) {
		final String SQL = "insert into Fruits values(default, ?, ?)";
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = ConnectionFactory.getConnection();
			/*
			 * The values of the parameters in a PreparedStatement are supplied later after
			 * the statement has been compiled.
			 */
			stmt = conn.prepareStatement(SQL);
			stmt.setString(1, Fruit.getName());
			stmt.setInt(2, Fruit.getPrice());
			stmt.execute();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionClosers.closeConnection(conn);
			ConnectionClosers.closeStatement(stmt);
		}
		
	}

		
	

	@Override
	public Fruit findById(int id) {
		Fruit Fruit= null;
		final String SQL = "select * from Fruits where Fruit_id = " + id;
		Connection conn = null;
		Statement stmt = null;
		ResultSet set = null;
		
		try {
			
			conn = ConnectionFactory.getConnection();
			stmt = conn.createStatement();
			set = stmt.executeQuery(SQL);
			
			if(set.next()) {
				Fruit = new Fruit(set.getInt(1),
						set.getString(2),
						set.getInt(3)
						);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionClosers.closeConnection(conn);
			ConnectionClosers.closeResultSet(set);
			ConnectionClosers.closeStatement(stmt);
		}
		
		return Fruit;
	}

	@Override
	public Fruit findByName(String name) {
		final String SQL = "select * from Fruits where Fruits_name = '" + name + "'";
		Fruit Fruit = null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet set = null;
		
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.createStatement();
			set = stmt.executeQuery(SQL);
			
			if(set.next()) {
				Fruit = new Fruit(set.getInt(1),
						set.getString(2),
						set.getInt(3));
						
									}
	} catch (SQLException e) {
		System.out.println("No Fruit found.  Please check the Name.");
	}	finally {
		ConnectionClosers.closeConnection(conn);
		ConnectionClosers.closeStatement(stmt);
		ConnectionClosers.closeResultSet(set);
	}
	return Fruit;
	}

	@Override
	public void update(Fruit Fruit) {
		final String SQL = "update Fruit set "
				+ "Fruit_Fruits_name = ? where Fruit_id = ?";
	
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.prepareStatement(SQL);
			stmt.setString(1, Fruit.getName());
			stmt.setInt(2, Fruit.getId());
			stmt.execute();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionClosers.closeConnection(conn);
			ConnectionClosers.closeStatement(stmt);
		}
	}

	@Override
	public void delete(Fruit fruit) {
		final String SQL = "delete from Fruits where Fruits_name = ?";
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.prepareStatement(SQL);
			
			stmt.setString(2, fruit.getName());
			
			stmt.execute();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionClosers.closeConnection(conn);
			ConnectionClosers.closeStatement(stmt);
		}
	}
}
