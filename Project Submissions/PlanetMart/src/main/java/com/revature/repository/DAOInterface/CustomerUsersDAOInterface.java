package com.revature.repository.DAOInterface;

import com.revature.models.accounts.CustomerAccount;
import com.revature.models.shop.Inventory;
import com.revature.models.shop.Planet;
import com.revature.models.users.User;

import java.util.List;

public interface CustomerUsersDAOInterface {

    List<User> getAllCustomerUsers();
    List<User> getAllUsersByCustomerId(int customerAccountId);

    User getUserById(int id);

    void updateUserName(int userId, String name);

    void deleteUserById(int id);
    void addUser(String name, Inventory inventory, CustomerAccount customerAccount);


}
