package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.Ingredient;
import com.revature.util.ConnectionClosers;
import com.revature.util.ConnectionFactory;

/*
 * This class is an implementation of our IngredientRepository. Any instance of this class
 * will be a Data Access Object (DAO). This is a design pattern that provides a nice abstraction
 * for data access in your applications. This is a dedicated class for data access, which makes
 * it easy to refactor and generally maintain our source code.
 */
public class IngredientRepositoryImpl implements IngredientRepository{

	@Override
	public void save(Ingredient ingredient) {
		/*
		 * When you use a PreparedStatement, you parameterize the values/inputs in the
		 * SQL string:
		 */
		final String SQL = "insert into ingredient values(default, ?, ?)";
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = ConnectionFactory.getConnection();
			/*
			 * The values of the parameters in a PreparedStatement are supplied later after
			 * the statement has been compiled.
			 */
			stmt = conn.prepareStatement(SQL);
			stmt.setString(1, ingredient.getName());
			stmt.setString(2, ingredient.getFlavor());
			stmt.execute();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionClosers.closeConnection(conn);
			ConnectionClosers.closeStatement(stmt);
		}
		
	}

	@Override
	public Ingredient findById(int id) {
		
		Ingredient ingredient = null;
		final String SQL = "select * from ingredient where ingredient_id = " + id;
		Connection conn = null;
		Statement stmt = null;
		ResultSet set = null;
		
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.createStatement();
			set = stmt.executeQuery(SQL);
			
			if(set.next()) {
				ingredient = new Ingredient(set.getInt(1),
						set.getString(2),
						set.getString(3),
						0.0f);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionClosers.closeConnection(conn);
			ConnectionClosers.closeResultSet(set);
			ConnectionClosers.closeStatement(stmt);
		}
		
		return ingredient;
	}

	@Override
	public Ingredient findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Ingredient> findAll() {
		/*
		 * Since our method has to return a List, we can create a list right now.
		 */
		List<Ingredient> ingredients = new ArrayList<>();
		
		/*
		 * JDBC (Java Database Connectivity) is an API that provides an interface against
		 * which we program in order to connect to a database from our Java application.
		 * 
		 * JDBC provides several interfaces (and classes) that we use to connect to our DB.
		 * These include:
		 * 
		 * DriverManager (class): manages our JDBC drivers for us and allows us to use these drivers
		 * 	to facilitate a connection to our database.
		 * Connection (interface) : represents a connection to a specific database. You must
		 * 	have a connection in order to run SQL statements against your database.
		 * Statement (interface) : this interface is used to execute statements against
		 * 	your database.
		 * ResultSet (interface) : an object representation of the result set that is
		 * 	returned after executing a query against your database (the records you wanted)
		 * SQLException (class) : a special exception when something goes wrong when you
		 * are using JDBC
		 */
		
		final String SQL = "select * from ingredient";
		Connection connection = null;
		Statement stmt = null;
		ResultSet set = null;
		
		try {
			// Get a connection to your database
			connection = ConnectionFactory.getConnection();
			
			/*
			 * Once you have a connection to your database, you can execute SQL statements
			 * against the DB.
			 */
			
			stmt = connection.createStatement();
			
			/*
			 * I need to store the results of running the query in a ResultSet so that
			 * I can access the records that are returned.
			 */
			set = stmt.executeQuery(SQL);
			
			/*
			 * You have to unpack the records from your ResultSet and store them in a
			 * List since we're returning a List.
			 */
			
			/*
			 * We want to iterate over the ResultSet to grab all of the records and
			 * place them into our ArrayList. We must do this by accessing the data
			 * in each column.
			 */
			while(set.next()) {
				ingredients.add(
						new Ingredient(
								set.getInt(1), 
								set.getString(2), 
								set.getString(3), 
								0.0f)
						);
				
				/*
				 * If you liked what we did below more, please do that instead.
				 */
//				int ingredientId = set.getInt(1);
//				String ingredientName = set.getString(2);
//				String ingredientFlavor = set.getString(3);
//				
//				Ingredient ingredient = new Ingredient(ingredientId, ingredientName, ingredientFlavor, 0.0f);
//				ingredients.add(ingredient);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			/*
			 * Yes, also close your connections, statements, and result sets!
			 */
			ConnectionClosers.closeConnection(connection);
			ConnectionClosers.closeResultSet(set);
			ConnectionClosers.closeStatement(stmt);
		}
		
		return ingredients;
	}

	@Override
	public void update(Ingredient ingredient) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Ingredient ingredient) {
		// TODO Auto-generated method stub
		
	}

}
