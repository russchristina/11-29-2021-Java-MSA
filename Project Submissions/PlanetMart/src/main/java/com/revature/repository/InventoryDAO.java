package com.revature.repository;


import com.revature.models.exceptions.NegativeAmountException;
import com.revature.models.shop.Inventory;
import com.revature.repository.DAOInterface.InventoryDAOInterface;
import com.revature.repository.Exception.DuplicateInventoryIdException;
import com.revature.repository.Exception.InvalidInventoryIdException;
import com.revature.utility.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InventoryDAO implements InventoryDAOInterface {

    private final Logger debugLogger = LoggerFactory.getLogger("debugLogger");

    @Override
    public List<Inventory> getAllInventories() {

        List<Inventory> inventories = new ArrayList<>();

        final String SQL = "select * from customer_inventory";

        try(Connection connection = ConnectionFactory.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL)
        ){
            while(resultSet.next()){
                inventories.add(
                        new Inventory(
                                resultSet.getInt(1),
                                resultSet.getInt(2)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return inventories;

    }

    @Override
    public Inventory getInventoryByInventoryId(int id) throws InvalidInventoryIdException {
        Inventory inventory = null;


        final String SQL = "select * from customer_inventory where inventory_id = ?";

        ResultSet resultSet = null;
        try(
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL)

        ){
            statement.setInt(1, id);

            resultSet = statement.executeQuery();

            if(resultSet.next()) inventory = new Inventory(
                    resultSet.getInt(1),
                    resultSet.getInt(2)
            );


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                assert resultSet != null;
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return inventory;

    }

    @Override
    public void updateInventoryBalance(int id, int newBalance) throws InvalidInventoryIdException, NegativeAmountException {
        final String SQL = "update customer_inventory set balance = ? where inventory_id = ?";

        if(!checkInventoryId(id)) throw new InvalidInventoryIdException("Invalid Inventory ID");
        if(newBalance<0) throw new NegativeAmountException("Negative Balance");
        try(
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL)
                ){

            statement.setInt(1, newBalance);
            statement.setInt(2, id);
            statement.execute();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


    @Override
    public void deleteInventoryById(int id) throws InvalidInventoryIdException {
        final String SQL = "delete from customer_inventory where inventory_id = ?";
        if(!checkInventoryId(id)) throw new InvalidInventoryIdException("Invalid Inventory ID");
        try(Connection connection = ConnectionFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL)) {

            statement.setInt(1, id);
            statement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void addInventory(Inventory inventory) throws DuplicateInventoryIdException{

        final String SQL = "insert into customer_inventory values( default, ?)";
        if(checkInventoryId(inventory.getId())) throw new DuplicateInventoryIdException("Inventory Already Exists");

        try(
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL)
        ) {
            statement.setInt(1, inventory.getBalance());
            statement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public boolean checkInventoryId(int inventoryId) {
        boolean success = false;
        Inventory inventory = null;
        try{
            inventory = getInventoryByInventoryId(inventoryId);

        }catch (Exception e){
            debugLogger.debug(e.toString());
        }
        if(inventory != null) success = true;
        return success;
    }
}
