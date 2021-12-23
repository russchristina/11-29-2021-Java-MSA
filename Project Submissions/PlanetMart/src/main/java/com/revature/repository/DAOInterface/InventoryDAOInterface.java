package com.revature.repository.DAOInterface;

import com.revature.models.shop.Inventory;
import com.revature.models.users.UserCredential;

import java.util.List;

public interface InventoryDAOInterface {

    public List<Inventory> getAllInventories();
    public Inventory getInventoryByInventoryId(int id);
    public void updateInventoryBalance(int id, int newBalance);
    public void deleteInventoryById(int id);
    public void addInventory(Inventory inventory);
}
