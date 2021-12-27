package com.revature.repository;

import com.revature.repository.Exception.InvalidPrimaryUserException;
import com.revature.repository.Exception.InvalidUserCredentialException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CustomerAccountDAOTest {
    CustomerAccountDAO customerAccountDAO = new CustomerAccountDAO();

    @Test
    void getCustomerAccountInvalidUserCredentialExceptionTest() {
        Assertions.assertThrows(InvalidUserCredentialException.class, () -> customerAccountDAO.getCustomerAccountsByUserCredentialId(-1));
    }

    @Test
    void getCustomerAccountsByPrimaryUserId() {
        Assertions.assertThrows(InvalidPrimaryUserException.class, () -> customerAccountDAO.getCustomerAccountsByPrimaryUserId(-1));
    }

    @Test
    void addCustomerAccountInvalidPrimaryUserException() {
        Assertions.assertThrows(InvalidPrimaryUserException.class, () -> customerAccountDAO.addCustomerAccount(1, -1));
    }

    @Test
    void addCustomerAccountInvalidUserCredentialException() {
        Assertions.assertThrows(InvalidUserCredentialException.class, () -> customerAccountDAO.addCustomerAccount(-1, 37));
    }
}