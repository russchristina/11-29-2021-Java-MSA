package com.revature.repository;

import com.revature.repository.Exception.InvalidCustomerAccountIdException;
import com.revature.repository.Exception.InvalidInventoryIdException;
import com.revature.repository.Exception.InvalidUserIdException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CustomerUserDAOTest {

    CustomerUserDAO customerUserDAO = new CustomerUserDAO();

    @Test
    void deleteUserById() {
        Assertions.assertThrows(InvalidUserIdException.class, () -> customerUserDAO.deleteUserById(-1));
    }

    @Test
    void addUserInvalidInventoryIdException() {
        Assertions.assertThrows(InvalidInventoryIdException.class, () -> customerUserDAO.addUser("Name", -1, 12));
    }

    @Test
    void addUserInvalidCustomerAccountIdException() {
        Assertions.assertThrows(InvalidCustomerAccountIdException.class, () -> customerUserDAO.addUser("Name", 2, -1));
    }
}