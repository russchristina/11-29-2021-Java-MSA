package com.revature.repository;

import com.revature.models.exceptions.NegativeAmountException;
import com.revature.models.shop.Inventory;
import com.revature.repository.Exception.DuplicateInventoryIdException;
import com.revature.repository.Exception.InvalidInventoryIdException;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InventoryDAOTest {

    InventoryDAO inventoryDAO = new InventoryDAO();

    @Test
    void updateInventoryBalanceInvalidInventoryExceptionTest() {
        Assertions.assertThrows(InvalidInventoryIdException.class, () -> inventoryDAO.updateInventoryBalance(-1, 0));
    }

    @Test
    void updateInventoryNegativeAmountExceptionTest(){
        Assertions.assertThrows(NegativeAmountException.class, () -> inventoryDAO.updateInventoryBalance(2, -1));
    }

    @Test
    void deleteInventoryById() {
        Assertions.assertThrows(InvalidInventoryIdException.class, () -> inventoryDAO.deleteInventoryById(-1));
    }

    @Test
    void addInventory() {
        Assertions.assertThrows(DuplicateInventoryIdException.class, () -> inventoryDAO.addInventory(new Inventory(2, 0)));
    }
}