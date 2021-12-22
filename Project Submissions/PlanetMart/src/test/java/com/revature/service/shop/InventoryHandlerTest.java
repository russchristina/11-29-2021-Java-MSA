package com.revature.service.shop;

import com.revature.database.DummyCustomerData;
import com.revature.models.accounts.CustomerAccount;
import com.revature.models.shop.Inventory;
import com.revature.models.users.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InventoryHandlerTest {

    @Test
    void generateUserInventoryTest() {
        CustomerAccount account = new CustomerAccount();

        User user = new User();
        InventoryHandler inventoryHandler = new InventoryHandler();
        Assertions.assertTrue(inventoryHandler.generateUserInventory(account, user) instanceof Inventory);
    }
}