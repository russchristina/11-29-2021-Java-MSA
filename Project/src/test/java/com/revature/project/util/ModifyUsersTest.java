package com.revature.project.util;

import org.junit.jupiter.api.*;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ModifyUsersTest {
    ModifyUsers modifyUsers;
    UserDBTest userDBTest;
    boolean throwsException;
    UserSpecs userSpecs;
    ChildUserSpecs childUserSpecs;
    EmployeeUserSpecs employeeUserSpecs;

    @BeforeEach
    void setUp() {
        modifyUsers =mock(ModifyUsers.class);
        UserDBTest userDB = mock(UserDBTest.class);
        throwsException = false;
        userSpecs = new UserSpecs(1, "test", "test",0);
        childUserSpecs = new ChildUserSpecs(1, "test", "test","test");
        employeeUserSpecs = new EmployeeUserSpecs(1, "test","test",true);

    }


    @Test
    void returnUserTable(String T) {
//List<String> data = Arrays.asList("Test","Names");
//when(modifyUsers.returnUserTable()).thenReturn(data);
//return String.valueOf(data);
    }

    @Test
    void returnChildUserTable() {
       throwsException = false;
        try {
            modifyUsers.returnChildUserTable();
        } catch (Exception e) {
            throwsException = true;
            Assertions.assertTrue(throwsException);
        }
    }

    @Test
    void returnEmployeeTable() {
       List<String> testString = Arrays.asList("T");
//      when(modifyUsers.returnEmployeeTable()).thenReturn(testString);
        throwsException = false;
        try {
            modifyUsers.returnEmployeeTable();
        } catch (Exception e) {
            throwsException = true;
            Assertions.assertTrue(throwsException);
        }

    }

    @Test
    void deleteMainUser() {
        throwsException = false;
        try {
            modifyUsers.deleteMainUser();
        } catch (Exception e) {
            throwsException = true;
            Assertions.assertTrue(throwsException);
        }
    }

    @Test
    void deleteChildUser() {
        throwsException = false;
        try {
            modifyUsers.deleteChildUser();
        } catch (Exception e) {
            throwsException = true;
            Assertions.assertTrue(throwsException);
        }
    }
    @Test
    void deleteEmployeeUser() {
        throwsException = false;
        try {
            modifyUsers.deleteEmployeeUser();
        } catch (Exception e) {
            throwsException = true;
            Assertions.assertTrue(throwsException);
        }
    }
    @Test
    void adminUsernameSelection() {
        throwsException = false;
        int choice = 2;
        try {
            modifyUsers.adminUsernameSelection( choice);
        } catch (Exception e) {
            throwsException = true;
            Assertions.assertTrue(throwsException);
        }
    }

    @Test
    void adminPasswordSelection() {
    }

    @Test
    void updateUsername() {
    }

    @Test
    void updateChildUsername() {
    }

    @Test
    void updateEmployeeUsername() {
    }

    @Test
    void updatePass() {
    }

    @Test
    void updateChildPass() {
    }

    @Test
    void updateEmployeePass() {
    }

    @Test
    void adminFundUpdate() {
        modifyUsers.deleteEmployeeUser();
    }
}