package com.revature.repository;

import com.revature.repository.Exception.InvalidAdminIdException;
import com.revature.repository.Exception.InvalidEmployeeAccountIdException;
import com.revature.repository.Exception.InvalidUserCredentialException;
import com.revature.repository.Exception.InvalidUserIdException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeAccountDAOTest {

    EmployeeAccountDAO employeeAccountDAO = new EmployeeAccountDAO();

    @Test
    void updateEmployeeAdmin() {
        Assertions.assertThrows(InvalidAdminIdException.class, () -> employeeAccountDAO.updateEmployeeAdmin(1, -1));
    }

    @Test
    void updateEmployeeAccountUserInvalidUserIdException() {
        Assertions.assertThrows(InvalidUserCredentialException.class, () -> employeeAccountDAO.updateEmployeeAccountUser(1, -1));
    }

    @Test
    void updateEmployeeAccountUserInvalidEmployeeIdException() {
        Assertions.assertThrows(InvalidEmployeeAccountIdException.class, () -> employeeAccountDAO.updateEmployeeAccountUser(-1, 3));
    }
}