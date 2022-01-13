package com.revature.repository.DAOClasses;

import com.revature.repository.DTO.EmployeeAccountEntity;
import com.revature.repository.DTO.EmployeeRoleEntity;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class EmployeeAccountDaoTest {

    private EmployeeAccountDao eDao;
    private EmployeeAccountEntity retrievedAccount;
    private EmployeeRoleEntity retrievedRole;
    private int employeeId;
    private String firstName;
    private String lastName;

    @BeforeAll
    void setUp(){
        eDao = new EmployeeAccountDao();
        employeeId = 7;
        firstName = "Iphazor";
        lastName = "Thitarum";
        retrievedRole = new EmployeeRoleEntity(3, "Mage");
        retrievedAccount = new EmployeeAccountEntity(employeeId, firstName, lastName, retrievedRole);
    }


    @Test
    void getEmployeeAccountTest() {
        assertEquals(retrievedAccount, eDao.getEmployeeAccount(new EmployeeAccountEntity(employeeId, firstName, lastName, new EmployeeRoleEntity())));
    }

    @Test
    void getEmployeeAccountOnlyIdValidTest() {
        assertEquals(retrievedAccount, eDao.getEmployeeAccount(new EmployeeAccountEntity(employeeId, "", "", new EmployeeRoleEntity())));
    }

    @Test
    void getEmployeeAccountIncorrectIdNullTest() {
        assertNull(eDao.getEmployeeAccount(new EmployeeAccountEntity(-1, firstName, lastName, new EmployeeRoleEntity())));
    }

    @Test
    void getAllEmployeeAccountListTest() {
        assertNotNull(eDao.getAllEmployeeAccountList());
    }

    @Test
    void getEmployeeAccountsByRoleIdTest() {
        assertEquals(retrievedRole, eDao.getEmployeeAccountsByRoleId(retrievedRole).get(0).getEmployeeRole());
    }
}