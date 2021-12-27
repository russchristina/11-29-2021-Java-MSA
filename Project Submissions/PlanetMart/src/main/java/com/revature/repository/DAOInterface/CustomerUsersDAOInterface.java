package com.revature.repository.DAOInterface;

import com.revature.models.accounts.CustomerAccount;
import com.revature.models.shop.Inventory;
import com.revature.models.shop.Planet;
import com.revature.models.users.User;
import com.revature.repository.Exception.InvalidCustomerAccountIdException;
import com.revature.repository.Exception.InvalidInventoryIdException;
import com.revature.repository.Exception.InvalidUserIdException;

import java.util.List;

public interface CustomerUsersDAOInterface {

    List<User> getAllCustomerUsers();
    List<User> getAllUsersByCustomerId(int customerAccountId);

    User getUserById(int id) throws InvalidUserIdException;

    void updateUserName(int userId, String name);

    void deleteUserById(int id) throws InvalidUserIdException;
    void addUser(String name, int inventoryId, int customerAccountId) throws InvalidCustomerAccountIdException, InvalidInventoryIdException;


}
