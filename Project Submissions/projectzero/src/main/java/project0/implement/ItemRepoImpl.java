package project0.implement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import project0.models.Item;
import project0.repos.ItemRepo;
import project0.util.ConnectCloser;
import project0.util.ConnectUtil;

public class ItemRepoImpl implements ItemRepo{

	@Override
	public Item findById(int id) {
		
		Item item = null;
		final String SQL = "select * from item where item_id = " + id;
		Connection conn = null;
		Statement stmt = null;
		ResultSet set = null;
		
		try {
			conn = ConnectUtil.getConnection();
			stmt = conn.createStatement();
			set = stmt.executeQuery(SQL);
			
			if(set.next()) {
				item = new Item(set.getInt(1),
						set.getString(2),
						set.getString(3),
						set.getString(4),
						set.getInt(5));
			}
			
		} catch (SQLException e) {
			System.out.println("No item found.  Please check the ID Number");
			e.printStackTrace();
		}finally {
			ConnectCloser.closeConnection(conn);
			ConnectCloser.closeStatement(stmt);
			ConnectCloser.closeResultSet(set);
		}
			
		return item;
	}

	@Override
	public Item findByName(String name) {
		Item item = null;
		final String SQL = "select * from item where item_name = '" + name + "'";
		Connection conn = null;
		Statement stmt = null;
		ResultSet set = null;
		
		try {
			conn = ConnectUtil.getConnection();
			stmt = conn.createStatement();
			set = stmt.executeQuery(SQL);
			
			if(set.next()) {
				item = new Item(set.getInt(1),
						set.getString(2),
						set.getString(3),
						set.getString(4),
						set.getInt(5));
			}
			
		} catch (SQLException e) {
			System.out.println("No item found.  Please check the Item Name");
		}finally {
			ConnectCloser.closeConnection(conn);
			ConnectCloser.closeStatement(stmt);
			ConnectCloser.closeResultSet(set);		
	}
	return item;
	}

	@Override
	public Item findByType(String type) {
		Item item = null;
		final String SQL = "select * from item where item_name = '" + type + "'";
		Connection conn = null;
		Statement stmt = null;
		ResultSet set = null;
		
		try {
			conn = ConnectUtil.getConnection();
			stmt = conn.createStatement();
			set = stmt.executeQuery(SQL);
			
			if(set.next()) {
				item = new Item(set.getInt(1),
						set.getString(2),
						set.getString(3),
						set.getString(4),
						set.getInt(5));
			}
			
		} catch (SQLException e) {
			System.out.println("No items found.  Please check the Item Type");
		}finally {
			ConnectCloser.closeConnection(conn);
			ConnectCloser.closeStatement(stmt);
			ConnectCloser.closeResultSet(set);		
	}
	return item;
	}

	@Override
	public Item findByMaterial(String material) {
		Item item = null;
		final String SQL = "select * from item where item_name = '" + material + "'";
		Connection conn = null;
		Statement stmt = null;
		ResultSet set = null;
		
		try {
			conn = ConnectUtil.getConnection();
			stmt = conn.createStatement();
			set = stmt.executeQuery(SQL);
			
			if(set.next()) {
				item = new Item(set.getInt(1),
						set.getString(2),
						set.getString(3),
						set.getString(4),
						set.getInt(5));
			}
			
		} catch (SQLException e) {
			System.out.println("No items found.  Please check the Item Material");
		}finally {
			ConnectCloser.closeConnection(conn);
			ConnectCloser.closeStatement(stmt);
			ConnectCloser.closeResultSet(set);		
	}
	return item;		 
	}

	@Override
	public List<Item> findAll() {
		
List<Item> items = new ArrayList<>();
		
		final String SQL = "select * from item";
		Connection conn = null;
		Statement stmt = null;
		ResultSet set = null;
		
		try {
			conn = ConnectUtil.getConnection();
			stmt = conn.createStatement();
			set = stmt.executeQuery(SQL);
			while(set.next()) {
				items.add(new Item (set.getInt(1),
						set.getString(2),
						set.getString(3),
						set.getString(4),
						set.getInt(5)));
			}
		} catch (SQLException e) {
			System.out.println("No records found.");
		}	finally {
			ConnectCloser.closeConnection(conn);
			ConnectCloser.closeStatement(stmt);
			ConnectCloser.closeResultSet(set);
		}
		return items;
	}

}
