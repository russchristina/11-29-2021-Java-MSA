package com.revature.repository.DAOInterface;

import com.revature.models.exceptions.NegativeAmountException;
import com.revature.models.shop.Inventory;
import com.revature.models.users.UserCredential;
import com.revature.repository.Exception.InvalidInventoryIdException;

import java.util.List;

public interface InventoryDAOInterface {

    public List<Inventory> getAllInventories();
    public Inventory getInventoryByInventoryId(int id) throws InvalidInventoryIdException;
    public void updateInventoryBalance(int id, int newBalance) throws InvalidInventoryIdException, NegativeAmountException;
    public void deleteInventoryById(int id) throws InvalidInventoryIdException;
    public void addInventory(Inventory inventory);
}
