package com.revature.repository;

import com.revature.repository.Exception.InvalidEmployeeAccountIdException;
import com.revature.repository.Exception.InvalidUserCredentialException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class EmployeeAccountDAOTest {

    EmployeeAccountDAO employeeAccountDAO = new EmployeeAccountDAO();

    @Test
    void updateEmployeeAccountUserInvalidUserIdException() {
        Assertions.assertThrows(InvalidUserCredentialException.class, () -> employeeAccountDAO.updateEmployeeAccountUser(1, -1));
    }

    @Test
    void updateEmployeeAccountUserInvalidEmployeeIdException() {
        Assertions.assertThrows(InvalidEmployeeAccountIdException.class, () -> employeeAccountDAO.updateEmployeeAccountUser(-1, 3));
    }
}